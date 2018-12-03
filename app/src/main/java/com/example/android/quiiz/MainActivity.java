package com.example.android.quiiz;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {
private int myQuestion;
    private TextView textView ;


    private TextView textView1 ;
    private TextView textView2 ;
    private TextView textView3 ;
    private TextView textView4 ;
    private static final String CORRECT_SENTENCE = "yes";
private static final String WRONG_SENTENCE="no";
    private boolean isPaused=false;
private  Map<String, String> questions = new HashMap<String , String>();
private boolean isCanceled= false;
   private Handler h = new Handler();

   private final Runnable r = new Runnable() {
       @Override
        public void run() {

            newActivity();
        }

   };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         textView = (TextView) findViewById(R.id.timer);
         textView1 = (TextView) findViewById(R.id.Yes);
         textView2 = (TextView) findViewById(R.id.No);
         textView3 = (TextView) findViewById(R.id.answer);
         textView4 = (TextView) findViewById(R.id.question);
         setMyQuestion();
        textView3.setVisibility(View.GONE);
        myQuestion = (int) (Math.random()*questions.size());
        String myString = questions.keySet().toArray()[myQuestion].toString();
        textView4.setText(myString);
         CountDownTimer myCounTimer=new CountDownTimer (10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                if (isPaused || isCanceled) {
                    //If the user request to cancel or paused the
                    //CountDownTimer we will cancel the current instance
                    cancel();
                    onFinish();
                } else {
                    textView.setText(millisUntilFinished / 1000 + "s");
                }
            }

            @Override
            public void onFinish() {
                textView.setText("10s");

            }
        }.start();

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView3.setVisibility(View.VISIBLE);
                isPaused = true;

                if (questions.values().toArray()[myQuestion].toString() == WRONG_SENTENCE) {

                    textView3.setText("Correct");
                } else {
                    textView3.setText("Wrong");
                }

h.postDelayed(r,5000);

            }

        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                textView3.setVisibility(View.VISIBLE);

                    if (questions.values().toArray()[myQuestion].toString() == CORRECT_SENTENCE) {
                        textView3.setText("Correct");
                    } else {

                        textView3.setText("Wrong");
                    }
                    h.postDelayed(r,5000);

                }
        });



}
private void setMyQuestion(){
    questions.put("Lira used to be the currency of Italy" , CORRECT_SENTENCE);
    questions.put("Pesetas was the currency of Greece", WRONG_SENTENCE);
    questions.put("Dogs are mammals", CORRECT_SENTENCE);
    questions.put("Cats are aliens that want to kill humans", CORRECT_SENTENCE);
    questions.put("Titanic film won eleven oscars", CORRECT_SENTENCE);
    questions.put("Woody Allen is the protagonist in the movie the Last Action Hero", WRONG_SENTENCE);
    questions.put("Robert Redford is the director of X-files", WRONG_SENTENCE);
    questions.put("GPS stands for Global Point System ", WRONG_SENTENCE);
    questions.put("The most spoken language in the world is the Chinese", CORRECT_SENTENCE);
    questions.put("The former name of New York was New Amsterdam", CORRECT_SENTENCE);
}
private void newActivity(){
    Intent in = new Intent(getApplicationContext(),MainActivity.class);
    startActivity(in);
}
}

