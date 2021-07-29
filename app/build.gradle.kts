plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlinx-serialization")
}

android {
    defaultConfig {
        applicationId = "com.mehmetkaya.relaxingtime"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            consumerProguardFile("proguard-rules.pro")
        }
    }

    buildTypes.forEach { buildType ->
        buildType.javaCompileOptions.annotationProcessorOptions {
            argument("epoxyDisableDslMarker", "true")
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":utils"))
    implementation(project(":widgets"))

    // Kotlin
    implementation(Deps.kotlin)
    implementation(Deps.coreKtx)

    // Ktx
    implementation(Deps.activityKtx)
    implementation(Deps.fragmentKtx)
    implementation(Deps.viewModelKtx)
    implementation(Deps.navigationFragmentKtx)
    implementation(Deps.navigationUiKtx)

    // Lifecycle
    implementation(Deps.lifecycleCommon)
    implementation(Deps.lifecycleRuntime)

    // Support Libraries
    implementation(Deps.appcompat)
    implementation(Deps.material)
    implementation(Deps.constraintLayout)

    // Hilt
    implementation(Deps.hilt)
    kapt(Deps.hiltCompiler)

    // Retrofit & OkHttp
    api(platform(Deps.okhttpBom))
    implementation(Deps.okhttp)
    api(Deps.okhttpLogging)
    implementation(Deps.retrofit)

    // Serialization
    api(Deps.kotlinSerialization)
    implementation(Deps.retrofitConverterKotlinSerialization)

    // Epoxy
    implementation(Deps.gravitySnapHelper)
    implementation(Deps.epoxy)
    kapt(Deps.epoxyProcessor)

    // Glide
    implementation(Deps.glide)
    kapt(Deps.glideAnnotationProcessor)

    // Misc
    implementation(Deps.timber)

    // ExoPlayer
    implementation(Deps.exoPlayer)

    // Test
    testImplementation(TestDeps.testCore)
    testImplementation(TestDeps.junit)
    testImplementation(TestDeps.assertj)
    testImplementation(TestDeps.coroutinesTest)
    testImplementation(TestDeps.mockK)
}
