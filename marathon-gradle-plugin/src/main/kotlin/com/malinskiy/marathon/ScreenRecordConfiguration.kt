package com.malinskiy.marathon

import com.malinskiy.marathon.device.DeviceFeature
import groovy.lang.Closure
import java.util.concurrent.TimeUnit
import com.malinskiy.marathon.android.ScreenRecordConfiguration as AndroidScreenRecordConfiguration
import com.malinskiy.marathon.android.ScreenshotConfiguration as AndroidScreenshotConfiguration
import com.malinskiy.marathon.android.VideoConfiguration as AndroidVideoConfiguration

open class ScreenRecordConfiguration {

    var preferableRecorderType: String? = null
    var videoConfiguration: VideoConfiguration = VideoConfiguration()
    var screenshotConfiguration: ScreenshotConfiguration = ScreenshotConfiguration()

    // Groovy
    fun videoConfiguration(closure: Closure<*>) {
        videoConfiguration = VideoConfiguration()
        closure.delegate = videoConfiguration
        closure.call()
    }

    fun screenshotConfiguration(closure: Closure<*>) {
        screenshotConfiguration = ScreenshotConfiguration()
        closure.delegate = screenshotConfiguration
        closure.call()
    }

    // Kotlin

    fun videConfiguration(block: VideoConfiguration.() -> Unit) {
        videoConfiguration.also(block)
    }

    fun screenshotConfiguration(block: ScreenshotConfiguration.() -> Unit) {
        screenshotConfiguration.also(block)
    }
}

class ScreenshotConfiguration {
    var enabled: Boolean = true
    var width: Int = 720
    var height: Int = 1280
    var delayMs: Int = 500
}

open class VideoConfiguration {
    var enabled: Boolean = true
    var width: Int = 720
    var height: Int = 1280
    var bitrateMbps: Int = 1
    var timeLimit: Long = 180
    var timeLimitUnits: TimeUnit = TimeUnit.SECONDS
}

fun ScreenRecordConfiguration.toStrategy(): AndroidScreenRecordConfiguration {
    return AndroidScreenRecordConfiguration(
        preferableRecorderType = DeviceFeature.fromString(preferableRecorderType),
        videoConfiguration = videoConfiguration.toConfiguration(),
        screenshotConfiguration = screenshotConfiguration.toConfiguration()
    )
}

fun VideoConfiguration.toConfiguration(): AndroidVideoConfiguration {
    return AndroidVideoConfiguration(
        enabled = enabled,
        width = width,
        height = height,
        bitrateMbps = bitrateMbps,
        timeLimit = timeLimit,
        timeLimitUnits = timeLimitUnits
    )
}

fun ScreenshotConfiguration.toConfiguration(): AndroidScreenshotConfiguration {
    return AndroidScreenshotConfiguration(
        enabled = enabled,
        width = width,
        height = height,
        delayMs = delayMs
    )
}
