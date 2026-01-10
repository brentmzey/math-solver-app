# Installation Guide

This comprehensive guide covers how to get the Math Solver App up and running, whether you're an end user looking for a quick installation or a developer interested in building from source, including with the new GraalVM native compilation support.

## For End Users (Easy Installation)

### Download Pre-built Binaries (Recommended)

The easiest way to use Math Solver is to download a native binary - **no JDK required!**

#### macOS
```bash
curl -L https://github.com/yourusername/math-solver-app/releases/latest/download/math-solver-macos -o math-solver
chmod +x math-solver
./math-solver
```

Or use Homebrew:
```bash
brew tap yourusername/math-solver
brew install math-solver
```

#### Linux
```bash
curl -L https://github.com/yourusername/math-solver-app/releases/latest/download/math-solver-linux -o math-solver
chmod +x math-solver
./math-solver
```

Or install the .deb package:
```bash
# Download from releases page
sudo dpkg -i math-solver_1.0.0_amd64.deb
math-solver
```

#### Windows
1. Download `math-solver.exe` from [releases](https://github.com/yourusername/math-solver-app/releases)
2. Double-click to run, or from terminal: `.\math-solver.exe`

### Why Native Binaries?

✅ **Fast startup** - ~10ms vs ~2s with JVM  
✅ **Low memory** - ~50MB vs ~200MB with JVM  
✅ **No dependencies** - Single executable, no JDK needed  
✅ **Small size** - ~30-50MB vs ~100MB+ with JRE bundle

---

## For Developers

### Option 1: Quick Start (No GraalVM Needed)

Run from source using regular JDK:

```bash
# Prerequisites: JDK 17+ only
git clone https://github.com/yourusername/math-solver-app.git
cd math-solver-app

# Run directly
./gradlew :desktopApp:run
```

### Option 2: Build Native Binary (Requires GraalVM)

For creating distributable executables:

#### Install GraalVM

**macOS/Linux (SDKMAN - Recommended):**
```bash
curl -s "https://get.sdkman.io" | bash
sdk install java 21-graalce
sdk use java 21-graalce
```

**macOS/Linux (Manual):**
```bash
# Download from https://www.graalvm.org/downloads/
# Then set JAVA_HOME
export JAVA_HOME=/path/to/graalvm-21
export PATH=$JAVA_HOME/bin:$PATH
```

**Windows:**
1. Download GraalVM from https://www.graalvm.org/downloads/
2. Extract to `C:\graalvm-21`
3. Set environment variable:
   ```powershell
   setx JAVA_HOME "C:\graalvm-21"
   ```

#### Build Native Image

```bash
# Verify GraalVM is installed
native-image --version

# Clone and build
git clone https://github.com/yourusername/math-solver-app.git
cd math-solver-app

# Create native executable
./gradlew :desktopApp:nativeCompile

# Run it
./desktopApp/build/native/math-solver
```

#### Create Platform Packages

```bash
# macOS: Create .dmg
./gradlew :desktopApp:packageDmg

# Linux: Create .deb  
./gradlew :desktopApp:packageDeb

# Windows: Create .msi
./gradlew :desktopApp:packageMsi

# Current platform
./gradlew :desktopApp:packageDistributionForCurrentOS
```

---

## Platform-Specific Notes

### macOS
- Native binaries support both Intel and Apple Silicon
- First run: Right-click → Open (or `xattr -d com.apple.quarantine math-solver`)
- Code signing available for distribution builds

### Linux
- Requires glibc 2.31+ (Ubuntu 20.04+, Fedora 32+)
- For older distributions, use JVM version: `./gradlew :desktopApp:run`

### Windows
- Requires Windows 10 1809+ or Windows Server 2019+
- Visual Studio Build Tools may be needed for native compilation
- Alternatively use MSI package for easier installation

---

## Installation Comparison

| Method | Startup Time | Memory Usage | Size | Requirements |
|--------|--------------|--------------|------|--------------|
| **Native Binary** | ~10ms | ~50MB | ~30-50MB | None |
| **JVM Package** | ~2s | ~200MB | ~100MB+ | JDK 17+ |
| **From Source** | ~2s | ~200MB | N/A | JDK 17+ |

---

## Troubleshooting

### Native Binary Issues

**"Cannot execute native binary"**
- **macOS**: `xattr -d com.apple.quarantine math-solver`
- **Linux**: `chmod +x math-solver`
- **Windows**: Right-click → Properties → Unblock

**"GraalVM not found" (during build)**
```bash
# Verify installation
native-image --version

# Ensure JAVA_HOME is set correctly
echo $JAVA_HOME  # Unix
echo %JAVA_HOME%  # Windows
```

### Build Issues

**Out of memory during native compilation**
Add to `gradle.properties`:
```properties
org.gradle.jvmargs=-Xmx4096M
```

**Native build fails**
Fall back to JVM build:
```bash
./gradlew :desktopApp:packageDistributionForCurrentOS
```

---

## What's Next?

- 📖 [Quick Start Guide](technical/QUICK_START.md) - Learn how to use the app
- 🏗️ [Developer Guide](technical/QUICK_START.md#for-developers) - Advanced development
- 🤝 [Contributing](../CONTRIBUTING.md) - Help improve Math Solver
- 🚀 [Roadmap](technical/ROADMAP.md) - Upcoming features

---

**Questions?** Open an issue on [GitHub](https://github.com/yourusername/math-solver-app/issues)
