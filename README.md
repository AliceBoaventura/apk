RedEnvelopeDemo - Project

------------------------

This is a ready-to-import Android Studio project (Java) that simulates "red envelopes".

How to build (in your machine):

1. Install Android Studio and Android SDK (command-line tools).
2. Open Android Studio -> Import Project and choose this folder.
3. Let Android Studio sync and download Gradle/SDK components if needed.
4. Build -> Build Bundle(s) / APK(s) -> Build APK(s). APK will be at app/build/outputs/apk/debug/app-debug.apk

To install on a device via adb:

    adb install -r app/build/outputs/apk/debug/app-debug.apk

If you prefer to build from command line, ensure you have Gradle and Android SDK set up, then run (from project root):

    ./gradlew assembleDebug

If running on Windows, use gradlew.bat instead.
