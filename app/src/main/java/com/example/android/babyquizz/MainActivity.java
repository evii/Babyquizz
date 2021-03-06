package com.example.android.babyquizz;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.android.babyquizz.R.string.question1;
import static com.example.android.babyquizz.R.string.result;

public class MainActivity extends AppCompatActivity {
    int finalResult = 0;
    EditText question5EditText;
    RadioButton answerToQuestion1;
    RadioButton answerToQuestion2;
    CheckBox answerToQuestion3a;
    CheckBox answerToQuestion3b;
    CheckBox answerToQuestion3c;
    CheckBox answerToQuestion3d;
    CheckBox answerToQuestion3e;
    RadioButton answerToQuestion4;
    TextView answerToQuestion5;
    CheckBox answerToQuestion6a;
    CheckBox answerToQuestion6b;
    CheckBox answerToQuestion6c;
    CheckBox answerToQuestion6d;
    TextView userName;
    TextView correctAnswersmessage;
    TextView resultsMessage;
    ScrollView scrollView;

    // Dismissing keyboard when click outside of EditText in android
    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting EditText to 0, so that the app does not crash when the EditText is not filled
        question5EditText = (EditText) findViewById(R.id.question5);
        question5EditText.setText("0");
        answerToQuestion1 = (RadioButton) findViewById(R.id.radio_button_1b);
        answerToQuestion2 = (RadioButton) findViewById(R.id.radio_button_2a);
        answerToQuestion3a = (CheckBox) findViewById(R.id.checkbox_3a);
        answerToQuestion3b = (CheckBox) findViewById(R.id.checkbox_3b);
        answerToQuestion3c = (CheckBox) findViewById(R.id.checkbox_3c);
        answerToQuestion3d = (CheckBox) findViewById(R.id.checkbox_3d);
        answerToQuestion3e = (CheckBox) findViewById(R.id.checkbox_3e);
        answerToQuestion4 = (RadioButton) findViewById(R.id.radio_button_4b);
        answerToQuestion5 = (TextView) findViewById(R.id.question5);
        answerToQuestion6a = (CheckBox) findViewById(R.id.checkbox_6a);
        answerToQuestion6b = (CheckBox) findViewById(R.id.checkbox_6b);
        answerToQuestion6c = (CheckBox) findViewById(R.id.checkbox_6c);
        answerToQuestion6d = (CheckBox) findViewById(R.id.checkbox_6d);
        userName = (TextView) findViewById(R.id.User_name_EditText);
        correctAnswersmessage = (TextView) findViewById(R.id.correctAnswers_TextView);
        resultsMessage = (TextView) findViewById(R.id.result_TextView);
        scrollView = (ScrollView) findViewById(R.id.activity_main);
    }

    // Method for calculating result
    private int calculateResultScore() {
        finalResult = 0;

        //Evaluation of Question 1 - correct 1b
        boolean answer1 = answerToQuestion1.isChecked();
        if (answer1) {
            finalResult = finalResult + 1;
        }

        //Evaluation of Question 2 - correct 2a
        boolean answer2 = answerToQuestion2.isChecked();
        if (answer2) {
            finalResult = finalResult + 1;
        }

        //Evaluation of Question 3 - correct 3b+e
        boolean answer3a = answerToQuestion3a.isChecked();
        boolean answer3b = answerToQuestion3b.isChecked();
        boolean answer3c = answerToQuestion3c.isChecked();
        boolean answer3d = answerToQuestion3d.isChecked();
        boolean answer3e = answerToQuestion3e.isChecked();
        if (!answer3a && answer3b && !answer3c && !answer3d && answer3e) {
            finalResult = finalResult + 1;
        }

        //Evaluation of Question 4 - correct 4b
        boolean answer4 = answerToQuestion4.isChecked();
        if (answer4) {
            finalResult = finalResult + 1;
        }

        //Evaluation of Question 5 - correct 1 inch
        int answer5 = Integer.parseInt(answerToQuestion5.getText().toString());
        if (answer5 == 1) {
            finalResult = finalResult + 1;
        }

        //Evaluation of Question 6 - correct 6a b d
        boolean answer6a = answerToQuestion6a.isChecked();
        boolean answer6b = answerToQuestion6b.isChecked();
        boolean answer6c = answerToQuestion6c.isChecked();
        boolean answer6d = answerToQuestion6d.isChecked();
        if (answer6a && answer6b && !answer6c && answer6d) {
            finalResult = finalResult + 1;
        }

        return (finalResult);
    }

    // Method defining display of the Results
    private void displayResults(int finalResult) {

        //Getting User name
        String nameOfUser = userName.getText().toString();

        // Displaying result
        String resultmessage = getString(R.string.results_summary_name, nameOfUser, finalResult);
        resultsMessage.setText(resultmessage);

        // Displaying correct answwers
        String correctAnswers = getString(R.string.correct_answers);
        correctAnswersmessage.setText(correctAnswers);

        //Toast with result
        String resultAndcorrect = resultmessage + correctAnswers;
        Toast.makeText(this, resultAndcorrect, Toast.LENGTH_LONG).show();
    }

    // Method for calculating + displaying the Results after pressing Submit button
    public void submitResults(View view) {
        calculateResultScore();
        displayResults(finalResult);
    }

    // Method for reseting
    public void resetResults(View view) {
        RadioGroup question1RadioGroup = (RadioGroup) findViewById(R.id.Question1_radio_group);
        question1RadioGroup.clearCheck();

        RadioGroup question2RadioGroup = (RadioGroup) findViewById(R.id.Question2_radio_group);
        question2RadioGroup.clearCheck();

        answerToQuestion3a.setChecked(false);
        answerToQuestion3b.setChecked(false);
        answerToQuestion3c.setChecked(false);
        answerToQuestion3d.setChecked(false);
        answerToQuestion3e.setChecked(false);

        RadioGroup question4RadioGroup = (RadioGroup) findViewById(R.id.Question4_radio_group);
        question4RadioGroup.clearCheck();

        question5EditText.setText(getString(R.string.zero));

        answerToQuestion6a.setChecked(false);
        answerToQuestion6b.setChecked(false);
        answerToQuestion6c.setChecked(false);
        answerToQuestion6d.setChecked(false);

        // Reseting result
        resultsMessage.setText(getString(R.string.result));

        // Reseting correct answwers
        correctAnswersmessage.setText(getString(R.string.blank));

        // Reseting Name
        userName.setText(getString(R.string.blank));

        // odrolování nahoru
        userName.requestFocus();

    }


    // Dismissing keyboard when click outside of EditText in android
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }


}

