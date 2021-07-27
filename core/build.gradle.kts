plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":utils"))

    // Kotlin
    implementation(Deps.kotlin)

    // Coroutines
    implementation(Deps.coroutinesCore)
    implementation(Deps.coroutinesAndroid)

    // Lifecycle
    implementation(Deps.lifecycleRuntime)

    // Okhttp
    implementation(platform(Deps.okhttpBom))
    implementation(Deps.okhttp)

    // Hilt
    kapt(Deps.hiltCompiler)
    implementation(Deps.hilt)

    // Ktx
    implementation(Deps.coreKtx)
    implementation(Deps.viewModelKtx)

    // Misc
    implementation(Deps.timber)
}
