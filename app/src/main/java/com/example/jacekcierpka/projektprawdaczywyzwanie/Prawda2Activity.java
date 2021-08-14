package com.example.jacekcierpka.projektprawdaczywyzwanie;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

public class Prawda2Activity extends AppCompatActivity {
    private TextView Ipytanie2;
    private Button Iodpowiedz2;
    private TextView IodpowiedzTresc;
    private TextView Isms21;
    private TextView Isms22;
    private List<Pytanie2> pytanieList2;
    private boolean odp2;

    private int zliczPytania2;
    private int wszystkiePytania2;
    private Pytanie2 aktualnePytanie2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prawda2);
        Ipytanie2=findViewById(R.id.pytanie2);
        Iodpowiedz2=findViewById(R.id.odpowiedz2);
        Isms21=findViewById(R.id.wiadomoscSMS3);
        Isms22=findViewById(R.id.wiadomoscSMS4);
        IodpowiedzTresc=findViewById(R.id.odpTresc);

        PrawdaBaza dbHelper2=new PrawdaBaza(this);
        pytanieList2=dbHelper2.getAllPytanie2();
        wszystkiePytania2=pytanieList2.size();
        Collections.shuffle(pytanieList2);

        pokazNastPytanie2();

        Iodpowiedz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!odp2){
                    Isms21.setText(zliczPytania2+". "+Ipytanie2.getText().toString()+" Moja Odpowiedz:  "+IodpowiedzTresc.getText().toString()+"\n");
                    Isms22.setText(Isms22.getText().toString()+" "+Isms21.getText().toString());
                }{pokazNastPytanie2();}
            }
        });

    }

    private void pokazNastPytanie2() {
        if(zliczPytania2 < wszystkiePytania2){
            aktualnePytanie2=pytanieList2.get(zliczPytania2);
            Ipytanie2.setText(aktualnePytanie2.getPytanie2());
            zliczPytania2++;
            odp2=false;
            Iodpowiedz2.setText("Odpowiedź");
        }else{
            zakonczQuiz2();
        }
    }
    private void zakonczQuiz2(){

        Intent smss=new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto: Mama"));
        smss.putExtra("sms_body","Cześć, gram właśnie w Prawdę czy Wyzwanie. Oto moje szczere odpowiedzi: "+Isms22.getText().toString());
        startActivity(smss);
        finish();
    }

}
