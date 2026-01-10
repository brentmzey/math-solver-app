#!/bin/bash
# Build script for Math Solver native binaries

set -e

echo "🔨 Math Solver Build Script"
echo "============================"
echo ""

# Check if GraalVM is installed
if ! command -v native-image &> /dev/null; then
    echo "❌ GraalVM native-image not found!"
    echo ""
    echo "Please install GraalVM 21+ first:"
    echo ""
    echo "  macOS/Linux (SDKMAN):"
    echo "    curl -s 'https://get.sdkman.io' | bash"
    echo "    sdk install java 21-graalce"
    echo "    sdk use java 21-graalce"
    echo ""
    echo "  Or download from: https://www.graalvm.org/downloads/"
    echo ""
    echo "To build without GraalVM, use:"
    echo "  ./gradlew :desktopApp:run"
    exit 1
fi

echo "✅ GraalVM found: $(native-image --version | head -1)"
echo ""

# Build native image
echo "🚀 Building native executable..."
echo "   This may take 5-10 minutes on first build..."
echo ""

./gradlew :desktopApp:nativeCompile

echo ""
echo "✅ Build complete!"
echo ""
echo "📦 Native executable: desktopApp/build/native/math-solver"
echo ""
echo "To run: ./desktopApp/build/native/math-solver"
echo ""
