package jstam.jessiestam_pset2_jaar2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Random;

/*
 * Mad Libs - Second Activity
 *
 * Jessie Stam
 *
 * Prompts the user for words, then fills them in and informs the user about what kind of word to
 * fill in next and how many words are yet to be filled in. Creates a Story string when all words
 * are filled in before continuing to Third Activity.
 */
public class SecondActivity extends MainActivity {

    // define InputStreams
    InputStream madlib0;
    InputStream madlib1;
    InputStream madlib2;
    InputStream madlib3;
    InputStream madlib4;
    InputStream story_object;

    // define Story object
    Story story;

    // define TextViews
    TextView wordsLeft;
    TextView wordKind;

    // define Strings
    String words_left;
    String user_input_string;
    String wordsLeftInstruction;
    String wordInstruction;
    String nextPlaceholder;
    String toast;

    // define EditText
    EditText user_input;

    // Define Integer
    Integer totalPlaceholdersLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        user_input = new EditText(this);
        wordsLeft = (TextView) findViewById(R.id.amount_left);
        wordKind = (TextView) findViewById(R.id.word_kind);
        user_input = (EditText) findViewById(R.id.user_input_word);
        wordsLeftInstruction = " word(s) left";
        wordInstruction = "Please enter a/an ";

        // if savedInstanceState is empty, create new Story object
        if (savedInstanceState == null) {
            // pick random story and update amount of words and kind of word to fill in
            getRandomStory();
            updateWordCountAndWordKind();
        }
        // if savedInstanceState is full, load Story object
        else {
            story = (Story) savedInstanceState.getSerializable("story");

            // update amount of words and kind of word to fill in
            updateWordCountAndWordKind();
        }
    }

    /*
     * Saves the word filled in by user to the Story object and updates amount of words left
     * and kind of word to fill in next when button is clicked.
     */
    public void addWord(View addWordView) {

        // let user know word is saved by random toast
        Toast.makeText(SecondActivity.this, getRandomToast(), Toast.LENGTH_SHORT).show();

        // input bold word in story and clear EditText for next word
        user_input_string = user_input.getText().toString();
        story.fillInPlaceholder("<b>" + user_input_string + "</b> ");
        user_input.getText().clear();

        // change amount of words left and the kind of word to fill in
        updateWordCountAndWordKind();

        // create boolean to check if isFilledIn function returns true
        boolean filledIn = story.isFilledIn();

        // when everything is filled in, move on to Third Activity to print story
        if (filledIn) {
            // create final story String
            String finalStory = story.toString();

            // open Third Activity to display story
            Intent printStory = new Intent(this, ThirdActivity.class);
            printStory.putExtra("finalStory", finalStory);
            startActivity(printStory);

            // finish current Activity
            finish();
        }
    }

    /*
     * Picks a story at random and creates the Story object
     */
    public void getRandomStory() {

        // define InputStreams
        madlib0 = getResources().openRawResource(R.raw.madlib0_simple);
        madlib1 = getResources().openRawResource(R.raw.madlib1_tarzan);
        madlib2 = getResources().openRawResource(R.raw.madlib2_university);
        madlib3 = getResources().openRawResource(R.raw.madlib3_clothes);
        madlib4 = getResources().openRawResource(R.raw.madlib4_dance);

        // get a random integer between 0 and 4
        Random randomStoryNum = new Random();
        int storyNum = randomStoryNum.nextInt(5);

        // define story at random using random Integer
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

        // create story object with correct InputStream
        story = new Story(story_object);
        story.read(story_object);
    }

    /*
     * Updates the total amount of words left and the kind of word to input and prints them to the
     * screen.
     */
    public void updateWordCountAndWordKind() {

        // clear TextViews to fill them again
        wordsLeft.setText("");
        wordKind.setText("");

        // get (total amount of) placeholders left and print to screen with instructions
        totalPlaceholdersLeft = story.getPlaceholderRemainingCount();
        words_left = totalPlaceholdersLeft.toString();
        wordsLeft.append(words_left + wordsLeftInstruction);

        nextPlaceholder = story.getNextPlaceholder();
        wordKind.append(wordInstruction + nextPlaceholder.toLowerCase());

        // give instructions for the kind of word to input as hint
        user_input.setHint(nextPlaceholder.toLowerCase());
    }

    /*
     * Takes a random Integer and picks a random Toast accordingly.
     */
    public String getRandomToast() {

        // get random int between 0 and 4
        Random randomNum = new Random();
        int num = randomNum.nextInt(5);

        // define toast at random using random Integer
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

        // save story object
        outState.putSerializable("story", story);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // restore story object
        story = (Story) savedInstanceState.getSerializable("story");
    }
}
