package com.example.cinema.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class MovieModel(
    @SerializedName("avg_rate")
    val avgRate: Double?,
    val censoship: Int,
    val cost: Int,
    @SerializedName("date_end")
    val dateEnd: String,
    @SerializedName("date_start")
    val dateStart: String,
    val id: Int,
    val language: String,
    @SerializedName("mount_watch")
    val mountWatch: Int,
    @SerializedName("movie_type")
    val movieType: String,
    val name: String,
    val poster: String,
    val time: Int,
    val trailer: String,
    @SerializedName("show_time")
    val listShowTime: List<ShowTimeModel>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString() !!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.createTypedArrayList(ShowTimeModel.CREATOR) ?: arrayListOf()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(avgRate ?: 0.0)
        parcel.writeInt(censoship)
        parcel.writeInt(cost)
        parcel.writeString(dateEnd)
        parcel.writeString(dateStart)
        parcel.writeInt(id)
        parcel.writeString(language)
        parcel.writeInt(mountWatch)
        parcel.writeString(movieType)
        parcel.writeString(name)
        parcel.writeString(poster)
        parcel.writeInt(time)
        parcel.writeString(trailer)
        parcel.writeTypedList(listShowTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieModel> {
        override fun createFromParcel(parcel: Parcel): MovieModel {
            return MovieModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieModel?> {
            return arrayOfNulls(size)
        }
    }
}
