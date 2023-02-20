package com.example.app6.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
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
import androidx.navigation.NavHostController
import com.example.app6.navigation.AppScreens
import com.example.app6.presentation.components.EventDialog
import com.example.app6.presentation.components.RoundedButton
import com.example.app6.presentation.components.SocialMediaButton
import com.example.app6.presentation.components.TransparentTextField
import com.example.app6.register.RegisterState


@Composable
fun RegistrationScreen(
    navController: NavHostController,
    state : RegisterState,
    onRegister : (nameValue : String, emailValue : String, phoneValue : String, passValue : String, confirmPassValue : String) -> Unit,
    onBack : () -> Unit,
    onDismissDialog : () -> Unit
)
{
    val nameValue = remember{ mutableStateOf("")}
    val emailValue = remember{ mutableStateOf("")}
    val phoneValue = remember{ mutableStateOf("")}
    val passValue = remember{ mutableStateOf("")}
    val confirmPassValue = remember{ mutableStateOf("")}

    var passwordVisibility by remember { mutableStateOf(false)}
    var confirmPasswordVisibility by remember { mutableStateOf(false)}

    val focusManager = LocalFocusManager.current

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        )
        {
            Row(verticalAlignment = Alignment.CenterVertically){
                IconButton(onClick = {
                    onBack()
                }){
                    Icon(
                        modifier = Modifier.clickable {
                            navController.navigate(route = AppScreens.Login.route)
                        },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Regresar a la pantalla principal",
                        tint = MaterialTheme.colors.primary
                    )
                }
                Text(
                    text = "Crear una cuenta",
                    style = MaterialTheme.typography.h6.copy(
                        color = MaterialTheme.colors.primary
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TransparentTextField(
                    textFieldValue = nameValue,
                    textLabel = "Nombre",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )
                TransparentTextField(
                    textFieldValue = emailValue,
                    textLabel = "Correo electronico",
                    keyboardType = KeyboardType.Email,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )
                TransparentTextField(
                    textFieldValue = phoneValue,
                    textLabel = "Numero de telefono",
                    keyboardType = KeyboardType.Phone,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )
                TransparentTextField(
                    textFieldValue = passValue,
                    textLabel = "Contraseña",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility
                            }
                        )
                        {
                            Icon(
                                imageVector = if (passwordVisibility) {
                                    Icons.Default.Visibility
                                }else   {
                                    Icons.Default.VisibilityOff
                                },
                                contentDescription = "Toogle"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisibility) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                )
                TransparentTextField(
                    textFieldValue = confirmPassValue,
                    textLabel = "Confirme su contraseña",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            onRegister(
                                nameValue.value,
                                emailValue.value,
                                phoneValue.value,
                                passValue.value,
                                confirmPassValue.value
                            )
                        }
                    ),
                    imeAction = ImeAction.Done,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                confirmPasswordVisibility = !confirmPasswordVisibility
                            }
                        )
                        {
                            Icon(
                                imageVector = if (confirmPasswordVisibility) {
                                    Icons.Default.Visibility
                                }else   {
                                    Icons.Default.VisibilityOff
                                },
                                contentDescription = "Toogle"
                            )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisibility) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                RoundedButton(
                    text = "Registrarse",
                    displayProgressBar = state.displayProgressBar,
                    onClick = {
                        onRegister(
                            nameValue.value,
                            emailValue.value,
                            phoneValue.value,
                            passValue.value,
                            confirmPassValue.value
                        )
                    }
                )
                ClickableText(
                    text = buildAnnotatedString {
                        append("Ya tiene una cuenta? ")
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        ){
                            append("Ingresar")
                        }
                    },
                    onClick = {
                        onBack()
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "O",
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Black)
                    )
                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                }
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Ingresar con",
                    style = MaterialTheme.typography.body1.copy(MaterialTheme.colors.primary),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement =  Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                // TODO {creamos un nuevo componente para los botones}
                SocialMediaButton(
                    text = "Entrar con Facebook",
                    onClick = { /*TODO*/ },
                    socialMediaColor = MaterialTheme.colors.onSurface
                )
                SocialMediaButton(
                    text = "Entrar con Gmail",
                    onClick = { /*TODO*/ },
                    socialMediaColor = MaterialTheme.colors.primary
                )
            }
        }
        if (state.errorMessages != null){
            EventDialog(errorMessage = state.errorMessages , onDismiss = onDismissDialog)
        }
    }
}