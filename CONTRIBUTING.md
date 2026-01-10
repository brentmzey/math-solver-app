# Contributing to Math Solver App

Thank you for your interest in contributing to Math Solver App! This document provides guidelines and instructions for contributing.

## 🌟 Ways to Contribute

- **Report Bugs**: Submit detailed bug reports via GitHub Issues
- **Suggest Features**: Propose new features or improvements
- **Submit Pull Requests**: Fix bugs or implement new features
- **Improve Documentation**: Help make our docs better
- **Write Tests**: Increase code coverage and reliability

## 🚀 Getting Started

### 1. Fork and Clone

```bash
git fork https://github.com/yourusername/math-solver-app.git
git clone https://github.com/yourusername/math-solver-app.git
cd math-solver-app
```

### 2. Set Up Development Environment

Ensure you have:
- JDK 17 or higher
- Android Studio (for Android development)
- Xcode (for iOS development, macOS only)

### 3. Build the Project

```bash
./gradlew build
```

### 4. Run Tests

```bash
./gradlew test
```

## 📝 Contribution Workflow

### 1. Create a Branch

```bash
git checkout -b feature/your-feature-name
```

Use prefixes:
- `feature/` - New features
- `fix/` - Bug fixes
- `docs/` - Documentation updates
- `refactor/` - Code refactoring
- `test/` - Test additions or modifications

### 2. Make Your Changes

- Write clean, readable code
- Follow Kotlin coding conventions
- Add comments for complex logic
- Update documentation as needed

### 3. Test Your Changes

```bash
# Run tests
./gradlew test

# Build all platforms
./gradlew build

# Test on desktop
./gradlew :desktopApp:run
```

### 4. Commit Your Changes

Follow conventional commit messages:

```bash
git commit -m "feat: add new word problem pattern recognition"
git commit -m "fix: correct integral calculation for annulus"
git commit -m "docs: update installation instructions"
```

Commit message format:
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, etc.)
- `refactor`: Code refactoring
- `test`: Adding or updating tests
- `chore`: Maintenance tasks

### 5. Push and Create Pull Request

```bash
git push origin feature/your-feature-name
```

Then create a Pull Request on GitHub with:
- Clear title and description
- Reference any related issues
- Screenshots/videos for UI changes
- Test results

## 🎨 Code Style Guidelines

### Kotlin Style

Follow the [official Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html):

```kotlin
// Good
fun evaluateExpression(expression: String): String {
    return try {
        val result = calculate(expression)
        "Result: $result"
    } catch (e: Exception) {
        "Error: ${e.message}"
    }
}

// Bad
fun evaluateExpression(expression:String):String{
  try{
    val result=calculate(expression)
    return "Result: "+result
  }catch(e:Exception){
    return "Error: "+e.message
  }
}
```

### Compose UI Guidelines

- Keep composable functions small and focused
- Extract reusable UI components
- Use meaningful parameter names
- Add preview annotations for UI components

```kotlin
@Composable
fun MathInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier
    )
}
```

## 🏗️ Architecture Guidelines

### Platform-Specific Code

When adding features that require platform-specific implementations:

1. Define the interface in `commonMain`:
```kotlin
expect fun solveNewProblemType(input: String): String
```

2. Implement in each platform:
```kotlin
// desktopMain
actual fun solveNewProblemType(input: String): String {
    // Desktop implementation using Symja
}

// androidMain
actual fun solveNewProblemType(input: String): String {
    // Android implementation
}

// iosMain
actual fun solveNewProblemType(input: String): String {
    // iOS implementation
}
```

### Adding Dependencies

1. Update `build.gradle.kts` in the appropriate module
2. Document why the dependency is needed
3. Keep dependencies minimal and well-maintained

## 🧪 Testing Guidelines

### Unit Tests

```kotlin
class MathEvaluatorTest {
    @Test
    fun `should correctly evaluate addition`() {
        val result = evaluateExpression("2 + 3")
        assertEquals("5", result)
    }
}
```

### Platform-Specific Tests

Place tests in the appropriate source set:
- `commonTest/` - Shared tests
- `desktopTest/` - Desktop-specific tests
- `androidTest/` - Android-specific tests
- `iosTest/` - iOS-specific tests

## 📋 Pull Request Checklist

Before submitting your PR, ensure:

- [ ] Code builds successfully on all platforms
- [ ] All tests pass
- [ ] New features have tests
- [ ] Documentation is updated
- [ ] Code follows style guidelines
- [ ] Commit messages are clear and conventional
- [ ] No unnecessary files are included
- [ ] Branch is up to date with main

## 🐛 Bug Reports

When reporting bugs, include:

1. **Description**: Clear description of the issue
2. **Steps to Reproduce**: Detailed steps to reproduce
3. **Expected Behavior**: What should happen
4. **Actual Behavior**: What actually happens
5. **Environment**:
   - Platform (Desktop/Android/iOS)
   - OS version
   - App version
6. **Screenshots/Logs**: If applicable

## 💡 Feature Requests

When suggesting features:

1. **Use Case**: Describe the problem or need
2. **Proposed Solution**: How you envision it working
3. **Alternatives**: Other approaches you've considered
4. **Additional Context**: Any relevant information

## 📞 Questions?

- Open a GitHub Discussion
- Check existing Issues
- Review the README and documentation

## 📄 License

By contributing, you agree that your contributions will be licensed under the Apache License 2.0.

---

Thank you for contributing to Math Solver App! 🎉
