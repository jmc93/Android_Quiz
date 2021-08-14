package com.example.jacekcierpka.projektprawdaczywyzwanie;


import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements SensorEventListener{
    private TextView Ipytanie;
    private TextView Iwynik;
    private TextView Iliczba_pytan;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button Iodpowiedz;
    private TextView Isms;
    private TextView Isms2;

    private int zliczPytania;
    private int wszystkiePytania;
    private Pytanie aktualnePytanie;
    private int punktacja;
    private boolean odp;
    private List<Pytanie> pytanieList;
    public int twojaOdp;

    private SensorManager sensorManager;
    Sensor akcelerometr;
    TextView yWartosc;
    TextView jeden,dwa;
    public double ygrek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Ipytanie=findViewById(R.id.pytanie);
        Iwynik=findViewById(R.id.wynik);
        Iliczba_pytan=findViewById(R.id.liczba_pytan);
        rbGroup=findViewById(R.id.radioGroup);
        rb1=findViewById(R.id.opcja1);
        rb2=findViewById(R.id.opcja2);
        rb3=findViewById(R.id.opcja3);
        rb4=findViewById(R.id.opcja4);
        Iodpowiedz=findViewById(R.id.odpowiedz);
        Isms=findViewById(R.id.wiadomoscSMS);
        Isms2=findViewById(R.id.wiadomoscSMS2);
        yWartosc=findViewById(R.id.yWartosc);
        jeden=findViewById(R.id.akcJeden);
        dwa=findViewById(R.id.zmiana);

        QuizBaza dbHelper=new QuizBaza(this);
        pytanieList=dbHelper.getAllPytanie();
        wszystkiePytania=pytanieList.size();
        Collections.shuffle(pytanieList);

        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        akcelerometr=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //akce2=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(QuizActivity.this,akcelerometr,SensorManager.SENSOR_DELAY_NORMAL);

        pokazNastPytanie();


        Iodpowiedz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!odp){
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        sprawdzOdp();
                        if (rb1.isChecked()){Isms.setText(zliczPytania+". "+Ipytanie.getText().toString()+" Moja Odpowiedz:  "+aktualnePytanie.getOpcja1()+"\n");}
                        else if(rb2.isChecked()){Isms.setText(zliczPytania+". "+Ipytanie.getText().toString()+" Moja Odpowiedz:  "+aktualnePytanie.getOpcja2()+"\n");}
                        else if(rb3.isChecked()){Isms.setText(zliczPytania+". "+Ipytanie.getText().toString()+" Moja Odpowiedz:  "+aktualnePytanie.getOpcja3()+"\n");}
                        else if(rb4.isChecked()){Isms.setText(zliczPytania+". "+Ipytanie.getText().toString()+" Moja Odpowiedz:  "+aktualnePytanie.getOpcja4()+"\n");}

                        Isms2.setText(Isms2.getText().toString()+" "+Isms.getText().toString());
                    } else {Toast.makeText(QuizActivity.this,"Wybierz odpowiedź", Toast.LENGTH_SHORT).show();}

                }{pokazNastPytanie();}
            }
        });

    }

    private void pokazNastPytanie() {
        if(zliczPytania < wszystkiePytania){
            aktualnePytanie=pytanieList.get(zliczPytania);
            Ipytanie.setText(aktualnePytanie.getPytanie());
            rb1.setText(aktualnePytanie.getOpcja1());
            rb2.setText(aktualnePytanie.getOpcja2());
            rb3.setText(aktualnePytanie.getOpcja3());
            rb4.setText(aktualnePytanie.getOpcja4());
            zliczPytania++;
            Iliczba_pytan.setText("Pytanie: "+zliczPytania+" / "+wszystkiePytania);
            odp=false;
            Iodpowiedz.setText("Odpowiedź");
        }else{zakonczQuiz();}
    }

    private void sprawdzOdp(){
        odp=true;
        RadioButton wybranyRb=findViewById(rbGroup.getCheckedRadioButtonId());
        int nrOdp=rbGroup.indexOfChild(wybranyRb)+1;
        twojaOdp=nrOdp;
        if (nrOdp==aktualnePytanie.getNrOdpowiedzi()){
            punktacja++;
            Iwynik.setText("Wynik: "+punktacja);
        }
    }

    private void zakonczQuiz(){

        Intent smss=new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto: Mama"));
        smss.putExtra("sms_body","Cześć, gram właśnie w Prawdę czy Wyzwanie. Oto moje odpowiedzi na Wyzwanie: "+Isms2.getText().toString());
        startActivity(smss);
        finish();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        yWartosc.setText("Wart. Y: "+event.values[1]);
        ygrek=Double.parseDouble(String.valueOf(event.values[1]));
        int i=0;
        boolean chodzi=false;
        if (ygrek !=9.81) {
            chodzi=true;
            i=1;
            jeden.setText(i+"");
        }
        else { i=0; jeden.setText(i+"");}
        if (chodzi==true) {dwa.setText("Skaczesz");}
        else {dwa.setText("Nie skaczesz");}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
