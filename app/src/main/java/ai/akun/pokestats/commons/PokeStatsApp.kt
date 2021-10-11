package ai.akun.pokestats.commons

import ai.akun.core.di.coreModule
import ai.akun.core.network.di.networkModule
import ai.akun.pokeanalyzer.di.pokeAnalyzerModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokeStatsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PokeStatsApp)
            modules(
                listOf(
                    coreModule,
                    networkModule,
                    pokeAnalyzerModule
                )
            )
        }
    }
}