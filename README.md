# Multiplatform common

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) 
[ ![Download](https://api.bintray.com/packages/olekdia/olekdia/multiplatform-common/images/download.svg?version=1.0.0) ](https://bintray.com/olekdia/olekdia/multiplatform-common/1.0.0/link)

---

This library contains kotlin multiplatform extensions.
It is very thin library that has no dependency despite kotlin stdlib.

### Setup

To use in multiplatform project add:

```gradle
dependencies {
  ...
  implementation("com.olekdia:multiplatform-common:1.0.0")
}
```

To use in platform specific projects include one of the following:
```gradle
dependencies {
  implementation("com.olekdia:multiplatform-common-jvm:1.0.0")
  implementation("com.olekdia:multiplatform-common-js:1.0.0")
  implementation("com.olekdia:multiplatform-common-native:1.0.0")
}
```

Make sure your Gradle vesion is 5.3+, and that you have metadata enabled in settings.gradle

```gradle
enableFeaturePreview("GRADLE_METADATA")
```

### Extensions

##### BitExt
Contains extension functions to work with bits:

##### CommonExt
Contains inline nullability functions:
```kotlin
ifNotNull(
    aField, bField, cField
) { a, b, c ->
    a.call()
    // Do something with a, b, c
}
```
