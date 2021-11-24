plugins {
    java
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.30")
    implementation("org.springframework.boot:spring-boot-starter:2.3.3.RELEASE")
    implementation("io.netty:netty-all:4.1.51.Final")
    implementation("org.jpos:jpos:2.1.4")
    implementation("commons-io:commons-io:2.7")
    implementation("org.bouncycastle:bcpkix-jdk15on:1.66")
    implementation("io.projectreactor:reactor-core:3.3.9.RELEASE")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.2")
    implementation("io.projectreactor.netty:reactor-netty:0.9.11.RELEASE")
    implementation("org.apache.commons:commons-lang3:3.11")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.1")
    implementation("org.springframework:spring-web:5.2.3.RELEASE")
    implementation("org.apache.maven.plugins:maven-compiler-plugin:3.8.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.4.30")
}

group = "ru.stepanov"
version = "1.0-SNAPSHOT"
description = "kotlin_awesome"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
