package com.alancamargo.hearthstone.filters.data.remote

import com.alancamargo.hearthstone.filters.data.api.FiltersApi
import com.alancamargo.hearthstone.filters.domain.model.FiltersResult
import com.alancamargo.hearthstone.filters.testtools.stubFiltersResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class FiltersRemoteDataSourceImplTest {

    private val mockApi = mockk<FiltersApi>()
    private val remoteDataSource = FiltersRemoteDataSourceImpl(mockApi)

    @Test
    fun `when api returns success getFilters should return Success`() {
        // GIVEN
        coEvery {
            mockApi.getFilters()
        } returns Response.success(stubFiltersResponse())

        // WHEN
        val result = runBlocking { remoteDataSource.getFilters() }

        // THEN
        assertThat(result).isInstanceOf(FiltersResult.Success::class.java)
    }

    @Test
    fun `when api returns success with null body getFilters should return GenericError`() {
        // GIVEN
        coEvery {
            mockApi.getFilters()
        } returns Response.success(null)

        // WHEN
        val result = runBlocking { remoteDataSource.getFilters() }

        // THEN
        assertThat(result).isInstanceOf(FiltersResult.GenericError::class.java)
    }

    @Test
    fun `when api returns error getFilters should return GenericError`() {
        // GIVEN
        coEvery {
            mockApi.getFilters()
        } returns Response.error(500, "".toResponseBody())

        // WHEN
        val result = runBlocking { remoteDataSource.getFilters() }

        // THEN
        assertThat(result).isInstanceOf(FiltersResult.GenericError::class.java)
    }

    @Test
    fun `when api throws IOException getFilters should return NetworkError`() {
        // GIVEN
        coEvery {
            mockApi.getFilters()
        } throws IOException()

        // WHEN
        val result = runBlocking { remoteDataSource.getFilters() }

        // THEN
        assertThat(result).isInstanceOf(FiltersResult.NetworkError::class.java)
    }
}
