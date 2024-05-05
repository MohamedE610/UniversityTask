plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.orgJetbrainsKotlinkapt)
}

android {
    namespace = "com.university.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"http://universities.hipolabs.com/\"")
        }

        release {
            buildConfigField("String", "BASE_URL", "\"http://universities.hipolabs.com/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
}


dependencies {

    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.material)
    api(libs.androidx.activity)
    api(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //mockito
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)

    //coroutines
    api(libs.kotlinx.coroutines.android)

    //viewModel
    api(libs.androidx.lifecycle.viewmodel.ktx)

    //retrofit2
    api(libs.retrofit)
    api(libs.retrofit.gson.converter)
    api(libs.okhttp3.logging.interceptor)

    //chucker
    debugApi(libs.chucker.library)
    releaseApi(libs.chucker.library.no.op)

    //fragmentKTX
    api(libs.androidx.fragment.ktx)

    //facebook shimmer
    api(libs.facebook.shimmer)

    //room
    api(libs.room.runtime)
    kapt(libs.room.compiler)
    api(libs.room.ktx)

    //dagger2
    api(libs.dagger)
    api(libs.dagger.android)
    kapt(libs.dagger.compiler)
    kapt(libs.dagger.android.processor)

}