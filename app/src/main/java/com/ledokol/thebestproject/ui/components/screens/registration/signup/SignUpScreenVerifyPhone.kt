package com.ledokol.thebestproject.ui.components.screens.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ledokol.thebestproject.R
import com.ledokol.thebestproject.ui.components.atoms.texts.HeadlineH4
import com.ledokol.thebestproject.ui.components.atoms.textfields.TextField
import com.ledokol.thebestproject.ui.components.atoms.buttons.ButtonFull
import com.ledokol.thebestproject.ui.components.atoms.texts.Body1
import com.ledokol.thebestproject.ui.components.molecules.BackToolbar

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreenVerifyPhone(
    phoneCode: String,
    phone: String,
    setPhoneCode: (String) -> Unit,
    buttonNextClick: () -> Unit,
    buttonBackClick: () -> Unit,
    error: String
){
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    if(!error.isNotEmpty()) {
        LaunchedEffect(focusRequester) {
            focusRequester.requestFocus()
            keyboard?.show()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        BackToolbar(
            buttonBackClick = {
                buttonBackClick()
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 50.dp, end = 50.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            if(error.isEmpty()) {
                HeadlineH4(
                    text = stringResource(id = R.string.verify_code),
                    fontWeight = FontWeight.W700,
                )
                Body1(
                    text = stringResource(id = R.string.verify_code_description, phone),
                    color = MaterialTheme.colors.secondaryVariant,
                )

                TextField(
                    text = phoneCode,
                    onValueChange = { setPhoneCode(it) },
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 20.dp)
                        .focusRequester(focusRequester),
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(onNext = {
                        buttonNextClick()
                    }),
                )

                ButtonFull(
                    text = stringResource(id = R.string.confirm),
                    onClick = { buttonNextClick() },
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                )

//            Body1(
//                text = stringResource(id = R.string.resend_code),
//                textAlign = TextAlign.Center,
//
//            )
            }
            else{
                HeadlineH4(
                    text = error,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier
                        .focusRequester(focusRequester),
                    )
            }
        }
    }


}