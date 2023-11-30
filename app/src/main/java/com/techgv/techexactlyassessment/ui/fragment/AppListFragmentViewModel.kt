package com.techgv.techexactlyassessment.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techgv.techexactlyassessment.data.remote.dto.App
import com.techgv.techexactlyassessment.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppListFragmentViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    private val _appListLiveData: MutableLiveData<List<App>> = MutableLiveData()
    val appListLiveData: LiveData<List<App>> = _appListLiveData

    init {
        getAppList()
    }

    private fun getAppList() {
        viewModelScope.launch {
            try {
                val response = repository.getAppList()
                _appListLiveData.postValue(response.data.appList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}