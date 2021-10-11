package ai.akun.pokestats.ui.main

import ai.akun.core.extensions.gone
import ai.akun.core.extensions.toSeconds
import ai.akun.core.extensions.visible
import ai.akun.pokeanalyzer.data.toKG
import ai.akun.pokeanalyzer.data.toMts
import ai.akun.pokeanalyzer.presentation.PokeAnalyzerViewModel
import ai.akun.pokestats.R
import ai.akun.pokestats.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.time.ExperimentalTime

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PokeAnalyzerViewModel by viewModel()

    @ExperimentalTime
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        startObserving()
    }

    @ExperimentalTime
    private fun initUI() {
        with(binding) {

            getPokeBtn.setOnClickListener {
                viewModel.getPokemonsAverage(
                    limit = viewModel.limit.value,
                    offset = viewModel.offset.value
                )
                inputLimit.clearFocus()
                inputOffset.clearFocus()
            }

            getPokeTypeBtn.setOnClickListener {
                viewModel.getPokemonsAverageByType(
                    limit = viewModel.limit.value,
                    offset = viewModel.offset.value
                )
                inputLimit.clearFocus()
                inputOffset.clearFocus()
            }


            inputOffset.doOnTextChanged { text, _, _, _ ->
                if (text.toString() != "") {
                    viewModel.saveOffset(text.toString().toInt())
                }
            }

            inputLimit.doOnTextChanged { text, _, _, _ ->
                if (text.toString() != "") {
                    viewModel.saveLimit(text.toString().toInt())
                }
            }
        }
    }

    private fun startObserving() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState -> updateUi(uiState) }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.averages.collect { PokemonAverage ->
                with(binding) {
                    total.text = PokemonAverage.total.toString()
                    weight.text = PokemonAverage.averageWeight.toKG()
                    height.text = PokemonAverage.averageHeight.toMts()
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.averagesType.collect { AveragesByType ->
                binding.pokemonsTypeAvg.text = AveragesByType.toString()
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.time.collect { time ->
                binding.time.text = "Pokemon average analyze time: ${time.toSeconds()} seconds"
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.timeType.collect { time ->
                binding.timeByType.text = "Pokemon by Type average analyze time: ${time.toSeconds()} seconds"
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.isFormValid.collect { isValid ->
                binding.getPokeBtn.isEnabled = isValid
                binding.getPokeTypeBtn.isEnabled = isValid
            }
        }
    }

    private fun updateUi(uiState: PokeAnalyzerViewModel.UIState) {
        if (uiState == PokeAnalyzerViewModel.UIState.Loading) binding.pokeProgress.visible()
        else binding.pokeProgress.gone()

        if (uiState == PokeAnalyzerViewModel.UIState.PokemonsSuccess) binding.pokeStats.visible()
        else binding.pokeStats.gone()

        if (uiState == PokeAnalyzerViewModel.UIState.PokemonsTypeSuccess) binding.pokeStatsAvg.visible()
        else binding.pokeStatsAvg.gone()

        if (uiState == PokeAnalyzerViewModel.UIState.Error) binding.pokeError.visible()
        else binding.pokeError.gone()
    }
}

