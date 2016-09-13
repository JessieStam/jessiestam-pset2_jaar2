package jstam.jessiestam_pset2_jaar2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
     * when start button is clicked, open Second Activity
     */
    public void getStarted(View view) {

        Intent startApp = new Intent(this, SecondActivity.class);
        startActivity(startApp);

        finish();
    }
}
