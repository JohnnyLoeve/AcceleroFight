package com.example.fightright

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(xAxis: Float, yAxis: Float, zAxis: Float, context: Context) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AccelerometerDataTextViews(xAxis, yAxis, zAxis, context)
        BarViews(xAxis, yAxis, zAxis, context)
        speedTextViews(xAxis, yAxis, zAxis)
        sortedPunchesTextViews(sortedPunches)
    }
}