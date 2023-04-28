package com.alancamargo.hearthstone.filters.data.repository

import app.cash.turbine.test
import com.alancamargo.hearthstone.core.log.Logger
import com.alancamargo.hearthstone.filters.data.local.FiltersLocalDataSource
import com.alancamargo.hearthstone.filters.data.remote.FiltersRemoteDataSource
import com.alancamargo.hearthstone.filters.domain.model.FiltersResult
import com.alancamargo.hearthstone.filters.testtools.stubFilters
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FiltersRepositoryImplTest {

    private val mockRemoteDataSource = mockk<FiltersRemoteDataSource>()
    private val mockLocalDataSource = mockk<FiltersLocalDataSource>(relaxed = true)
    private val mockLogger = mockk<Logger>(relaxed = true)

    private val repository = FiltersRepositoryImpl(
        mockRemoteDataSource,
        mockLocalDataSource,
        mockLogger
    )

    @Test
    fun `when remote returns Success getFilters should return Success`() {
        runBlocking {
            // GIVEN
            coEvery {
                mockRemoteDataSource.getFilters()
            } returns FiltersResult.Success(stubFilters())

            // WHEN
            val result = repository.getFilters()

            // THEN
            result.test {
                val actual = awaitItem()
                assertThat(actual).isInstanceOf(FiltersResult.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun `when remote returns Success getFilters should save filters in local`() {
        runBlocking {
            // GIVEN
            coEvery { mockRemoteDataSource.getFilters() } returns FiltersResult.Success(stubFilters())

            // WHEN
            val result = repository.getFilters()

            // THEN
            result.test {
                awaitItem()
                coVerify { mockLocalDataSource.saveFilters(filters = any()) }
                awaitComplete()
            }
        }
    }

    @Test
    fun `when remote returns Success getFilters should log saving filters`() {
        runBlocking {
            // GIVEN
            coEvery { mockRemoteDataSource.getFilters() } returns FiltersResult.Success(stubFilters())

            // WHEN
            val result = repository.getFilters()

            // THEN
            result.test {
                awaitItem()
                verify { mockLogger.debug("Saving filters...") }
                awaitComplete()
            }
        }
    }

    @Test
    fun `when remote returns GenericError and local returns Success getFilters should return Success`() {
        runBlocking {
            // GIVEN
            coEvery { mockRemoteDataSource.getFilters() } returns FiltersResult.GenericError
            coEvery {
                mockLocalDataSource.getFilters()
            } returns FiltersResult.Success(stubFilters())

            // WHEN
            val result = repository.getFilters()

            // THEN
            result.test {
                val actual = awaitItem()
                assertThat(actual).isInstanceOf(FiltersResult.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun `when both remote and local return GenericError getFilters should return GenericError`() {
        runBlocking {
            // GIVEN
            coEvery { mockRemoteDataSource.getFilters() } returns FiltersResult.GenericError
            coEvery { mockLocalDataSource.getFilters() } returns FiltersResult.GenericError

            // WHEN
            val result = repository.getFilters()

            // THEN
            result.test {
                val actual = awaitItem()
                assertThat(actual).isInstanceOf(FiltersResult.GenericError::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun `when remote returns GenericError getFilters should log fetching from local`() {
        runBlocking {
            // GIVEN
            coEvery { mockRemoteDataSource.getFilters() } returns FiltersResult.GenericError

            // WHEN
            val result = repository.getFilters()

            // THEN
            result.test {
                awaitItem()
                verify { mockLogger.debug("Fetching filters from database...") }
                awaitComplete()
            }
        }
    }

    @Test
    fun `when both remote and local return GenericError getFilters should log no filters in database`() {
        runBlocking {
            // GIVEN
            coEvery { mockRemoteDataSource.getFilters() } returns FiltersResult.GenericError
            coEvery { mockLocalDataSource.getFilters() } returns FiltersResult.GenericError

            // WHEN
            val result = repository.getFilters()

            // THEN
            result.test {
                awaitItem()
                verify { mockLogger.debug("No filters in database") }
                awaitComplete()
            }
        }
    }

    @Test
    fun `clearCache should emit task result`() {
        runBlocking {
            // WHEN
            val result = repository.clearCache()

            // THEN
            result.test {
                awaitItem()
                awaitComplete()
            }
        }
    }

    @Test
    fun `clearCache should log clearing filters cache`() {
        runBlocking {
            // WHEN
            val result = repository.clearCache()

            // THEN
            result.test {
                awaitItem()
                verify { mockLogger.debug("Clearing filters cache...") }
                awaitComplete()
            }
        }
    }
}
