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

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

/**
 * Provides access to a database of notes. Each note has a title, the note
 * itself, a creation date and a modified data.
 */
public class AlertsProvider extends ContentProvider  {
    // Used for debugging and logging
    private static final String TAG = "AlertsProvider";

    /**
     * The database that the provider uses as its underlying data store
     */
    private static final String DATABASE_NAME = "alerts.db";

    /**
     * The database version
     */
    private static final int DATABASE_VERSION = 5;

    /**
     * A projection map used to select columns from the database
     */
    private static HashMap<String, String> sAlertsProjectionMap;

    /**
     * A projection map used to select columns from the database
     */
//    private static HashMap<String, String> sLiveFolderProjectionMap;

    /*
     * Constants used by the Uri matcher to choose an action based on the pattern
     * of the incoming URI
     */
    // The incoming URI matches the Notes URI pattern
    private static final int ALERTS = 1;

    // The incoming URI matches the Note ID URI pattern
    private static final int ALERT_ID = 2;

    // The incoming URI matches the Live Folder URI pattern
//    private static final int LIVE_FOLDER_ALERTS = 3;

    /**
     * A UriMatcher instance
     */
    private static final UriMatcher sUriMatcher;

    // Handle to a new DatabaseHelper.
    private DatabaseHelper mOpenHelper;


    /**
     * A block that instantiates and sets static objects
     */
    static {

        /*
         * Creates and initializes the URI matcher
         */
        // Create a new instance
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // Add a pattern that routes URIs terminated with "notes" to a NOTES operation
        sUriMatcher.addURI(AlertsContract.AUTHORITY, "alerts", ALERTS);

        // Add a pattern that routes URIs terminated with "notes" plus an integer
        // to a note ID operation
        sUriMatcher.addURI(AlertsContract.AUTHORITY, "alerts/#", ALERT_ID);

        // Add a pattern that routes URIs terminated with live_folders/notes to a
        // live folder operation
//        sUriMatcher.addURI(AlertsContract.AUTHORITY, "live_folders/alerts", LIVE_FOLDER_ALERTS);

        /*
         * Creates and initializes a projection map that returns all columns
         */

        // Creates a new projection map instance. The map returns a column name
        // given a string. The two are usually equal.
        sAlertsProjectionMap = new HashMap<String, String>();

        // Maps the string "_ID" to the column name "_ID"
        sAlertsProjectionMap.put(AlertsContract.Alerts._ID, AlertsContract.Alerts._ID);

        // Maps "event_id" to "event_id"
        sAlertsProjectionMap.put(AlertsContract.Alerts.EVENT_ID, AlertsContract.Alerts.EVENT_ID);

        // Maps "calendar_color" to "calendar_color"
        sAlertsProjectionMap.put(AlertsContract.Alerts.CALENDAR_COLOR, AlertsContract.Alerts.CALENDAR_COLOR);

        // Maps "title" to "title"
        sAlertsProjectionMap.put(AlertsContract.Alerts.TITLE, AlertsContract.Alerts.TITLE);

        // Maps "begin" to "begin"
        sAlertsProjectionMap.put(AlertsContract.Alerts.BEGIN, AlertsContract.Alerts.BEGIN);
        
        // Maps "end" to "end"
        sAlertsProjectionMap.put(AlertsContract.Alerts.END, AlertsContract.Alerts.END);
        
        // Maps "location" to "location"
        sAlertsProjectionMap.put(AlertsContract.Alerts.LOCATION, AlertsContract.Alerts.LOCATION);
        
        // Maps "description" to "description"
        sAlertsProjectionMap.put(AlertsContract.Alerts.DESCRIPTION, AlertsContract.Alerts.DESCRIPTION);
        
        // Maps "duration" to "duration"
        sAlertsProjectionMap.put(AlertsContract.Alerts.DURATION, AlertsContract.Alerts.DURATION);
        
        // Maps "copyrights" to "copyrights"
        sAlertsProjectionMap.put(AlertsContract.Alerts.COPYRIGHTS, AlertsContract.Alerts.COPYRIGHTS);
        
        // Maps "json" to "json"
        sAlertsProjectionMap.put(AlertsContract.Alerts.JSON, AlertsContract.Alerts.JSON);
        
        // Maps "fired" to "fired"
        sAlertsProjectionMap.put(AlertsContract.Alerts.FIRED, AlertsContract.Alerts.FIRED);

        // Maps "dismissed" to "dismissed"
        sAlertsProjectionMap.put(AlertsContract.Alerts.DISMISSED, AlertsContract.Alerts.DISMISSED);

        /*
         * Creates and initializes a projection map for handling Live Folders
         */

//        // Creates a new projection map instance
//        sLiveFolderProjectionMap = new HashMap<String, String>();
//
//        // Maps "_ID" to "_ID AS _ID" for a live folder
//        sLiveFolderProjectionMap.put(LiveFolders._ID, AlertsContract.Alerts._ID + " AS " + LiveFolders._ID);
//
//        // Maps "NAME" to "title AS NAME"
//        sLiveFolderProjectionMap.put(LiveFolders.NAME, AlertsContract.Alerts.TITLE + " AS " +
//            LiveFolders.NAME);
    }

