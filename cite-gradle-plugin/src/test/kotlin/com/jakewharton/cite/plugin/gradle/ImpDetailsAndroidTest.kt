package com.jakewharton.cite.plugin.gradle

import assertk.assertThat
import assertk.assertions.contains
import java.io.File
import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class ImpDetailsAndroidTest {
	private companion object {
		val fixtureDir = File("src/test/fixtures/imp-details-android")

		@BeforeClass
		@JvmStatic fun first() {
			val gradleRoot = File(fixtureDir, "gradle").also { it.mkdir() }
			File("../gradle").copyRecursively(gradleRoot, true)
		}
	}

	private lateinit var result: BuildResult

	@Before fun before() {
		result = GradleRunner.create()
			.withProjectDir(fixtureDir)
			.withDebug(true) // Run in-process.
			.withArguments(
				"clean",
				"assemble",
				"-PciteVersion=$CiteVersion",
			)
			.forwardOutput()
			.build()
	}

	@Test fun jvm() {
		val classFile = fixtureDir.resolve("build/tmp/kotlin-classes/debug/com/example/cite/ui/MainActivityKt.class")
		val bytecode = classFile.readBytes()
		val bytecodeText = bytecodeToText(bytecode)
		assertThat(bytecodeText)
			.contains("""
				|    LINENUMBER 17 L0
				|    LDC "Hello: main.kt, Greeter, sayHi, 17"
				|    GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
				|    SWAP
				|    INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/Object;)V
				""".trimMargin())
	}
}
