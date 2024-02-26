package com.example.fightright
import android.content.Context
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
    // Function to calculate speed of punch
    fun calculatePunchSpeed(accelerationX: Float, accelerationY: Float, accelerationZ: Float): Float {
        // Combine accelerations to get total acceleration magnitude
        val totalAcceleration = sqrt(accelerationX.pow(2) + accelerationY.pow(2) + accelerationZ.pow(2))

        // Convert acceleration to m/s^2 (assuming sensor units are in m/s^2)
        val accelerationMetersPerSecSquared = totalAcceleration * 9.81f // Convert to m/s^2

        // Assuming initial velocity is 0 m/s
        val initialVelocity = 0f

        // Calculate speed using kinematic equation (v = u + at)
        // Final velocity (v) is the punch speed
        val speed = initialVelocity + accelerationMetersPerSecSquared

        return speed
    }
