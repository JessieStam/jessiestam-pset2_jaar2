package jstam.jessiestam_pset2_jaar2;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    // Define InputStreams
    InputStream madlib0;
    InputStream madlib1;
    InputStream madlib2;
    InputStream madlib3;
    InputStream madlib4;
    InputStream story_object;

    // Define Story object
    Story story;

    // Define TextViews
    TextView wordsLeft;
    TextView wordKind;

    // Define Strings
    String words_left;
    String user_input_string;
    String wordsLeftInstruction;
    String wordInstruction;
    String new_words_left_text;
    String new_word_kind_text;
    String nextPlaceholder;
    String toast;

    // Define EditText
    EditText user_input;

    // Define List
    ArrayList<String> filled_in_list;

    // Define Integer
    Integer totalPlaceholdersLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        user_input = new EditText(this);

        // if savedInstanceState is empty, create new ArrayList for IDs of checked CheckBoxes
        if (savedInstanceState == null) {

            filled_in_list = new ArrayList<>();
            wordsLeft = (TextView) findViewById(R.id.amount_left);
            wordKind = (TextView) findViewById(R.id.word_kind);

            getRandomStory();

            story = new Story(story_object);
            story.read(story_object);

            user_input = (EditText) findViewById(R.id.user_input_word);

            wordsLeftInstruction = " word(s) left";
            wordInstruction = "Please enter a/an ";

            updateWordCountAndWordKind();

        }
        // if savedInstanceState is full, load list of checked CheckBox IDs
        else {
            filled_in_list = savedInstanceState.getStringArrayList("savedList");

            // for (list.length) fillinplaceholder + getnextplaceholder + getremainingwordcount (dit denk ik in oncreate)
            if (filled_in_list != null) {
                for (String list_item : filled_in_list) {
                    story.fillInPlaceholder(list_item);

                    // get total amount of placeholders and print to screen with instructions
                    Integer totalPlaceholdersLeft = story.getPlaceholderRemainingCount();
                    words_left = totalPlaceholdersLeft.toString();
                    new_words_left_text = words_left + wordsLeftInstruction;
                    wordsLeft.setText(new_words_left_text);

                    // get placeholder and print to screen with instructions
                    String nextPlaceholder = story.getNextPlaceholder();
                    new_word_kind_text = wordInstruction + nextPlaceholder.toLowerCase();
                    wordKind.setText(new_word_kind_text);
                }
            }

        }
    }

    public void addWord(View addWordView) {

        // let user know word is saved by random toast
        Toast.makeText(SecondActivity.this, getRandomToast(), Toast.LENGTH_SHORT).show();

        // save word from EditText to string
//        user_input = (EditText) findViewById(R.id.user_input_word);
        user_input_string = user_input.getText().toString();

        //clear EditText
        user_input.getText().clear();

        // input word in story
        story.fillInPlaceholder(user_input_string);

        // save word to list for when activity is killed
        filled_in_list.add(user_input_string);

        updateWordCountAndWordKind();

//        // get total amount of placeholders and print to screen with instructions
//        totalPlaceholdersLeft = story.getPlaceholderRemainingCount();
//        words_left = totalPlaceholdersLeft.toString();
//        new_words_left_text = words_left + wordsLeftInstruction;
//        wordsLeft.setText(new_words_left_text);
//
//        // get placeholder and print to screen with instructions
//        nextPlaceholder = story.getNextPlaceholder();
//        new_word_kind_text = wordInstruction + nextPlaceholder.toLowerCase();
//        wordKind.setText(new_word_kind_text);
//
//        // set EditText hint to placeholder
//        user_input.setHint(nextPlaceholder.toLowerCase());

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
            finish();
        }
    }

    public void getRandomStory() {

        // define textfiles InputStreams
        madlib0 = getResources().openRawResource(R.raw.madlib0_simple);
        madlib1 = getResources().openRawResource(R.raw.madlib1_tarzan);
        madlib2 = getResources().openRawResource(R.raw.madlib2_university);
        madlib3 = getResources().openRawResource(R.raw.madlib3_clothes);
        madlib4 = getResources().openRawResource(R.raw.madlib4_dance);

        // get a random integer between 0 and 4
        Random randomStoryNum = new Random();
        int storyNum = randomStoryNum.nextInt(5);

        // define story at random using random integer
        switch(storyNum) {
            case (0):
                story_object = madlib0;
                break;
            case (1):
                story_object = madlib1;
                break;
            case (2):
                story_object = madlib2;
                break;
            case (3):
                story_object = madlib3;
                break;
            case (4):
                story_object = madlib4;
                break;
        }
    }

    public void updateWordCountAndWordKind() {

        wordsLeft.setText("");

        // get (total amount of) placeholders left and print to screen with instructions
        totalPlaceholdersLeft = story.getPlaceholderRemainingCount();
        words_left = totalPlaceholdersLeft.toString();
        wordsLeft.append(words_left + wordsLeftInstruction);

        wordKind.setText("");

        nextPlaceholder = story.getNextPlaceholder();
        wordKind.append(wordInstruction + nextPlaceholder.toLowerCase());

        user_input.setHint(nextPlaceholder.toLowerCase());

    }

    public String getRandomToast() {

        // misschien kun je hier beter een lijst van maken en misschien kan dat bij de eerste random int ook
        Random randomNum = new Random();
        int num = randomNum.nextInt(5);

        // define toast at random using random integer
        switch(num) {
            case (0):
                toast = "Added!";
                break;
            case (1):
                toast = "Thanks!";
                break;
            case (2):
                toast = "Nice one";
                break;
            case (3):
                toast = "Saved!";
                break;
            case (4):
                toast = "Thank you";
                break;
        }

        return toast;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save ArrayList of filled in words
        outState.putStringArrayList("savedList", filled_in_list);



    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // restore ArrayList of filled in words
        filled_in_list = savedInstanceState.getStringArrayList("savedList");

    }
}
