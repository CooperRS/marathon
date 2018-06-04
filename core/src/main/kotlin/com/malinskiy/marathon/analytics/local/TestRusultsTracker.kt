package com.malinskiy.marathon.analytics.local

import com.malinskiy.marathon.analytics.NoOpTracker
import com.malinskiy.marathon.device.Device
import com.malinskiy.marathon.device.DevicePoolId
import com.malinskiy.marathon.execution.TestResult
import com.malinskiy.marathon.report.internal.TestResultReporter

class TestRusultsTracker(private val testResultReporter: TestResultReporter) : NoOpTracker() {
    override fun trackTestResult(poolId: DevicePoolId, device: Device, testResult: TestResult) {
        testResultReporter.testFinished(poolId, device, testResult)
    }
}