package com.example.fightright
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun speedTextViews(xAxis: Float, yAxis: Float, zAxis: Float){
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        val speed = calculatePunchSpeed(xAxis, yAxis, zAxis)
        SpeedTextView(label = "Punching Speed: ", speed = speed)
    }
}

    @Composable
    fun SpeedTextView(label: String, speed: Float){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
            ){
            Text(text = label + speed + "m/s", fontSize = 16.sp)
            Spacer(modifier = Modifier.width(30.dp))
        }
    }
@Composable
fun sortedPunchesTextViews(sortedPunches: List<Punch>?) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        sortedPunches?.forEachIndexed { index, punch ->
            PunchTextView(label = "Punch ${index + 1}: Speed - ${punch.speedChange}")
        }
    }
}

@Composable
fun PunchTextView(label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = label, fontSize = 16.sp)
        Spacer(modifier = Modifier.width(30.dp))
    }
}

data class Punch(val timestamp: Long, val speedChange: Float)

var sortedPunches = listOf<Punch>()

// Function to calculate speed changes and identify punches

fun processAccelerometerData(xAxis: Float, yAxis: Float, zAxis: Float, speedChange: Float, currentTime: Long) {
    // Calculate speed change based on accelerometer data
    val currentSpeed = calculatePunchSpeed(xAxis, yAxis, zAxis)

    // Check if the speed change exceeds a certain threshold to identify a punch
    // Read about threshold in Wiki.
    val threshold = 9.0f
    if (speedChange > threshold) {
      // If speed is greater than the threshold. The punch will be registered.
        val punch = Punch(currentTime, speedChange)
        sortedPunches += punch
        sortedPunches = sortedPunches.sortedByDescending {it.speedChange}
    }
}

fun calculatePunchSpeed(xAxis: Float, yAxis: Float, zAxis: Float): Float {
    // Combine accelerations to get total acceleration magnitude
    val totalAcceleration = sqrt(xAxis.pow(2) + yAxis.pow(2) + zAxis.pow(2))

    // Convert acceleration to m/s^2 (assuming sensor units are in m/s^2)
    val accelerationMetersPerSecSquared = totalAcceleration * 9.81f // Convert to m/s^2

    // Assuming initial velocity is 0 m/s
    val initialVelocity = 0f

    // Calculate speed using kinematic equation (v = u + at)
    // Final velocity (v) is the punch speed
    val speed = initialVelocity + accelerationMetersPerSecSquared

    return speed
}

fun calculateSpeedChange(previousSpeed: Float, currentSpeed: Float, deltaTimeMillis: Long): Float {
    return currentSpeed - previousSpeed
}
