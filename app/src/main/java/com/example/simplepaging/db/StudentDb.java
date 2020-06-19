package com.example.simplepaging.db;

import android.content.Context;

import com.example.simplepaging.Executors;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDb extends RoomDatabase {
    public abstract StudentDao studentDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile StudentDb INSTANCE;

    public static StudentDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Student.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StudentDb.class, "word_database")
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.ioThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            StudentDao studentDao = getDatabase(context).studentDao();
                                            List<Student> students = new ArrayList<>();
                                            for (int i = 0; i < names.length; i++) {
                                                Student student = new Student();
                                                student.setName(names[i]);
                                                students.add(student);
                                            }
                                            studentDao.insert(students);
                                        }
                                    });
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final String[] names = new String[]{"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag",
            "Airedale", "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert",  // 15
            "American Cheese", "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro",
            "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String",
            "Aromes au Gene de Marc", "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", // 30
            "Avaxtskyr", "Baby Swiss", "Babybel", "Baguette Laonnaise", "Bakers",
            "Baladi", "Balaton", "Bandal", "Banon", "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase",
            "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
            "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
            "Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
            "Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
            "Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini", "Bocconcini (Australian)"};

}
