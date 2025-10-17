# Build Android APK in a container
FROM ubuntu:22.04

ARG DEBIAN_FRONTEND=noninteractive
RUN apt-get update && apt-get install -y --no-install-recommends     openjdk-17-jdk curl unzip git ca-certificates   && rm -rf /var/lib/apt/lists/*

ENV ANDROID_SDK_ROOT=/opt/android-sdk
ENV ANDROID_HOME=/opt/android-sdk
ENV PATH="$ANDROID_SDK_ROOT/cmdline-tools/latest/bin:$ANDROID_SDK_ROOT/platform-tools:$ANDROID_SDK_ROOT/build-tools/34.0.0:$PATH"

RUN mkdir -p $ANDROID_SDK_ROOT  && curl -sSL https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip -o /tmp/cmdline-tools.zip  && mkdir -p $ANDROID_SDK_ROOT/cmdline-tools  && unzip -q /tmp/cmdline-tools.zip -d $ANDROID_SDK_ROOT/cmdline-tools  && mkdir -p $ANDROID_SDK_ROOT/cmdline-tools/latest  && mv $ANDROID_SDK_ROOT/cmdline-tools/cmdline-tools/* $ANDROID_SDK_ROOT/cmdline-tools/latest/  && yes | sdkmanager --sdk_root=$ANDROID_SDK_ROOT --licenses >/dev/null  && sdkmanager --sdk_root=$ANDROID_SDK_ROOT "platform-tools" "build-tools;34.0.0" "platforms;android-34"

WORKDIR /app
COPY . /app

# try wrapper, fallback to system gradle (installed below if needed)
RUN chmod +x ./gradlew || true  || (apt-get update && apt-get install -y gradle)

CMD ["/bin/bash", "-lc", "([ -f ./gradlew ] && ./gradlew --no-daemon assembleDebug) || (gradle --no-daemon assembleDebug) && ls -l app/build/outputs/apk/debug"]
