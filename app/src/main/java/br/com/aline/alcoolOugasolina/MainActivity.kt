package br.com.aline.alcoolOugasolina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import br.com.aline.alcoolOugasolina.ui.theme.AlcoolOuGasolinaTheme
import br.com.aline.alcoolOugasolina.ui.theme.bungeeSpice

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars()) // Hide status bar icons
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        setContent {
            AlcoolOuGasolinaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {

    var valorGasolina by remember {
        mutableStateOf("")
    }
    var valorAlcool by remember {
        mutableStateOf("")
    }

    val gradientColors = listOf(Color(0xFFFF2800), Color(0xFFFFD700))
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    listOf(Color(0xFF656565), Color(0xFF040404))
                )
            )
    )

    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Álcool",
                style = TextStyle(
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = bungeeSpice,
                    textAlign = TextAlign.Center,
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    )
                )
            )

            Text(
                text = "ou",
                style = TextStyle(

                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = bungeeSpice,
                    textAlign = TextAlign.Center,
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    )
                )
            )

            Text(
                text = "Gasolina?",
                style = TextStyle(

                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = bungeeSpice,
                    textAlign = TextAlign.Center,
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    )
                )

            )

            Spacer(modifier = Modifier.padding(8.dp))

            AnimatedVisibility(visible = valorAlcool.isNotBlank() && valorGasolina.isNotBlank()) {
                if (valorAlcool.isNotBlank() && valorGasolina.isNotBlank()) {
                    val gasolina = valorAlcool.toDouble() / valorGasolina.toDouble() > 0.7
                    val resultado = if (gasolina) {
                        "Abasteça com gasolina"
                    } else {
                        "Abasteça com álcool"
                    }
                    //val cor = if (gasolina) Color(0xFFFFD700) else Color(0xFFFF2800)

                    Text(
                        text = resultado,
                        style = TextStyle(
                            color = Color(0xFFD3D3D3),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontFamily = bungeeSpice

                        )
                    )
                }
            }

            Spacer(modifier = Modifier.padding(5.dp))

            TextField(
                value = valorGasolina,
                onValueChange = {
                    valorGasolina = it
                },
                label = {
                    Text(
                        text = "Preço da gasolina",
                        fontWeight = FontWeight.Bold
                    )
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            TextField(
                value = valorAlcool,
                onValueChange = {
                    valorAlcool = it

                },
                label = {
                    Text(
                        text = "Preço do álcool",
                        fontWeight = FontWeight.Bold
                    )
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() })
            )

        }
    }

}

@Preview
@Composable
private fun AppPreview() {
    AlcoolOuGasolinaTheme {
        App()
    }
}

