package com.techgv.techexactlyassessment.domain.repository

import com.techgv.techexactlyassessment.data.remote.utils.RemoteService
import javax.inject.Inject

class AppRepository @Inject constructor(private val remoteService: RemoteService) {

    suspend fun getAppList() = remoteService.getAppList()
}