package com.techgv.techexactlyassessment.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("app_list")
    val appList: List<App>,
    @SerializedName("usage_access")
    val usageAccess: Int
)