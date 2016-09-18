package jstam.jessiestam_pset2_jaar2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

/*
 * Mad Libs - Third Activity
 *
 * Jessie Stam
 *
 * Gets the story string from Second Activity and prints the final story to the screen. Allows the
 * user to create a new story by clicking a button and restarting Second Activity.
 */

public class ThirdActivity extends SecondActivity {

    String finalStory;
    TextView final_story;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // get story string from Second Activity
        Bundle extras = getIntent().getExtras();
        finalStory = extras.getString("finalStory");

        final_story = (TextView) findViewById(R.id.final_story);

        // check if API 24 or API 23 is used, set Html accordingly to print words from user bold
        if (Build.VERSION.SDK_INT >= 24) {
            final_story.setText(Html.fromHtml(finalStory, Html.FROM_HTML_MODE_LEGACY));
        } else {
            final_story.setText(Html.fromHtml(finalStory));
        }
    }

    /*
     * When button is clicked, restart Second Activity to create a new story.
     */
    public void makeNewStory(View newStoryView) {

        // clear Story object to make space for new story
        story.clear();

        // open Second Activity to make a new story
        Intent newStory = new Intent(this, SecondActivity.class);
        startActivity(newStory);

        // finish this Activity
        finish();
    }
}
