package com.example.littlelemonandroid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuItemNetwork(
    @SerialName("menu")
    val menu: List<MenuNetworkdata>,
)

@Serializable
data class MenuNetworkdata(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String
) {
    fun toMenuItemRoom() = MenuItemRoom(
        id,
        title,
        description,
        price,
        image,
        category
    )
}