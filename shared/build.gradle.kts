import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("co.touchlab.skie") version "0.9.5"
    kotlin("plugin.serialization") version "1.9.20"
    alias(libs.plugins.sqlDelight)

}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            implementation("io.ktor:ktor-client-core:2.3.4")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.4")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
            implementation("io.ktor:ktor-client-cio:2.3.4")
            implementation("io.insert-koin:koin-core:3.5.3")
            implementation("app.cash.sqldelight:coroutines-extensions:2.0.1")
        }

        androidMain.dependencies {
            implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
            implementation("io.ktor:ktor-client-android:2.3.4")
            implementation("app.cash.sqldelight:android-driver:2.0.1")
        }

        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:2.3.4")
            implementation("app.cash.sqldelight:native-driver:2.0.1")

        }
        commonTest.dependencies {
            implementation("io.ktor:ktor-client-core:2.3.5")
        }
    }
}

android {
    namespace = "com.example.lab1"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
sqldelight {
    databases {
        create(name = "ArticlesDatabase") {
            packageName.set("sqldelight.articles.db")
        }
    }
}