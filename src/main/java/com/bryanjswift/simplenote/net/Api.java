package com.bryanjswift.simplenote.net;

import com.bryanjswift.simplenote.util.Base64;
import com.bryanjswift.simplenote.util.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

/** @author bryanjswift */
public class Api {
    /** Logger for Api */
    private static final Logger logger = LoggerFactory.getLogger(Api.class);
    private Api() { }

    /**
     * Sends an HTTP POST request
     *
     * @param ua User-Agent to send as header
     * @param url to connect to
     * @param data to send in POST body
     * @return Response object containing status code and response body
     */
    public static ApiResponse<String> Post(final String ua, final String url, final String data) {
        final HttpClient client = new DefaultHttpClient();
        ApiResponse<String> apiResponse = new ApiResponse<String>(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        try {
            final URI uri = new URI(url);
            final HttpPost post = new HttpPost(uri);
            post.setEntity(new StringEntity(data));
            post.addHeader(new BasicHeader("User-Agent", ua));
            final HttpResponse response = client.execute(post);
            final HttpEntity entity = response.getEntity();
            final int status = response.getStatusLine().getStatusCode();
            logger.info("API (POST) call to {} returned with {} status", uri.toString(), status);
            apiResponse = new ApiResponse<String>(status, IOUtils.slurp(entity.getContent()), extractHeaders(response));
            logger.debug("API (POST) call to {} had response -- {}", uri.toString(), apiResponse.payload);
        } catch (URISyntaxException urise) {
            logger.error("Couldn't create URI", urise);
        } catch (UnsupportedEncodingException uee) {
            logger.error("Encountered unsupported encoding", uee);
        } catch (ClientProtocolException cpe) {
            logger.error("Wrong protocol", cpe);
        } catch (IOException ioe) {
            logger.error("Something bad happened", ioe);
        }
        return apiResponse;
    }
    /**
     * Sends an HTTP GET request
     *
     * @param ua User-Agent to send as header
     * @param url to connect to
     * @return Response object containing status code and response body
     */
    public static ApiResponse<String> Get(final String ua, final String url) {
        final HttpClient client = new DefaultHttpClient();
        ApiResponse<String> apiResponse = new ApiResponse<String>(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        try {
            final URI uri = new URI(url);
            final HttpGet get = new HttpGet(uri);
            get.addHeader(new BasicHeader("User-Agent", ua));
            final HttpResponse response = client.execute(get);
            final HttpEntity entity = response.getEntity();
            final int status = response.getStatusLine().getStatusCode();
            logger.info("API (GET) call to {} returned with {} status", uri.toString(), status);
            final String body = status == HttpStatus.SC_OK ? IOUtils.slurp(entity.getContent()) : null;
            apiResponse = new ApiResponse<String>(status, body, extractHeaders(response));
            logger.debug("API (GET) call to {} had response -- {}", uri.toString(), apiResponse.payload);
        } catch (URISyntaxException urise) {
            logger.error("Couldn't create URI", urise);
        } catch (UnsupportedEncodingException uee) {
            logger.error("Encountered unsupported encoding", uee);
        } catch (ClientProtocolException cpe) {
            logger.error("Wrong protocol", cpe);
        } catch (IOException ioe) {
            logger.error("Something bad happened", ioe);
        }
        return apiResponse;
    }

    /**
     * Sends an HTTP DELETE request
     *
     * @param ua User-Agent to send as header
     * @param url to connect to
     * @return Response object containing status code and response body
     */
    public static ApiResponse<String> Delete(final String ua, final String url) {
        final HttpClient client = new DefaultHttpClient();
        ApiResponse<String> apiResponse = new ApiResponse<String>(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        try {
            final URI uri = new URI(url);
            final HttpDelete delete = new HttpDelete(uri);
            delete.addHeader(new BasicHeader("User-Agent", ua));
            final HttpResponse response = client.execute(delete);
            final HttpEntity entity = response.getEntity();
            final int status = response.getStatusLine().getStatusCode();
            logger.info("API (DELETE) call to {} returned with {} status", uri.toString(), status);
            final String body = status == HttpStatus.SC_OK ? IOUtils.slurp(entity.getContent()) : null;
            apiResponse = new ApiResponse<String>(status, body, extractHeaders(response));
            logger.debug("API (DELETE) call to {} had response -- {}", uri.toString(), apiResponse.payload);
        } catch (URISyntaxException urise) {
            logger.error("Couldn't create URI", urise);
        } catch (UnsupportedEncodingException uee) {
            logger.error("Encountered unsupported encoding", uee);
        } catch (ClientProtocolException cpe) {
            logger.error("Wrong protocol", cpe);
        } catch (IOException ioe) {
            logger.error("Something bad happened", ioe);
        }
        return apiResponse;
    }

    /**
     * Create a map of header names to header values from an HttpResponse
     * @param response to create header map from
     * @return map of header name to value list
     */
    public static Headers extractHeaders(final HttpResponse response) {
        return new Headers(response.getAllHeaders());
    }
    /**
     * Quick way to base64 and URL encode a String
     * @param str to encode
     * @return URL encoded String
     */
    public static String encode(String str) {
        return encode(str, true, true);
    }
    /**
     * Encode a String, optionally with base64, optionally with URL encoding
     * @param str to encode
     * @param base64Encode - whether or not to use base64 encoding
     * @param urlEncode - whether or not to use URL encoding
     * @return encoded String
     */
    public static String encode(String str, boolean base64Encode, boolean urlEncode) {
        if (urlEncode) {
            try {
                return base64Encode ? Base64.encodeBytes(URLEncoder.encode(str, "UTF-8").getBytes()) : URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return base64Encode ? Base64.encodeBytes(URLEncoder.encode(str).getBytes()) : URLEncoder.encode(str);
            }
        } else {
            return base64Encode ? Base64.encodeBytes(str.getBytes()) : str;
        }
    }
}
