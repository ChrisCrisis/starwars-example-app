package dev.glimmr.starwarssample.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class MainCoroutineRule(private val dispatcher: TestDispatcher = StandardTestDispatcher()) :
    TestWatcher() {

    val testScope: TestScope = TestScope(dispatcher)

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        testScope.cancel()
        Dispatchers.resetMain()
    }

    fun runTest(testBody: suspend TestScope.() -> Unit) = runTest(
        context = dispatcher,
        dispatchTimeoutMs = DEFAULT_DISPATCH_TIMEOUT_MS,
        testBody = testBody
    )
}

private const val DEFAULT_DISPATCH_TIMEOUT_MS = 60_000L