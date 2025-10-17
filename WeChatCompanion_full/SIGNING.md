# Assinar APK (Debug e Release)

> Requer: Java (JDK 8+), Android SDK Build-Tools (apksigner), `keytool` e `zipalign` no PATH.

## A) APK de Debug (automático pelo Gradle)
```bash
./gradlew assembleDebug
# APK: app/build/outputs/apk/debug/app-debug.apk
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

## B) APK de Release Assinado Manualmente

### 1) Gerar um keystore
```bash
keytool -genkeypair -v -keystore my-release-key.jks -storetype JKS -keyalg RSA -keysize 2048 -validity 3650 -alias release
# Guarde a senha do keystore e do alias.
```

### 2) Build do APK sem assinatura
```bash
./gradlew assembleRelease
# APK não alinhado/assinado: app/build/outputs/apk/release/app-release-unsigned.apk
```

### 3) Alinhar com zipalign
```bash
# Ajuste o caminho do build-tools conforme o seu SDK, ex: $ANDROID_HOME/build-tools/34.0.0/zipalign
zipalign -f -v 4 app/build/outputs/apk/release/app-release-unsigned.apk app-release-aligned.apk
```

### 4) Assinar com apksigner
```bash
# Ajuste o caminho do apksigner (build-tools)
apksigner sign --ks my-release-key.jks --ks-key-alias release app-release-aligned.apk
# Verificar assinatura
apksigner verify --verbose app-release-aligned.apk
```

### 5) Instalar
```bash
adb install -r app-release-aligned.apk
```

## Observações
- Para CI, use variáveis de ambiente: `KEYSTORE_PATH`, `KEYSTORE_PASS`, `KEY_ALIAS`, `KEY_PASS`.
- No Android Studio: Build > Generate Signed Bundle/APK... (GUI).
