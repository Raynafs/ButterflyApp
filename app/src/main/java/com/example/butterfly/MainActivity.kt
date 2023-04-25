package com.example.butterfly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.butterfly.ui.theme.ButterflyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ButterflyTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    val viewModel: MainViewModel = hiltViewModel()
                    val butterfly = viewModel.state.value.butterfly
                    val isLoading = viewModel.state.value.isLoading

                    butterfly?.let {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = butterfly.imageUrl)
                                    .apply(block = fun ImageRequest.Builder.() {
                                        crossfade(true)
                                    }).build()
                            ),
                            contentDescription = "butterfly"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = butterfly.name, fontWeight = Bold, fontSize = 20.sp)

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = butterfly.description)
                    }
                    Button(
                        onClick = viewModel::getRandomButterfly,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Next Butterfly")
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    if (isLoading) {
                        CircularProgressIndicator()
                    }

                }

            }
        }
    }
}


