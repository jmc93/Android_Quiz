package com.example.jacekcierpka.projektprawdaczywyzwanie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.jacekcierpka.projektprawdaczywyzwanie.PrawdaSQL.TabelaPytan2;

import java.util.ArrayList;
import java.util.List;

public class PrawdaBaza extends SQLiteOpenHelper {
    private static final String NAZWA_BAZY2="Pytania_do_Prawdy.db";
    private static final int WERSJA_BAZY2=1;
    private SQLiteDatabase db2;

    public PrawdaBaza(Context context) {
        super(context, NAZWA_BAZY2, null, WERSJA_BAZY2);
    }


    @Override
    public void onCreate(SQLiteDatabase db2) {
            this.db2=db2;
            final String SQL_CREATE_PYTANIA_DO_PRAWDY=" CREATE TABLE "
                    +TabelaPytan2.NAZWA_TABELI2+" ( "
                    +TabelaPytan2._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +TabelaPytan2.KOLUMNA_PYTANIA2+ " TEXT "+ " ) ";
        db2.execSQL(SQL_CREATE_PYTANIA_DO_PRAWDY);
        napelnijBaze2();
    }


    private void napelnijBaze2() {

        Pytanie2 pp1=new Pytanie2("Kiedy się urodziłeś(aś)?");
        dodajPytanie2(pp1);
        Pytanie2 pp2=new Pytanie2("Gdzie się urodziłeś(aś)?");
        dodajPytanie2(pp2);
        Pytanie2 pp3=new Pytanie2("Którą babcię wolisz?");
        dodajPytanie2(pp3);
        Pytanie2 pp4=new Pytanie2("Kawa, cz herbata?");
        dodajPytanie2(pp4);
        Pytanie2 pp5=new Pytanie2("Kot, czy pies?");
        dodajPytanie2(pp5);

    }
    private void dodajPytanie2(Pytanie2 pytanie2){
        ContentValues cv2= new ContentValues();
        cv2.put(TabelaPytan2.KOLUMNA_PYTANIA2, pytanie2.getPytanie2());
        db2.insert(TabelaPytan2.NAZWA_TABELI2, null, cv2);
    }
    public List<Pytanie2> getAllPytanie2(){
        List<Pytanie2> pytanieList2= new ArrayList<>();
        db2= getReadableDatabase();
        Cursor c2= db2.rawQuery( "SELECT * FROM "+ TabelaPytan2.NAZWA_TABELI2,null);
        if(c2.moveToFirst()){
            do{
                Pytanie2 pytanie2=new Pytanie2();
                pytanie2.setPytanie2(c2.getString(c2.getColumnIndex(TabelaPytan2.KOLUMNA_PYTANIA2)));
                pytanieList2.add(pytanie2);
            }while (c2.moveToNext());
        }
        c2.close();
        return pytanieList2;

    }
    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion2, int newVersion2) {
        db2.execSQL("DROP TABLE IF EXISTS " + TabelaPytan2.NAZWA_TABELI2);
        onCreate(db2);
    }
}
