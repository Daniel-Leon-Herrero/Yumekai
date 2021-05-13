package net.codefastly.yumekai.fragments.Calendar

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url




interface APIService {
    @GET
    suspend fun getCalendarAnimes(@Url url:String): Response<CalendarAnimeResponse>
}