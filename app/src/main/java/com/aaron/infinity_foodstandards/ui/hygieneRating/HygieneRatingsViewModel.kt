package com.aaron.infinity_foodstandards.ui.hygieneRating

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aaron.infinity_foodstandards.model.EstablishmentResponse
import com.aaron.infinity_foodstandards.network.FoodStandardsRepo
import com.aaron.infinity_foodstandards.network.RetrofitService

class HygieneRatingsViewModel : ViewModel() {

    private var foodStandardsRepo: FoodStandardsRepo =
        FoodStandardsRepo(RetrofitService.createService())

    fun getAllEstablishments(id: Int): LiveData<EstablishmentResponse> {
        return foodStandardsRepo.getEstablishments(id)
    }
}
