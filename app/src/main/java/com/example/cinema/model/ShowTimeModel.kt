package com.example.cinema.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ShowTimeModel(
    val id: Int,
    val rap: Int,
    @SerializedName("date_time")
    val dateTime: String,
    @SerializedName("time_start")
    val timeStart: String,
    @SerializedName("time_end")
    val timeEnd: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(rap)
        parcel.writeString(dateTime)
        parcel.writeString(timeStart)
        parcel.writeString(timeEnd)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShowTimeModel> {
        override fun createFromParcel(parcel: Parcel): ShowTimeModel {
            return ShowTimeModel(parcel)
        }

        override fun newArray(size: Int): Array<ShowTimeModel?> {
            return arrayOfNulls(size)
        }
    }
}