package com.jetpack.loadinganimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jetpack.loadinganimation.ui.theme.LoadingAnimationTheme
import com.jetpack.loadinganimation.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadingAnimationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Animate Loading",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            )
                        }
                    ) {
                        LoadingAnimation()
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(28.dp))

                Box(
                    modifier = Modifier
                        .size(400.dp)
                        .padding(24.dp)
                ) {
                    Text(
                        text = "LOADING",
                        style = Typography.h6.copy(
                            Color(0xFFFF0000),
                            shadow = Shadow(
                                color = Color(0xFFFF0000),
                                offset = Offset(1f, 1f),
                                blurRadius = 5f
                            )
                        ),
                        modifier = Modifier
                            .align(Alignment.Center)
                    )

                    Canvas(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(150.dp),
                        onDraw = {
                            drawCircle(
                                color = Color.Red,
                                style = Stroke(width = 5f)
                            )
                        }
                    )

                    Canvas(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(150.dp),
                        onDraw = {
                            drawArc(
                                color = Color(0xFFFFEB3B),
                                style = Stroke(
                                    width = 5f,
                                    cap = StrokeCap.Round,
                                    join = StrokeJoin.Round
                                ),
                                startAngle = angle,
                                sweepAngle = 360 / 4f,
                                useCenter = false
                            )
                        }
                    )
                }
            }
        }
    }
}
























