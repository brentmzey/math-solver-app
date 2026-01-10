#!/bin/bash
# Cleanup script for Math Solver App
# Removes all build artifacts, caches, and temporary files

echo "🧹 Cleaning Math Solver App..."

# Remove IDE files
echo "Removing IDE files..."
rm -rf .idea

# Clean Gradle build
echo "Running Gradle clean..."
./gradlew clean

# Remove Gradle cache
echo "Removing Gradle cache..."
rm -rf .gradle

# Remove build directories
echo "Removing build directories..."
rm -rf build
rm -rf */build
rm -rf **/build

# Remove iOS specific files
echo "Removing iOS build artifacts..."
rm -rf iosApp/iosApp.xcworkspace
rm -rf iosApp/Pods
rm -rf iosApp/iosApp.xcodeproj/project.xcworkspace
rm -rf iosApp/iosApp.xcodeproj/xcuserdata
rm -rf iosApp/Podfile.lock

# Remove macOS system files
echo "Removing .DS_Store files..."
find . -name ".DS_Store" -type f -delete

# Remove log files
echo "Removing log files..."
find . -name "*.log" -type f -delete

echo "✨ Cleanup complete!"
 
