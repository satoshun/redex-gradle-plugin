# Gradle Plugin for Redex


## Prerequisite

- [Redex](https://github.com/facebook/redex)



## Usage

This library has two commands.


### assembleRedex${variant.name}

Assembles a $variant builds and redex.


### installRedex${variant.name}

Installs a $variant builds.


## settings

As necessary edits your build.gradle.

```gradle
buildscript {
    dependencies {
        classpath "com.github.satoshun.redex.gradle:redex-gradle-plugin:0.2.0"
    }
}

apply plugin: 'redex'


redex {
    storePath "your keystore"
    storePassword "your storePassword"
    keyAlias "your keyAlias"
    keyPass "your keyPass"
}
```


## TODO

- corresponds more Redex options.
- corresponds vairants


## License

Copyright (C) 2016 Sato Shun

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
