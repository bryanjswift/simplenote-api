package com.bryanjswift.simplenote.util;

import android.util.Log;
import com.bryanjswift.simplenote.Constants;

import java.io.IOException;
import java.io.InputStream;

/** @author bryanjswift */
public class IOUtils {
    private static final String LOGGING_TAG = Constants.TAG + "IOUtils";
    /**
     * Convert InputStream to String by reading all the data from it
     * @param in InputStream to read
     * @return String containing the bytes of in
     */
    public static String slurp(InputStream in) {
        final StringBuilder out = new StringBuilder();
        try {
            byte[] b = new byte[4096];
            for (int n; (n = in.read(b)) != -1;) {
                out.append(new String(b, 0, n));
            }
        } catch (IOException ioe) {
            Log.e(LOGGING_TAG, "Unable to read stream contents", ioe);
        }
        return out.toString();
    }
}
