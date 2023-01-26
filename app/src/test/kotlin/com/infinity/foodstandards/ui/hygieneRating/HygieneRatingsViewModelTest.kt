package com.infinity.foodstandards.ui.hygieneRating

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.infinity.foodstandards.network.FoodStandardsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class HygieneRatingsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: HygieneRatingsViewModel

    private val foodStandardsRepo: FoodStandardsRepo = mock()

    @Before
    fun before() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = HygieneRatingsViewModel(foodStandardsRepo)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }
}
