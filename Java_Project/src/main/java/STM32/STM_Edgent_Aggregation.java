package STM32;

import java.io.File;

import java.util.List;
import java.util.Map;

import java.util.concurrent.TimeUnit;

import org.apache.edgent.analytics.math3.Aggregations;

import org.apache.edgent.analytics.math3.ResultMap;

import org.apache.edgent.analytics.math3.stat.Statistic2;

import org.apache.edgent.connectors.iot.IotDevice;
import org.apache.edgent.connectors.iot.QoS;
import org.apache.edgent.connectors.iotp.IotpDevice;
import org.apache.edgent.console.server.HttpServer;
import org.apache.edgent.function.Functions;
import org.apache.edgent.providers.development.DevelopmentProvider;
import org.apache.edgent.providers.direct.DirectProvider;

import org.apache.edgent.topology.TStream;
import org.apache.edgent.topology.TWindow;
import org.apache.edgent.topology.Topology;

import com.google.gson.JsonObject;

public class STM_Edgent_Aggregation {

	public static void main(String[] args) throws Exception {

		String deviceCfg = "c:/device_STM32.cfg";

		Nucleo_Expansion_Sensor sensor = new Nucleo_Expansion_Sensor();

		DirectProvider dp = new DevelopmentProvider();

		Topology top = dp.newTopology("STM32_Nucleo_Aggregation");

		/**
		 * This connects to IBM Watson IoT Platform service as the Application
		 * defined in a application config file. The file format is the standard
		 * one for IBM Watson IoT Platform.
		 */
		IotDevice device = new IotpDevice(top, new File(deviceCfg));

		TStream<Map<String, Double>> readings = device.topology().poll(sensor, 1, TimeUnit.SECONDS);

		TStream<Double> temperatureData = readings.map(t -> t.get("Temperatur").doubleValue());

		/**
		 * Create a window on the stream of the last 30 readings partitioned
		 */
		TWindow<Double, Integer> window = temperatureData.last(30, TimeUnit.SECONDS, Functions.unpartitioned());

		/**
		 * reduce the readings to one average value every window batch. Once the
		 * window is full, the batch of tuples is aggregated, the result is
		 * added to the output stream and the window is cleared. The next
		 * aggregation isn't generated until the window is full again. Aggregate
		 * the windows calculating the min, max, mean and standard deviation
		 */
		TStream<ResultMap> aggResults = window.batch((List<Double> list, Integer partition) -> Aggregations
				.aggregateN(list, Statistic2.MAX, Statistic2.MIN, Statistic2.MEAN, Statistic2.STDDEV));

		TStream<JsonObject> joResultMap = aggResults.map(ResultMap.toJsonObject());

		joResultMap.print();
		/**
		 * Publishing to Gateway device events
		 */
		device.events(joResultMap, "Temperatur", QoS.FIRE_AND_FORGET);

		dp.submit(top);
		System.out.println(dp.getServices().getService(HttpServer.class).getConsoleUrl());
	}

}
