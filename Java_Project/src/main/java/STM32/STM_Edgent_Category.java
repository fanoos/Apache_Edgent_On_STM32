package STM32;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.edgent.analytics.sensors.Range;
import org.apache.edgent.analytics.sensors.Ranges;
import org.apache.edgent.connectors.iot.IotDevice;
import org.apache.edgent.connectors.iot.QoS;
import org.apache.edgent.connectors.iotp.IotpDevice;
import org.apache.edgent.console.server.HttpServer;
import org.apache.edgent.providers.development.DevelopmentProvider;
import org.apache.edgent.providers.direct.DirectProvider;

import org.apache.edgent.topology.TStream;
import org.apache.edgent.topology.Topology;

import com.google.gson.JsonObject;

public class STM_Edgent_Category {

	public static void main(String[] args) throws Exception {

		String deviceCfg = "c:/device_STM32.cfg";

		Nucleo_Expansion_Sensor sensor = new Nucleo_Expansion_Sensor();

		DirectProvider dp = new DevelopmentProvider();

		Topology top = dp.newTopology("STM32_Nucleo_Temp_Humid");

		IotDevice device = new IotpDevice(top, new File(deviceCfg));

		TStream<Map<String, Double>> readings = device.topology().poll(sensor, 1, TimeUnit.SECONDS);

		// Split the stream by comfortable zone category
		List<TStream<Map<String, Double>>> categories = readings.split(10, tuple -> {
			Double t = tuple.get("Temperatur");
			Double h = tuple.get("Humidity");
			if ((t >= 18 && t <= 24) && (h >= 37 && h <= 65)) {
				// Comfortable
				return 0;
			} else if ((t >= 21 && t <= 27) && (h >= 30 && h <= 70)) {
				// Still Comfortable
				return 1;
			} else {
				boolean TooCold = false, TooWarm = false, TooDry = false, TooHumid = false;
				if (t < 21)
					TooCold = true;
				if (t > 27)
					TooWarm = true;
				if (h < 30)
					TooDry = true;
				if (h > 70)
					TooHumid = true;
				if (TooCold && TooDry)
					return 2;
				if (TooCold && TooHumid)
					return 3;
				if (TooWarm && TooDry)
					return 4;
				if (TooWarm && TooHumid)
					return 5;
				if (TooCold)
					return 6;
				if (TooWarm)
					return 7;
				if (TooDry)
					return 8;
				if (TooHumid)
					return 9;

			}
			return -1;
		});

		// Get each individual stream
		TStream<Map<String, Double>> Comfortable = categories.get(0).tag("Comfortable");
		TStream<Map<String, Double>> Still_Comfortable = categories.get(1).tag("Still_Comfortable");
		TStream<Map<String, Double>> TooCold_TooDry = categories.get(2).tag("TooCold_TooDry");
		TStream<Map<String, Double>> TooCold_TooHumid = categories.get(3).tag("TooCold_TooHumid");
		TStream<Map<String, Double>> TooWarm_TooDry = categories.get(4).tag("TooWarm_TooDry");
		TStream<Map<String, Double>> TooWarm_TooHumid = categories.get(5).tag("TooWarm_TooHumid");
		TStream<Map<String, Double>> TooCold = categories.get(6).tag("TooCold");
		TStream<Map<String, Double>> TooWarm = categories.get(7).tag("TooWarm");
		TStream<Map<String, Double>> TooDry = categories.get(8).tag("TooDry");
		TStream<Map<String, Double>> TooHumid = categories.get(9).tag("TooHumid");

		// Category
		TStream<JsonObject> comfortableObjects = Comfortable.map(tuple -> {
			JsonObject jObj = new JsonObject();
			jObj.addProperty("Temperatur", tuple.get("Temperatur"));
			jObj.addProperty("Humidity", tuple.get("Humidity"));
			jObj.addProperty("State", "Comfortable.");
			return jObj;
		}).tag("Comfortable");

		TStream<JsonObject> still_ComfortableObjects = Still_Comfortable.map(tuple -> {
			JsonObject jObj = new JsonObject();
			jObj.addProperty("Temperatur", tuple.get("Temperatur"));
			jObj.addProperty("Humidity", tuple.get("Humidity"));
			jObj.addProperty("State", "Still Comfortable.");
			return jObj;
		}).tag("Still_Comfortable");

		TStream<JsonObject> tooCold_TooDryObjects = TooCold_TooDry.map(tuple -> {
			JsonObject jObj = new JsonObject();
			jObj.addProperty("Temperatur", tuple.get("Temperatur"));
			jObj.addProperty("Humidity", tuple.get("Humidity"));
			jObj.addProperty("State", "Not Comfortable,it is Too Cold and Too Dry.");
			return jObj;
		}).tag("TooCold_TooDry");

		TStream<JsonObject> tooCold_TooHumidObjects = TooCold_TooHumid.map(tuple -> {
			JsonObject jObj = new JsonObject();
			jObj.addProperty("Temperatur", tuple.get("Temperatur"));
			jObj.addProperty("Humidity", tuple.get("Humidity"));
			jObj.addProperty("State", "Not Comfortable,it is Too Cold and Too Humid.");
			return jObj;
		}).tag("TooCold_TooHumid");

		TStream<JsonObject> tooWarm_TooDryObjects = TooWarm_TooDry.map(tuple -> {
			JsonObject jObj = new JsonObject();
			jObj.addProperty("Temperatur", tuple.get("Temperatur"));
			jObj.addProperty("Humidity", tuple.get("Humidity"));
			jObj.addProperty("State", "Not Comfortable,it is Too Warm and Too Dry.");
			return jObj;
		}).tag("TooWarm_TooDry");

		TStream<JsonObject> tooWarm_TooHumidObjects = TooWarm_TooHumid.map(tuple -> {
			JsonObject jObj = new JsonObject();
			jObj.addProperty("Temperatur", tuple.get("Temperatur"));
			jObj.addProperty("Humidity", tuple.get("Humidity"));
			jObj.addProperty("State", "Not Comfortable,it is Too Warm and Too Humid.");
			return jObj;
		}).tag("TooWarm_TooHumid");

		TStream<JsonObject> tooColdObjects = TooCold.map(tuple -> {
			JsonObject jObj = new JsonObject();
			jObj.addProperty("Temperatur", tuple.get("Temperatur"));
			jObj.addProperty("Humidity", tuple.get("Humidity"));
			jObj.addProperty("State", "Not Comfortable,it is Too Cold.");
			return jObj;
		}).tag("TooCold");

		TStream<JsonObject> tooWarmObjects = TooWarm.map(tuple -> {
			JsonObject jObj = new JsonObject();
			jObj.addProperty("Temperatur", tuple.get("Temperatur"));
			jObj.addProperty("Humidity", tuple.get("Humidity"));
			jObj.addProperty("State", "Not Comfortable,it is Too Warm.");
			return jObj;
		}).tag("TooWarm");

		TStream<JsonObject> tooDryObjects = TooDry.map(tuple -> {
			JsonObject jObj = new JsonObject();
			jObj.addProperty("Temperatur", tuple.get("Temperatur"));
			jObj.addProperty("Humidity", tuple.get("Humidity"));
			jObj.addProperty("State", "Not Comfortable,it is Too Dry.");
			return jObj;
		}).tag("TooDry");

		TStream<JsonObject> tooHumidObjects = TooHumid.map(tuple -> {
			JsonObject jObj = new JsonObject();
			jObj.addProperty("Temperatur", tuple.get("Temperatur"));
			jObj.addProperty("Humidity", tuple.get("Humidity"));
			jObj.addProperty("State", "Not Comfortable,it is Too Humid.");
			return jObj;
		}).tag("TooHumid");



		/**
		 * union two streams to obtain a single stream containing alerts from
		 * the Comfortable and Still Comfortable alert streams.
		 */
		TStream<JsonObject> allComfortable = comfortableObjects.union(still_ComfortableObjects);

		// Set of streams containing alerts from the other categories
		HashSet<TStream<JsonObject>> othersSet = new HashSet<TStream<JsonObject>>();
		// othersSet.add(tooCold_TooDryObjects);
		othersSet.add(tooCold_TooHumidObjects);
		othersSet.add(tooWarm_TooDryObjects);
		othersSet.add(tooWarm_TooHumidObjects);
		othersSet.add(tooColdObjects);
		othersSet.add(tooWarmObjects);
		othersSet.add(tooDryObjects);
		othersSet.add(tooHumidObjects);

		TStream<JsonObject> AllSet = tooCold_TooDryObjects.union(othersSet);
		
		device.events(AllSet, "sensors", QoS.FIRE_AND_FORGET);
		AllSet.print();

		dp.submit(top);
		System.out.println(dp.getServices().getService(HttpServer.class).getConsoleUrl());
	}

}
