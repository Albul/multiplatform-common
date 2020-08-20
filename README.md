# Multiplatform common

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) 
[ ![Download](https://api.bintray.com/packages/olekdia/olekdia/multiplatform-common/images/download.svg?version=0.5.23) ](https://bintray.com/olekdia/olekdia/multiplatform-common/0.5.23/link)

---

This library contains kotlin multiplatform extensions.
It is very thin library that has no dependency despite kotlin stdlib.

### Setup

To use in multiplatform project add:

```gradle
dependencies {
  ...
  implementation("com.olekdia:multiplatform-common:0.5.23")
}
```

To use in platform specific projects include one of the following:
```gradle
dependencies {
  implementation("com.olekdia:multiplatform-common-jvm:0.5.23")
  implementation("com.olekdia:multiplatform-common-js:0.5.23")
  implementation("com.olekdia:multiplatform-common-linuxX64:0.5.23")
  implementation("com.olekdia:multiplatform-common-linuxArm64:0.5.23")
}
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

ifNotNullAnd(
    model, model?.isActive
) { model
    model.call()
}
```

##### MathExt
* Random generators
```kotlin
// All functions have inclusive ranges
val randInt = getRandom(1, 10)
val randLong = getRandom(1L, 10L) 
val randFloat = getRandom(1f, 10.5f)
// ....
val randBoolean: Boolean = getRandom()
```
