package com.example.kuitandroidapiexample.detail.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.kuitandroidapiexample.R
import com.example.kuitandroidapiexample.common.TagChip
import com.example.kuitandroidapiexample.detail.viewmodel.DetailViewModel
import com.example.kuitandroidapiexample.model.AnimalType
import com.example.kuitandroidapiexample.ui.theme.FindUTheme.colors
import com.example.kuitandroidapiexample.ui.theme.FindUTheme.typography

@Composable
fun DetailScreen(
    padding: PaddingValues,
    animalId: Int,
    navigateToBack: () -> Unit = {},
    viewModel: DetailViewModel = viewModel()
) {
    val response by viewModel.animalDetailState
    val animal = response?.data
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.getAnimalDetail(animalId)
    }

    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.verticalScroll(state = scrollState)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(20.dp)
                            .clickable { navigateToBack() },
                        painter = painterResource(R.drawable.ic_chevron_left),
                        contentDescription = "뒤로 가기",
                        tint = Color.Unspecified
                    )
                }
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(420.dp),
                    model = animal?.url,
                    contentDescription = "동물 사진"
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Text(
                    text = animal?.name ?: "이름",
                    style = typography.semiBold.copy(fontSize = 24.sp),
                    modifier = Modifier.padding(start = 40.dp, top = 42.dp, bottom = 20.dp)
                )

                TagChip(
                    modifier = Modifier.padding(start = 40.dp),
                    animalType = animal?.state ?: AnimalType.MISSING
                )

                Spacer(modifier = Modifier.height(30.dp))

                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = colors.black,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clip(RoundedCornerShape(20.dp))
                        .shadow(
                            elevation = 10.dp,
                            spotColor = Color(0x1AA0A0A0),
                            ambientColor = Color(0x1AA0A0A0)
                        )
                        .height(89.dp)
                        .padding(horizontal = 40.dp, vertical = 20.dp)
                ) {
                    Text(
                        text = "주소",
                        style = typography.semiBold.copy(
                            fontSize = 14.sp,
                            color = colors.orange
                        ),
                    )
                    Text(
                        text = animal?.address ?: "주소",
                        style = typography.semiBold.copy(fontSize = 14.sp),
                        modifier = Modifier.align(Alignment.BottomStart)
                    )
                }
                Text(
                    text = "신고자 : 최지현",
                    style = typography.semiBold.copy(fontSize = 14.sp),
                    modifier = Modifier.padding(start = 40.dp, top = 21.dp)
                )
                Spacer(modifier = Modifier.size(15.dp))
                Button(
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.red
                    ),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        viewModel.deleteAnimal(animalId)
                        navigateToBack()
                    }
                ) {
                    Text(
                        text = "삭제하기",
                        style = typography.semiBold.copy(fontSize = 18.sp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun DetailScreenPreview() {
    DetailScreen(PaddingValues(), 6)
}