# MouseWheel Parameter Control for Bitwig Studio

Control Bitwig parameters using your mouse wheel when hovering over them.

## Features

- Adjust parameters with mouse wheel scroll
- Fine adjustment with Shift + wheel
- Uses API v20's `LastClickedParameter` for hover detection
- OSC-based communication for mouse event capture

## Requirements

- Bitwig Studio (supporting API version 20+)
- Python 3.7+
- Java 17+ (for building)

## Building the Extension

### Quick Build

```bash
cd extension
./gradlew build
```

The extension will be output as `build/libs/MouseWheelParameterControl-0.1.0.bwextension`

### Manual Build (without Gradle)

If you don't have Gradle:

```bash
cd extension/src/main/java
javac -d ../../../build/classes com/github/segudev/bitwig/*.java
cd ../../../build/classes
jar cf MouseWheelParameterControl.bwextension com/
```

## Installation

### 1. Install the Bitwig Extension

Copy the `.bwextension` file to your Bitwig Studio extensions folder:

**macOS:**
```bash
cp build/libs/MouseWheelParameterControl-0.1.0.bwextension \
   ~/Documents/Bitwig\ Studio/Extensions/
```

**Linux:**
```bash
cp build/libs/MouseWheelParameterControl-0.1.0.bwextension \
   ~/Bitwig\ Studio/Extensions/
```

**Windows:**
```powershell
copy build\libs\MouseWheelParameterControl-0.1.0.bwextension ^
     %USERPROFILE%\Documents\Bitwig Studio\Extensions\
```

### 2. Enable the Extension in Bitwig

1. Open Bitwig Studio
2. Go to **Settings > Controllers**
3. Click **Add controller**
4. Find "MouseWheel Parameter Control" by segudev
5. Click **Add**

### 3. Install Python Dependencies

```bash
pip install -r requirements.txt
```

## Usage

### 1. Start the OSC Monitor

In the project root directory:

```bash
python3 mousewheel_osc_monitor.py
```

You should see:
```
OSC Mouse Wheel Monitor started
Sending to 127.0.0.1:8000
Monitoring mouse wheel... Press Ctrl+C to stop
```

### 2. Use in Bitwig

1. Hover your mouse over any parameter in Bitwig
2. Scroll your mouse wheel to adjust the parameter
3. Hold **Shift** while scrolling for fine adjustment

The console will show which parameters you're adjusting.

## Configuration

Edit the following constants in the files to customize behavior:

### Python Monitor (`mousewheel_osc_monitor.py`)

```python
OSC_PORT = 8000          # OSC port (must match extension)
COARSE_STEP = 0.01       # 1% per wheel tick
FINE_STEP = 0.001        # 0.1% per wheel tick with Shift
```

### Java Extension (`MouseWheelParameterControl.java`)

```java
private static final int OSC_PORT = 8000;  // Must match Python script
```

## Known Limitations

- No automatic "hover detection" - relies on Bitwig's LastClickedParameter
- May conflict with other mouse wheel bindings (e.g., SteerMouse)
- Python script must be running for mouse wheel control to work
- No visual feedback when parameter is selected (check Bitwig console)

## Troubleshooting

### Extension doesn't appear in Bitwig

- Check that the `.bwextension` file is in the correct Extensions folder
- Restart Bitwig Studio
- Check Bitwig's log file for errors

### Mouse wheel not working

- Ensure Python script is running
- Check OSC port isn't blocked by firewall
- Verify port numbers match in both Python and Java files
- Check Bitwig's Controller Script Console for error messages

### Parameters not adjusting

- Click on a parameter first to ensure it's "last clicked"
- Check that the extension is enabled in Settings > Controllers
- Look for error messages in Bitwig's script console

## Development

To see debug output:
1. Open Bitwig's **Controller Script Console** (View menu)
2. All `host.println()` messages will appear there

## License

MIT License - Free to use and modify
