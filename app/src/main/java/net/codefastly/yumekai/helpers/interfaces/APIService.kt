package net.codefastly.yumekai.helpers.interfaces

import net.codefastly.yumekai.models.CalendarAnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url




interface APIService {
    @GET
    suspend fun getCalendarAnimes(@Url url:String): Response<CalendarAnimeResponse>
}