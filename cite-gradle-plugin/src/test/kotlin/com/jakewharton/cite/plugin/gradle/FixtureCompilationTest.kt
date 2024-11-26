package com.jakewharton.cite.plugin.gradle

import java.io.File
import org.gradle.testkit.runner.GradleRunner
import org.junit.Test

class FixtureCompilationTest {
	@Test fun android() {
		createRunner("android", "assembleAndroidTest").build()
	}

	@Test fun jvm() {
		createRunner("jvm").build()
	}

	private fun createRunner(fixtureName: String, vararg otherTasks: String): GradleRunner {
		val fixtureDir = File("src/test/fixtures", fixtureName)
		val gradleRoot = File(fixtureDir, "gradle").also { it.mkdir() }
		File("../gradle/wrapper").copyRecursively(File(gradleRoot, "wrapper"), true)
		return GradleRunner.create()
			.withProjectDir(fixtureDir)
			.withDebug(true) // Run in-process
			.withArguments(
				"clean",
				"build",
				*otherTasks,
				"--stacktrace",
				"--continue",
				"-PciteVersion=$CiteVersion",
			)
			.forwardOutput()
	}
}
