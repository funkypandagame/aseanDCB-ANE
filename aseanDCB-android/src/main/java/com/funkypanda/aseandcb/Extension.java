package com.funkypanda.aseandcb;

import android.app.Activity;
import android.util.Log;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class Extension implements FREExtension
{
    public static final String TAG = "AseanDCB";
    private static ExtensionContext context;

    public static void dispatchStatusEventAsync(String eventCode, String message)
    {
        if (context != null) {
            Log.d(TAG, eventCode + " " + message);
            context.dispatchStatusEventAsync(eventCode, message);
        }
        else {
            Log.e(TAG, "Extension context is null, was the extension disposed? Tried to send event " +
                 eventCode + " with message " + message);
        }
    }

    public static Activity getActivity()
    {
        return context.getActivity();
    }

    // Called automatically from Flash: ExtensionContext.createExtensionContext()
    public FREContext createContext(String extId)
    {
        return context = new ExtensionContext();
    }

    public void dispose()
    {
        context = null;
    }

    public void initialize() {
    }

    public static void log(String message)
    {
        Log.d(TAG, message);
        if (context != null) {
            context.dispatchStatusEventAsync(FlashConstants.DEBUG, message);
        }
    }

    public static void logError(String message)
    {
        Log.e(TAG, message);
        if (context != null) {
            context.dispatchStatusEventAsync(FlashConstants.ERROR, message);
        }
    }
}