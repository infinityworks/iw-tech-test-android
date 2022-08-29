package com.infinity.foodstandards.ui.hygieneRating

//import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.infinity.foodstandards.model.LocalAuthoritiesResponse
import com.infinity.foodstandards.ui.authorities.AuthoritiesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

//import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
class AuthoritiesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: AuthoritiesViewModel

    private val observer: Observer<LocalAuthoritiesResponse> = mock()

    private val mockServer = MockWebServer()

    @Before
    fun before() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        viewModel = AuthoritiesViewModel()

        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(200)
        mockedResponse.setBody("{}") // sample JSON
        mockServer.enqueue(mockedResponse)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getLocalAuthorities() {
        viewModel.getLocalAuthorities().observeForever(observer)

        val captor = ArgumentCaptor.forClass(LocalAuthoritiesResponse::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
        }
    }
}