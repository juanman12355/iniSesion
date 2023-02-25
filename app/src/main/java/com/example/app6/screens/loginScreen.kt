package com.example.app6.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.app6.R
import com.example.app6.login.LoginState
import com.example.app6.navigation.AppScreens
import com.example.app6.presentation.components.EventDialog
import com.example.app6.presentation.components.RoundedButton
import com.example.app6.presentation.components.TransparentTextField


@Composable
fun LoginScreen(
    navController: NavHostController,
    state : LoginState,
    onLogin : (user : String , pass :String) -> Unit,
    onNavigateToRegister : () -> Unit,
    onDismissDialog : () -> Unit
)
{
    val emailValue = rememberSaveable { mutableStateOf("")}
    val passwordValue = rememberSaveable { mutableStateOf("")}
    var passwordVisibility by remember { mutableStateOf(false)}
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    )
    {
        Image(
            modifier = Modifier
                .width(450.dp)
                .height(287.dp),
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "imagen del logo",
             contentScale = ContentScale.FillBounds
        )
        Box(
            modifier= Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        )
        {
            ConstraintLayout{
                //creamos las referenciasde cada elemento del constrain
                val (surface , fab) = createRefs()
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .constrainAs(surface) {
                            //alinea el control al final del surface
                            bottom.linkTo(parent.bottom)
                        },
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStartPercent = 8,
                        topEndPercent = 8
                    )
                )
                {
                    //boton de input y de password
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                    {
                        Text(text = "Bienvenido",
                            style = MaterialTheme.typography.h4.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Text(text = "Accede a tu cuenta",
                            style = MaterialTheme.typography.h5.copy(
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        )
                        {
                            TransparentTextField(
                                textFieldValue = emailValue,
                                textLabel = "Correo electronico",
                                keyboardType = KeyboardType.Email,
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(focusDirection = FocusDirection.Down)
                                    }
                                ),
                                imeAction = ImeAction.Next
                            )
                            TransparentTextField(
                                textFieldValue = passwordValue,
                                textLabel = "Contraseña",
                                keyboardType = KeyboardType.Password,
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                        onLogin(emailValue.value , passwordValue.value)
//                                        navController.navigate(AppScreens.Home.createRoute((emailValue.value.toString())))
                                    }
                                ),
                                imeAction = ImeAction.Done,
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            passwordVisibility = !passwordVisibility
                                        }
                                    )
                                    {
                                        Icon(
                                            imageVector = if (passwordVisibility){
                                                Icons.Default.Visibility
                                            }else{
                                                Icons.Default.VisibilityOff
                                            },
                                            contentDescription = "Toogle"
                                        )
                                    }
                                },
                                visualTransformation = if (passwordVisibility){
                                    VisualTransformation.None
                                } else {
                                    PasswordVisualTransformation()
                                }
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "Olvido su contrasena?",
                                style = MaterialTheme.typography.body1,
                                textAlign = TextAlign.End
                            )
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            )
                            {
                                RoundedButton(
                                    text = "Login",
                                    displayProgressBar = state.displayProgressBar,
                                    onClick = {
                                        onLogin(emailValue.value , passwordValue.value)
                                    }
                                )
                                ClickableText(
                                    text = buildAnnotatedString {
                                        append("No tiene cuenta activa? ")
                                        withStyle(
                                            style = SpanStyle(
                                                color = MaterialTheme.colors.primary,
                                                fontWeight = FontWeight.Bold
                                            )
                                        ){
                                            append("Registrarse")
                                        }
                                    },
                                    onClick = {onNavigateToRegister() },
                                )
                            }
                        }
                    }
                }
                //agregamos el floatingActionButton
                FloatingActionButton(
                    modifier = Modifier
                        .size(72.dp)
                        .constrainAs(fab) {
                            //alineamos el control en la parte superior del surface
                            top.linkTo(surface.top, margin = (-36).dp)
                            //alineamos el otro control en la parte inferior
                            end.linkTo(surface.end, margin = 36.dp)
                        },
                    backgroundColor = MaterialTheme.colors.primary,
                    onClick = {onNavigateToRegister()}
                )
                {
                    Icon(
                        modifier = Modifier.size(72.dp),
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "boton de avanzar",
                        tint = Color.White
                    )
                }
            }
        }
        if (state.errorMessages !=null){
            EventDialog(
                errorMessage = state.errorMessages,
                onDismiss = onDismissDialog
            )
        }
    }
}
