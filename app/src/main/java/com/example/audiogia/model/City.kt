package com.example.audiogia.model

import java.io.Serializable

data class City (
    val id: Int? = null,
    val title: String? = null,
    val featuredImage: String? = null,
    val description: String? = null
) : Serializable