import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.secrets.gradle)
}

android {
    namespace = "com.hcato.assistantvirtual"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.hcato.assistantvirtual"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        buildConfig = true
        resValues = true
    }

    flavorDimensions.add("environment")
    productFlavors {

        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"

            buildConfigField("String", "BASE_URL", "\"https://api.adviceslip.com/\"")

            resValue("string", "app_name", "Assistant Virtual (DEV)")
        }

        create("prod") {
            dimension = "environment"

            buildConfigField("String", "BASE_URL", "\"https://api.adviceslip.com/\"")

            resValue("string", "app_name", "Assistant Virtual"
            )
        }
    }

}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

secrets {
    propertiesFileName = "local.properties"
    defaultPropertiesFileName = "local.default.properties"
    ignoreList.add("sdk.dir")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.viewmodel.compose)   //viewModel()
    implementation(libs.com.squareup.retrofit2.retrofit)        // Retrofit
    implementation(libs.com.squareup.retrofit2.converter.json)  // JSON
    implementation(libs.io.coil.kt.coil.compose)
    implementation(libs.androidx.ui)                // IO
    implementation("io.github.sceneview:sceneview:2.2.1") //SceneView 3D
    implementation("androidx.media:media:1.7.0") //TTS Voice
    implementation("com.google.mlkit:translate:17.0.2") //Translate
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}