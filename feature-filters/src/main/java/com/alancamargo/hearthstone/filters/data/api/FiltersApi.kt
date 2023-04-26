package com.alancamargo.hearthstone.filters.data.api

import com.alancamargo.hearthstone.filters.data.model.FiltersResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface FiltersApi {

    @GET("info")
    suspend fun getFilters(): Response<FiltersResponse>
}
