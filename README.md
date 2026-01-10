# Math Solver App

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.23-blue.svg)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-1.6.0-blue.svg)](https://github.com/JetBrains/compose-multiplatform)

A powerful cross-platform mathematical solver application built with Kotlin Multiplatform and Compose Multiplatform. This app provides symbolic computation, proof verification, and word problem solving capabilities across Desktop, Android, and iOS platforms.

## ✨ Features

### 🧮 Mathematical Expression Evaluation
- **Desktop**: Advanced symbolic computation using [Symja/MathEclipse](https://github.com/axkr/symja_android_library)
  - Integration and differentiation
  - Symbolic algebra
  - Complex mathematical operations
- **Android**: Basic arithmetic evaluation using [exp4j](https://www.objecthunter.net/exp4j/)
- **iOS**: Basic mathematical operations

### 📐 Proof Verification
- Set theory proofs (e.g., "The empty set is a subset of all sets")
- Mathematical inequalities (e.g., Bernoulli's Inequality)
- Limit proofs
- Geometric proofs (e.g., Area of an Annulus)

### 📝 Word Problem Solver
Intelligent natural language processing to solve various types of word problems:
- **Arithmetic Problems**: Addition, subtraction, multiplication, division
  - Example: "If I have 2 apples and I buy 3 more, how many apples do I have?"
- **Calculus Problems** (Desktop only):
  - Derivative problems
  - Integration problems
  - Geometric calculus (e.g., annulus area calculations)
- **Pattern Recognition**: Automatically detects keywords like "sum", "total", "difference", "product"

## 🚀 Getting Started

### Prerequisites

- **JDK 17** or higher
- **Android Studio** (for Android development)
- **Xcode** (for iOS development, macOS only)
- **Gradle 8.7** (included via wrapper)

### Building the Project

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/math-solver-app.git
   cd math-solver-app
   ```

2. **Build all targets**
   ```bash
   ./gradlew build
   ```

### Running the Application

#### Desktop
```bash
./gradlew :desktopApp:run
```

#### Android
1. Open the project in Android Studio
2. Select the `androidApp` configuration
3. Run on an emulator or physical device

#### iOS (macOS only)
1. Open the project in Android Studio or Xcode
2. Select an iOS simulator or device
3. Run the `iosApp` target

## 📱 Platform Support

| Platform | Math Evaluation | Proof Verification | Word Problems |
|----------|----------------|-------------------|---------------|
| Desktop  | ✅ Full (Symja) | ✅ Full           | ✅ Full       |
| Android  | ✅ Basic        | ❌                | ✅ Basic      |
| iOS      | ✅ Basic        | ❌                | ✅ Basic      |

## 🏗️ Architecture

The project follows Kotlin Multiplatform Mobile (KMM) architecture:

```
math-solver-app/
├── shared/                    # Shared Kotlin code
│   ├── commonMain/           # Common UI and interfaces
│   ├── androidMain/          # Android-specific implementations
│   ├── desktopMain/          # Desktop-specific implementations (with Symja)
│   └── iosMain/              # iOS-specific implementations
├── androidApp/               # Android application module
├── desktopApp/               # Desktop application module
└── iosApp/                   # iOS application module
```

### Key Technologies

- **[Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)**: Share code across platforms
- **[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform)**: Shared declarative UI
- **[Symja/MathEclipse](https://github.com/axkr/symja_android_library)**: Symbolic math engine (Desktop)
- **[exp4j](https://www.objecthunter.net/exp4j/)**: Expression evaluator (Android)

## 🔧 Development

### Project Structure

- `App.kt` - Main UI component with state management
- `MathEvaluator.kt` - Platform-specific implementations for math operations
- Each platform implements `expect` functions defined in `commonMain`

### Adding New Features

1. Define the interface in `commonMain/kotlin/App.kt`
2. Implement platform-specific logic in:
   - `desktopMain/kotlin/MathEvaluator.kt`
   - `androidMain/kotlin/MathEvaluator.kt`
   - `iosMain/kotlin/MathEvaluator.kt`

### Code Style

This project follows the [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html).

## 🧪 Testing

Run tests with:
```bash
./gradlew test
```

## 🧹 Cleanup

To clean build artifacts:
```bash
./cleanup.sh
```

Or manually:
```bash
./gradlew clean
```

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE.txt](LICENSE.txt) file for details.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📚 Resources

- [Kotlin Multiplatform Documentation](https://kotlinlang.org/docs/multiplatform.html)
- [Compose Multiplatform Documentation](https://github.com/JetBrains/compose-multiplatform)
- [Symja Documentation](https://github.com/axkr/symja_android_library)

## 🐛 Known Issues

- iOS and Android platforms currently have limited mathematical capabilities compared to Desktop
- Proof verification is only available on Desktop platform
- Advanced calculus features in word problems are Desktop-only

## 🗺️ Roadmap

- [ ] Enhanced word problem parsing with ML/LLM integration
- [ ] Support for more proof types
- [ ] Graph plotting capabilities
- [ ] Step-by-step solution explanations
- [ ] LaTeX rendering for mathematical expressions
- [ ] Cloud sync for problem history

## 👥 Authors

- Your Name - Initial work

## 🙏 Acknowledgments

- JetBrains for Compose Multiplatform
- Axel Kramer for Symja/MathEclipse
- The Kotlin community

---

**Built with ❤️ using Kotlin Multiplatform**