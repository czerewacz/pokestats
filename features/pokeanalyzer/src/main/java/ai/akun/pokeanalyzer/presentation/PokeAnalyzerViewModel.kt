package ai.akun.pokeanalyzer.presentation

import ai.akun.pokeanalyzer.domain.model.PokemonAverage
import ai.akun.pokeanalyzer.domain.usecases.GetPokeAveragesByTypeUseCase
import ai.akun.pokeanalyzer.domain.usecases.GetPokeAveragesUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime


class PokeAnalyzerViewModel(
    private val getPokeAveragesUseCase: GetPokeAveragesUseCase,
    private val getPokeAveragesByTypeUseCase: GetPokeAveragesByTypeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Init)
    val uiState: StateFlow<UIState> = _uiState

    private val _limit = MutableStateFlow(0)
    val limit: StateFlow<Int> = _limit

    private val _offset = MutableStateFlow(0)
    val offset: StateFlow<Int> = _offset

    private val _averages = MutableStateFlow(PokemonAverage())
    val averages: StateFlow<PokemonAverage> = _averages

    private val _averagesType = MutableStateFlow(listOf<PokemonAverage>())
    val averagesType: StateFlow<List<PokemonAverage>> = _averagesType

    private val _time = MutableStateFlow(0L)
    val time: StateFlow<Long> = _time

    private val _timeType = MutableStateFlow(0L)
    val timeType: StateFlow<Long> = _timeType

    val isFormValid = combine(limit, offset) { limit, offset ->
        limit > 0 && offset > 0
    }

    @ExperimentalTime
    fun getPokemonsAverage(limit: Int, offset: Int) {
        viewModelScope.launch {
            _time.emit(measureTimeMillis {
                _uiState.emit(UIState.Loading)
                getPokeAveragesUseCase(
                    limit = limit,
                    offset = offset
                ).collect { result ->
                    if (result is GetPokeAveragesUseCase.Result.Success) {
                        _uiState.emit(UIState.PokemonsSuccess)
                        _averages.emit(result.pokemonAverage)
                    } else if (result is GetPokeAveragesUseCase.Result.Failure) {
                        _uiState.emit(UIState.Error)
                    }
                }
            })
        }
    }

    @ExperimentalTime
    fun getPokemonsAverageByType(limit: Int, offset: Int) {
        viewModelScope.launch {
            _timeType.emit(measureTimeMillis {
                _uiState.emit(UIState.Loading)
                getPokeAveragesByTypeUseCase(
                    limit = limit,
                    offset = offset
                ).collect { result ->
                    if (result is GetPokeAveragesByTypeUseCase.Result.Success) {
                        _uiState.emit(UIState.PokemonsTypeSuccess)
                        _averagesType.emit(result.pokemonAverage)
                    } else if (result is GetPokeAveragesByTypeUseCase.Result.Failure) {
                        _uiState.emit(UIState.Error)
                    }
                }
            })
        }
    }

    fun saveLimit(limit: Int) {
        viewModelScope.launch {
            _limit.emit(limit)
        }
    }

    fun saveOffset(offset: Int) {
        viewModelScope.launch {
            _offset.emit(offset)
        }
    }

    sealed class UIState {
        object Init : UIState()
        object Loading : UIState()
        object Error : UIState()
        object PokemonsSuccess : UIState()
        object PokemonsTypeSuccess : UIState()
    }
}