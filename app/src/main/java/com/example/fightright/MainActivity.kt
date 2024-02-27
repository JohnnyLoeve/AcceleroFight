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

    private var xAxis by mutableStateOf(0f)
    private var yAxis by mutableStateOf(0f)
    private var zAxis by mutableStateOf(0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize SensorManager
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // Get accelerometer sensor
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)

        if (accelerometer == null) {
           println("Device does not support linear acceleration sensor")
        }

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

    private var previousSpeed = 0f
    private var previousTimeMillis: Long = 0

    override fun onSensorChanged(event: SensorEvent?) {



        // Check if the sensor type is Linear acceleration
        if (event?.sensor?.type == Sensor.TYPE_LINEAR_ACCELERATION) {

            val currentTimeMillis = System.currentTimeMillis()
            val deltaTimeMillis = currentTimeMillis - previousTimeMillis

            // Access accelerometer values: event.values[0], event.values[1], event.values[2]
            xAxis = event.values[0]
            yAxis = event.values[1]
            zAxis = event.values[2]

            val currentSpeed = calculatePunchSpeed(xAxis, yAxis, zAxis)
            val speedChange = calculateSpeedChange(previousSpeed, currentSpeed, deltaTimeMillis)

            previousSpeed = currentSpeed
            previousTimeMillis = currentTimeMillis

            val currentTime = System.currentTimeMillis()

            // Process accelerometer data to identify punches
            processAccelerometerData(xAxis, yAxis, zAxis, speedChange, currentTime)
        }


    }
}

