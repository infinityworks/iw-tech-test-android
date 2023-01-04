package com.infinity.foodstandards.ui.authorities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infinity.foodstandards.model.LocalAuthoritiesResponse
import com.infinity.foodstandards.network.FoodStandardsRepo
import com.infinity.foodstandards.network.RetrofitService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthoritiesViewModel() : ViewModel() {

    private var foodStandardsRepo: FoodStandardsRepo =
        FoodStandardsRepo(RetrofitService.createService())

    public val localAuthorities = MutableLiveData<LocalAuthoritiesResponse>()

    ///Get local authorities live data
    fun getLocalAuthorities(): LiveData<LocalAuthoritiesResponse> {
        viewModelScope.launch {
            val result = foodStandardsRepo.getLocalAuthorities().getOrNull()
            result?.let {
                localAuthorities.postValue(it)
            }
        }
        return localAuthorities
    }
}
