package com.example.kuitandroidapiexample.detail.delete

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kuitandroidapiexample.ui.theme.FindUTheme.colors



@Composable
fun DeleteButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit = {}

){
    Button(
        modifier = modifier
            .height(38.dp),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFE0E0E0)
        ),
        onClick = onClick,
    ){
        Text(
            text = title,
            color = colors.black
        )
    }

}

@Preview (showBackground = true,
    backgroundColor = 0xFFFFFFFF)
@Composable
private fun DeleteButtonPreview(){
    DeleteButton(
        title = "삭제",
        modifier = Modifier,
        onClick = {}
    )
}