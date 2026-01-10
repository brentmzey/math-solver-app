# Quick Start Guide

Get up and running with the Math Solver App in minutes!

## Prerequisites

Before you begin, ensure you have the following installed:

### Required for All Platforms
- **JDK 17 or higher** - [Download here](https://adoptium.net/)
- **Git** - [Download here](https://git-scm.com/)

### Platform-Specific Requirements

#### Desktop Development
- JDK 17+ (already listed above)

#### Android Development
- **Android Studio** (latest stable) - [Download here](https://developer.android.com/studio)
- **Android SDK** (installed via Android Studio)

#### iOS Development (macOS only)
- **Xcode** (latest stable) - [Download from App Store](https://apps.apple.com/us/app/xcode/id497799835)
- **CocoaPods** - Install with: `sudo gem install cocoapods`

#### Web Development
- Node.js is automatically downloaded by Gradle when building Web target

---

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/math-solver-app.git
cd math-solver-app
```

### 2. Verify Prerequisites

```bash
# Check Java version (must be 17+)
java -version

# Check Gradle wrapper
./gradlew --version
```

---

## Running the App

### 🖥️ Desktop

```bash
./gradlew :desktopApp:run
```

The desktop app will launch with full symbolic math capabilities powered by Symja.

**Features Available:**
- ✅ Full symbolic computation
- ✅ Discrete mathematics
- ✅ Real analysis
- ✅ Proof verification
- ✅ Offline & online modes

---

### 📱 Android

#### Option 1: Using Android Studio (Recommended)
1. Open Android Studio
2. Select "Open an Existing Project"
3. Navigate to the `math-solver-app` folder
4. Wait for Gradle sync to complete
5. Select the `androidApp` run configuration
6. Click the green "Run" button or press `Shift + F10`

#### Option 2: Command Line
```bash
# Build the APK
./gradlew :androidApp:assembleDebug

# Install on connected device/emulator
./gradlew :androidApp:installDebug
```

**Features Available:**
- ✅ Basic arithmetic (exp4j)
- ✅ Word problems
- ✅ Offline & online modes

---

### 🍎 iOS

#### Using Xcode
1. Open the project in Xcode:
   ```bash
   open iosApp/iosApp.xcodeproj
   ```
   
2. Select a simulator or device
3. Press `Cmd + R` to build and run

#### Using Android Studio
1. Open Android Studio
2. Install the "Kotlin Multiplatform Mobile" plugin
3. Select iOS simulator
4. Run the `iosApp` configuration

**Features Available:**
- ✅ Basic arithmetic
- ✅ Word problems
- ✅ Offline mode (limited online support)

---

### 🌐 Web

```bash
# Development mode with hot reload
./gradlew :shared:wasmJsBrowserDevelopmentRun
```

The app will be available at `http://localhost:8080`

**Features Available:**
- ✅ Basic arithmetic
- ✅ Word problems
- ✅ Offline & online modes
- ✅ No installation required

#### Building for Production
```bash
./gradlew :shared:wasmJsBrowserProductionWebpack
```

Production files will be in `shared/build/dist/wasmJs/productionExecutable/`

---

## Using the App

### Basic Usage

1. **Choose Your Mode**:
   - Toggle "Online Mode (API)" switch for cloud computation
   - Leave OFF for local/offline computation

2. **Solve Math Expressions**:
   ```
   Desktop: Integrate[Sin[x], x]
   Mobile: 2 + 2 * 3
   ```

3. **Verify Proofs** (Desktop only):
   ```
   That the empty set is a subset of all sets
   Bernoulli's Inequality
   ```

4. **Solve Word Problems**:
   ```
   Basic: If I have 2 apples and buy 3 more, how many do I have?
   Discrete Math: How many ways can we choose 3 items from 10?
   Real Analysis: Find the limit of 1/n as n approaches infinity
   ```

### Configuring Online Mode

To use cloud APIs for complex problems:

1. Toggle "Online Mode (API)" to ON
2. Enter your API endpoint (optional)
3. Implement the API client in platform-specific `SolverConfig` files

See [IMPLEMENTATION_SUMMARY.md](IMPLEMENTATION_SUMMARY.md#configuring-online-mode) for implementation details.

---

## Troubleshooting

### Common Issues

#### Build Fails with "Could not find JDK 17"
**Solution**: Ensure JAVA_HOME is set correctly:
```bash
# macOS/Linux
export JAVA_HOME=/path/to/jdk-17
# or use jEnv: https://www.jenv.be/

# Windows
setx JAVA_HOME "C:\Path\To\JDK-17"
```

#### Android Emulator Not Starting
**Solution**: 
1. Open Android Studio
2. Go to Tools → AVD Manager
3. Create a new virtual device if needed
4. Ensure hardware acceleration is enabled

#### iOS Build Fails
**Solution**:
```bash
# Clean and rebuild
cd iosApp
rm -rf Pods Podfile.lock
pod install
```

#### Web Build Slow on First Run
**Solution**: This is normal! Gradle downloads Node.js and dependencies on first build. Subsequent builds are much faster.

### Getting Help

- **Issues**: [GitHub Issues](https://github.com/yourusername/math-solver-app/issues)
- **Discussions**: [GitHub Discussions](https://github.com/yourusername/math-solver-app/discussions)
- **Contributing**: See [CONTRIBUTING.md](CONTRIBUTING.md)

---

## Next Steps

- 📖 Read the [full documentation](../../README.md)
- 🔧 Learn about [project structure](PROJECT_STRUCTURE.md)
- 🚀 Check out [implementation details](IMPLEMENTATION_SUMMARY.md)
- 🤝 Learn how to [contribute](../../CONTRIBUTING.md)

---

## Quick Commands Reference

```bash
# Clean build artifacts
./gradlew clean

# Run desktop app
./gradlew :desktopApp:run

# Build Android APK
./gradlew :androidApp:assembleDebug

# Run web dev server
./gradlew :shared:wasmJsBrowserDevelopmentRun

# Run all tests
./gradlew test

# Check for dependency updates
./gradlew dependencyUpdates
```

Happy solving! 🎓✨
