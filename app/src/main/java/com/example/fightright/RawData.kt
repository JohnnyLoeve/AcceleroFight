package com.example.fightright

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*

@Composable
fun AccelerometerDataTextViews(xAxis: Float, yAxis: Float, zAxis: Float, context: Context) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AccelerometerDataTextView("X-Axis: $xAxis")
        AccelerometerDataTextView("Y-Axis: $yAxis")
        AccelerometerDataTextView("Z-Axis: $zAxis")
    }
}

@Composable
fun AccelerometerDataTextView(label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = label, fontSize = 16.sp)
        Spacer(modifier = Modifier.width(30.dp))
    }
}


@Composable
fun BarViews(xAxis: Float, yAxis: Float, zAxis: Float, context: Context) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
                BarView("X-Axis: ", xAxis)
        BarView("Y-Axis: ", yAxis)
        BarView("Z-Axis: ", zAxis)
    }
}

@Composable
fun BarView(label: String, axisValue: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Find a way to take gravity into account.
        val percentage = axisValue / 25f

        Text(text = label, fontSize = 16.sp)
        Spacer(modifier = Modifier.width(30.dp))
        Box(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth(percentage)
                .background(color = Color.Cyan)
        )
    }
}