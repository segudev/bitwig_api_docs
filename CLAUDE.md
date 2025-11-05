# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository Purpose

This repository contains the JavaDoc HTML documentation for the **Bitwig Studio Extension API**. It is a static documentation site for developers creating control surface scripts and extensions for Bitwig Studio (a digital audio workstation). This is generated documentation only - there is no source code, build process, or tests in this repository.

## Repository Structure

```
.
├── index.html                    # Main entry point - API overview
├── allclasses-index.html         # Alphabetical list of all classes
├── overview-tree.html            # Class hierarchy tree
├── index-all.html                # Complete alphabetical index
├── deprecated-list.html          # Deprecated API elements
├── new-list.html                 # New API additions
├── com/bitwig/extension/         # Package hierarchy (JavaDoc HTML)
├── legal/                        # jQuery licenses
├── resources/                    # CSS/image resources
└── script-dir/                   # JavaScript dependencies (jQuery)
```

## Key API Packages

The API is organized under `com.bitwig.extension` with these main sub-packages:

- **`com.bitwig.extension.api`** - Core API interfaces
  - **`Host`** interface is the primary entry point - singleton available in script scope
  - Contains main interfaces for interacting with Bitwig Studio

- **`com.bitwig.extension.controller`** - Controller extension framework
  - Base classes and interfaces for building controller extensions

- **`com.bitwig.extension.api.graphics`** - Graphics and UI rendering

- **`com.bitwig.extension.api.opensoundcontrol`** - OSC (Open Sound Control) support

- **`com.bitwig.extension.api.util.midi`** - MIDI utilities and helpers

- **`com.bitwig.extension.callback`** - Callback interfaces for event handling

## Important Documentation Entry Points

1. **Start here**: `com/bitwig/extension/api/Host.html` - The main interface for extension interaction
2. **Overview**: `index.html` - Explains the API structure and references the Control Surface Scripting Guide
3. **Class hierarchy**: `overview-tree.html` - Visual representation of inheritance relationships
4. **Search**: `search.html` - Full-text search across the API

## Working with This Repository

Since this is static documentation:
- Changes are typically regenerated from source, not hand-edited
- HTML files follow JavaDoc 21 structure
- The documentation references an external "Control Surface Scripting Guide" available in Bitwig Studio's Help menu

## Navigation

The documentation uses standard JavaDoc navigation:
- Top navbar: Overview | Package | Class | Tree | New | Deprecated | Index | Help
- Left sidebar: Package/class navigation (in individual class pages)
- Search functionality powered by jQuery UI
