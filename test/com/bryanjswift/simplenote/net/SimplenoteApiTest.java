package com.bryanjswift.simplenote.net;

import android.test.AndroidTestCase;
import com.bryanjswift.simplenote.Constants;
import com.bryanjswift.simplenote.model.Credentials;
import junit.framework.TestCase;
import org.apache.http.HttpStatus;

/** @author bryanjswift */
public class SimplenoteApiTest extends AndroidTestCase {
    public static final String LOGGING_TAG = Constants.TAG + "SwiftNoteApiTest";
    public final Credentials credentials = new Credentials(null, "swiftnote@bryanjswift.com", "simplenote1234");
    public final Credentials badCredentials = new Credentials(null, "swiftnote@bryanjswift.com", "bad password");

    public void testSuccessfulLogin() {
        final Credentials creds = credentials;
        final ApiResponse<Credentials> response = SimplenoteApi.login(creds.email, creds.password);
        assertEquals("Should return with OK status", HttpStatus.SC_OK, response.status);
        assertEquals("Email addresses must match", response.payload.email, creds.email);
        assertTrue("Payload auth should not be empty", response.payload.auth.length() > 0);
        assertNull("Password should not be returned", response.payload.password);
    }

    public void testFailingLogin() {
        final Credentials creds = badCredentials;
        final ApiResponse<Credentials> response = SimplenoteApi.login(creds.email, creds.password);
        assertEquals("Should return with OK status", HttpStatus.SC_UNAUTHORIZED, response.status);
        assertEquals("Email addresses must match", response.payload.email, creds.email);
        assertNull("Password should not be returned", response.payload.password);
    }
}
