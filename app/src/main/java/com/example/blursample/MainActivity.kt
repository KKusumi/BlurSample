package com.example.blursample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import com.example.blursample.ui.theme.BlurSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlurSampleTheme {
                // A surface container using the 'background' color from the theme
                BlurSampleScreen()
            }
        }
    }
}

@Composable
fun BlurSampleScreen() {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            // Modifier.blurを使う場合
            Image(
                painter = painterResource(id = R.drawable.droid),
                contentDescription = null,
                modifier = Modifier
                    .blur(8.dp)
                    .wrapContentSize()
                    .padding(horizontal = 16.dp)
            )


            // Coil使ってURLから読み込む場合
            Image(
                painter = rememberImagePainter(
                    data = "https://developer.android.com/images/brand/Android_Robot.png?hl=ja",
                    builder = {
                        transformations(
                            BlurTransformation(
                                LocalContext.current,
                                16f
                            )
                        )

                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 16.dp)
            )

            // Coil使ってローカルから読み込む場合
            Image(
                painter = rememberImagePainter(
                    data = R.drawable.droid,
                    builder = {
                        transformations(
                            BlurTransformation(
                                LocalContext.current,
                                16f
                            )
                        )
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BlurSampleTheme {
        BlurSampleScreen()
    }
}