package com.example.hardwaresensors

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.core.content.getSystemService
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : AppCompatActivity() , SensorEventListener {

    lateinit var flproxIndicator : FrameLayout
    lateinit var flaccelIndicator : FrameLayout
    lateinit var sensorManager: SensorManager
    lateinit var proxSensor: Sensor
    lateinit var accelSensor:Sensor

    val colors = arrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.YELLOW)


    override fun onSensorChanged(event: SensorEvent?) {
        flproxIndicator=findViewById(R.id.flproxIndicator)
        flaccelIndicator=findViewById(R.id.flaccelIndicator)


        if(event!!.sensor.type==Sensor.TYPE_PROXIMITY){
            if (event!!.values[0] > 0) {
                flproxIndicator.setBackgroundColor(colors[Random.nextInt(colors.size)])
            }
        }

        if(event!!.sensor.type==Sensor.TYPE_ACCELEROMETER){
            val bgColor = Color.rgb(
                accel2Color(event!!.values[0]),
                accel2Color(event!!.values[1]),
                accel2Color(event!!.values[2])
            )
            flaccelIndicator.setBackgroundColor(bgColor)
        }

        //To check if the proximity sensor is working or not
        /*
            Log.d("HWSENS", """
                    ${it.name}  |  ${it.stringType}  |  ${it.vendor}
                    onSensorChanged: ${event!!.values[0]}
                """.trimIndent())
         */
        //To check if the accelerometer is working or not
        /*
        Log.d("HWSENS", """
            ----
            ax = ${event!!.values[0]}
            ay = ${event!!.values[1]}
            az = ${event!!.values[2]}
            ----
        """.trimIndent())
         */

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService<SensorManager>()!!
        proxSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        //To check all the list of sensors on my phone
        /*
        if (sensorManager == null) {
            Toast.makeText(this, "Could not get sensors", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
            sensors.forEach {
                Log.d("HWSENS", """
                    ${it.name}  |  ${it.stringType}  |  ${it.vendor}
                """.trimIndent())
            }
        }
         */

    }


    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, proxSensor, 1000 * 1000)
        sensorManager.registerListener(this, accelSensor, 1000 * 1000)
    }

    override fun onPause() {
        sensorManager.unregisterListener(this)
        super.onPause()
    }

    private fun accel2Color(accel:Float) : Int = (((accel+12) / 24) * 255).roundToInt()
}