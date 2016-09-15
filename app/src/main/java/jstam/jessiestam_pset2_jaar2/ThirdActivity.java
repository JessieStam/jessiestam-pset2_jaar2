package jstam.jessiestam_pset2_jaar2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Jessie on 15/09/2016.
 */
public class ThirdActivity extends SecondActivity {

    String finalStory;
    TextView final_story;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Bundle extras = getIntent().getExtras();
        finalStory = extras.getString("finalStory");

        final_story = (TextView) findViewById(R.id.final_story);

        final_story.setText(finalStory);
    }

    public void makeNewStory(View newStoryView) {

        story.clear();
        finish();

    }
}
