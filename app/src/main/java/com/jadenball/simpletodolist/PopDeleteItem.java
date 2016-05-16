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

        //void cancelPopUpActivity(){
        //  Pop.this.finish();
        // }

    }

    public void cancelDeleteTask(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void deleteTheTask(View view) {
        setResult(RESULT_OK);
        finish();
    }
}
