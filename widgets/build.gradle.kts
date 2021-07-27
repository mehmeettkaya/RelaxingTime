plugins {
  id("com.android.library")
  id("kotlin-android")
  id("kotlin-kapt")
}

dependencies {
  // Material
  implementation(Deps.material)

  // Epoxy
  implementation(Deps.epoxy)
  kapt(Deps.epoxyProcessor)
}
