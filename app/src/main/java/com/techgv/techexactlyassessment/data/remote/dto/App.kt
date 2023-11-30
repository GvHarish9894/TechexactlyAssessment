package com.techgv.techexactlyassessment.data.remote.dto


import com.google.gson.annotations.SerializedName

data class App(
    @SerializedName("app_icon")
    val appIcon: String,
    @SerializedName("app_id")
    val appId: Int,
    @SerializedName("app_name")
    val appName: String,
    @SerializedName("app_package_name")
    val appPackageName: String,
    @SerializedName("fk_kid_id")
    val fkKidId: Int,
    @SerializedName("kid_profile_image")
    val kidProfileImage: String,
    @SerializedName("status")
    val status: String,
) {
    fun isEnabled(): Boolean {
        return status.contains("active", true)
    }
}