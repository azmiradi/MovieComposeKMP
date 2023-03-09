plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(npm("path-browserify", "^1.0.1"))
                implementation(npm("os-browserify", "^0.3.0"))
                implementation (npm("sql.js", "1.6.2"))
                implementation (devNpm("copy-webpack-plugin", "9.1.0"))
            }
        }
    }
}

compose.experimental {
    web.application {}
}