    /**
    *
    * This class helps open, create, and upgrade the database file. Set to package visibility
    * for testing purposes.
    */
   static class DatabaseHelper extends SQLiteOpenHelper {

       DatabaseHelper(Context context) {

           // calls the super constructor, requesting the default cursor factory.
           super(context, DATABASE_NAME, null, DATABASE_VERSION);
       }

       /**
        *
        * Creates the underlying database with table name and column names taken from the
        * NotePad class.
        */
       @Override
       public void onCreate(SQLiteDatabase db) {
           db.execSQL("CREATE TABLE " + AlertsContract.Alerts.TABLE_NAME + " ("
                   + AlertsContract.Alerts._ID + " INTEGER PRIMARY KEY,"
                   + AlertsContract.Alerts.EVENT_ID + " TEXT,"
                   + AlertsContract.Alerts.CALENDAR_COLOR + " INTEGER,"
                   + AlertsContract.Alerts.TITLE + " TEXT,"
                   + AlertsContract.Alerts.BEGIN + " INTEGER,"
                   + AlertsContract.Alerts.END + " INTEGER,"
                   + AlertsContract.Alerts.LOCATION + " TEXT,"
                   + AlertsContract.Alerts.DESCRIPTION + " TEXT,"
                   + AlertsContract.Alerts.DURATION + " INTEGER,"
                   + AlertsContract.Alerts.COPYRIGHTS + " TEXT,"
                   + AlertsContract.Alerts.JSON + " TEXT,"
                   + AlertsContract.Alerts.FIRED + " INTEGER,"
                   + AlertsContract.Alerts.DISMISSED + " INTEGER"
                   + ");");
       }

       /**
        *
        * Demonstrates that the provider must consider what happens when the
        * underlying datastore is changed. In this sample, the database is upgraded
        * by destroying the existing data.
        * A real application should upgrade the database in place.
        */
       @Override
       public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

           // Logs that the database is being upgraded
           Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                   + newVersion + ", which will destroy all old data");

           // Kills the table and existing data
           db.execSQL("DROP TABLE IF EXISTS " + AlertsContract.Alerts.TABLE_NAME);

