//import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.baseName
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
// xcframework setup
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    // xcframework setup
    kotlin("multiplatform")

    id("com.android.application")
    id("com.google.gms.google-services")
    kotlin("native.cocoapods")

//    alias(libs.plugins.kotlinMultiplatform)
//    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    @Suppress("DEPRECATION")
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    // xcframework setup
    val xcframeworkName = "KmmFirebaseAuth"
    val xcframework = XCFramework(xcframeworkName)


    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = xcframeworkName

            binaryOption("bundleId", "com.roshan.kmmfirebaseauth")
            xcframework.add(this)

            isStatic = true
        }
    }

    cocoapods {
        version = "1.0.0"
        summary = "Firebase bridge"
        homepage = "https://github.com/developerroshan09/kmm-firebase-auth.git"
        ios.deploymentTarget = "15.0" // Ensure matching with Podfile
        podfile = project.file("../iosApp/Podfile")

        pod("FirebaseCore")
        pod("FirebaseAuth")

        framework {
            baseName = "ComposeApp"
            // Fixes the bundle ID warning
            freeCompilerArgs += listOf("-Xbinary=bundleId=com.example.auth.kmm-firebase-auth.framework")
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

// Register XCFramework assembly task
tasks.register("assembleXCFramework") {
    dependsOn("assembleKmmFirebaseAuthXCFramework")
}

android {
    namespace = "com.example.auth.kmm_firebase_auth"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.auth.kmm_firebase_auth"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-auth-ktx")

    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")


    // Add the dependencies for any other desired Firebase products
    // https://firebase.google.com/docs/android/setup#available-libraries
    debugImplementation(compose.uiTooling)
}

apply(plugin = "com.google.gms.google-services")
