package com.jadenball.simpletodolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    List<String> taskList = new ArrayList<String>();
    ArrayAdapter<String> theAdapter;
    ListView theListView;
    int positionInListView;
    Context ctx = this;
    //private DatabaseHandler taskData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadTasksFromDB();

        //theAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, taskList); // If using given library
        theAdapter = new MyAdapter(this, taskList);

        theListView = (ListView) findViewById(R.id.theListView);
        theListView.setAdapter(theAdapter);


        theListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                positionInListView = position;
                promptDeleteTask(view);
                return false;
            }
        });

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        if(id == R.id.exit_the_app){
            finish();
            return true;
        }
        else if(id == R.id.action_NewTask){
            addTaskToDo();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    protected void addTaskToDo(){
        Intent getPopUpScreen = new Intent(MainActivity.this, Pop.class);

        final int result = 1;
        //startActivity(new Intent(MainActivity.this, Pop.class));

        startActivityForResult(getPopUpScreen, result);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                String tempTask = data.getStringExtra("UsersTask");

                taskList.add(tempTask);

                TaskdbHelper DB = new TaskdbHelper(ctx);

                DB.putInformation(DB, tempTask);

            }
            else if(requestCode == 2){
                TaskdbHelper DB = new TaskdbHelper(ctx);

                DB.deleteTaskFromDB(DB, taskList.get(positionInListView));

                taskList.remove(positionInListView);
            }

            updateListViewData();

        }
    }

    protected void updateListViewData(){
        theAdapter.notifyDataSetChanged();
    }

    public void promptDeleteTask(View view) {
        Intent getPopUpDelScreen = new Intent(MainActivity.this, PopDeleteItem.class);

        final int result = 2;

        startActivityForResult(getPopUpDelScreen, result);

    }

    protected void loadTasksFromDB(){
        Log.v("Start loadTasksFromDB", "success");
        int x = 0;
        String tempString;
        TaskdbHelper DB = new TaskdbHelper(ctx);


        tempString = DB.getInformation(DB, x);

        while (tempString != null) {
            taskList.add(tempString);
            x++;
            tempString = DB.getInformation(DB, x);

        }
    }
}
