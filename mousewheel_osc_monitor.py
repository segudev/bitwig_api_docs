#!/usr/bin/env python3
"""
Simple OSC Mouse Wheel Monitor for Bitwig Parameter Control
Sends OSC messages when mouse wheel is scrolled with modifiers
"""

from pynput import mouse
from pynput.keyboard import Key, Controller as KeyboardController
from pythonosc import udp_client
import time

# Configuration
OSC_HOST = "127.0.0.1"
OSC_PORT = 8000
OSC_ADDRESS = "/bitwig/parameter/adjust"

# Sensitivity
COARSE_STEP = 0.01  # 1% per wheel tick
FINE_STEP = 0.001   # 0.1% per wheel tick

class MouseWheelMonitor:
    def __init__(self):
        self.osc_client = udp_client.SimpleUDPClient(OSC_HOST, OSC_PORT)
        self.keyboard = KeyboardController()
        self.shift_pressed = False
        print(f"OSC Mouse Wheel Monitor started")
        print(f"Sending to {OSC_HOST}:{OSC_PORT}")
        print(f"Coarse step: {COARSE_STEP}, Fine step (Shift): {FINE_STEP}")

    def on_scroll(self, x, y, dx, dy):
        """Called when mouse wheel is scrolled"""
        if dy == 0:
            return

        # Determine step size based on Shift key
        # Note: This is a simple check - we'll detect shift via modifier keys if available
        step = FINE_STEP if self.shift_pressed else COARSE_STEP

        # Calculate adjustment value (positive = up, negative = down)
        adjustment = dy * step

        # Send OSC message
        try:
            self.osc_client.send_message(OSC_ADDRESS, [adjustment, self.shift_pressed])
            print(f"Sent: {adjustment:.4f} (shift={self.shift_pressed})")
        except Exception as e:
            print(f"Error sending OSC: {e}")

    def run(self):
        """Start monitoring mouse wheel events"""
        with mouse.Listener(on_scroll=self.on_scroll) as listener:
            print("\nMonitoring mouse wheel... Press Ctrl+C to stop")
            listener.join()

if __name__ == "__main__":
    try:
        monitor = MouseWheelMonitor()
        monitor.run()
    except KeyboardInterrupt:
        print("\nStopped by user")
    except Exception as e:
        print(f"Error: {e}")
        print("\nMake sure you have installed dependencies:")
        print("  pip install pynput python-osc")
