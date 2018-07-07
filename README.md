# Description of Project
We use Apache Edgent to real-time analytics on the continuous streams of data coming from Environmental expansion board (X-Nucleo-IKS01A2) connected on STM32F401 Nucleo-64.Finally,send events from an Edgent application to the Watson IoT platform.

# What is Edge Analytics?
Edge analytics is the collection, processing, and analysis of data at the edge of a network either at or close to a sensor, a network switch or some other connected device.

When you analyze on the edge, you can:
* Reduce the amount of data that you transmit to your analytics server
* Reduce the amount of data that you store

# What is Apache Edgent?
Apache Edgent is a programming model and micro-kernel style runtime that can be embedded in gateways and small footprint edge devices enabling local, real-time, analytics on the continuous streams of data coming from equipment, vehicles, systems, appliances, devices and sensors of all kinds (for example, Raspberry Pis or smart phones). Working in conjunction with centralized analytic systems, Apache Edgent provides efficient and timely analytics across the whole IoT ecosystem: from the center to the edge.

# Main Steps and Functionalities
<b>Watson IoT platform</b>:
<ul>
<li>Create an instance of the Watson IoT platform service.</li>
<li>Register your device on the service and save the service connection details in a file.  We refer to this as the device configuration file.</li>
</ul>

<b>Connect To Sensor</b>:
<ul>
<li>Configure STM32 to connect and use Expansion Board.</li>
<li>Using STM32CubeMX and Mbed Software</li>
</ul>

<b>Collect Data</b>:
<ul>
<li>Serial port - capture raw data in COM port</li>
<li>Create Apache Edgent Sensor class</li>
</ul>

<b>Edgent application</b>:
<ul>
<li>Create Edgent application</li>
<li>Reading data from sensors and performing analytics on the data</li>
</ul>

<b>Send events</b>:
<ul>
<li>Send events stream to the IoT platform</li>
<li>Use the IotpDevice class to send the events to the platform.</li>
</ul>

<b>Visualizing and monitoring application</b>:
<ul>
<li>Adding the console web app</li>
<li>Use the the DevelopmentProvider class</li>
</ul>

# Architecture and Technology
<table>
<tr>
<td><h5>Apache Edgent</h5>
<ul>
<li>Version 1.2.0-incubating</li>
<li>supported on Java 8 SE</li>
</ul></td>
<td><img src="https://edgent.apache.org/img/apache_logo.png" width="400"/>
</td>
<td>
<h5>STM32</h5>
<ul>
<li>STM32F401 microcontrollers</li>
<li>X-NUCLEO-IKS01A2 expansion board</li>
</ul>
<td><img src="https://percepio.com/wp-content/uploads/2015/08/stm32generic.png" width="400"/></td>
</tr>
<tr>
<td><h5>RXTX</h5>
<ul>
<li>x64 Binaries</li>
<li>Version RXTX-2-2-20081207</li>
</ul></td>
<td><img src="http://rxtx.qbang.org/wiki/skins/common/images/wikii.png" width="400" height="50"/>
</td>
<td>
<h5>Eclipse IDE</h5>
<ul>
<li>Version Neon</li>
</ul>
<td><img src="http://www.eclipse.org/artwork/images/v2/logo-800x188.png" width="400"/></td>
</tr>
</table>

<table>
  <tr>
    <td>
      <img src="https://github.com/fanoos/Apache_Edgent_On_STM32/blob/master/Slide/Architecture.png" >
    </td>
   
  </tr>
</table>

# Hardware

<img src="https://github.com/fanoos/Apache_Edgent_On_STM32/blob/master/Slide/Hardware.png">

# Screenshots

<div style="width:100vw">

<img width="45%" src="https://github.com/fanoos/Apache_Edgent_On_STM32/blob/master/Slide/EdgentCategoryConsol-1.png" alt="EdgentCategoryConsol" border="1">
<img width="45%" src="https://github.com/fanoos/Apache_Edgent_On_STM32/blob/master/Slide/EdgentCategoryConsol-2.png" alt="EdgentCategoryConsol" border="1">

<img width="45%" src="https://github.com/fanoos/Apache_Edgent_On_STM32/blob/master/Slide/EdgentCategoryConsol-3.png" alt="EdgentCategoryConsol" border="1">
<img width="45%" src="https://github.com/fanoos/Apache_Edgent_On_STM32/blob/master/Slide/EdgentCategoryWatsonEvent.png" alt="EdgentCategoryWatsonEvent" border="1">

<img width="45%" src="https://github.com/fanoos/Apache_Edgent_On_STM32/blob/master/Slide/EdgentCategoryWatsonState.png" alt="EdgentCategoryWatsonState" border="1">
<img width="45%" src="https://github.com/fanoos/Apache_Edgent_On_STM32/blob/master/Slide/EdgentCategoryCode.png" alt="EdgentCategoryCode" border="1">

<img width="45%" src="https://github.com/fanoos/Apache_Edgent_On_STM32/blob/master/Slide/EdgentAggregationConsol-1.png" alt="EdgentAggregationConsol" border="1">
<img width="45%" src="https://github.com/fanoos/Apache_Edgent_On_STM32/blob/master/Slide/EdgentAggregationConsol-2.png" alt="EdgentAggregationConsol" border="1">
<img width="45%" src="https://github.com/fanoos/Apache_Edgent_On_STM32/blob/master/Slide/EdgentAggregationWatsonEvent.png" alt="EdgentAggregationWatsonEvent" border="1">

<img width="45%" src="https://github.com/fanoos/Apache_Edgent_On_STM32/blob/master/Slide/EdgentAggregationWatsonState.png" alt="EdgentAggregationWatsonState" border="1">


# Video Demo

- Apache Edgent On STM32F401-Category Edgent Application [https://youtu.be/pyLKdrLl7r0]

- Apache Edgent On STM32F401-Aggregation Edgent Application [https://youtu.be/EvwKW1WH4sQ]

# Roadmap

<img src="https://github.com/fanoos/heart_rate_monitor/blob/master/Demo/RoadMap.JPG">

# Team Members
The team members are: Mostafa Ramezani, Soma Shekarchi.

Links to our LinkedIn accounts:
- Mostafa [LinkedIn: https://www.linkedin.com/in/mostafaramezani/]
- Soma [LinkedIn: https://www.linkedin.com/in/somashekarchi/]

# Slideshow links
- First overview [https://github.com/fanoos/heart_rate_monitor/blob/master/Diabetes-heart-rate.pdf]
- Feedback overview [https://github.com/fanoos/heart_rate_monitor/blob/master/Feedback-Heartrate%20Monitoring%20in%20Diabetic%20Children.pdf]
- Final Presentation [https://github.com/fanoos/heart_rate_monitor/blob/master/DocHero-HeartRate-Monitor.pdf]

# Source code
- Andriod App [https://github.com/fanoos/heart_rate_monitoring]
