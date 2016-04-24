# Redex Gradle Plugin


## Prerequisite

- [redex](https://github.com/facebook/redex)


## Usage

This library has two commands.


### assembleRedex${variant.name}

For example, application variant is debug.

First, run assembleDebug, create a debug.apk. Finally, run `redex debug.apk -o redex-debug.apk`, create a redex-debug.apk.


### installRedex${variant.name}

For example, application variant is release.

First, run assembleRelease, create a release.apk. Second, run `redex release.apk -o redex-release.apk --sign ...`, create a redex-release.apk.
Finally, run `adb install redex-release.apk`, install redex-release.apk on connected devices.


## customize

As necessary edits your build.gradle.

```gradle
redex {
    storePath "your keystore"
    storePassword "your storePassword"
    keyAlias "your keyAlias"
    keyPass "your keyPass"
}
```



## License

Copyright (C) 2016 Sato Shun

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
