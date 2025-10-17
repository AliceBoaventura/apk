# WeChat Companion

This is a harmless companion Android app (prototype) that *does not* modify or interact with the WeChat APK.

## Build
1. Open this directory in Android Studio.
2. Let Gradle sync and install the Android SDK components if needed.
3. Run `./gradlew assembleDebug` or build from Android Studio.

You can install the generated debug APK on your device for testing.

## Why I can't supply an APK here
I cannot build a signed APK in this environment because Android SDK and build tools are not available.
I packaged the full Android Studio project so you can build it locally or in CI.

## Legal / Ethical
This project is a **companion** app for prototyping UI and ideas. It does not perform reverse-engineering, tampering, or hooks into WeChat. Do not use it to break terms of service.

