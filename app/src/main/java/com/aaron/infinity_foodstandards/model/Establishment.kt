package com.aaron.infinity_foodstandards.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Establishment(
    @SerializedName("RatingKey")
    @Expose
    var rating: String
): Parcelable

class EstablishmentResponse {
    lateinit var establishments: List<Establishment>
}

