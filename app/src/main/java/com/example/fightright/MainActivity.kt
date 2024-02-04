package com.example.fightright

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.util.Random
import kotlin.concurrent.fixedRateTimer


class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private lateinit var textViewXAxis: TextView
    private lateinit var textViewYAxis: TextView
    private lateinit var textViewZAxis: TextView
    private lateinit var barViewXAxis: View
    private lateinit var barViewYAxis: View
    private lateinit var barViewZAxis: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        barViewXAxis = findViewById(R.id.barViewXAxis)
        barViewYAxis = findViewById(R.id.barViewYAxis)
        barViewZAxis = findViewById(R.id.barViewZAxis)

        // Initialize SensorManager
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // Get accelerometer sensor
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        // Initialize TextViews
        textViewXAxis = findViewById(R.id.textViewXAxis)
        textViewYAxis = findViewById(R.id.textViewYAxis)
        textViewZAxis = findViewById(R.id.textViewZAxis)

        startEmulatingData()
    }


    private fun startEmulatingData() {
        // Emulate sensor data every 500 milliseconds
        fixedRateTimer("sensorDataTimer", false, 0L, 500) {
            runOnUiThread {
                // Generate random values for x, y, and z axes
                val random = Random()
                val xAxis = random.nextFloat() * 20 - 10 // Random float between -10 and 10
                val yAxis = random.nextFloat() * 20 - 10 // Random float between -10 and 10
                val zAxis = random.nextFloat() * 20 - 10 // Random float between -10 and 10

                // Update UI with the generated values
                textViewXAxis.text = "X-Axis: $xAxis"
                textViewYAxis.text = "Y-Axis: $yAxis"
                textViewZAxis.text = "Z-Axis: $zAxis"

                updateBarSize(xAxis, yAxis, zAxis)
            }

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
        // Not used in this example
    }


    override fun onSensorChanged(event: SensorEvent?) {
        // Check if the sensor type is accelerometer
       /*
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            // Access accelerometer values: event.values[0], event.values[1], event.values[2]
            val xAxis = event.values[0]
            val yAxis = event.values[1]
            val zAxis = event.values[2]

            // Update UI with accelerometer data
            textViewXAxis.text = "X-Axis: $xAxis"
            textViewYAxis.text = "Y-Axis: $yAxis"
            textViewZAxis.text = "Z-Axis: $zAxis"




            updateBarSize(xAxis)
        }

        */
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

}

