# Math Solver App

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](LICENSE.txt)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.23-blue.svg)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-1.6.0-blue.svg)](https://github.com/JetBrains/compose-multiplatform)

A cross-platform mathematical solver with symbolic computation, proof verification, and word problem solving across **Desktop, Android, iOS, and Web** platforms.

📚 **[Full Documentation](docs/)** | 🚀 **[Quick Start](docs/technical/QUICK_START.md)** | 🤝 **[Contributing](CONTRIBUTING.md)**

## ✨ Features

- **🌐 Multi-Platform**: Desktop, Android, iOS, Web
- **🤖 Dual Modes**: Offline (local libraries) and Online (cloud APIs) with automatic fallback
- **🧮 Math Engine**: Symbolic computation, derivatives, integrals ([Symja](https://github.com/axkr/symja_android_library) on Desktop)
- **📐 Proof Verification**: Set theory, inequalities, limits, geometry
- **📝 Word Problems**: Natural language solver with discrete math and real analysis support

## 🚀 Quick Start

**Prerequisites**: JDK 17+, Android Studio (optional), Xcode (iOS only)

```bash
# Desktop
./gradlew :desktopApp:run

# Web
./gradlew :shared:wasmJsBrowserDevelopmentRun
```

📖 **[Detailed Setup Guide](docs/technical/QUICK_START.md)**

## 🏗️ Architecture

Built with **Kotlin Multiplatform** and **Compose Multiplatform** for code sharing. Uses [Symja](https://github.com/axkr/symja_android_library) (Desktop), [exp4j](https://www.objecthunter.net/exp4j/) (Android).

📐 **[Project Structure](docs/technical/PROJECT_STRUCTURE.md)** | 🔍 **[Implementation Details](docs/technical/IMPLEMENTATION_SUMMARY.md)**

## 🔧 Development

```bash
# Run tests
./gradlew test

# Clean build
./cleanup.sh
```

**[Contributing Guide](CONTRIBUTING.md)** | **[Roadmap](docs/technical/ROADMAP.md)**

## 📄 License

Apache License 2.0 - see [LICENSE.txt](LICENSE.txt)

---

**Built with ❤️ using Kotlin Multiplatform** | Thanks to JetBrains, Axel Kramer (Symja), and the Kotlin community