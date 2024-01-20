package com.example.doan_3tuan.View.Login

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doan_3tuan.Model.SignInState
import com.example.doan_3tuan.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleCard(
    state : SignInState,
    onSignInClick: ()->Unit
){
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let { error->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp),
        onClick = onSignInClick,
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(width = 1.5.dp, color = Color.Black)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),

            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(
                painter = painterResource(id = R.drawable.gg_icon),
                contentDescription = "",
                modifier = Modifier
                    .padding(8.dp)
                    .size(33.dp)
            )
            Text(
                text = "Sign in with Google",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier.padding(5.dp)

            )
        }
    }
}