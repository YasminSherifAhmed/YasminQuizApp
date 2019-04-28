package com.example.hp.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import com.example.hp.quizapp.R;

// This App calculates the results of a Science quiz
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startEvaluation(View view) {
        String[] answers = evaluateGui();
        int result = evaluateQuiz(answers);
        toastResult(result);
    }

    public String[] evaluateGui() {
        String[] ret = new String[5];
        EditText editTextQuestion1 = findViewById(R.id.question_1);

        CheckBox checkBoxQuestion2Hydrogen = findViewById(R.id.question_2_hydrogen);
        CheckBox checkBoxQuestion2Helium = findViewById(R.id.question_2_helium);
        CheckBox checkBoxQuestion2Oxygen = findViewById(R.id.question_2_oxygen);

        Boolean answerQuestion2 = false;

        if (checkBoxQuestion2Hydrogen.isChecked() == true && checkBoxQuestion2Helium.isChecked() == true && checkBoxQuestion2Oxygen.isChecked() == false) {
            answerQuestion2 = true;
        }

        CheckBox checkBoxQuestion4AlfredHale = findViewById(R.id.question_4_AlfredHale);
        CheckBox checkBoxQuestion4Chamberlain = findViewById(R.id.question_4_Chamberlain);
        CheckBox checkBoxQuestion4Moulton = findViewById(R.id.question_4_Moulton);

        Boolean answerQuestion4 = false;

        Boolean AlfredHale = checkBoxQuestion4AlfredHale.isChecked();
        Boolean Chamberlain = checkBoxQuestion4Chamberlain.isChecked();
        Boolean Moulton = checkBoxQuestion4Moulton.isChecked();


        if (AlfredHale == false && Chamberlain == true && Moulton == true) {
            answerQuestion4 = true;
        }

        ret[0] = editTextQuestion1.getText().toString().toLowerCase();
        ret[1] = Boolean.toString(answerQuestion2);
        ret[2] = evaluateRadioGroup(R.id.radio_group_question_3).toLowerCase();
        ret[3] = Boolean.toString(answerQuestion4);
        ret[4] = evaluateRadioGroup(R.id.radio_group_question_5).toLowerCase();

        return ret;
    }

    // The Correct Answers and incrementing the result by 1
    public int evaluateQuiz(String[] answers) {
        int result = 0;
        String[] correctAnswers = {"sun", "true", "220", "true", "8"};

        for (int i = 0; i < correctAnswers.length; i++) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
            }
        }

        return result;
    }

    //Toast Messages for each Result
    public void toastResult(int result) {
        String message = result + " out of 5. ";

        if (result == 0) {
            message += "Poor luck.";
        } else if (result == 1) {
            message += "You could do better.";
        } else if (result == 2) {
            message += "Quite nice.";
        } else if (result == 3) {
            message += "Really nice.";
        } else if (result == 4) {
            message += "Great!";
        } else if (result == 5) {
            message += "Absolutely awesome!";
        }

        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        reportResult.show();
    }

    private String evaluateRadioGroup(int id) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = findViewById(id);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();
        radioButtonQuestion = findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }

        return (String)radioButtonQuestion.getText();
    }

    //Clear values by pressing RESET Button
    public void reset(View view) {
        EditText editText = findViewById(R.id.question_1);
        editText.setText("");

        CheckBox checkBox = findViewById(R.id.question_2_hydrogen);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_helium);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_oxygen);
        checkBox.setChecked(false);

        RadioGroup radioGroup = findViewById(R.id.radio_group_question_3);
        radioGroup.clearCheck();

        checkBox = findViewById(R.id.question_4_AlfredHale);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4_Chamberlain);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4_Moulton);
        checkBox.setChecked(false);

        radioGroup = findViewById(R.id.radio_group_question_5);
        radioGroup.clearCheck();
    }
}
