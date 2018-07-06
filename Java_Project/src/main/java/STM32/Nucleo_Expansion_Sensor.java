
package STM32;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.edgent.function.Supplier;

/*Streams of X-NUCLEO-IKS01A2.*/
public class Nucleo_Expansion_Sensor implements Supplier<Map<String,Double>> {
    private static final long serialVersionUID = 1L;
    // Initial environment sensor
    public Double currentTemperatur = 0.0;
    public Double currentHumidity = 0.0;
 
    private ComPort comPort=null;

    public Nucleo_Expansion_Sensor() {
    	comPort=new ComPort();
    	try {
			comPort.connect("COM6");
		} catch (Exception e) {
		
			e.printStackTrace();
		}
    }

  
    @Override
    public Map<String, Double> get() {
        /**
         * Read data from Serial Communication
         */
    	currentHumidity=comPort.humidityValue;
    	currentTemperatur=comPort.TemperaturValue;

        Map<String, Double> pressures = new HashMap<String, Double>();
        pressures.put("Temperatur", currentTemperatur);
        pressures.put("Humidity", currentHumidity);
        return pressures;
    }
}
