# re-sourcer [![license](https://img.shields.io/github/license/mashape/apistatus.svg) ](LICENSE) [![jitpack](https://jitpack.io/v/nkosmos/re-sourcer.svg)](https://jitpack.io/#nkosmos/re-sourcer)

Resource loading library used by our launcher and Rupture Client.

## What is this?
re-sourcer is a language/asset loading library.

This library is used by our launcher, Rupture Client, and Stellair

## Import
You can import it using Maven or Gradle: (be sure to change `VERSION` to whatever version you want, you can also set it to `master-SNAPSHOT` for the latest)

Gradle:
```groovy
dependencies {
    compile 'com.github.nkosmos:re-sourcer:VERSION'
}

repositories {
    maven {
        url = 'https://jitpack.io'
    }
}
```

Maven: (why are you using maven...)
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.nkosmos</groupId>
    <artifactId>re-sourcer</artifactId>
    <version>VERSION</version>
</dependency>
```