package com.example.jacekcierpka.projektprawdaczywyzwanie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.jacekcierpka.projektprawdaczywyzwanie.QuizSQL.*;

import java.util.ArrayList;
import java.util.List;

public class QuizBaza extends SQLiteOpenHelper {
    private static final String NAZWA_BAZY="Pytania_do_Quizu.db";
    private static final int WERSJA_BAZY=1;

    private SQLiteDatabase db;

    public QuizBaza(Context context) {
        super(context, NAZWA_BAZY, null, WERSJA_BAZY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        final String SQL_CREATE_PYTANIA_DO_QUIZU=" CREATE TABLE "
                +TabelaPytan.NAZWA_TABELI+" ( "
                +TabelaPytan._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TabelaPytan.KOLUMNA_PYTANIA+ " TEXT, "
                +TabelaPytan.KOLUMNA_OPCJA1+ " TEXT, "
                +TabelaPytan.KOLUMNA_OPCJA2+ " TEXT, "
                +TabelaPytan.KOLUMNA_OPCJA3+ " TEXT, "
                +TabelaPytan.KOLUMNA_OPCJA4+ " TEXT, "
                +TabelaPytan.KOLUMNA_ODPOWIEDZI+ " INTEGER "+ " ) ";

        db.execSQL(SQL_CREATE_PYTANIA_DO_QUIZU);
        napelnijBaze();
    }

    private void napelnijBaze() {
        Pytanie p1=new Pytanie("Jaka jest dokładna data bitwy pod Grunwaldem?", "15 lipca 1410","15 sierpnia 1410","26 lipca 1410","1 sierpnia 1410", 1);
        dodajPytanie(p1);
        Pytanie p2=new Pytanie("Kto jest autorem tekstu polskiego hymnu narodowego?", "Jan Henryk Dąbrowski","Józef Wybicki","Tadeusz Kościuszko","Jan Wybicki", 2);
        dodajPytanie(p2);
        Pytanie p3=new Pytanie("Podaj daty 3 rozbiorów Polski:", "1774, 1795, 1798","1888, 1889, 1895","1772, 1793, 1795","1825, 1829, 1837", 3);
        dodajPytanie(p3);
        Pytanie p4=new Pytanie("Jaka jest data wstąpienia polski do Unii Europejskiej?", "1 maja 2005","1 maja 2000","1 maja 2008","1 maja 2004", 4);
        dodajPytanie(p4);
        Pytanie p5=new Pytanie("Kto jako pierwszy sprawował funkcję I Sekretarza KC PZPR (lata 1948-1956)?", "Bolesław Bierut","Władysław Gomułka","Edward Gierek","Wojciech Jaruzelski", 1);
        dodajPytanie(p5);
        Pytanie p6=new Pytanie("Kto sprawuje w Polsce władzę wykonawczą?", "Sejm","Sejm i Senat","Rada Ministrów","Prezydent i Rada Ministrów", 4);
        dodajPytanie(p6);
        Pytanie p7=new Pytanie("Ile województw jest w Polsce?", "18","16","19","15", 2);
        dodajPytanie(p7);
        Pytanie p8=new Pytanie("Z kim Polska graniczy od wschodu?", "Z Ukrainą i Białorusią","Z Litwą i Ukrainą","Z Białorusią i Czechami","Z Litwą i Słowacją", 1);
        dodajPytanie(p8);
        Pytanie p9=new Pytanie("Jakie języki urzędowe obowiązują obecnie w Polsce?", "Tylko polski","Polski i śląski","Polski, śląski i kaszubski","Polski i kaszubski", 1);
        dodajPytanie(p9);
    }
    private void dodajPytanie(Pytanie pytanie){
        ContentValues cv= new ContentValues();
        cv.put(TabelaPytan.KOLUMNA_PYTANIA, pytanie.getPytanie());
        cv.put(TabelaPytan.KOLUMNA_OPCJA1, pytanie.getOpcja1());
        cv.put(TabelaPytan.KOLUMNA_OPCJA2, pytanie.getOpcja2());
        cv.put(TabelaPytan.KOLUMNA_OPCJA3, pytanie.getOpcja3());
        cv.put(TabelaPytan.KOLUMNA_OPCJA4, pytanie.getOpcja4());
        cv.put(TabelaPytan.KOLUMNA_ODPOWIEDZI, pytanie.getNrOdpowiedzi());
        db.insert(TabelaPytan.NAZWA_TABELI, null, cv);
    }

    public List<Pytanie> getAllPytanie(){
        List<Pytanie> pytanieList= new ArrayList<>();
        db= getReadableDatabase();
        Cursor c= db.rawQuery( "SELECT * FROM "+ TabelaPytan.NAZWA_TABELI,null);
        if(c.moveToFirst()){
            do{
                Pytanie pytanie=new Pytanie();
                pytanie.setPytanie(c.getString(c.getColumnIndex(TabelaPytan.KOLUMNA_PYTANIA)));
                pytanie.setOpcja1(c.getString(c.getColumnIndex(TabelaPytan.KOLUMNA_OPCJA1)));
                pytanie.setOpcja2(c.getString(c.getColumnIndex(TabelaPytan.KOLUMNA_OPCJA2)));
                pytanie.setOpcja3(c.getString(c.getColumnIndex(TabelaPytan.KOLUMNA_OPCJA3)));
                pytanie.setOpcja4(c.getString(c.getColumnIndex(TabelaPytan.KOLUMNA_OPCJA4)));
                pytanie.setNrOdpowiedzi(c.getInt(c.getColumnIndex(TabelaPytan.KOLUMNA_ODPOWIEDZI)));
                pytanieList.add(pytanie);
            }while (c.moveToNext());
        }
        c.close();
        return pytanieList;

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TabelaPytan.NAZWA_TABELI);
        onCreate(db);
    }
}
