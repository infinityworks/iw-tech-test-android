package com.infinity.foodstandards.ui.hygieneRating

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infinity.foodstandards.model.EstablishmentResponse
import com.infinity.foodstandards.network.FoodStandardsRepo
import com.infinity.foodstandards.network.FoodStandardsRepository
import com.infinity.foodstandards.network.RetrofitService

class HygieneRatingsViewModel(
    private val foodStandardsRepo: FoodStandardsRepo = FoodStandardsRepository(RetrofitService.createService())
) : ViewModel() {

    fun getAllEstablishments(id: Int): LiveData<EstablishmentResponse> {
        return MutableLiveData()
    }
}
