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

        compileSdkVersion(Config.compileSdkVersion)
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)

        versionName = Config.versionName
        versionCode = Config.versionCode

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            consumerProguardFile("proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    testOptions {
        unitTests.apply {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes.forEach { buildType ->
        buildType.javaCompileOptions.annotationProcessorOptions {
            argument("epoxyDisableDslMarker", "true")
        }
    }
}

dependencies {
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
