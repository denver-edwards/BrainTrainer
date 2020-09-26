package com.unsignedco.android.braintraining;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    TextView sumTextView;
    TextView scoreTextView;
    TextView timeTextView;
    TextView resultTextView;
    Button choice1, choice2, choice3, choice4, playAgainButton;

    RelativeLayout gameRelativeLayout;

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timeTextView.setText("30s");
        scoreTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timeTextView.setText(String.valueOf(l / 1000) + "s");
            }
            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setVisibility(View.VISIBLE);

                timeTextView.setText("0s");
                resultTextView.setText("Score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();

    }

    public void generateQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(31);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        choice1.setText(Integer.toString(answers.get(0)));
        choice2.setText(Integer.toString(answers.get(1)));
        choice3.setText(Integer.toString(answers.get(2)));
        choice4.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);

        choice1 = findViewById(R.id.option1Button);
        choice2 = findViewById(R.id.option2Button);
        choice3 = findViewById(R.id.option3Button);
        choice4 = findViewById(R.id.option4Button);
        timeTextView = findViewById(R.id.timeTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameRelativeLayout = findViewById(R.id.relativeLayout);
    }

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);

        // Start timer when view is displayed
        playAgain(findViewById(R.id.playAgainButton));
    }

    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
        } else {

        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }


}
