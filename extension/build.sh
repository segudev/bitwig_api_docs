#!/bin/bash
# Simple build script for MouseWheel Parameter Control extension
# No Gradle required!

set -e

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

echo "Building MouseWheel Parameter Control extension..."

# Clean previous build
rm -rf build
mkdir -p build/classes

echo "Compiling Java sources..."
javac -source 17 -target 17 \
    -d build/classes \
    src/main/java/com/github/segudev/bitwig/*.java

if [ $? -ne 0 ]; then
    echo "ERROR: Compilation failed"
    exit 1
fi

echo "Creating .bwextension package..."
cd build/classes
jar cf ../MouseWheelParameterControl.bwextension \
    com/github/segudev/bitwig/*.class

cd ../..

# Rename to proper extension
mv build/MouseWheelParameterControl.bwextension \
   build/MouseWheelParameterControl-0.1.0.bwextension

echo ""
echo "✅ Build successful!"
echo "Extension: build/MouseWheelParameterControl-0.1.0.bwextension"
echo ""
echo "To install, copy to your Bitwig Extensions folder:"
echo "  macOS:   ~/Documents/Bitwig Studio/Extensions/"
echo "  Linux:   ~/Bitwig Studio/Extensions/"
echo "  Windows: %USERPROFILE%\\Documents\\Bitwig Studio\\Extensions\\"
