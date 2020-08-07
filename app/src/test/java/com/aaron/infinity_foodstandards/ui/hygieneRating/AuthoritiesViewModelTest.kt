package com.aaron.infinity_foodstandards.ui.hygieneRating

//import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aaron.infinity_foodstandards.model.LocalAuthoritiesResponse
import com.aaron.infinity_foodstandards.ui.authorities.AuthoritiesViewModel
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

//import org.junit.runner.RunWith

class AuthoritiesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: AuthoritiesViewModel

    private val observer: Observer<LocalAuthoritiesResponse> = mock()

    private val mockServer = MockWebServer()

    @Before
    fun before() {
        viewModel =
            AuthoritiesViewModel()
        viewModel.getLocalAuthorities().observeForever(observer)

        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(200)
        mockedResponse.setBody("{}") // sample JSON
        mockServer.enqueue(mockedResponse)
    }


    @Test
    fun getLocalAuthorities() {
        viewModel.getLocalAuthorities()

        val captor = ArgumentCaptor.forClass(LocalAuthoritiesResponse::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
        }

    }
}