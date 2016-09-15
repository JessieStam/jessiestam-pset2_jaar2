package jstam.jessiestam_pset2_jaar2;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import static jstam.jessiestam_pset2_jaar2.Story.*;

/**
 * Second Activity
 *
 * Prompts the user for words.
 */
public class SecondActivity extends MainActivity {

    Story story;
    TextView wordsLeft;
    String words_left;
    TextView wordKind;
    EditText user_input;
    String user_input_string;
    ArrayList<String> filled_in_list;
    String wordsLeftInstruction;
    String wordInstruction;
    String new_words_left_text;
    String new_word_kind_text;
    Integer totalPlaceholdersLeft;
    String nextPlaceholder;

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

        user_input = new EditText(this);

        // get a random integer between 0 and 4
        Random randomStoryNum = new Random();
        int storyNum = randomStoryNum.nextInt(5);

        // define story at random using random integer
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

        // get total amount of placeholders and print to screen with instructions
        totalPlaceholdersLeft = story.getPlaceholderRemainingCount();
        wordsLeft = (TextView) findViewById(R.id.amount_left);
        words_left = totalPlaceholdersLeft.toString();
        wordsLeftInstruction = " word(s) left";
        wordsLeft.append(words_left + wordsLeftInstruction);

        // get placeholder and print to screen with instructions (deze gaat nog niet goed, print eentje dubbel VRAAG)
        nextPlaceholder = story.getNextPlaceholder();
        wordKind = (TextView) findViewById(R.id.word_kind);
        wordInstruction = "Please enter a/an ";
        wordKind.append(wordInstruction + nextPlaceholder.toLowerCase());

        user_input = (EditText) findViewById(R.id.user_input_word);
        user_input.setHint(nextPlaceholder);

        // create list to remember words for when activity is killed
        filled_in_list = new ArrayList<>();

    }

    public void addWord(View addWordView) {

        // get total amount of placeholders and print to screen with instructions
        Integer totalPlaceholdersLeft = story.getPlaceholderRemainingCount() - 1;
        words_left = totalPlaceholdersLeft.toString();
        new_words_left_text = words_left + wordsLeftInstruction;
        wordsLeft.setText(new_words_left_text);

        // get placeholder and print to screen with instructions
        String nextPlaceholder = story.getNextPlaceholder();
        new_word_kind_text = wordInstruction + nextPlaceholder.toLowerCase();
        wordKind.setText(new_word_kind_text);

        // save word from EditText to string
        user_input = (EditText) findViewById(R.id.user_input_word);
        user_input_string = user_input.getText().toString();

        // set EditText hint to placeholder
        user_input.setHint(nextPlaceholder);

        // save word to list for when activity is killed
        filled_in_list.add(user_input_string);

        // input word in story
        story.fillInPlaceholder(user_input_string);

        //clear EditText
        user_input.getText().clear();

        // create boolean to check if isFilledIn returns true
        boolean filledIn = story.isFilledIn();

        // when everything is filled in, move on to third activity to print story
        if (filledIn) {
            // create final story String
            String finalStory = story.toString();

            // open third activity to display story
            Intent printStory = new Intent(this, ThirdActivity.class);
            printStory.putExtra("finalStory", finalStory);

            startActivity(printStory);

            // finish current activity
        }
    }
    // onsavedinstacesate
}
