package com.gm.assignment.musicapp

class Config {
    companion object{
        const val BASE_URL = "https://itunes.apple.com/"
        fun postArtist(artistName: String): String{
            return BASE_URL + artistName
        }
    }



}