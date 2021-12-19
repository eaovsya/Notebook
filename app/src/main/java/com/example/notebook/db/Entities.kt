package com.example.notebook.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "contacts",
    indices = [Index(value = ["first_name", "last_name"], unique = true)]
)
@Parcelize
data class Contact(
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String? = null,
    @ColumnInfo(name = "middle_name") val middleName: String? = null,
    @ColumnInfo(name = "pic_url") val picUrl: String? = null,
    @ColumnInfo(name = "phone_numbers") val phoneNumbers: List<String?>? = null,
    @ColumnInfo(name = "social_media") val socialMedia: List<String?>? = null,
    @ColumnInfo(name = "description") val description: String? = null,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
) : Parcelable