package com.gm.assignment.musicapp

data class TrackListResult(val resultCount: Int, val results: ArrayList<Track>)
data class Track(
    val artistName: String,
    var collectionName: String,
    var releaseDate: String,
    var collectionPrice: String,
    var primaryGenreName: String
)