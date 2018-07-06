# Description of Project
We use Apache Edgent to real-time analytics on the continuous streams of data coming from Environmental expansion board (X-Nucleo-IKS01A2) connected on STM32F401 Nucleo-64.Finally,send events from an Edgent application to the Watson IoT platform.

# What is Edge Analytics?
Edge analytics is the collection, processing, and analysis of data at the edge of a network either at or close to a sensor, a network switch or some other connected device.

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
<li>Check heart rate value and sends immediate alerts (Alarm,Call,SMS)</li>
</ul>

<b>Collect Data</b>:
<ul>
<li>Store Data in the Cloud (Firebase Realtime Database)</li>
<li>Make Heart Rate Report Base on Last Hour|Night|Week</li>
</ul>

<b>Settings</b>:
<ul>
<li>Child Profile</li>
<li>Patient Care Attendant Profile</li>
</ul>

# Architecture and Technology
<table>
<tr>
<td><h5>Android</h5>
<ul>
<li>Minimum API 18</li>
<li>Target API 25</li>
<li>Compiled with API 25</li>
</ul></td>
<td><img src="https://4.bp.blogspot.com/-brgnjo5GUa0/WLhXuAwnQII/AAAAAAAAD88/oxL3WK0wiU8zRVDAKyt1sUo37VZLo3BrQCLcB/s1600/Android%2BLogo.png" width="200"/>
</td>
<td>
<h5>Firebase</h5>
<ul>
<li>Firebase Realtime Database (Data is stored as JSON and synchronized in realtime to every connected client) </li>
<li>Firebase Authentication (Knowing a user's identity allows an app to securely save user data in the cloud and provide the same personalized experience across all of the user's devices)</li>
<li>Cloud Storage</li>
</ul>
<td><img src="https://www.joshmorony.com/wp-content/uploads/2016/11/firebase.png" width="300"/></td>
</tr>
<tr>
<td><h5>Scichart</h5>
<ul>
<li>
Realtime Graphing Solution</li>
<li>LineChart</li>
</ul></td>
<td><img src="https://raw.github.com/PhilJay/MPChart/master/design/other/bottom.png" width="400" height="50"/>
</td>
<td>
<h5>BLE</h5>
<ul>
<li>Bluetooth LE Technology</li>
<li>(Bluetooth LE, BLE, marketed as Bluetooth Smart)</li>
</ul>
<td><img src="https://cdn.mist.com/wp-content/uploads/ble.png" width="300"/></td>
</tr>
</table>

<table>
  <tr>
    <td>
      <img src="https://firebase.google.com/docs/auth/images/auth-providers.png" width="600">
    </td>
    <td>
      <img src="https://cloud.google.com/solutions/mobile/images/overview-firebase-appengine-standard.png">
    </td>
  </tr>
</table>

# Hardware

<img src="https://github.com/fanoos/heart_rate_monitor/blob/master/Demo/polar_h7.png">

# Screenshots

<div style="width:100vw">

<img width="45%" src="https://github.com/fanoos/heart_rate_monitor/blob/master/Demo/scr-1.png" alt="Signin" border="1">
<img width="45%" src="https://github.com/fanoos/heart_rate_monitor/blob/master/Demo/scr-2.png" alt="ParentDashboard" border="1">

<img width="45%" src="https://github.com/fanoos/heart_rate_monitor/blob/master/Demo/scr-3.png" alt="MonitoringDashboard" border="1">
<img width="45%" src="https://github.com/fanoos/heart_rate_monitor/blob/master/Demo/scr-4.png" alt="ScanDevice" border="1">

<img width="45%" src="https://github.com/fanoos/heart_rate_monitor/blob/master/Demo/scr-5.png" alt="MonitoringHeartRate" border="1">
<img width="45%" src="https://github.com/fanoos/heart_rate_monitor/blob/master/Demo/scr-6.png" alt="Alarm" border="1">

<img width="45%" src="https://github.com/fanoos/heart_rate_monitor/blob/master/Demo/scr-7.png" alt="SMS" border="1">
<img width="45%" src="https://github.com/fanoos/heart_rate_monitor/blob/master/Demo/scr-8.png" alt="ChartLine" border="1">


# Video Demo

- Initial App and Use Heart Rate Monitoring [https://youtu.be/F6RQ29Ilp54]

- Run Alarm of High Heart Rate [https://youtu.be/EvwKW1WH4sQ]

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
