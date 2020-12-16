package com.srizan.dinlipi.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class DinlipiContract {

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.srizan.dinlipi";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.srizan.dinlipi/notes/ is a valid path for
     * looking at pet data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_NOTES = "notes";


    public static final class NoteEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NOTES);

        //Table Name
        public final static String TABLE_NAME = "notes";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NOTE_TITLE ="title";
        public final static String COLUMN_NOTE_TEXT = "text";
    }
}
