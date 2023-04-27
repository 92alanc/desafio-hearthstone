package com.alancamargo.hearthstone.cards.data.remote

import com.alancamargo.hearthstone.cards.data.api.CardsApi
import com.alancamargo.hearthstone.cards.data.mapping.toData
import com.alancamargo.hearthstone.cards.domain.model.CardListResult
import com.alancamargo.hearthstone.core.data.mapping.toDomain
import com.alancamargo.hearthstone.core.domain.FilterType
import java.io.IOException
import javax.inject.Inject

internal class CardsRemoteDataSourceImpl @Inject constructor(
    private val api: CardsApi
) : CardsRemoteDataSource {

    override suspend fun getCards(type: FilterType, filter: String): CardListResult {
        val category = type.toData()

        return try {
            val response = api.getCards(
                category = category,
                filter = filter
            )

            return if (response.isSuccessful) {
                response.body()?.let { body ->
                    val cards = body.map { it.toDomain() }
                    CardListResult.Success(cards)
                } ?: CardListResult.GenericError
            } else {
                CardListResult.GenericError
            }
        } catch (e: IOException) {
            CardListResult.NetworkError
        }
    }
}
