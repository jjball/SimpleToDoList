package com.jadenball.simpletodolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

// Popup menu class
public class Pop extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.85), (int) (height * 0.15));


        // LAUNCHES USERS KEYBOARD WHEN POPUP WINDOW IS LOADED
        EditText usersTask = (EditText) findViewById(R.id.editTextUserTask);
        usersTask.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }



    public void cancelPopUpActivity(View view) {
        removeKeyboardFocus();
        setResult(RESULT_CANCELED);
        finish();
    }

    public void addTaskToList(View view) {

        EditText usersTask = (EditText) findViewById(R.id.editTextUserTask);
        removeKeyboardFocus();
        String txtOfTaskToAdd = String.valueOf(usersTask.getText());

        Intent goingBack = new Intent();
        goingBack.putExtra("UsersTask", txtOfTaskToAdd);

        setResult(RESULT_OK, goingBack);

        finish();
    }

    public void removeKeyboardFocus(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText usersTask = (EditText) findViewById(R.id.editTextUserTask);

        imm.hideSoftInputFromWindow(usersTask.getWindowToken(), 0);
        /*if(this.getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        else{
            usersTask.requestFocus();
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }*/
    }

}
