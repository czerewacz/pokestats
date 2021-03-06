object TestDependencies {
    object Junit{
        const val junit = "junit:junit:${TestDependenciesVersions.junit}"
        const val koin =  "io.insert-koin:koin-test-junit4:${DependenciesVersions.koin}"
    }

    object Coroutines{
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${TestDependenciesVersions.coroutines}"
    }

    object Mock{
        const val mockk ="io.mockk:mockk:${TestDependenciesVersions.mockk}"
    }

    object Koin{
        const val koin = "io.insert-koin:koin-test:${DependenciesVersions.koin}"
    }
}