           // Recreates the database with a new version
           onCreate(db);
       }
   }

   /**
    *
    * Initializes the provider by creating a new DatabaseHelper. onCreate() is called
    * automatically when Android creates the provider in response to a resolver request from a
    * client.
    */
   @Override
   public boolean onCreate() {

       // Creates a new helper object. Note that the database itself isn't opened until
       // something tries to access it, and it's only created if it doesn't already exist.
       mOpenHelper = new DatabaseHelper(getContext());

       // Assumes that any failures will be reported by a thrown exception.
       return true;
   }

   /**
    * This method is called when a client calls
    * {@link android.content.ContentResolver#query(Uri, String[], String, String[], String)}.
    * Queries the database and returns a cursor containing the results.
    *
    * @return A cursor containing the results of the query. The cursor exists but is empty if
    * the query returns no results or an exception occurs.
    * @throws IllegalArgumentException if the incoming URI pattern is invalid.
    */
   @Override
   public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
           String sortOrder) {

       // Constructs a new query builder and sets its table name
       SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
       qb.setTables(AlertsContract.Alerts.TABLE_NAME);

       /**
        * Choose the projection and adjust the "where" clause based on URI pattern-matching.
        */
       switch (sUriMatcher.match(uri)) {
           // If the incoming URI is for notes, chooses the Notes projection
           case ALERTS:
               qb.setProjectionMap(sAlertsProjectionMap);
               break;

           /* If the incoming URI is for a single note identified by its ID, chooses the
            * note ID projection, and appends "_ID = <noteID>" to the where clause, so that
            * it selects that single note
            */
           case ALERT_ID:
               qb.setProjectionMap(sAlertsProjectionMap);
               qb.appendWhere(
                   AlertsContract.Alerts._ID +    // the name of the ID column
                   "=" +
                   // the position of the note ID itself in the incoming URI
                   uri.getPathSegments().get(AlertsContract.Alerts.ALERT_ID_PATH_POSITION));
               break;

//           case LIVE_FOLDER_ALERTS:
//               // If the incoming URI is from a live folder, chooses the live folder projection.
//               qb.setProjectionMap(sLiveFolderProjectionMap);
//               break;

           default:
               // If the URI doesn't match any of the known patterns, throw an exception.
               throw new IllegalArgumentException("Unknown URI " + uri);
       }


       String orderBy;
       // If no sort order is specified, uses the default
       if (TextUtils.isEmpty(sortOrder)) {
           orderBy = AlertsContract.Alerts.DEFAULT_SORT_ORDER;
       } else {
           // otherwise, uses the incoming sort order
           orderBy = sortOrder;
       }

       // Opens the database object in "read" mode, since no writes need to be done.
       SQLiteDatabase db = mOpenHelper.getReadableDatabase();

       /*
        * Performs the query. If no problems occur trying to read the database, then a Cursor
        * object is returned; otherwise, the cursor variable contains null. If no records were
        * selected, then the Cursor object is empty, and Cursor.getCount() returns 0.
        */
       Cursor c = qb.query(
           db,            // The database to query
           projection,    // The columns to return from the query
           selection,     // The columns for the where clause
           selectionArgs, // The values for the where clause
           null,          // don't group the rows
           null,          // don't filter by row groups
           orderBy        // The sort order
       );

       // Tells the Cursor what URI to watch, so it knows when its source data changes
       c.setNotificationUri(getContext().getContentResolver(), uri);
       return c;
   }

   /**
    * This is called when a client calls {@link android.content.ContentResolver#getType(Uri)}.
    * Returns the MIME data type of the URI given as a parameter.
    *
    * @param uri The URI whose MIME type is desired.
    * @return The MIME type of the URI.
    * @throws IllegalArgumentException if the incoming URI pattern is invalid.
    */
   @Override
   public String getType(Uri uri) {

       /**
        * Chooses the MIME type based on the incoming URI pattern
        */
       switch (sUriMatcher.match(uri)) {

           // If the pattern is for notes or live folders, returns the general content type.
           case ALERTS:
//           case LIVE_FOLDER_ALERTS:
               return AlertsContract.Alerts.CONTENT_TYPE;

           // If the pattern is for note IDs, returns the note ID content type.
           case ALERT_ID:
               return AlertsContract.Alerts.CONTENT_ITEM_TYPE;

           // If the URI pattern doesn't match any permitted patterns, throws an exception.
           default:
               throw new IllegalArgumentException("Unknown URI " + uri);
       }
    }

    /**
     * This is called when a client calls
     * {@link android.content.ContentResolver#insert(Uri, ContentValues)}.
     * Inserts a new row into the database. This method sets up default values for any
     * columns that are not included in the incoming map.
     * If rows were inserted, then listeners are notified of the change.
     * @return The row ID of the inserted row.
     * @throws SQLException if the insertion fails.
     */
    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {

        // Validates the incoming URI. Only the full provider URI is allowed for inserts.
        if (sUriMatcher.match(uri) != ALERTS) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // A map to hold the new record's values.
        ContentValues values;

        // If the incoming values map is not null, uses it for the new values.
        if (initialValues != null) {
            values = new ContentValues(initialValues);

        } else {
            // Otherwise, throw NullPointerException
        	throw new NullPointerException("ContentValues initialValues must be provided");
        }

        // If the values map doesn't contain the following values, throw IllegalArgumentException:
        // --> event_id, calendar_color, begin, end, location, duration, json
        if (	values.containsKey(AlertsContract.Alerts.EVENT_ID) == false ||
        		values.containsKey(AlertsContract.Alerts.CALENDAR_COLOR) == false ||
        		values.containsKey(AlertsContract.Alerts.BEGIN) == false ||
        		values.containsKey(AlertsContract.Alerts.END) == false ||
        		values.containsKey(AlertsContract.Alerts.LOCATION) == false ||
        		values.containsKey(AlertsContract.Alerts.DURATION) == false ||
        		values.containsKey(AlertsContract.Alerts.JSON) == false) {
        	
        	throw new IllegalArgumentException("Insert statement must include the following columns: " +
        			"EVENT_ID, CALENDAR_COLOR, BEGIN, END, LOCATION, DURATION, JSON");
        }
        
	    // If the values map doesn't contain a title, sets the value to the default title.
	    if (values.containsKey(AlertsContract.Alerts.TITLE) == false ||
	    		values.getAsString(AlertsContract.Alerts.TITLE) == "") {
	    	Resources r = getContext().getResources();
	    	values.put(AlertsContract.Alerts.TITLE, r.getString(R.string.untitled_event));
	    }
	    
	    // If the values map doesn't contain a description, just include an empty one
	    if (values.containsKey(AlertsContract.Alerts.DESCRIPTION) == false) {
	    	values.put(AlertsContract.Alerts.DESCRIPTION, "");
	    }
	    
	    // If the values map doesn't include the FIRED flag, set it to 0 (false)
	    if (values.containsKey(AlertsContract.Alerts.FIRED) == false) {
	    	values.put(AlertsContract.Alerts.FIRED, 0);
	    }
	    
	    // If the values map doesn't include the DISMISSED flag, set it to 0 (false)
	    if (values.containsKey(AlertsContract.Alerts.DISMISSED) == false) {
	    	values.put(AlertsContract.Alerts.DISMISSED, 0);
	    }
	    
	    // Opens the database object in "write" mode.
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        // Performs the insert and returns the ID of the new note.
        long rowId = db.insert(
        	AlertsContract.Alerts.TABLE_NAME,   // The table to insert into.
        	AlertsContract.Alerts.EVENT_ID,  	// A hack, SQLite sets this column value to null
                                             	// if values is empty.
            values                           	// A map of column names, and the values to insert
                                             	// into the columns.
        );

        // If the insert succeeded, the row ID exists.
        if (rowId > 0) {
            // Creates a URI with the note ID pattern and the new row ID appended to it.
            Uri alertUri = ContentUris.withAppendedId(AlertsContract.Alerts.CONTENT_ID_URI_BASE, rowId);

            // Notifies observers registered against this provider that the data changed.
            getContext().getContentResolver().notifyChange(alertUri, null);
            return alertUri;
        }

        // If the insert didn't succeed, then the rowID is <= 0. Throws an exception.
        throw new SQLException("Failed to insert row into " + uri);
    }

    /**
     * This is called when a client calls
     * {@link android.content.ContentResolver#delete(Uri, String, String[])}.
     * Deletes records from the database. If the incoming URI matches the note ID URI pattern,
     * this method deletes the one record specified by the ID in the URI. Otherwise, it deletes a
     * a set of records. The record or records must also match the input selection criteria
     * specified by where and whereArgs.
     *
     * If rows were deleted, then listeners are notified of the change.
     * @return If a "where" clause is used, the number of rows affected is returned, otherwise
     * 0 is returned. To delete all rows and get a row count, use "1" as the where clause.
     * @throws IllegalArgumentException if the incoming URI pattern is invalid.
     */
    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {

        // Opens the database object in "write" mode.
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String finalWhere;

        int count;

        // Does the delete based on the incoming URI pattern.
        switch (sUriMatcher.match(uri)) {

            // If the incoming pattern matches the general pattern for notes, does a delete
            // based on the incoming "where" columns and arguments.
            case ALERTS:
                count = db.delete(
                	AlertsContract.Alerts.TABLE_NAME,   // The database table name
                    where,                     			// The incoming where clause column names
                    whereArgs                  			// The incoming where clause values
                );
                break;

                // If the incoming URI matches a single note ID, does the delete based on the
                // incoming data, but modifies the where clause to restrict it to the
                // particular note ID.
            case ALERT_ID:
                /*
                 * Starts a final WHERE clause by restricting it to the
                 * desired alert ID.
                 */
                finalWhere =
                        AlertsContract.Alerts._ID +                      // The ID column name
                        " = " +                                          // test for equality
                        uri.getPathSegments().                           // the incoming alert ID
                            get(AlertsContract.Alerts.ALERT_ID_PATH_POSITION)
                ;

                // If there were additional selection criteria, append them to the final
                // WHERE clause
                if (where != null) {
                    finalWhere = finalWhere + " AND " + where;
                }

                // Performs the delete.
                count = db.delete(
                	AlertsContract.Alerts.TABLE_NAME,   // The database table name.
                    finalWhere,                			// The final WHERE clause
                    whereArgs                  			// The incoming where clause values.
                );
                break;

            // If the incoming pattern is invalid, throws an exception.
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        /*Gets a handle to the content resolver object for the current context, and notifies it
         * that the incoming URI changed. The object passes this along to the resolver framework,
         * and observers that have registered themselves for the provider are notified.
         */
        getContext().getContentResolver().notifyChange(uri, null);

        // Returns the number of rows deleted.
        return count;
    }

    /**
     * This is called when a client calls
     * {@link android.content.ContentResolver#update(Uri,ContentValues,String,String[])}
     * Updates records in the database. The column names specified by the keys in the values map
     * are updated with new data specified by the values in the map. If the incoming URI matches the
     * note ID URI pattern, then the method updates the one record specified by the ID in the URI;
     * otherwise, it updates a set of records. The record or records must match the input
     * selection criteria specified by where and whereArgs.
     * If rows were updated, then listeners are notified of the change.
     *
     * @param uri The URI pattern to match and update.
     * @param values A map of column names (keys) and new values (values).
     * @param where An SQL "WHERE" clause that selects records based on their column values. If this
     * is null, then all records that match the URI pattern are selected.
     * @param whereArgs An array of selection criteria. If the "where" param contains value
     * placeholders ("?"), then each placeholder is replaced by the corresponding element in the
     * array.
     * @return The number of rows updated.
     * @throws IllegalArgumentException if the incoming URI pattern is invalid.
     */
    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {

        // Opens the database object in "write" mode.
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        String finalWhere;

        // Does the update based on the incoming URI pattern
        switch (sUriMatcher.match(uri)) {

            // If the incoming URI matches the general alerts pattern, does the update based on
            // the incoming data.
            case ALERTS:

                // Does the update and returns the number of rows updated.
                count = db.update(
                	AlertsContract.Alerts.TABLE_NAME, 	// The database table name.
                    values,                   			// A map of column names and new values to use.
                    where,                    			// The where clause column names.
                    whereArgs                 			// The where clause column values to select on.
                );
                break;

            // If the incoming URI matches a single alert ID, does the update based on the incoming
            // data, but modifies the where clause to restrict it to the particular alert ID.
            case ALERT_ID:
                // From the incoming URI, get the alert ID
                String alertId = uri.getPathSegments().get(AlertsContract.Alerts.ALERT_ID_PATH_POSITION);

                /*
                 * Starts creating the final WHERE clause by restricting it to the incoming
                 * alert ID.
                 */
                finalWhere =
                		AlertsContract.Alerts._ID +        // The ID column name
                        " = " +                            // test for equality
                        alertId;                           // the incoming alert ID
                        

                // If there were additional selection criteria, append them to the final WHERE
                // clause
                if (where !=null) {
                    finalWhere = finalWhere + " AND " + where;
                }


                // Does the update and returns the number of rows updated.
                count = db.update(
                	AlertsContract.Alerts.TABLE_NAME, 	// The database table name.
                    values,                   			// A map of column names and new values to use.
                    finalWhere,               			// The final WHERE clause to use
                                              			// placeholders for whereArgs
                    whereArgs                 			// The where clause column values to select on, or
                                              			// null if the values are in the where argument.
                );
                break;
            // If the incoming pattern is invalid, throws an exception.
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        /*Gets a handle to the content resolver object for the current context, and notifies it
         * that the incoming URI changed. The object passes this along to the resolver framework,
         * and observers that have registered themselves for the provider are notified.
         */
        getContext().getContentResolver().notifyChange(uri, null);

        // Returns the number of rows updated.
        return count;
    }

    /**
     * A test package can call this to get a handle to the database underlying NotePadProvider,
     * so it can insert test data into the database. The test case class is responsible for
     * instantiating the provider in a test context; {@link android.test.ProviderTestCase2} does
     * this during the call to setUp()
     *
     * @return a handle to the database helper object for the provider's data.
     */
    DatabaseHelper getOpenHelperForTest() {
        return mOpenHelper;
    }
}