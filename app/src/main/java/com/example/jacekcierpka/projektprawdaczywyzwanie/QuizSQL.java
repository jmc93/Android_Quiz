package com.example.jacekcierpka.projektprawdaczywyzwanie;

import android.provider.BaseColumns;

public final class QuizSQL {

    private QuizSQL(){}

    public static class TabelaPytan implements BaseColumns {
        public static final String NAZWA_TABELI="QuizPytania";
        public static final String KOLUMNA_PYTANIA="Pytanko";
        public static final String KOLUMNA_OPCJA1="opcja1";
        public static final String KOLUMNA_OPCJA2="opcja2";
        public static final String KOLUMNA_OPCJA3="opcja3";
        public static final String KOLUMNA_OPCJA4="opcja4";
        public static final String KOLUMNA_ODPOWIEDZI="nrOdpowiedzi";
    }
}
