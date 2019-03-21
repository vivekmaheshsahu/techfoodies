package tech.foodies.app.techfoodies.database;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;

import tech.foodies.ins_armman.techfoodies.utility.Constants;
import tech.foodies.ins_armman.techfoodies.utility.utility;

import java.util.Arrays;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public class LocalDataSource {

    private final SQLiteDatabase mSQLiteDatabase;

    public LocalDataSource(SQLiteDatabase sqLiteDatabase) {
        mSQLiteDatabase = sqLiteDatabase;
    }

    public long insert(String tableName, ContentValues contentValues) {
        Log.e("Attendance", " in function insert Inserted data in table:  " + tableName + " " + contentValues.toString() + "");
        long newRowId = mSQLiteDatabase.insert(tableName, null, contentValues);
        return newRowId;
    }

    public Cursor read(String tableName, String projection[], String selection, String selectionArgs[], String sortOrder) {
        Cursor c = null;
        try {
            c = mSQLiteDatabase.query(
                    tableName,                     // The table to query
                    projection,                    // The columns to return
                    selection,                     // The columns for the WHERE clause
                    selectionArgs,                 // The values for the WHERE clause
                    null,                          // don't group the rows
                    null,                          // don't filter by row groups
                    sortOrder                      // The sort order
            );
        } catch (SQLiteException e) {
            if (e.getMessage().contains("no such table")) {
                // create table
                // re-run query, etc.
            }
        }
        return c;
    }

    public Cursor read(boolean distinct,
                       String table,
                       String[] columns,
                       String selection,
                       String[] selectionArgs,
                       String groupBy,
                       String having,
                       String orderBy,
                       String limit) {
        Cursor c = null;

        if (table != null) {
            c = mSQLiteDatabase.query(
                    distinct,
                    table,
                    columns,
                    selection,
                    selectionArgs,
                    groupBy,
                    having,
                    orderBy,
                    limit
            );
        }

        return c;
    }

    public Cursor read(Bundle bundle) {
        Cursor cursor = read(
                bundle.getBoolean(Constants.QUERY_ARGS_DISTINCT),
                bundle.getString(Constants.QUERY_ARGS_TABLE_NAME),
                bundle.getStringArray(Constants.QUERY_ARGS_PROJECTION),
                bundle.getString(Constants.QUERY_ARGS_SELECTION),
                bundle.getStringArray(Constants.QUERY_ARGS_SELECTION_ARGS),
                bundle.getString(Constants.QUERY_ARGS_GROUP_BY),
                null,
                bundle.getString(Constants.QUERY_ARGS_ORDER_BY),
                null
        );

        return cursor;
    }

    public Cursor rawQuery(String query) {
        Log.e("Attendance", " in rawQuery() query --> " + query);
        Cursor cursor = null;
        if (query != null) {
            cursor = utility.getDatabase().rawQuery(query, null);

        }
        return cursor;
    }

    public Cursor rawQuery(String query, String[] selectionArgs) {
        System.out.println(query + " " + Arrays.toString(selectionArgs));
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = null;
        if (query != null) {
            cursor = sqLiteDatabase.rawQuery(query, selectionArgs);
        }
        return cursor;
    }

    public int delete(String tableName, String selection /*where' part of query*/, String[] selectionArgs /*placeholders for where clause*/) {
        return mSQLiteDatabase.delete(tableName, selection, selectionArgs);
    }

    public int update(String tableName, ContentValues values, String selection, String[] selectionArgs) {
        Log.e("Attendance", " in function insert Updated data in table:  " + tableName + " " + values.toString() + " " + selection + "   " + selectionArgs);
        int count = mSQLiteDatabase.update(
                tableName,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public enum QueryType {
        RAW,
        FUNCTION

    }
}
