package net.codefastly.yumekai.interfaces

import net.codefastly.yumekai.models.calendar.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url




interface APIService {
    @GET
    suspend fun getCalendarAnimesMonday(@Url url:String): Response<CalendarAnimeMondayResponse>

    @GET
    suspend fun getCalendarAnimesTuesday(@Url url:String): Response<CalendarAnimeTuesdayResponse>

    @GET
    suspend fun getCalendarAnimesWednesday(@Url url:String): Response<CalendarAnimeWednesdayResponse>

    @GET
    suspend fun getCalendarAnimesThursday(@Url url:String): Response<CalendarAnimeThursdayResponse>

    @GET
    suspend fun getCalendarAnimesFriday(@Url url:String): Response<CalendarAnimeFridayResponse>

    @GET
    suspend fun getCalendarAnimesSaturday(@Url url:String): Response<CalendarAnimeSaturdayResponse>

    @GET
    suspend fun getCalendarAnimesSunday(@Url url:String): Response<CalendarAnimeSundayResponse>
}