package com.bryanjswift.simplenote.net;

import com.google.common.base.Objects;

/** @author bryanjswift */
public class ApiResponse<T> {
    /** Status code of API request */
    public final int status;
    /** Result of API request */
    public final T payload;
    /** Headers from response */
    public final Headers headers;

    /**
     * Create a response with a payload
     * @param status code from response
     * @param payload object from response
     * @param headers object containing Header information from response
     */
    public ApiResponse(final int status, final T payload, final Headers headers) {
        this.status = status;
        this.payload = payload;
        this.headers = headers;
    }

    /**
     * Create a response with no payload
     * @param status code from response
     */
    public ApiResponse(int status) {
        this(status, null, Headers.EMPTY);
    }

    /**
     * Create a response with no Headers
     * @param status code from response
     * @param payload object from response
     */
    public ApiResponse(final int status, final T payload) {
        this(status, payload, Headers.EMPTY);
    }

    /**
     * Create a response with no payload
     * @param status code from response
     * @param headers object containing Header information from response
     */
    public ApiResponse(final int status, final Headers headers) {
      this(status, null, headers);
    }

    /**
     * Clone the response with a new status, keeping other values the same
     * @param status to set on the new response
     * @return ApiResponse with new status
     */
    public ApiResponse<T> clone(int status) {
        return new ApiResponse<T>(status, this.payload, this.headers);
    }

    /**
     * Clone the response with a new payload, keeping other values the same
     * @param payload to set on the new response
     * @return ApiResponse with new payload
     */
    public ApiResponse<T> clone(T payload) {
        return new ApiResponse<T>(this.status, payload, this.headers);
    }

    /**
     * Return an ApiResponse with an empty (null) payload and the specified status code
     * @param status to set as the response status code
     * @param <K> type to use when creating the ApiResponse
     * @return an empty ApiResponse with status code specified
     */
    public static <K> ApiResponse<K> empty(final int status) {
        return new ApiResponse<K>(status, null, Headers.EMPTY);
    }

    /**
     * Show ApiResponse info in a hopefully concise manner
     * @return String representation of ApiResponse
     */
    public String toString() {
        final Objects.ToStringHelper helper = Objects.toStringHelper(this);
        helper.addValue(this.status);
        helper.addValue(this.payload);
        helper.addValue(this.headers);
        return helper.toString();
    }
}

