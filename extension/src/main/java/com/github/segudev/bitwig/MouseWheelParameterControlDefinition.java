package com.github.segudev.bitwig;

import com.bitwig.extension.api.PlatformType;
import com.bitwig.extension.controller.AutoDetectionMidiPortNamesList;
import com.bitwig.extension.controller.ControllerExtensionDefinition;
import com.bitwig.extension.controller.api.ControllerHost;

import java.util.UUID;

public class MouseWheelParameterControlDefinition extends ControllerExtensionDefinition
{
   private static final UUID DRIVER_ID = UUID.fromString("a1b2c3d4-e5f6-7890-abcd-ef1234567890");

   public MouseWheelParameterControlDefinition()
   {
   }

   @Override
   public String getName()
   {
      return "MouseWheel Parameter Control";
   }

   @Override
   public String getAuthor()
   {
      return "segudev";
   }

   @Override
   public String getVersion()
   {
      return "0.1.0";
   }

   @Override
   public UUID getId()
   {
      return DRIVER_ID;
   }

   @Override
   public String getHardwareVendor()
   {
      return "segudev";
   }

   @Override
   public String getHardwareModel()
   {
      return "MouseWheel Parameter Control";
   }

   @Override
   public int getRequiredAPIVersion()
   {
      return 20;
   }

   @Override
   public int getNumMidiInPorts()
   {
      return 0;
   }

   @Override
   public int getNumMidiOutPorts()
   {
      return 0;
   }

   @Override
   public void listAutoDetectionMidiPortNames(final AutoDetectionMidiPortNamesList list, final PlatformType platformType)
   {
      // No MIDI ports for auto-detection
   }

   @Override
   public ControllerExtension createInstance(final ControllerHost host)
   {
      return new MouseWheelParameterControl(this, host);
   }

   @Override
   public String getHelpFilePath()
   {
      return "README.md";
   }

   @Override
   public boolean shouldFailOnDeprecatedUse()
   {
      return true;
   }
}
