package com.example.fightright

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import java.util.Random
import kotlin.concurrent.fixedRateTimer
import androidx.compose.runtime.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.*

class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private lateinit var barViewXAxis: View
    private lateinit var barViewYAxis: View
    private lateinit var barViewZAxis: View
    private lateinit var textViewXAxisSpeed: TextView
    private lateinit var textViewYAxisSpeed: TextView
    private lateinit var textViewZAxisSpeed: TextView

    private var xAxis by mutableStateOf(0f)
    private var yAxis by mutableStateOf(0f)
    private var zAxis by mutableStateOf(0f)

    // Define state variables for x, y, and z values
    private var xValue = mutableStateOf("X-Axis: 0.0")
    private var yValue = mutableStateOf("Y-Axis: 0.0")
    private var zValue = mutableStateOf("Z-Axis: 0.0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Initialize SensorManager
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // Get accelerometer sensor
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        setContent{
            val context = LocalContext.current
            MainScreen(xAxis, yAxis, zAxis, context)
        }


    }

    override fun onResume() {
        super.onResume()
        // Register sensor listener
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        // Unregister sensor listener to save battery
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not in use yet
    }


    override fun onSensorChanged(event: SensorEvent?) {
        // Check if the sensor type is accelerometer

        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            // Access accelerometer values: event.values[0], event.values[1], event.values[2]
            xAxis = event.values[0]
            yAxis = event.values[1]
            zAxis = event.values[2]

          if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                  val accelerationX = event.values[0]
                  val accelerationY = event.values[1]
                  val accelerationZ = event.values[2]

                  val currentTime = System.currentTimeMillis()

                //  calculateSpeed(accelerationX, accelerationY, accelerationZ, currentTime)
              }


         //   updateBarSize(xAxis)
        }

              //  val currentTime = System.currentTimeMillis()
              //  calculateSpeed(xAxis, yAxis, zAxis, currentTime)


    }



    private fun updateBarSize(xNumber: Float, yNumber: Float, zNumber: Float){
        // setting xAxis
        val maxWidthXAxis = resources.displayMetrics.widthPixels
        val newWidthXAxis = (maxWidthXAxis * (xNumber.toFloat() / 100)).toInt()

        val layoutParamsXAxis = barViewXAxis.layoutParams
        layoutParamsXAxis.width = newWidthXAxis
        barViewXAxis.layoutParams = layoutParamsXAxis

        // setting yAxis
        val maxWidthYAxis = resources.displayMetrics.widthPixels
        val newWidthYAxis = (maxWidthYAxis * (yNumber.toFloat() / 100)).toInt()

        val layoutParamsYAxis = barViewYAxis.layoutParams
        layoutParamsYAxis.width = newWidthYAxis
        barViewYAxis.layoutParams = layoutParamsYAxis

        // setting zAxis
        val maxWidthZAxis = resources.displayMetrics.widthPixels
        val newWidthZAxis = (maxWidthZAxis * (zNumber.toFloat() / 100)).toInt()

        val layoutParamsZAxis = barViewXAxis.layoutParams
        layoutParamsZAxis.width = newWidthZAxis
        barViewZAxis.layoutParams = layoutParamsZAxis
    }

    // trackMovementSpeed
    private var lastAccelerationX = 0.0f
    private var lastAccelerationY = 0.0f
    private var lastAccelerationZ = 0.0f

    private var lastVelocityX = 0.0f
    private var lastVelocityY = 0.0f
    private var lastVelocityZ = 0.0f

    private var lastPositionX = 0.0f
    private var lastPositionY = 0.0f
    private var lastPositionZ = 0.0f

    private var lastTime = 0L
/*
    private fun calculateSpeed(accelerationX: Float, accelerationY: Float, accelerationZ: Float, currentTime: Long) {

        val deltaTime = (currentTime - lastTime) / 1000.0f // Convert milliseconds to seconds
        lastTime = currentTime

        // Accumulate changes in velocity
        val deltaVelocityX = accelerationX * deltaTime
        val deltaVelocityY = accelerationY * deltaTime
        val deltaVelocityZ = accelerationZ * deltaTime

        // Update velocity values
        lastVelocityX += deltaVelocityX
        lastVelocityY += deltaVelocityY
        lastVelocityZ += deltaVelocityZ

        // Calculate speed (magnitude of velocity)
        val speed = Math.sqrt((lastVelocityX * lastVelocityX + lastVelocityY * lastVelocityY + lastVelocityZ * lastVelocityZ).toDouble()).toFloat()

        // Update last acceleration values for next iteration
        lastAccelerationX = accelerationX
        lastAccelerationY = accelerationY
        lastAccelerationZ = accelerationZ

        // Display velocity components (optional)
        textViewXAxisSpeed.text = "Velocity X: ${lastVelocityX}"
        textViewYAxisSpeed.text = "Velocity Y: ${lastVelocityY}"
        textViewZAxisSpeed.text = "Velocity Z: ${lastVelocityZ}"

        // Display speed (magnitude of velocity)
        textViewXAxisSpeed.text = "Speed: $speed"
    }

 */
}

