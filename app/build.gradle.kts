plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
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

    // Kotlin
    implementation(Deps.kotlin)
    implementation(Deps.coreKtx)

    // Ktx
    implementation(Deps.activityKtx)
    implementation(Deps.fragmentKtx)
    implementation(Deps.viewModelKtx)

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

    // Misc
    implementation(Deps.timber)

    // Test
    testImplementation(TestDeps.testCore)
    testImplementation(TestDeps.junit)
    testImplementation(TestDeps.assertj)
    testImplementation(TestDeps.coroutinesTest)
    testImplementation(TestDeps.mockK)
}
