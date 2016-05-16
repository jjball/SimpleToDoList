package com.jadenball.simpletodolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class TaskdbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public String CREATE_QUERY = "CREATE TABLE " + TaskData.TaskInfo.TABLE_NAME + "(" + TaskData.TaskInfo.SPECIFIC_TASKS + " TEXT" + ");";


    public TaskdbHelper(Context context){
        super(context, TaskData.TaskInfo.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){

    }

    public void putInformation(TaskdbHelper dataOperation, String task){

        SQLiteDatabase SQ = dataOperation.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TaskData.TaskInfo.SPECIFIC_TASKS, task);
        long k = SQ.insert(TaskData.TaskInfo.TABLE_NAME, null, cv);

        Log.v("Row#", String.valueOf(k));
    }

    public String getInformation(TaskdbHelper dataOperation, int indexOfItemToGrab){

        SQLiteDatabase SQ = dataOperation.getReadableDatabase();




        Cursor c = SQ.query(
                TaskData.TaskInfo.TABLE_NAME,       // NAME OF THE TABLE
                null,                         // ARRAY OF STRINGS OF ALL COLUMN NAMES
                null,                              // COLUMN TO BE ACCESSED
                null,                              // STRING/ITEM TO BE ACCESSED
                null,
                null,
                null
        );
        c.moveToFirst();
        Log.v("cCount", String.valueOf(c.getCount()));

        if(c.getCount() == indexOfItemToGrab || c.getCount() == 0){
            Log.v("InsertF", String.valueOf(indexOfItemToGrab));
            return null;
        }
        else {

            c.moveToPosition(indexOfItemToGrab);

            String tempString = c.getString(c.getColumnIndexOrThrow(TaskData.TaskInfo.SPECIFIC_TASKS));

            //Log.v("String:", tempString);
            c.close();
            return tempString;

        }

    }

    public void deleteTaskFromDB(TaskdbHelper dataOperation, String task){
        SQLiteDatabase SQ = dataOperation.getWritableDatabase();

        String selection = TaskData.TaskInfo.SPECIFIC_TASKS + " LIKE ?";
        String[] selectionArgs = {task};

        SQ.delete(TaskData.TaskInfo.TABLE_NAME, selection, selectionArgs);
    }


}