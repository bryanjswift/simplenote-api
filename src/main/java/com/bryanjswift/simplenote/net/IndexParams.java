package com.bryanjswift.simplenote.net;

import com.bryanjswift.simplenote.Constants;
import org.joda.time.DateTime;

/**
 * Represents the parameters that can be sent to the index or list API call
 *
 * @author bryanjswift
 */
public class IndexParams {
    protected final String mark;
    protected final DateTime since;
    protected final int length;

    protected static final IndexParams DEFAULT = Builder.length(Constants.DEFAULT_INDEX_LENGTH);

    /**
     * Private constructor to set all values
     * @param mark to set
     * @param since to set
     * @param length to set
     */
    private IndexParams(final String mark, final DateTime since, final int length) {
        this.mark = mark;
        this.since = since;
        this.length = length;
    }

    /**
     * Get new IndexParams with mark updated
     * @param mark to set on new IndexParams
     * @return IndexParams with mark properly set and since/length carried over
     */
    public IndexParams mark(final String mark) {
        return new IndexParams(mark, this.since, this.length);
    }

    /**
     * Get new IndexParams with since updated
     * @param since to set on new IndexParams
     * @return IndexParams with since properly set and mark/length carried over
     */
    public IndexParams since(final DateTime since) {
        return new IndexParams(this.mark, since, this.length);
    }

    /**
     * Get new IndexParams with length updated
     * @param length to set on new IndexParams
     * @return IndexParams with length properly set and mark/since carried over
     */
    public IndexParams length(final int length) {
        return new IndexParams(this.mark, this.since, length);
    }

    /**
     * Builder class to be able to statically generate IndexParams
     *
     * @author bryanjswift
     */
    public static class Builder {
        /**
         * Get new IndexParams with mark set
         * @param mark to set on new IndexParams
         * @return IndexParams with mark properly set and since/length set to defaults
         */
        public static IndexParams mark(final String mark) {
            return new IndexParams(mark, null, Constants.DEFAULT_INDEX_LENGTH);
        }
        /**
         * Get new IndexParams with since set
         * @param since to set on new IndexParams
         * @return IndexParams with since properly set and mark/length set to defaults
         */
        public static IndexParams since(final DateTime since) {
            return new IndexParams(null, since, Constants.DEFAULT_INDEX_LENGTH);
        }
        /**
         * Get new IndexParams with length set
         * @param length to set on new IndexParams
         * @return IndexParams with length properly set and mark/since set to defaults
         */
        public static IndexParams length(final int length) {
            return new IndexParams(null, null, length);
        }
    }
}
