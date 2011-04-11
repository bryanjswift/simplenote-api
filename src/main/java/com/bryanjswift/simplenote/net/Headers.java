package com.bryanjswift.simplenote.net;

import com.google.common.collect.ImmutableList;
import org.apache.http.Header;

import java.util.EmptyStackException;
import java.util.List;

/**
 * Meant to mimic the interface of getting Header data from a org.apache.http.HttpResponse
 *
 * @author bryanjswift
 */
public class Headers {
    public static final Headers EMPTY = new Headers(new Header[] {});
    private final Header[] headers;

    /**
     * Create an instance of Headers from an Array of HTTP Headers
     * @param headers to be stored for later access
     */
    public Headers(final Header[] headers) {
        this.headers = headers;
    }

    /**
     * Get the value of the first Header encountered with a given name
     * @param name of Header to retrieve value for
     * @return first Header value encountered for provided name
     */
    public String get(final String name) {
        for (final Header h : headers) {
            if (h.getName().equals(name)) {
                return h.getValue();
            }
        }
        return null;
    }

    /**
     * Retrieve a List of values for a given Header name
     * @param name of Headers to look for
     * @return List of String values for a given header name
     */
    public List<String> getAll(final String name) {
        final ImmutableList.Builder<String> b = ImmutableList.builder();
        for (final Header h : headers) {
            if (h.getName().equals(name)) {
                b.add(h.getValue());
            }
        }
        return b.build();
    }
}
