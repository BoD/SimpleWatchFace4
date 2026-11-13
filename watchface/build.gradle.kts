plugins {
  alias(libs.plugins.android.application)
}

android {
  namespace = "org.jraf.android.simplewatchface4"
  compileSdk {
    version = release(37)
  }

  defaultConfig {
    applicationId = "org.jraf.android.simplewatchface4"
    minSdk = 35
    targetSdk = 37
    versionCode = 1
    versionName = "1.0.0"
  }

  buildTypes {
    release {
      optimization {
        enable = true
        packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
      }
    }
  }
  enableKotlin = false
}

dependencies {
}
