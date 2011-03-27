package com.bryanjswift.simplenote.util;

import com.bryanjswift.simplenote.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/** @author bryanjswift */
public class IOUtils {
    /** Logger for IOUtils */
    private static final Logger logger = LoggerFactory.getLogger(IOUtils.class);
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
            logger.error("Unable to read stream contents", ioe);
        }
        return out.toString();
    }
}
