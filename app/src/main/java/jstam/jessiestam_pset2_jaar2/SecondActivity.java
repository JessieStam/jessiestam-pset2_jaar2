package jstam.jessiestam_pset2_jaar2;

import android.os.Bundle;

import java.io.InputStream;

/**
 * Created by Jessie on 13/09/2016.
 */
public class SecondActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        // define textfiles InputStreams
        InputStream madlib0 = getResources().openRawResource(R.raw.madlib0_simple);
        InputStream madlib1 = getResources().openRawResource(R.raw.madlib1_tarzan);
        InputStream madlib2 = getResources().openRawResource(R.raw.madlib2_university);
        InputStream madlib3 = getResources().openRawResource(R.raw.madlib3_clothes);
        InputStream madlib4 = getResources().openRawResource(R.raw.madlib4_dance);


    }

}
