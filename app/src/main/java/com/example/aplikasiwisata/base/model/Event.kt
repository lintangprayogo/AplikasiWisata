package com.example.aplikasiwisata.base.model

data class Event(
    val description: String?,
    val end_date: String?,
    val event_name: String?,
    val id: Int,
    val location: String?,
    val longitude: String?,
    val poster: String?,
    val price: String?,
    val start_date: String?
)