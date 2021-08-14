package com.example.jacekcierpka.projektprawdaczywyzwanie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EkranStartowy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_startowy);

        Button wyzwanieM= findViewById(R.id.wyzwanieB);
        Button prawdaM= findViewById(R.id.prawdaB);
        wyzwanieM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    rozpocznijQuiz();
            }
        });
        prawdaM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rozpocznijPrawde();
            }
        });


    }
    private void rozpocznijQuiz(){
        Intent intent=new Intent(EkranStartowy.this, QuizActivity.class);
        startActivity(intent);
    }
    private void rozpocznijPrawde(){
        Intent intent2=new Intent(EkranStartowy.this, Prawda2Activity.class);
        startActivity(intent2);
    }
}
