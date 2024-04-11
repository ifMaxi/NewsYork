package com.maxidev.newsyork.presentation.topstoriesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.newsyork.domain.repository.TopStoriesRepository
import com.maxidev.newsyork.utils.TopStoriesUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class TopStoriesViewModel @Inject constructor(
    private val repository: TopStoriesRepository
): ViewModel() {

    private val _storiesResponseStat: MutableStateFlow<StoriesResponseStat> =
        MutableStateFlow(StoriesResponseStat.Loading)
    val storiesResponseStat = _storiesResponseStat.asStateFlow()

//    init {
//        fetchedStories()
//    }

    fun fetchedStories(stories: String = "") {
        viewModelScope.launch {
            _storiesResponseStat.value = StoriesResponseStat.Loading
            _storiesResponseStat.value = try {
                StoriesResponseStat.Success(onSuccess = repository.getTopStoriesStream(stories))
            } catch (e: IOException) {
                StoriesResponseStat.Error(onError = e)
            } catch (e: HttpException) {
                StoriesResponseStat.Error(onError = e)
            }
        }
    }

    private val _topics: MutableStateFlow<List<String>> = MutableStateFlow(TopStoriesUtils.topCalls)
    val topics = _topics.asStateFlow()

    fun selectedTopics(topics: TopStoriesUtils) {
        viewModelScope.launch {
            _topics.value.forEachIndexed { index, s ->
                topics.topCalls[index]
            }
        }
    }
}