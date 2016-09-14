package com.jadenball.simpletodolist;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;


public class PopDeleteItem extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popdeletetask);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout( (int)(width * 0.6), (int)(height * 0.15));

    }

    //When user presses the cancel button on the dialog, sets the result to cancelled to do nothing
    public void cancelDeleteTask(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    //When user presses the delete button, send the ok in the result to delete the string from the list/Database
    public void deleteTheTask(View view) {
        setResult(RESULT_OK);
        finish();
    }
}
