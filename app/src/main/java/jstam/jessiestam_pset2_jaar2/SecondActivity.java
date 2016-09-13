package jstam.jessiestam_pset2_jaar2;

import android.os.Bundle;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Random;

import static jstam.jessiestam_pset2_jaar2.Story.*;

/**
 * Second Activity
 *
 * Prompts the user for words.
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


        // construct a new story
        Story story = new Story(madlib0);

        // get a random integer between 0 and 4
        Random randomStoryNum = new Random();
        int storyNum = randomStoryNum.nextInt(5);


        // define story at random using random integer

        // String storyTitle = "madlib" + storyNum;
        //storyName = new Story(storyTitle);

        switch(storyNum) {
            case (0):
                story = new Story(madlib0);
                story.read(madlib0);
                break;
            case (1):
                story = new Story(madlib1);
                story.read(madlib1);
                break;
            case (2):
                story = new Story(madlib2);
                story.read(madlib2);
                break;
            case (3):
                story = new Story(madlib3);
                story.read(madlib3);
                break;
            case (4):
                story = new Story(madlib4);
                story.read(madlib4);
                break;
        }

        // REMEMBER storyNum zodat je niet meteen hetzelfde verhaaltje krijgt

        // get total amount of placeholders and print to screen
        Integer totalPlaceholders = story.getPlaceholderCount();

        TextView wordsLeft = (TextView) findViewById(R.id.amount_left);
        //wordsLeft.append(totalPlaceholders.toString() + " ");

        //textview.setText(" append string" + textView.getText());

        String words_left = wordsLeft.getText().toString();
        wordsLeft.setText(totalPlaceholders + " " + words_left);

        // get word kind and print to screen
        String nextPlaceholder = story.getNextPlaceholder();

        TextView wordKind = (TextView) findViewById(R.id.word_kind);
        wordKind.append(" " + nextPlaceholder);

    }

}
