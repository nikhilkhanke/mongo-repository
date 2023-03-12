package com.nk.pagination.utils;

public class Constants {

    public static class General {
        public static final String DEFAULT_PAGE_LIMIT = "10";
        public static final String DEFAULT_PAGE = "0";
        public static final long MIN_OFFSET = 0;
        public static final long MAX_PAGE_LIMIT = 100;
        public static final long MIN_PAGE_LIMIT = 1;

    }
    public static class Errors {
        public static final String PAGE_OUT_OF_BOUND = "page value should be greater than or equal to 0";
        public static final String INVALID_PAGE_LIMIT = "Page limit value should be between 1 and 100";
    }
}
