package com.infinity.foodstandards.ui.hygieneRating

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infinity.foodstandards.model.EstablishmentResponse
import com.infinity.foodstandards.network.FoodStandardsRepo
import com.infinity.foodstandards.network.RetrofitService

class HygieneRatingsViewModel : ViewModel() {

    private var foodStandardsRepo: FoodStandardsRepo =
        FoodStandardsRepo(RetrofitService.createService())

    fun getAllEstablishments(id: Int): LiveData<EstablishmentResponse> {
        return MutableLiveData()
    }
}
