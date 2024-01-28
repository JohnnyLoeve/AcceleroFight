package com.example.fightright

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private lateinit var textViewXAxis: TextView
    private lateinit var textViewYAxis: TextView
    private lateinit var textViewZAxis: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SensorManager
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // Get accelerometer sensor
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        // Initialize TextViews
        textViewXAxis = findViewById(R.id.textViewXAxis)
        textViewYAxis = findViewById(R.id.textViewYAxis)
        textViewZAxis = findViewById(R.id.textViewZAxis)
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
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            // Access accelerometer values: event.values[0], event.values[1], event.values[2]
            val xAxis = event.values[0]
            val yAxis = event.values[1]
            val zAxis = event.values[2]

            // Update UI with accelerometer data
            textViewXAxis.text = "X-Axis: $xAxis"
            textViewYAxis.text = "Y-Axis: $yAxis"
            textViewZAxis.text = "Z-Axis: $zAxis"
        }
    }
}
