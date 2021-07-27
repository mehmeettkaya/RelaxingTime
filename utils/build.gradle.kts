plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Ktx
    implementation(Deps.activityKtx)
    implementation(Deps.fragmentKtx)

    // Epoxy
    implementation(Deps.appcompat)
    implementation(Deps.epoxy)

    // Hilt
    kapt(Deps.hiltCompiler)
    implementation(Deps.hilt)

    // Lifecycle
    implementation(Deps.lifecycleCommon)
    implementation(Deps.lifecycleRuntime)

    // Misc
    implementation(Deps.timber)

    // Retrofit
    implementation(Deps.retrofit)
    implementation(Deps.okhttp)

    // Epoxy
    implementation(Deps.epoxy)

    // Kotlin Serialization
    implementation(Deps.kotlinSerialization)

    // Coroutines
    implementation(Deps.coroutinesCore)
}
