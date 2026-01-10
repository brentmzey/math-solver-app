# Math Solver App

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](LICENSE.txt)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.23-blue.svg)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-1.6.0-blue.svg)](https://github.com/JetBrains/compose-multiplatform)

A cross-platform mathematical solver with symbolic computation, proof verification, and word problem solving across **Desktop, Android, iOS, and Web** platforms.

📚 **[Full Documentation](docs/)** | 🚀 **[Installation Guide](docs/INSTALLATION.md)** | 💻 **[Developer Guide](docs/technical/QUICK_START.md)** | 🤝 **[Contributing](CONTRIBUTING.md)**

---

## ✨ Recent Updates: GraalVM Native & Documentation Overhaul ✨

We've significantly enhanced the Math Solver App! This update introduces **GraalVM native compilation support**, providing blazing-fast startup times and minimal resource usage, and a completely **overhauled documentation suite**.

### 🎯 Key Improvements

**For End Users:**

  - ✅ **Single executable download** - no JDK installation needed
  - ✅ **Fast startup** (~10ms vs ~2s with JVM)
  - ✅ **Low memory** (~50MB vs ~200MB with JVM)
  - ✅ **Clear, simple installation instructions**

**For Developers:**

  - ✅ **Optional GraalVM** - can still use regular JDK for development
  - ✅ **Easy native build**: `./gradlew :desktopApp:nativeCompile`
  - ✅ **Automated CI/CD** for releases
  - ✅ **Better documentation structure**

---

## ✨ Features

- **🌐 Multi-Platform**: Desktop, Android, iOS, Web
- **🤖 Dual Modes**: Offline (local libraries) and Online (cloud APIs) with automatic fallback
- **🧮 Math Engine**: Symbolic computation, derivatives, integrals ([Symja](https://github.com/axkr/symja_android_library) on Desktop)
- **📐 Proof Verification**: Set theory, inequalities, limits, geometry
- **📝 Word Problems**: Natural language solver with discrete math and real analysis support

## 🚀 Quick Installation

### For End Users (No JDK Required!)

Download a native binary for your platform - **single executable, no dependencies**:

#### macOS / Linux
```bash
curl -L https://github.com/yourusername/math-solver-app/releases/latest/download/math-solver-$(uname -s | tr '[:upper:]' '[:lower:]') -o math-solver
chmod +x math-solver
./math-solver
```

#### Windows
Download `math-solver.exe` from [releases](https://github.com/yourusername/math-solver-app/releases) and run.

**[📥 Full Installation Guide](docs/INSTALLATION.md)**

---

### For Developers

**Prerequisites**: GraalVM 21+ or JDK 17+

```bash
# Clone
git clone https://github.com/yourusername/math-solver-app.git
cd math-solver-app

# Build native binary (GraalVM)
./gradlew :desktopApp:nativeCompile

# OR run from source (development)
./gradlew :desktopApp:run
```

**[💻 Developer Quick Start](docs/technical/QUICK_START.md)**

## 🏗️ Architecture

Built with **Kotlin Multiplatform** and **Compose Multiplatform** for code sharing. Uses [Symja](https://github.com/axkr/symja_android_library) (Desktop), [exp4j](https://www.objecthunter.net/exp4j/) (Android).

Native binaries compiled with **GraalVM** for fast startup and low memory footprint.

📐 **[Project Structure](docs/technical/PROJECT_STRUCTURE.md)** | 🔍 **[Implementation Details](docs/technical/IMPLEMENTATION_SUMMARY.md)**

## 🔧 Development

```bash
# Run tests
./gradlew test

# Build native binary
./gradlew :desktopApp:nativeCompile

# Create platform packages
./gradlew :desktopApp:packageDistributionForCurrentOS

# Clean build
./cleanup.sh
```

**[Contributing Guide](CONTRIBUTING.md)** | **[Roadmap](docs/technical/ROADMAP.md)**

## 📦 Distribution

Native binaries available for:
- **macOS** (Intel + Apple Silicon)
- **Linux** (x86_64, aarch64)
- **Windows** (x64)

Platform packages:
- `.dmg` (macOS)
- `.deb` (Linux)
- `.msi` (Windows)

## 📄 License

Apache License 2.0 - see [LICENSE.txt](LICENSE.txt)

---

**Built with ❤️ using Kotlin Multiplatform & GraalVM** | Thanks to JetBrains, Axel Kramer (Symja), and the Kotlin community