# Bitwig MouseWheel Parameter Control

Use your mouse wheel to adjust Bitwig Studio parameters while hovering over them, with fine control using Shift.

## Quick Start

### 1. Install Python Dependencies
```bash
pip install -r requirements.txt
```

### 2. Build the Extension
```bash
cd extension
./gradlew build  # or: ./gradlew.bat on Windows
```

### 3. Install Extension
Copy the built `.bwextension` file to your Bitwig Extensions folder:

- **macOS**: `~/Documents/Bitwig Studio/Extensions/`
- **Linux**: `~/Bitwig Studio/Extensions/`
- **Windows**: `%USERPROFILE%\Documents\Bitwig Studio\Extensions\`

### 4. Enable in Bitwig
1. Settings → Controllers → Add controller
2. Select "MouseWheel Parameter Control"
3. Click Add

### 5. Run the Monitor
```bash
python3 mousewheel_osc_monitor.py
```

### 6. Use It!
- Hover over any parameter in Bitwig
- Scroll mouse wheel to adjust
- Hold Shift for fine control

## How It Works

```
Mouse Wheel Event → Python Monitor → OSC Message → Bitwig Extension → LastClickedParameter.inc()
```

1. **Python monitor** captures mouse wheel events
2. Sends **OSC messages** to localhost:8000
3. **Bitwig extension** receives OSC and adjusts the hovered parameter
4. Uses **LastClickedParameter** (API v20) to track which parameter is active

## Project Structure

```
.
├── mousewheel_osc_monitor.py    # Python: Mouse wheel → OSC
├── requirements.txt              # Python dependencies
├── extension/                    # Java Bitwig extension
│   ├── build.gradle             # Build configuration
│   ├── README.md                # Detailed docs
│   └── src/main/java/com/github/segudev/bitwig/
│       ├── MouseWheelParameterControlDefinition.java
│       └── MouseWheelParameterControl.java
└── README.md                     # This file
```

## Known Issues / TODOs

- ⚠️ May conflict with SteerMouse or other mouse wheel bindings
- ⚠️ Python script must stay running
- 🔧 No conflict avoidance yet (planned)
- 🔧 No visual feedback for selected parameter (use Bitwig console)

## Next Steps

Test it and see where it breaks! Potential improvements:
- Add toggle mode (keyboard shortcut to enable/disable)
- Use exclusive modifier keys to avoid SteerMouse conflicts
- Add visual feedback in Bitwig
- Auto-start Python monitor with Bitwig

## Documentation

- See `extension/README.md` for detailed setup and troubleshooting
- Check Bitwig's Controller Script Console for debug output

## Requirements

- Bitwig Studio (API v20+)
- Python 3.7+
- Java 17+ (for building)

## License

MIT License
