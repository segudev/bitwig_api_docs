package com.github.segudev.bitwig;

import com.bitwig.extension.controller.ControllerExtension;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.LastClickedParameter;
import com.bitwig.extension.controller.api.Parameter;
import com.bitwig.extension.api.opensoundcontrol.OscAddressSpace;
import com.bitwig.extension.api.opensoundcontrol.OscMessage;
import com.bitwig.extension.api.opensoundcontrol.OscModule;

public class MouseWheelParameterControl extends ControllerExtension
{
   private ControllerHost host;
   private LastClickedParameter lastClickedParameter;
   private OscModule oscModule;

   private static final int OSC_PORT = 8000;

   protected MouseWheelParameterControl(final MouseWheelParameterControlDefinition definition, final ControllerHost host)
   {
      super(definition, host);
      this.host = host;
   }

   @Override
   public void init()
   {
      host.println("MouseWheel Parameter Control initializing...");

      // Create LastClickedParameter interface
      lastClickedParameter = host.createLastClickedParameter("mousewheel-param", "MouseWheel Parameter");

      // Subscribe to parameter changes to see what we're controlling
      lastClickedParameter.parameter().exists().markInterested();
      lastClickedParameter.parameter().name().markInterested();

      // Set up OSC server
      setupOscServer();

      host.println("MouseWheel Parameter Control initialized!");
      host.println("Listening for OSC on port " + OSC_PORT);
      host.showPopupNotification("MouseWheel Parameter Control Active");
   }

   private void setupOscServer()
   {
      try
      {
         oscModule = host.getOscModule();

         // Create OSC address space
         OscAddressSpace addressSpace = oscModule.createAddressSpace();

         // Register method for parameter adjustment
         addressSpace.registerMethod("/bitwig/parameter/adjust", "fb",
            "Adjust parameter value (float adjustment, bool isShift)",
            (source, message) -> handleParameterAdjustment(message));

         // Create UDP server to listen for OSC messages
         oscModule.createUdpServer(OSC_PORT, addressSpace);

         host.println("OSC server set up on port " + OSC_PORT);
      }
      catch (Exception e)
      {
         host.errorln("Failed to set up OSC server: " + e.getMessage());
         e.printStackTrace();
      }
   }

   private void handleParameterAdjustment(OscMessage message)
   {
      try
      {
         // Get adjustment value and shift flag from OSC message
         float adjustment = message.getFloat(0);
         boolean isShift = message.getBoolean(1);

         // Get the current parameter
         Parameter param = lastClickedParameter.parameter();

         // Check if parameter exists
         if (!param.exists().get())
         {
            host.println("No parameter selected");
            return;
         }

         // Apply adjustment
         param.value().inc(adjustment);

         // Log the adjustment
         String paramName = param.name().get();
         String mode = isShift ? "FINE" : "COARSE";
         host.println(String.format("Adjusted '%s' by %.4f [%s]", paramName, adjustment, mode));
      }
      catch (Exception e)
      {
         host.errorln("Error handling parameter adjustment: " + e.getMessage());
         e.printStackTrace();
      }
   }

   @Override
   public void exit()
   {
      host.println("MouseWheel Parameter Control shutting down...");
      // OSC server will be cleaned up automatically by Bitwig
      host.println("MouseWheel Parameter Control stopped");
   }

   @Override
   public void flush()
   {
      // Called periodically - nothing to flush for this extension
   }
}
