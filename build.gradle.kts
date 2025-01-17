// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application").version("8.1.2").apply(false)
    id("com.android.library").version("8.1.2").apply(false)
    kotlin("android").version("1.9.10").apply(false)
    id("io.gitlab.arturbosch.detekt").version("1.23.1").apply(false)
    id("com.vanniktech.maven.publish").version("0.25.3")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
