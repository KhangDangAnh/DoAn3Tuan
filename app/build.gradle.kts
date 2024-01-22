plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("D:\\Android Programer\\DoAn_3Tuan\\DoAn3Tuan\\app\\huh.jks")
            keyAlias = "key0"
            storePassword = "123456"
            keyPassword = "123456"
        }
    }
    namespace = "com.example.doan_3tuan"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.doan_3tuan"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("io.coil-kt:coil:2.5.0")

    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("com.google.firebase:firebase-firestore:24.9.1")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))

    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
//
//    implementation("androidx.compose.material:material-icons-extended-android:1.5.4")
    implementation("androidx.navigation:navigation-compose:2.7.4")
//    implementation("androidx.room:room-compiler:2.6.1")
//
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
//
//    implementation("io.ktor:ktor-client-cio:2.3.6")
//    implementation("io.ktor:ktor-client-android:2.3.6")
//    implementation("com.github.ivanisidrowu.KtRssReader:kotlin:v2.2.1")
//
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.0-rc01")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0-alpha01")

    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.firebase:firebase-firestore:24.10.1")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.navigation:navigation-compose:2.7.6")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    //implementation("io.coil-kt:coil-compose:2.2.2")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    implementation("io.ktor:ktor-client-cio:2.3.6")
    implementation("io.ktor:ktor-client-android:2.3.6")
    implementation("com.github.ivanisidrowu.KtRssReader:kotlin:v2.2.1")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("com.google.firebase:firebase-database-ktx:20.3.0")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("com.google.code.gson:gson:2.9.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("com.google.android.exoplayer:exoplayer:2.18.0")
}