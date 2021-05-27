package net.codefastly.yumekai.interfaces

import net.codefastly.yumekai.models.AnimeCharacters.CharacterAnimeResponse
import net.codefastly.yumekai.models.anime.AnimeResponse
import net.codefastly.yumekai.models.calendar.*
import net.codefastly.yumekai.models.pictures.PicturesResponse
import net.codefastly.yumekai.models.ranking.TopResponse
import net.codefastly.yumekai.models.recents.RecentsResponse
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

    @GET
    suspend fun getAnimes(@Url url:String): Response<AnimeResponse>

    @GET
    suspend fun getAnimesCharacter(@Url url:String): Response<CharacterAnimeResponse>

    @GET
    suspend fun getRecents(@Url url:String): Response<RecentsResponse>

    @GET
    suspend fun searchAnimeByQuery(@Url url:String): Response<RecentsResponse>

    @GET
    suspend fun topData(@Url url:String): Response<TopResponse>

    @GET
    suspend fun getPicturesByAnime(@Url url:String): Response<PicturesResponse>

}