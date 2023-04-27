package com.alancamargo.hearthstone.cards.data.api

import com.alancamargo.hearthstone.core.data.remote.CardResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CardsApi {

    @GET("cards/{category}/{filter}")
    suspend fun getCards(
        @Path("category") category: String,
        @Path("filter") filter: String
    ): Response<List<CardResponse>>
}
