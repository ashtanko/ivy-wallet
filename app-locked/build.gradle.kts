apply<com.ivy.buildsrc.IvyComposePlugin>()

plugins {
    `android-library`
    `kotlin-android`
}

dependencies {
    implementation(project(":ui-common"))
    implementation(project(":app-base"))
    implementation(project(":ui-components-old"))
    implementation(project(":data-model"))
}