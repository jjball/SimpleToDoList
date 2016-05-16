package com.jadenball.simpletodolist;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.List;

public final class TaskData {

    public TaskData(){

    }

    public static abstract class TaskInfo implements BaseColumns {

        public static final String DATABASE_NAME = "UserTasks.db";
        public static final String TABLE_NAME = "user_tasks_table";
        public static final String SPECIFIC_TASKS = "specific_tasks";



    }

}
