package com.alancamargo.hearthstone.cards.data.remote

import com.alancamargo.hearthstone.cards.data.api.CardsApi
import com.alancamargo.hearthstone.cards.domain.model.CardListResult
import com.alancamargo.hearthstone.cards.testtools.stubCardResponseList
import com.alancamargo.hearthstone.core.domain.FilterType
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class CardsRemoteDataSourceImplTest {

    private val mockApi = mockk<CardsApi>()
    private val remoteDataSource = CardsRemoteDataSourceImpl(mockApi)

    @Test
    fun `when api returns empty list getCards should return GenericError`() {
        // GIVEN
        coEvery {
            mockApi.getCards(category = any(), filter = any())
        } returns Response.success(emptyList())

        // WHEN
        val result = runBlocking {
            remoteDataSource.getCards(type = FilterType.FACTION, filter = "Alan")
        }

        // THEN
        assertThat(result).isInstanceOf(CardListResult.GenericError::class.java)
    }

    @Test
    fun `when api returns cards getCards should return Success`() {
        // GIVEN
        val cards = stubCardResponseList()
        coEvery {
            mockApi.getCards(category = any(), filter = any())
        } returns Response.success(cards)

        // WHEN
        val result = runBlocking {
            remoteDataSource.getCards(type = FilterType.FACTION, filter = "Alan")
        }

        // THEN
        assertThat(result).isInstanceOf(CardListResult.Success::class.java)
    }

    @Test
    fun `when api returns success with null body getCards should return GenericError`() {
        // GIVEN
        coEvery {
            mockApi.getCards(category = any(), filter = any())
        } returns Response.success(null)

        // WHEN
        val result = runBlocking {
            remoteDataSource.getCards(type = FilterType.FACTION, filter = "Alan")
        }

        // THEN
        assertThat(result).isInstanceOf(CardListResult.GenericError::class.java)
    }

    @Test
    fun `when api returns error getCards should return GenericError`() {
        // GIVEN
        coEvery {
            mockApi.getCards(category = any(), filter = any())
        } returns Response.error(500, "".toResponseBody())

        // WHEN
        val result = runBlocking {
            remoteDataSource.getCards(type = FilterType.FACTION, filter = "Alan")
        }

        // THEN
        assertThat(result).isInstanceOf(CardListResult.GenericError::class.java)
    }

    @Test
    fun `when api throws IOException getCards should return NetworkError`() {
        // GIVEN
        coEvery {
            mockApi.getCards(category = any(), filter = any())
        } throws IOException()

        // WHEN
        val result = runBlocking {
            remoteDataSource.getCards(type = FilterType.FACTION, filter = "Alan")
        }

        // THEN
        assertThat(result).isInstanceOf(CardListResult.NetworkError::class.java)
    }
}
