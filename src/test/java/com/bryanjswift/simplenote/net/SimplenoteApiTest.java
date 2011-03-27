package com.bryanjswift.simplenote.net;

import android.test.AndroidTestCase;
import com.bryanjswift.simplenote.Constants;
import com.bryanjswift.simplenote.model.Credentials;
import junit.framework.TestCase;
import org.apache.http.HttpStatus;

/** @author bryanjswift */
public class SimplenoteApiTest extends AndroidTestCase {
    public static final String LOGGING_TAG = Constants.TAG + "SwiftNoteApiTest";
    public static final String USER_AGENT = "SimplenoteJavaApiTest/1.0";
    public final Credentials credentials = new Credentials(null, "swiftnote@bryanjswift.com", "simplenote1234");
    public final Credentials badCredentials = new Credentials(null, "swiftnote@bryanjswift.com", "bad password");
    private final SimplenoteApi api = new SimplenoteApi(USER_AGENT);

    public void testSuccessfulLogin() {
        final Credentials creds = credentials;
        final ApiResponse<Credentials> response = api.login(creds.email, creds.password);
        assertEquals("Should return with OK status", HttpStatus.SC_OK, response.status);
        assertEquals("Email addresses must match", response.payload.email, creds.email);
        assertTrue("Payload auth should not be empty", response.payload.auth.length() > 0);
        assertNull("Password should not be returned", response.payload.password);
    }

    public void testFailingLogin() {
        final Credentials creds = badCredentials;
        final ApiResponse<Credentials> response = api.login(creds.email, creds.password);
        assertEquals("Should return with OK status", HttpStatus.SC_UNAUTHORIZED, response.status);
        assertEquals("Email addresses must match", response.payload.email, creds.email);
        assertNull("Password should not be returned", response.payload.password);
    }
}
