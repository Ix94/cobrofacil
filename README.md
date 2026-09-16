# CobroFácil

App para gestionar préstamos personales (deudores, intereses, pagos parciales). Guarda todo localmente en el dispositivo (`localStorage`), sin backend.

Empaquetada como app standalone de Android con [Capacitor](https://capacitorjs.com/): el HTML/CSS/JS vive dentro del propio `.apk`/`.aab`, no depende de ningún servidor para funcionar.

## Estructura

- `www/index.html` — toda la interfaz y lógica de la app (edítala aquí para cambios de diseño/funcionalidad).
- `capacitor.config.json` — nombre de la app, `appId` del paquete, colores.
- `android/` — proyecto nativo generado por Capacitor. No se edita a mano salvo para configuración avanzada (permisos, firma, etc.).
- `assets/` — fuente del ícono y splash screen (1024x1024). Para cambiarlos, reemplaza estos archivos y corre `npx capacitor-assets generate --android`.
- `.github/workflows/android-build.yml` — compila automáticamente un APK de pruebas (y un AAB firmado, si configuras las llaves) en cada push a `main`.

## Cambiar el `appId` (nombre de paquete)

Se generó `com.cobrofacil.app` como identificador genérico. **Debe decidirse antes de la primera publicación en Play Store** — no se puede cambiar después sin que cuente como una app nueva. Para cambiarlo: edita `applicationId` en `android/app/build.gradle`, `appId` en `capacitor.config.json`, y el paquete en `android/app/src/main/AndroidManifest.xml` y las carpetas `android/app/src/main/java/...`.

## Desarrollo local

Requiere Node.js, JDK 17+ y Android SDK (o Android Studio) instalados.

```bash
npm install
npx cap sync android
cd android
./gradlew assembleDebug     # genera un APK de pruebas sin firmar
```

El APK queda en `android/app/build/outputs/apk/debug/app-debug.apk` — instálalo en tu teléfono para probar (activa "Instalar apps de origen desconocido").

## Compilación automática (CI)

Cada push a `main` dispara `.github/workflows/android-build.yml`, que compila un APK de pruebas descargable desde la pestaña **Actions** del repo (sección "Artifacts" del run).

### Compilar también un AAB firmado, listo para Play Store

Se generó una llave de firma (`cobrofacil-release.keystore`) que se entregó por fuera del repositorio (nunca se sube a git). Para que el workflow además genere el `.aab` firmado automáticamente, agrega estos 4 secrets en **Settings → Secrets and variables → Actions** del repo:

- `ANDROID_KEYSTORE_BASE64`
- `ANDROID_KEYSTORE_PASSWORD`
- `ANDROID_KEY_ALIAS`
- `ANDROID_KEY_PASSWORD`

(Los valores están en el archivo de credenciales que se entregó aparte.)

## Publicar en Google Play

1. Crea una cuenta en [Google Play Console](https://play.google.com/console) (pago único de $25).
2. Sube el `.aab` firmado (de un run de Actions, o generado localmente con `./gradlew bundleRelease`) a una pista de pruebas internas.
3. Completa ficha de la tienda, política de privacidad y el formulario de seguridad de datos.
4. Promueve a producción cuando esté validado.

## Ícono provisional

El ícono actual (círculo azul con "CF" sobre fondo oscuro) es un placeholder. Reemplaza `assets/icon-foreground.png`, `assets/icon-background.png` y `assets/splash.png` con el arte final y vuelve a correr `npx capacitor-assets generate --android`.
