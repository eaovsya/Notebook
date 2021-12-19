package com.example.notebook.utils

import com.example.notebook.db.Contact

fun createDummies() = listOf(
    Contact(
        "John",
        "Johnson",
        picUrl = "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?cs=srgb&dl=pexels-pixabay-220453.jpg&fm=jpg",
        phoneNumbers = listOf("88003735467", "89763451265"),
        socialMedia = listOf("vk.ru/something")
    ),
    Contact(
        "Jeremy",
        "Fischer",
        "William",
        "https://images.pexels.com/photos/697509/pexels-photo-697509.jpeg?cs=srgb&dl=pexels-andrew-personal-training-697509.jpg&fm=jpg",
        listOf("88003735467", "89763451265"),
        socialMedia = listOf("facebook.com/hqc"),
        "Professor at Uni"
    ),
    Contact(
        "Ben",
        "Goethler",
        picUrl = "https://images.pexels.com/photos/10520767/pexels-photo-10520767.jpeg?cs=srgb&dl=pexels-zinaw-photography-10520767.jpg&fm=jpg",
        phoneNumbers = listOf("448987309382"),
        socialMedia = listOf("instagram.com/boi")
    ),
    Contact(
        "Sydney",
        picUrl = "https://images.pexels.com/photos/762020/pexels-photo-762020.jpeg?cs=srgb&dl=pexels-andrea-piacquadio-762020.jpg&fm=jpg",
        phoneNumbers = listOf("736303023"),
        socialMedia = listOf("instagram.com/sydznts", "facebook.com/no")
    )
)