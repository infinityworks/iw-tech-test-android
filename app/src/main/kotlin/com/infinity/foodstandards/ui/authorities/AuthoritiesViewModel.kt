package com.infinity.foodstandards.ui.authorities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infinity.foodstandards.model.LocalAuthoritiesResponse
import com.infinity.foodstandards.network.FoodStandardsRepo
import com.infinity.foodstandards.network.FoodStandardsRepository
import com.infinity.foodstandards.network.RetrofitService
import kotlinx.coroutines.launch

class AuthoritiesViewModel(
    private val foodStandardsRepo: FoodStandardsRepo = FoodStandardsRepository(RetrofitService.createService())
) : ViewModel() {

    val localAuthorities = MutableLiveData<LocalAuthoritiesResponse>()
    val errorLiveData = MutableLiveData<Boolean>()

    fun getLocalAuthorities() = viewModelScope.launch {
        val result = foodStandardsRepo.getLocalAuthorities().getOrNull()
        result?.let {
            localAuthorities.postValue(it)
        }
        errorLiveData.postValue(result == null)
    }
}
