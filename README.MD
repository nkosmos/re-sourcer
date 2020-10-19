# re-sourcer ![GitHub release (latest by date)](https://img.shields.io/github/v/release/nKosmos/re-sourcer?style=flat-square)

Resource loading library used by our launcher and Rupture Client.

## What is this?
re-sourcer is a language/asset loading library.

This library is used by our launcher and Rupture Client.

## Import
You can import it using Maven or Gradle: (be sure to change `VERSION` to whatever version you want)

Gradle:
```gradle
dependencies {
    compile 'fr.nkosmos:re-sourcer:VERSION'
}

repositories {
    maven {
        url = "https://nkosmos.github.io/maven/"
    }
}
```

Maven: (why... stop using maven... please...)
```xml
<dependency>
    <groupId>fr.nkosmos</groupId>
    <artifactId>re-sourcer</artifactId>
    <version>VERSION</version>
</dependency>
<repository>
    <id>nkosmos</id>
    <name>nkosmos</name>
    <url>https://nkosmos.github.io/maven</url>
</repository>
```

## License
This project is under the [GNU LGPL v3.0](https://github.com/nkosmos/re-sourcer/blob/master/LICENSE) license.