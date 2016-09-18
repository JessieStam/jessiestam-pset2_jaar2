package jstam.jessiestam_pset2_jaar2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Mad Libs - Main Activity
 *
 * Jessie Stam
 *
 * Shows the user instructions on how to use this application, moves to Second Activity when start
 * button is clicked.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
     * Opens Second Activity when start button is clicked.
     */
    public void getStarted(View instructionsView) {

        Intent startApp = new Intent(this, SecondActivity.class);
        startActivity(startApp);
    }
}
