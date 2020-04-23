
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version "1.3.61"
}

repositories {
    mavenCentral()
}

kotlin {

  sourceSets {
    commonTest {
      dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }
  }

  ios {
    binaries {
      framework {
        baseName = "KotlinNativeFramework"
      }
    }
  }

  val testBinary = kotlin.targets.getByName<KotlinNativeTarget>("iosX64").binaries.getTest("DEBUG")
  val runIosTests by project.tasks.creating(SimulatorTestsTask::class) {
    dependsOn(testBinary.linkTask)
    testExecutable.set(testBinary.outputFile)
    simulatorId.set("E6A910F0-F569-47D4-9F49-165676A35E0A")
  }

  project.tasks["check"].dependsOn(runIosTests)
}

tasks.withType<Wrapper> {
  gradleVersion = "5.3.1"
  distributionType = Wrapper.DistributionType.ALL
}
