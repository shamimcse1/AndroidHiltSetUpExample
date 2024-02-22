import org.codehaus.groovy.runtime.ArrayTypeUtils.dimension

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.hiltsetup"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hiltsetup"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
//
//    flavorDimensions.add("type")
//
//    productFlavors {
//        create("dev") {
//            setDimension("type")
//            applicationId = "com.example.hiltsetup.dev"
//        }
//
//        create("pro") {
//
//            setDimension("type")
//            applicationId = "com.example.hiltsetup.pro"
//        }
//    }

    flavorDimensions += "version"
    productFlavors {
        create("demo") {
            // Assigns this product flavor to the "version" flavor dimension.
            // If you are using only one dimension, this property is optional,
            // and the plugin automatically assigns all the module's flavors to
            // that dimension.
            dimension = "version"
            applicationIdSuffix = ".demo"
            versionNameSuffix = "-demo"
            applicationId = "com.example.hiltsetup.dev"
        }
        create("full") {
            dimension = "version"
            applicationIdSuffix = ".full"
            versionNameSuffix = "-full"
            applicationId = "com.example.hiltsetup.pro"
        }
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")


    // For instrumentation tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.50")
    androidTestAnnotationProcessor("com.google.dagger:hilt-compiler:2.50")

    // For local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.50")
    testAnnotationProcessor("com.google.dagger:hilt-compiler:2.50")

    // Retrofit & OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.6")

    // RxJava 2
    implementation("io.reactivex.rxjava2:rxjava:2.2.18")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    //RxJava2 with Retrofit
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.13.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.2")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
}
kapt {
    correctErrorTypes = true
}