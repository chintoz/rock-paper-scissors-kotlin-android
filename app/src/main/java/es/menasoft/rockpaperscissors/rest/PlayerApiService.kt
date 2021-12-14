package es.menasoft.rockpaperscissors.rest

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


class Player(val id: String, val name: String, val surname: String? = null)

interface PlayerApiService {
    @POST("player")
    fun createPlayer(@Body player: Player): Call<Player>
}