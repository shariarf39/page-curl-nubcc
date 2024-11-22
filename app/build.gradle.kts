plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.nubcc.pagecurlnub"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nubcc.pagecurlnub"
        minSdk = 24
        targetSdk = 34
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
dependencies {
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.activity:activity:1.2.3")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation("com.github.denzcoskun:ImageSlideshow:0.1.0")
    implementation("com.github.fornewid:neumorphism:0.2.1")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("com.airbnb.android:lottie:4.2.0")

}
