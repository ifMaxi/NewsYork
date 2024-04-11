package com.maxidev.newsyork.search.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maxidev.newsyork.search.domain.model.ArtSearch
import com.maxidev.newsyork.search.domain.repository.ArtSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtSearchViewModel @Inject constructor(
    private val repository: ArtSearchRepository
): ViewModel() {

    private val _search: MutableState<String> = mutableStateOf("")
    val search: State<String> = _search

    fun onSearchChange(newValue: String) {
        _search.value = newValue
    }

    private val _searchedArt =  MutableStateFlow<PagingData<ArtSearch>>(PagingData.empty())
    val searchedArt = _searchedArt

    fun artSearched(query: String = "") = viewModelScope.launch {
        repository.artSearchStream(q = query)
            .cachedIn(viewModelScope)
            .collect { _searchedArt.value = it }
    }
}