# Mobile App Deployment Guide

This guide explains how to configure and deploy the Math Solver App to Android and iOS app stores.

## 🤖 Android Deployment

### Prerequisites

1. **Google Play Console Account** - Sign up at [play.google.com/console](https://play.google.com/console)
2. **Android Keystore** - For signing release builds

### Step 1: Create Android Keystore

```bash
keytool -genkey -v -keystore release.keystore -alias math-solver \
  -keyalg RSA -keysize 2048 -validity 10000
```

Save the keystore password and key alias - you'll need them.

### Step 2: Configure GitHub Secrets

Add these secrets in your GitHub repo (Settings → Secrets → Actions):

- `KEYSTORE_BASE64` - Base64-encoded keystore file:
  ```bash
  base64 -i release.keystore | pbcopy  # macOS
  base64 -w 0 release.keystore         # Linux
  ```
- `KEYSTORE_PASSWORD` - Keystore password
- `KEY_ALIAS` - Key alias (e.g., "math-solver")
- `KEY_PASSWORD` - Key password

### Step 3: (Optional) Configure Google Play Auto-Deploy

For automatic deployment to Google Play:

1. Create a service account in Google Cloud Console
2. Grant access in Google Play Console (Setup → API access)
3. Download JSON key file
4. Add to GitHub Secrets:
   - `PLAY_SERVICE_ACCOUNT_JSON` - Contents of the JSON file

### Manual Deployment

1. Download the `.aab` file from GitHub Actions artifacts
2. Upload to Google Play Console → Release → Production/Internal testing
3. Complete store listing and submit for review

---

## 🍎 iOS Deployment

### Prerequisites

1. **Apple Developer Account** ($99/year) - [developer.apple.com](https://developer.apple.com)
2. **Xcode Project** - Currently needs to be set up
3. **Signing Certificate & Provisioning Profile**

### Step 1: Set Up iOS App with Xcode

The current `iosApp/` directory only has a Swift file. You need to:

1. Open Xcode and create a new iOS App project in `iosApp/`
2. Configure the shared Kotlin framework:
   ```swift
   import shared
   ```
3. Set up proper app structure with storyboards/SwiftUI

Or use Kotlin Multiplatform Wizard to generate proper iOS app structure.

### Step 2: Create Signing Certificate

1. Open Xcode → Preferences → Accounts
2. Add your Apple ID
3. Manage Certificates → Create "Apple Distribution" certificate
4. Export certificate as `.p12` file with password

### Step 3: Create Provisioning Profile

1. Go to [developer.apple.com/account](https://developer.apple.com/account)
2. Certificates, IDs & Profiles → Profiles
3. Create "App Store" provisioning profile
4. Download the `.mobileprovision` file

### Step 4: Configure GitHub Secrets

Add these secrets:

- `IOS_CERTIFICATE_BASE64` - Base64-encoded `.p12` certificate:
  ```bash
  base64 -i certificate.p12 | pbcopy
  ```
- `IOS_CERTIFICATE_PASSWORD` - Certificate password
- `IOS_PROVISION_PROFILE_BASE64` - Base64-encoded provisioning profile:
  ```bash
  base64 -i profile.mobileprovision | pbcopy
  ```

### Step 5: Create ExportOptions.plist

Create `iosApp/ExportOptions.plist`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
    <key>method</key>
    <string>app-store</string>
    <key>teamID</key>
    <string>YOUR_TEAM_ID</string>
    <key>uploadSymbols</key>
    <true/>
    <key>compileBitcode</key>
    <false/>
</dict>
</plist>
```

### Step 6: (Optional) Configure TestFlight Auto-Upload

For automatic TestFlight deployment:

1. Create App Store Connect API Key (Users and Access → Keys)
2. Download API Key file
3. Add to GitHub Secrets:
   - `APPLE_API_KEY_ID` - Key ID
   - `APPLE_API_ISSUER_ID` - Issuer ID  
   - `APPLE_API_KEY_BASE64` - Base64-encoded `.p8` file

### Manual Deployment

1. Download `.ipa` from GitHub Actions artifacts
2. Upload via Xcode → Window → Organizer → Archives
3. Or use Transporter app from Mac App Store
4. Submit for review in App Store Connect

---

## 📦 Sideloading (Development/Testing)

### Android APK Sideloading

1. Download debug/release APK from GitHub Actions artifacts
2. Enable "Install unknown apps" on Android device
3. Transfer APK to device and install
4. Or use ADB:
   ```bash
   adb install app-debug.apk
   ```

### iOS Sideloading

Options for non-jailbroken devices:

1. **TestFlight** (recommended) - Free with Apple Developer account
2. **AltStore** - Free, requires computer connection weekly
3. **Direct install** - Requires Apple Developer account and Xcode:
   ```bash
   # Build and install to connected device
   xcodebuild -project iosApp/iosApp.xcodeproj \
     -scheme iosApp \
     -destination 'platform=iOS,id=DEVICE_UDID' \
     clean install
   ```

---

## 🔄 Workflow Triggers

Both workflows run on:

- ✅ **Tags** (`v*`) - Full release with signing
- ✅ **Main branch pushes** - Debug builds only
- ✅ **Manual trigger** - Via GitHub Actions UI
- ✅ **Path filters** - Only when app code changes

---

## 💰 Cost Optimization

The workflows already include:

- ✅ Path-based triggers (only build when relevant files change)
- ✅ Timeout limits (30 min max)
- ✅ Smart Gradle caching
- ✅ `--no-daemon` flag

---

## 📝 Version Management

Update version in `androidApp/build.gradle.kts`:

```kotlin
defaultConfig {
    versionCode = 2  // Increment for each release
    versionName = "1.1.0"
}
```

For iOS, update in Xcode project settings.

---

## 🚀 Release Checklist

- [ ] Update version numbers
- [ ] Test builds locally
- [ ] Create and push git tag: `git tag v1.0.0 && git push --tags`
- [ ] Monitor GitHub Actions workflow
- [ ] Download artifacts from successful build
- [ ] Test signed APK/IPA on real devices
- [ ] Upload to stores or distribute for sideloading
- [ ] Update release notes on GitHub

---

## 🆘 Troubleshooting

**Android build fails:**
- Verify keystore secrets are correctly base64-encoded
- Check that passwords match
- Ensure `applicationId` in `build.gradle.kts` matches Play Console

**iOS build fails:**
- Ensure Xcode project exists in `iosApp/`
- Verify certificate and provisioning profile match bundle ID
- Check that certificate is "Apple Distribution" type
- Ensure provisioning profile is "App Store" type

**Workflow doesn't trigger:**
- Check path filters match your changed files
- Verify branch name is exactly "main"
- For tags, use format `v1.0.0` (starts with `v`)

---

**Need help?** Open an issue or check [Contributing Guide](../CONTRIBUTING.md)
