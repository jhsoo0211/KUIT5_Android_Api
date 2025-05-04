package com.example.kuitandroidapiexample.home.component

import android.widget.Button
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kuitandroidapiexample.home.viewmodel.DeleteViewModel

@Composable
fun DeleteButton(
    viewModel: DeleteViewModel,
    navigateToBack: () -> Unit,
    deleteId: Int
){
    Button(
        onClick = {
            viewModel.deleteAnimal(deleteId)
            navigateToBack()
        }
    ){
        Text(
            text = "삭제"
        )
    }
}