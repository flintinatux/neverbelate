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

package com.madhackerdesigns.neverbelate.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Defines a contract between the NeverLate Alerts content provider and its clients. A contract defines the
 * information that a client needs to access the provider as one or more data tables. A contract
 * is a public, non-extendable (final) class that contains constants defining column names and
 * URIs. A well-written client depends only on the constants in the contract.
 */
public final class AlertsContract {
    public static final String AUTHORITY = "com.madhackerdesigns.neverbelate.provider.AlertsContract";

    // This class cannot be instantiated
    private AlertsContract() {
    }

    /**
     * NeverLate Alerts table contract
     */
    public static final class Alerts implements BaseColumns {

        // This class cannot be instantiated
        private Alerts() {}

        /**
         * The table name offered by this provider
         */
        public static final String TABLE_NAME = "alerts";

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
        private static final String PATH_ALERTS = "/alerts";

        /**
         * Path part for the Note ID URI
         */
        private static final String PATH_ALERT_ID = "/alerts/";

        /**
         * 0-relative position of a note ID segment in the path part of a note ID URI
         */
        public static final int ALERT_ID_PATH_POSITION = 1;

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
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.neverlate.alert";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
         * note.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.neverlate.alert";

        /**
         * The default sort order for this table (by instance begin time, sooner to later)
         */
        public static final String DEFAULT_SORT_ORDER = "begin ASC";

        /*
         * Column definitions 
         */

        /**
         * Column name for the title of the event
         * <P>Type: TEXT (String from instance.getString(CalendarHelper.PROJ_EVENT_ID))</P>
         */
        public static final String EVENT_ID = "event_id";
        
        /**
         * Column name for the calendar color (just to be pretty)
         * <P>Type: INTEGER (int from instance.getInt(CalendarHelper.PROJ_COLOR))</P>
         */
        public static final String CALENDAR_COLOR = "calendar_color";
        
        /**
         * Column name for the title of the event
         * <P>Type: TEXT (String from instance.getString(CalendarHelper.PROJ_TITLE))</P>
         */
        public static final String TITLE = "title";

        /**
         * Column name of the event instance begin time
         * <P>Type: INTEGER (long from instance.getLong(CalendarHelper.PROJ_BEGIN))</P>
         */
        public static final String BEGIN = "begin";

        /**
         * Column name for the event instance end time
         * <P>Type: INTEGER (long from instance.getLong(CalendarHelper.PROJ_END))</P>
         */
        public static final String END = "end";

        /**
         * Column name for the event location
         * <P>Type: TEXT (String from instance.getString(CalendarHelper.PROJ_EVENT_LOCATION))</P>
         */
        public static final String LOCATION = "location";
        
        /**
         * Column name for the event description
         * <P>Type: TEXT (String from instance.getString(CalendarHelper.PROJ_DESCRIPTION))</P>
         */
        public static final String DESCRIPTION = "description";
        
        /**
         * Column name for the travel duration time to the event location
         * <P>Type: INTEGER (long from leg.getJSONObject("duration").getLong("value") * 1000)</P>
         */
        public static final String DURATION = "duration";
        
        /**
         * Column name for the copyright of the maps data
         * <P>Type: TEXT (String from route.getString("copyrights")</P>
         */
        public static final String COPYRIGHTS = "copyrights";
        
        /**
         * Column name for the json-formatted directions to the event location
         * <P>Type: TEXT (String from NetUtils.DownloadText(url))</P>
         */
        public static final String JSON = "json";
        
        /**
         * Column name for the boolean flag noting that alert has been fired
         * <P>Type: INTEGER (Boolean flag: 0 = not fired, 1 = fired)</P>
         */
        public static final String FIRED = "fired";
        
        /**
         * Column name for the boolean flag noting that alert has been dismissed
         * <P>Type: INTEGER (Boolean flag: 0 = not dismissed, 1 = dismissed)</P>
         */
        public static final String DISMISSED = "dismissed";
    }
}