plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
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
        release {
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

    configurations {
        create("coreTestImplementation") {
            extendsFrom(configurations["testImplementation"])
        }
        create("coreAndroidTestImplementation") {
            extendsFrom(configurations["androidTestImplementation"])
        }
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

    //glide
    api(libs.glide)

    //facebook shimmer
    api(libs.facebook.shimmer)
}