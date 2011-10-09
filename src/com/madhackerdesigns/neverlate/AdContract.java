/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.madhackerdesigns.neverlate;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Defines a contract between the NeverLate Ad content provider and its clients. A contract defines the
 * information that a client needs to access the provider as one or more data tables. A contract
 * is a public, non-extendable (final) class that contains constants defining column names and
 * URIs. A well-written client depends only on the constants in the contract.
 */
public final class AdContract {
    public static final String AUTHORITY = "com.madhackerdesigns.neverlate.AdsContract";

    // This class cannot be instantiated
    private AdContract() {
    }

    /**
     * NeverLate Alerts table contract
     */
    public static final class Ads implements BaseColumns {

        // This class cannot be instantiated
        private Ads() {}

        /**
         * The table name offered by this provider
         */
        public static final String TABLE_NAME = "ads";

        /*
         * URI definitions
         */

        /**
         * The scheme part for this provider's URI
         */
        private static final String SCHEME = "content://";

        /**
         * Path parts for the URIs
         */

        /**
         * Path part for the Notes URI
         */
        private static final String PATH_ALERTS = "/ads";

        /**
         * Path part for the Note ID URI
         */
        private static final String PATH_ALERT_ID = "/ads/";

        /**
         * 0-relative position of an ad ID segment in the path part of a ad ID URI
         */
        public static final int AD_ID_PATH_POSITION = 1;

        /**
         * Path part for the Live Folder URI
         */
//        private static final String PATH_LIVE_FOLDER = "/live_folders/alerts";

        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI =  Uri.parse(SCHEME + AUTHORITY + PATH_ALERTS);

        /**
         * The content URI base for a single note. Callers must
         * append a numeric note id to this Uri to retrieve a note
         */
        public static final Uri CONTENT_ID_URI_BASE
            = Uri.parse(SCHEME + AUTHORITY + PATH_ALERT_ID);

        /**
         * The content URI match pattern for a single note, specified by its ID. Use this to match
         * incoming URIs or to construct an Intent.
         */
        public static final Uri CONTENT_ID_URI_PATTERN
            = Uri.parse(SCHEME + AUTHORITY + PATH_ALERT_ID + "/#");

        /**
         * The content Uri pattern for a notes listing for live folders
         */
//        public static final Uri LIVE_FOLDER_URI
//            = Uri.parse(SCHEME + AUTHORITY + PATH_LIVE_FOLDER);

        /*
         * MIME type definitions
         */

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of notes.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.neverlate.ad";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
         * note.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.neverlate.ad";

        /**
         * The default sort order for this table (by instance begin time, sooner to later)
         */
        public static final String DEFAULT_SORT_ORDER = "current ASC";

        /*
         * Column definitions 
         */

        /**
         * Column name for the current number of warnings dismissed
         * <P>Type: INTEGER </P>
         */
        public static final String CURRENT = "current";
        
        /**
         * Column name for the next time to show an ad
         * <P>Type: INTEGER </P>
         */
        public static final String AD_NEXT = "ad_next";
        
        /**
         * Column name for the last time an ad was shown
         * <P>Type: INTEGER </P>
         */
        public static final String AD_LAST = "ad_last";

        /**
         * Column name of the previous fibonacci number
         * <P>Type: INTEGER </P>
         */
        public static final String FIB_PREV = "fib_prev";

        /**
         * Column name for the next fibonacci number
         * <P>Type: INTEGER </P>
         */
        public static final String FIB_NEXT = "fib_next";

        /**
         * Column name for the next time that registration will be requested
         * <P>Type: INTEGER </P>
         */
        public static final String REGISTER_NEXT = "register_next";
        
        /**
         * Column name for whether the user has registered yet  
         * <P>Type: INTEGER (boolean) </P>
         */
        public static final String IS_REGISTERED = "is_registered";
        
        /**
         * Column name for the next version of the registration request copy
         * <P>Type: INTEGER </P>
         */
        public static final String REQUEST_NEXT = "request_next";
        
    }
}