#!/bin/bash
# Trigger native build workflow manually

echo "This will trigger the native build workflow on GitHub Actions"
echo ""
echo "Choose a method:"
echo "1. Via GitHub CLI (recommended)"
echo "2. Via commit message keyword"
echo ""
read -p "Enter choice (1 or 2): " choice

if [ "$choice" = "1" ]; then
    echo ""
    echo "Running: gh workflow run build-native.yml"
    gh workflow run build-native.yml
    echo ""
    echo "Native build triggered! Check status with:"
    echo "  gh run list --workflow=build-native.yml"
elif [ "$choice" = "2" ]; then
    echo ""
    echo "Create an empty commit with [native] keyword:"
    git commit --allow-empty -m "[native] Trigger native build"
    echo ""
    echo "Push to trigger the build:"
    echo "  git push origin main"
else
    echo "Invalid choice"
    exit 1
fi
