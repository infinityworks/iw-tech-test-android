package com.infinity.foodstandards.ui.authorities

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.infinity.foodstandards.model.LocalAuthoritiesResponse
import com.infinity.foodstandards.model.LocalAuthority
import com.infinity.foodstandards.network.FoodStandardsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class AuthoritiesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: AuthoritiesViewModel

    private val observer: Observer<LocalAuthoritiesResponse> = mock()
    private val foodStandardsRepo: FoodStandardsRepo = mock()

    private val mockAuthorities = mutableListOf<LocalAuthority>().apply {
        add(LocalAuthority("Aberdeen City", 1))
        add(LocalAuthority("Amber Valley", 2))
    }
    private val mockAuthoritiesResponse = LocalAuthoritiesResponse().apply {
        authorities = mockAuthorities
    }

    @Before
    fun before() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = AuthoritiesViewModel(foodStandardsRepo)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given view model, when local authorities requested, then repository function is invoked`() = runTest {
        viewModel.getLocalAuthorities()
        verify(foodStandardsRepo, times(1)).getLocalAuthorities()
    }

    @Test
    fun `Given get local authorities, when response is failure, then local authorities is not updated`() = runTest {
        whenever(foodStandardsRepo.getLocalAuthorities()).doReturn(Result.failure(Exception()))
        viewModel.getLocalAuthorities()
        viewModel.localAuthorities.observeForever(observer)
        assertNull(viewModel.localAuthorities.value)
    }

    @Test
    fun `Given get local authorities, when response is failure, then error live data updated`() = runTest {
        whenever(foodStandardsRepo.getLocalAuthorities()).doReturn(Result.failure(Exception()))
        viewModel.getLocalAuthorities()
        assert(viewModel.errorLiveData.value == true)
    }

    @Test
    fun `Given get local authorities, when response is success, then local authorities is updated`() = runTest {
        whenever(foodStandardsRepo.getLocalAuthorities()).doReturn(Result.success(mockAuthoritiesResponse))
        viewModel.getLocalAuthorities()
        assertEquals(viewModel.localAuthorities.value, mockAuthoritiesResponse)
    }

    @Test
    fun `Given get local authorities, when response is null, then error live data updated`() = runTest {
        whenever(foodStandardsRepo.getLocalAuthorities()).doReturn(Result.success(mockAuthoritiesResponse))
        viewModel.getLocalAuthorities()
        assert(viewModel.errorLiveData.value == false)
    }
}
