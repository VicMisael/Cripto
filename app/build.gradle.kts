import com.google.protobuf.gradle.*
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.protobuf") version("0.9.4")
}

android {
    namespace = "com.example.cripto"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cripto"
        minSdk = 26
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
    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation(libs.protobuf.javalite)
    implementation(libs.retrofit)
    implementation(libs.converter.protobuf)
    implementation(libs.converter.simplexml)
    implementation(libs.converter.gson)
    implementation(libs.converter.jaxb)


//    implementation("com.squareup.retrofit2:converter-protobuf:2.11.0")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.27.3"
    }
    generateProtoTasks {
        all().forEach {
            it.builtins {
                create("java") {
                    option("lite")
                }
            }

//            it.plugins {
//                create("grpc") {
//                    option("lite")
//                }
//            }
        }
    }
}


//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//}
