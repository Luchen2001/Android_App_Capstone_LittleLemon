package com.example.littlelemonandroid

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun HomeScreen(navController: NavHostController, database: AppDatabase) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }

    val allDatabaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())
    val filteredMenuItems = allDatabaseMenuItems.filter {
        it.title.contains(
            searchQuery,
            ignoreCase = true
        ) && (selectedCategory == "All" || it.category == selectedCategory)
    }

    val categories = allDatabaseMenuItems.map { it.category }.distinct()

    Column(
        Modifier
            .fillMaxSize(),
    ) {
        HomeHeader(navController = navController)
        HomeBanner(
            searchText = searchQuery,
            onSearchTextChanged = { newSearchText -> searchQuery = newSearchText }
        )

        //Filter
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 30.dp)
        ) {
            Text(
                text = "ORDER FOR DELIVERY",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 23.sp
            )
            // Menu Breakdown
            LazyRow(
                modifier = Modifier.padding(top = 10.dp).fillMaxWidth(), // Add padding to the LazyRow
                horizontalArrangement = Arrangement.SpaceBetween // Add space between each item
            ) {
                items(categories) { category ->
                    Text(
                        text = category,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF495E57),
                        modifier = Modifier
                            .clickable { if (selectedCategory == category) selectedCategory ="All" else selectedCategory =category }
                            .background(
                                if (selectedCategory == category) Color(0xFFF4CE14) else Color.LightGray,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clip(RoundedCornerShape(20.dp))
                            .padding(horizontal = 16.dp, vertical = 8.dp) // Add padding to the Text
                    )
                }
            }

        }
        Row(
            modifier = Modifier.padding(horizontal = 20.dp)
        ){
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )
        }

        // Menu Items
        MenuItems(items = filteredMenuItems)
    }
}

@Composable
fun HomeHeader(navController: NavHostController){
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        // First image centered
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .height(80.dp)
                .width(200.dp),
        )

        // Second image at the right end
        Image(
            painter = painterResource(id = R.drawable.profile), // Replace with your actual resource ID
            contentDescription = "another logo",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .height(70.dp)
                .width(70.dp)
                .padding(horizontal = 10.dp)
                .clickable { navController.navigate(Profile.route) }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBanner(searchText: String, onSearchTextChanged: (String) -> Unit){
    Column {
        Column(
            modifier = Modifier
                .background(Color(0xFF495E57))
                .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Little Lemon",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF4CE14)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Text(
                        text = "Chicago",
                        fontSize = 24.sp,
                        color = Color(0xFFEDEFEE)
                    )
                    Text(
                        text = stringResource(id = R.string.description),
                        color = Color(0xFFEDEFEE),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(bottom = 28.dp)
                            .fillMaxWidth(0.6f)
                            .padding(top = 18.dp)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.hero),
                    contentDescription = "Upper Panel Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .clip(RoundedCornerShape(40.dp))
                        .height(150.dp)
                        .width(160.dp)
                        .padding(horizontal = 10.dp)
                )
            }
            Row() {
                TextField(
                    value = searchText,
                    onValueChange = { onSearchTextChanged(it) },
                    label = { Text("Enter Search Phrase") },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    }
}

@Composable
fun Filter(){
    Column(
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 30.dp)
    ) {
        Text(
            text = "ORDER FOR DELIVERY",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 23.sp
        )
    }
    Row(
        modifier = Modifier.padding(horizontal = 20.dp)
    ){
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        )
    }
}

@Composable
fun MenuItems(items: List<MenuItemRoom>){
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.6f)
                            .height(120.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = menuItem.title,
                            fontSize = 20.sp,  // Make the text larger
                            fontWeight = FontWeight.Bold  // Make the text bold
                        )
                        Text(
                            text = menuItem.description,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Gray  // Change the text color to grey
                        )
                        Text(
                            text = "$${menuItem.price}",  // Add $ before the price
                            color = Color.Gray,  // Change the text color to grey
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    LoadImage(path = menuItem.image)
                }
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray)
                )

            }
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadImage(path:String){
    println(path)
    GlideImage(
        model = path,
        contentDescription = "load Image",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .size(100.dp, 100.dp)
            .padding(10.dp)
    )
}
