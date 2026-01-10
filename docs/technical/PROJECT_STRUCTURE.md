# Project Structure

This document outlines the organization of the Math Solver App codebase.

## Root Directory

```
math-solver-app/
├── androidApp/              # Android application module
├── desktopApp/              # Desktop application module  
├── iosApp/                  # iOS application module (Swift)
├── shared/                  # Shared Kotlin Multiplatform code
├── docs/                    # Documentation files
├── gradle/                  # Gradle wrapper files
├── readme_images/           # Images for documentation
├── build.gradle.kts         # Root build configuration
├── settings.gradle.kts      # Project settings
├── gradle.properties        # Gradle properties
├── gradlew                  # Gradle wrapper script (Unix)
├── gradlew.bat              # Gradle wrapper script (Windows)
├── cleanup.sh               # Build cleanup script
├── LICENSE.txt              # Apache 2.0 license
└── README.md                # Main project documentation
```

## Shared Module Structure

The `shared/` directory contains all platform-shared code using Kotlin Multiplatform:

```
shared/
├── src/
│   ├── commonMain/kotlin/          # Platform-agnostic code
│   │   ├── App.kt                 # Main UI with Compose
│   │   └── SolverConfig.kt        # Agentic mode configuration
│   │
│   ├── androidMain/kotlin/         # Android-specific implementations
│   │   ├── MathEvaluator.kt       # exp4j integration
│   │   ├── SolverConfig.android.kt # Network detection
│   │   └── main.android.kt        # Platform name
│   │
│   ├── desktopMain/kotlin/         # Desktop-specific implementations
│   │   ├── MathEvaluator.kt       # Symja integration (full features)
│   │   ├── SolverConfig.desktop.kt # Network detection
│   │   └── main.desktop.kt        # Platform name
│   │
│   ├── iosMain/kotlin/             # iOS-specific implementations
│   │   ├── MathEvaluator.kt       # Basic arithmetic
│   │   ├── SolverConfig.ios.kt    # Network detection
│   │   └── main.ios.kt            # Compose bridge
│   │
│   └── wasmJsMain/                 # Web-specific implementations
│       ├── kotlin/
│       │   ├── MathEvaluator.kt   # Basic arithmetic
│       │   ├── SolverConfig.wasmJs.kt # Always online
│       │   └── main.wasmJs.kt     # Web entry point
│       └── resources/
│           └── index.html          # Web page template
│
└── build.gradle.kts                # Shared module build config
```

## Documentation Structure

```
docs/
├── IMPLEMENTATION_SUMMARY.md       # Complete technical implementation details
├── CONTRIBUTING.md                 # Contribution guidelines
├── CODE_OF_CONDUCT.md             # Community standards
└── CHANGELOG.md                    # Version history
```

## Platform-Specific Modules

### Android App (`androidApp/`)
- Native Android application wrapper
- Uses Jetpack Compose integration from shared module
- Basic arithmetic with exp4j library

### Desktop App (`desktopApp/`)
- JVM-based desktop application
- Full symbolic math capabilities via Symja/MathEclipse
- Supports discrete mathematics and real analysis

### iOS App (`iosApp/`)
- SwiftUI wrapper for iOS platform
- Integrates shared Kotlin framework
- Basic arithmetic capabilities

## Key Technologies by Platform

| Platform | UI Framework | Math Library | Network |
|----------|-------------|--------------|---------|
| Desktop  | Compose Desktop | Symja | Ping-based |
| Android  | Compose Android | exp4j | ConnectivityManager |
| iOS      | SwiftUI + Compose | Native Kotlin | Framework |
| Web      | Compose WASM | Native Kotlin | Fetch API |

## Build System

- **Gradle 8.7** with Kotlin DSL
- **Kotlin 1.9.23** with Multiplatform plugin
- **Compose Multiplatform 1.7.3**
- **Android Gradle Plugin 8.3.0**

## Code Organization Principles

1. **Expect/Actual Pattern**: Platform-specific implementations use Kotlin's expect/actual mechanism
2. **Shared UI**: All UI code is in `commonMain` using Compose Multiplatform
3. **Platform Capabilities**: Each platform implements features appropriate to its capabilities
4. **Agentic Architecture**: Intelligent mode selection based on platform and network state

## Adding New Features

1. Define `expect` functions in `commonMain`
2. Implement `actual` functions in each platform's source set
3. Update UI in `App.kt` if needed
4. Update documentation in `docs/`

## Testing

Each source set can have corresponding test directories:
- `commonTest/` - Shared tests
- `androidUnitTest/` - Android unit tests
- `desktopTest/` - Desktop tests
- `iosTest/` - iOS tests

## Resources

Platform-specific resources go in their respective `resources/` directories:
- Images, icons
- Configuration files
- Platform-specific assets
