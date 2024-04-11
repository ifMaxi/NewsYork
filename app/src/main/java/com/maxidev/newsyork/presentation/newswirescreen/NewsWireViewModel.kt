package com.maxidev.newsyork.presentation.newswirescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.newsyork.domain.repository.NewsWireRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class NewsWireViewModel @Inject constructor(
    private val repository: NewsWireRepository
): ViewModel() {

    private val _nwResponseStatus: MutableStateFlow<NwResponseStat> =
        MutableStateFlow(NwResponseStat.Loading)
    val nwResponseStat = _nwResponseStatus.asStateFlow()

    init {
        fetchedNewsWire()
    }

    private fun fetchedNewsWire() {
        viewModelScope.launch {
            _nwResponseStatus.value = NwResponseStat.Loading

            _nwResponseStatus.value = try {
                NwResponseStat.Success(onSuccess = repository.getNewsWireStream())
            } catch (e: IOException) {
                NwResponseStat.Error(onError = e)
            } catch (e: HttpException) {
                NwResponseStat.Error(onError = e)
            }
        }
    }
}