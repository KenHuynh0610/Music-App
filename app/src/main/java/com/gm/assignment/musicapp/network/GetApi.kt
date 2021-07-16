package com.gm.assignment.musicapp.network

import com.gm.assignment.musicapp.TrackListResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query



interface GetApi {
    @GET("search")
    fun getTracks(@Query("term") query: String): Observable<TrackListResult>
}