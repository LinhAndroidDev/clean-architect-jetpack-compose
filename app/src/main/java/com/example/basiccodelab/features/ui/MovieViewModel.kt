package com.example.basiccodelab.features.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basiccodelab.common.doOnFailure
import com.example.basiccodelab.common.doOnLoading
import com.example.basiccodelab.common.doOnSuccess
import com.example.basiccodelab.data.model.Movie
import com.example.basiccodelab.features.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val useCase: MovieUseCase
) : ViewModel() {
    private val _res: MutableState<MovieState> = mutableStateOf(MovieState())
    val res: State<MovieState> = _res

    init {
        viewModelScope.launch {
            useCase.getMovies().doOnSuccess {
                _res.value = MovieState(
                    data = it ?: emptyList()
                )
            }.doOnFailure {
                _res.value = MovieState(
                    error = it?.message ?: "Unknown error"
                )
            }.doOnLoading {
                _res.value = MovieState(
                    isLoading = true
                )
            }.collect()
        }
    }
}

data class MovieState(
    val data: List<Movie.Result> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)