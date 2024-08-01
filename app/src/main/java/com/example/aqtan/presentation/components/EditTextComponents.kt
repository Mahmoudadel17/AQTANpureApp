package com.example.aqtan.presentation.components



import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aqtan.R


@Composable
fun textFieldColors() : TextFieldColors {

    return TextFieldDefaults.colors(
        // text color
        focusedTextColor = MaterialTheme.colorScheme.primary,
        unfocusedTextColor = MaterialTheme.colorScheme.primary,
        errorTextColor = MaterialTheme.colorScheme.primary,

        focusedContainerColor = MaterialTheme.colorScheme.onBackground,
        unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
        disabledContainerColor = MaterialTheme.colorScheme.onBackground,
        errorContainerColor = MaterialTheme.colorScheme.onBackground,
        // Indicator color
        cursorColor = MaterialTheme.colorScheme.primary,
        // icon color
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedTrailingIconColor = MaterialTheme.colorScheme.secondary,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.secondary,
        errorTrailingIconColor = MaterialTheme.colorScheme.secondary,

        )
}















@Composable
fun StringEditText(
    textValue:String ,
    placeholder:String = "",
    isTextValueError:Boolean ,
    textValueErrorMessage:String,
    onValueChange:(String) -> Unit) {
    Column {
        TextField(
            placeholder = { Text(text = placeholder, fontSize = 16.sp,color = MaterialTheme.colorScheme.secondary) },
            value = textValue,
            onValueChange = {
                onValueChange(it)
            },
            colors = textFieldColors(),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .shadow(elevation = 24.dp),
            isError = isTextValueError,
        )
        Row {
            Text(
                textValueErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                    .padding(top = 3.dp, start = 25.dp), color = Color.Red
            )
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}














@Composable
fun UserNameEditText(
    userName:String ,
    modifier: Modifier = Modifier,
    editTextHeight: Int = 60,
    isUserNameError:Boolean ,

    userNameErrorMessage:String,
    onValueChange:(String) -> Unit) {
    Column {
        TextField(
            placeholder = { Text(text = stringResource(R.string.user_name), fontSize = 16.sp,color = MaterialTheme.colorScheme.secondary) },
            value = userName,
            onValueChange = {
                onValueChange(it)
            },
            colors = textFieldColors(),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
                .height(editTextHeight.dp)
                .fillMaxWidth()
                .shadow(elevation = 24.dp),
            singleLine = true,
            isError = isUserNameError,
        )
        Row {
            Text(
                userNameErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                    .padding(top = 3.dp, start = 25.dp), color = Color.Red
            )
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}




@Composable
fun NumberEditText(
    number:String ,
    modifier: Modifier = Modifier,
    placeholderID: Int,
    editTextHeight: Int = 60,
    isNumberError:Boolean ,
    numberErrorMessage:String,
    onValueChange:(String) -> Unit) {
    Column {
        TextField(
            placeholder = { Text(text = stringResource(placeholderID), fontSize = 16.sp,color = MaterialTheme.colorScheme.secondary) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = number,
            onValueChange = {
                onValueChange(it)
            },
            colors = textFieldColors(),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
                .height(editTextHeight.dp)
                .fillMaxWidth()
                .shadow(elevation = 24.dp),
            singleLine = true,
            isError = isNumberError,
        )
        Row {
            Text(
                numberErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                    .padding(top = 3.dp, start = 25.dp), color = Color.Red
            )
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}



@Composable
fun EmailEditText(
    email:String ,
    modifier: Modifier = Modifier,
    editTextHeight: Int = 60,
    isErrorEmail:Boolean ,
    emailErrorMessage:String,
    onValueChange:(String) -> Unit
) {
    Column {

        TextField(
            placeholder = {
                Text(
                    text = stringResource(R.string.example_gmail_com),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                onValueChange(it)

            },
            colors = textFieldColors(),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
                .height(editTextHeight.dp)
                .fillMaxWidth()
                .shadow(elevation = 24.dp),
            singleLine = true,
            isError = isErrorEmail,

            )

        Row {
            Text(
                emailErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                    .padding(top = 3.dp, start = 25.dp), color = Color.Red
            )
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}





