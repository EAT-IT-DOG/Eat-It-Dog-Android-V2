import org.gradle.api.JavaVersion

object ProjectProperties {
    const val VERSION_CODE = 16
    const val VERSION_NAME = "2.3.1"

    const val APPLICATION_ID = "com.stac.eatitdog"
    const val TEST_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    val JAVA_VERSION = JavaVersion.VERSION_1_8

    const val PROGUARD_NAME = "proguard-android.txt"
    const val PROGUARD_FILE = "proguard-rules.pro"
    const val IS_MINIFY_ENABLE = false
    const val APPLICATION_BUILD_TYPE = "release"

    const val MIN_SDK_VERSION = 26
    const val TARGET_SDK_VERSION = 32

    const val JVM_TARGET = "1.8"

    const val KT_LINT = "10.2.0"
    const val COMPILE_SDK_VERSION = 32

    const val ANDROID_VERSION = "7.2.1"
    const val JETBRAINS_KOTLIN = "1.7.0"

    const val PATH_DATA = ":data"
    const val PATH_DOMAIN = ":domain"
    const val PATH_PRESENTATION = ":presentation"
}
