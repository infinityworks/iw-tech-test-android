package com.aaron.infinity_foodstandards.ui.authorities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aaron.infinity_foodstandards.model.LocalAuthoritiesResponse
import com.aaron.infinity_foodstandards.network.FoodStandardsRepo
import com.aaron.infinity_foodstandards.network.RetrofitService

class AuthoritiesViewModel : ViewModel() {

    private var foodStandardsRepo: FoodStandardsRepo =
        FoodStandardsRepo(RetrofitService.createService())

    ///Get local authorities live data
    fun getLocalAuthorities(): MutableLiveData<LocalAuthoritiesResponse> {
        return foodStandardsRepo.getLocalAuthorities()
    }
}
