// Add compose gradle plugin
plugins {
    //https://github.com/JetBrains/compose-jb
    kotlin("multiplatform") version "1.5.31"
    id("org.jetbrains.compose") version "1.0.0-alpha4-build362"
}

group = "com.baldr"
version = "1.0-SNAPSHOT"

// Add maven repositories
repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}


// Enable JS(IR) target and add dependencies
kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }



    sourceSets {
        val ktorVersion = "1.6.4"

        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")

                implementation(compose.web.core)
                implementation(compose.runtime)
                implementation("io.ktor:ktor-client-js:$ktorVersion")
            }
        }

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")

                //https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

                //ktor
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                //ktor CIO is broken... can't build
                //                implementation("io.ktor:ktor-client-cio:$ktorVersion")

                //ktor: https://ktor.io/docs/json.html#configure_serializer
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")

                //ktor: https://ktor.io/docs/client-logging.html
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                //UUID: https://search.maven.org/artifact/com.benasher44/uuid
                implementation("com.benasher44:uuid:0.3.1")
            }
        }
    }
}

rootProject.plugins.withType(org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin::class.java) {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().versions.webpackDevServer.version =
        "4.0.0-rc.0"
}