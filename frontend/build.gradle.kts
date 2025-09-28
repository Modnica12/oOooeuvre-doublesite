plugins {
    kotlin("multiplatform") version "2.1.21"
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.21"
    id("org.jetbrains.compose") version "1.8.2"
    kotlin("plugin.serialization") version "2.1.21"
}

group = "dev.oOooeuvre"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {

}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                // Import libraries
                implementation(compose.html.core)
                implementation(compose.runtime)
                implementation("io.ktor:ktor-client-js:3.2.3")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:3.2.3")
                implementation("io.ktor:ktor-client-content-negotiation:3.2.3")
                implementation("io.ktor:ktor-serialization-kotlinx-json:3.2.3")
            }
        }
    }
}