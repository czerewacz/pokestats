object Dependencies {

    object BuildPlugins{
        // Kotlin gradle plugin
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${DependenciesVersions.kotlin}"
        // Gradle Build Tools plugin
        const val gradle = "com.android.tools.build:gradle:${DependenciesVersions.gradle}"
    }

    object AndroidX {
        // Kotlin extensions for android
        const val ktx = "androidx.core:core-ktx:${DependenciesVersions.ktx}"

        // AppCompat library
        const val appcompat = "androidx.appcompat:appcompat:${DependenciesVersions.appcompat}"

        // AppCompat library
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${DependenciesVersions.constraint}"

        // Android lifecycle runtime
        const val lifecycle =
            "androidx.lifecycle:lifecycle-runtime-ktx:${DependenciesVersions.lifecycle}"
        //Android lifecycle viewmodel
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${DependenciesVersions.lifecycle}"
    }

    object Kotlin{
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${DependenciesVersions.kotlin}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${DependenciesVersions.coroutines}"
    }

    object Google{
        // Android Material Design Components
        const val material = "com.google.android.material:material:${DependenciesVersions.material}"
    }

    object DI{
        // Koin for Kotlin Multiplatform
        const val koin = "io.insert-koin:koin-core:${DependenciesVersions.koin}"
        // Koin main features for Android (Scope,ViewModel ...)
        const val koinAndroid ="io.insert-koin:koin-android:${DependenciesVersions.koin}"

    }

    object Ktor{
        val ktor = "io.ktor:ktor-client-core:${DependenciesVersions.ktor}"
        val ktorCio = "io.ktor:ktor-client-cio:${DependenciesVersions.ktor}"
        val ktorLog = "io.ktor:ktor-client-logging:${DependenciesVersions.ktor}"
        val logNative = "io.ktor:ktor-client-logging-native:${DependenciesVersions.ktor}"
        val ktorSerial = "io.ktor:ktor-client-serialization:${DependenciesVersions.ktor}"
    }

    object Logging{
        const val slf4j = "org.slf4j:slf4j-simple:${DependenciesVersions.slf4j}"
    }

}