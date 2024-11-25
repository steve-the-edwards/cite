package com.example.cite

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jakewharton.cite.__FILE__
import com.jakewharton.cite.__LINE__
import com.jakewharton.cite.__MEMBER__
import com.jakewharton.cite.__TYPE__

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
	@Test
	fun useAppContext() {
		// Context of the app under test.
		val appContext = InstrumentationRegistry.getInstrumentation().targetContext
		assertEquals("com.example.cite", appContext.packageName)
	}

	@Test
	fun testCiteReplacement() {
		assertEquals(
			"$__FILE__, $__TYPE__, $__MEMBER__",
			"ExampleInstrumentedTest.kt, ExampleInstrumentedTest, testCiteReplacement",
		)
	}
}
