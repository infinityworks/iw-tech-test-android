package com.infinity.foodstandards.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LocalAuthority (
    @SerializedName("Name")
    @Expose
    var name: String,
    @SerializedName("LocalAuthorityId")
    @Expose
    var id: Int = 0
)

class LocalAuthoritiesResponse {
    lateinit var authorities: List<LocalAuthority>
}