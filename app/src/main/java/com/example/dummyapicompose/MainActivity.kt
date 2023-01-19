@file:OptIn(ExperimentalCoilApi::class)

package com.example.dummyapicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.dummyapicompose.ui.theme.DummyApiComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DummyApiComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }
}

@Composable
fun PhotoList(photoList : List<Photos>){
    var selectedIndex by remember {
        mutableStateOf(-1)
    }

    LazyColumn{

        itemsIndexed(items = photoList){index, item ->

            PhotoItem(photo = item,index,selectedIndex){i->
                selectedIndex=i
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhotoItem(){
    val photo = Photos(
        1,
        1,
        "accusamus beatae ad facilis cum similique qui sunt",
        "https://via.placeholder.com/600/92c952",
        "https://via.placeholder.com/150/92c952"
    )

    PhotoItem(photo = photo,0,0){i->

    }
}

@Composable
fun PhotoItem(
    photo:Photos,
    index:Int,
    selectedIndex:Int,
    onClick:(Int)->Unit
){
    val backgroundColor =
        if (index == selectedIndex) MaterialTheme.colors.primary
        else MaterialTheme.colors.background

    Card(modifier = Modifier
        .padding(8.dp, 4.dp)
        .fillMaxWidth()
        .clickable { onClick(index) }
        .height(110.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp
    ) {
        Surface(color = backgroundColor) {

            Row(modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
            ){
                Image(
                    painter = rememberImagePainter(
                        data = photo.url,

                        builder = {
                            scale(Scale.FILL)
                            placeholder(R.drawable.img)
                            transformations(RoundedCornersTransformation(5f))
                        }
                    ),
                    contentDescription =photo.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.2f)
                )


                Column(verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.8f)
                        .padding(5.dp)
                ) {
                    Text(
                        text = "Album id : ${photo.albumId}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )

                    Text(
                        text = "Id : ${photo.id}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "Id : ${photo.title}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )

                }
            }
        }
    }
}

