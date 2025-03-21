import com.android.build.api.artifact.SingleArtifact

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.zy.zylibuiandroid"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            androidComponents.onVariants { variant ->
                val versionName = libs.versions.versionname.get()
                variant.artifacts.get(SingleArtifact.AAR).outputFile.set(
                    layout.buildDirectory.file("outputs/aar/${project.name}-${variant.name}-$versionName.aar")
                )
            }
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            androidComponents.onVariants { variant ->
                val versionName = libs.versions.versionname.get()
                variant.artifacts.get(SingleArtifact.AAR).outputFile.set(
                    layout.buildDirectory.file("outputs/aar/${project.name}-${variant.name}-$versionName.aar")
                )
            }
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}