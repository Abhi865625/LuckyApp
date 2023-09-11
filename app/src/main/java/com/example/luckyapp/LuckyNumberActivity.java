package com.example.luckyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class LuckyNumberActivity extends AppCompatActivity {
    TextView welcomeTxt, luckyNumberTxt;
    Button share_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_number);

        welcomeTxt = findViewById(R.id.textView2);
        luckyNumberTxt = findViewById(R.id.lucky_number_text);
        share_btn = findViewById(R.id.share_number_btn);

        // UserName
        Intent i = getIntent();
        String user_name = i.getStringExtra("name");

        // Random Number Generated

        int random_number = generateRandomNumber();

        luckyNumberTxt.setText(""+random_number);

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(user_name,random_number);
            }
        });

    }
    public int generateRandomNumber(){
        Random random = new Random();
        int upper_Limit = 1000;
        int randomNumberGenerated = random.nextInt(upper_Limit);
        return randomNumberGenerated;
    }

    public void shareData(String userName, int randomNum){

        // Implicit Intents
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        // convert the int to string
        String number = String.valueOf(randomNum);

        i.putExtra(Intent.EXTRA_SUBJECT, userName + " got lucky today!");
        i.putExtra(Intent.EXTRA_TEXT, "His lucky number is "+number);

        startActivity(Intent.createChooser(i,"Choose a Platform"));


    }

}