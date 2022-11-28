package com.example.footballchampionship;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.LinkedList;

public class DB extends SQLiteOpenHelper {
    private static final String Foolball = "Football";
    private static final String ID = "ID";
    private static final String TeamHome = "TeamHome";
    private static final String TeamGuest = "TeamGuest";
    private static final String GoalsHome = "GoalsHome";
    private static final String GoalsGuest = "GoalsGuest";


    public String createTables = "CREATE table " + Foolball + "(" +
            ID + " INTEGER NOT NULL primary key autoincrement," +
            TeamHome + " Varchar(255)," +
            TeamGuest + " Varchar(255)," +
            GoalsHome + " INTEGER," +
            GoalsGuest + "INTEGER" +
            ")";
    public String DropTable = "DROP table " + Foolball;

    public DB(@Nullable Context context ) {
        super(context,  "example", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTables);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i != i1){
            sqLiteDatabase.execSQL(DropTable);
            onCreate(sqLiteDatabase);
        }

    }
    public void Insert(String teamHome, String teamGuest, String goalsHome, String goalsGuest){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TeamGuest,teamHome);
        cv.put(TeamGuest,teamGuest);
        cv.put(GoalsHome,goalsHome);
        cv.put(GoalsGuest,goalsGuest);
        sqLiteDatabase.insert(Foolball,null,cv);
        sqLiteDatabase.close();
    }
    public LinkedList<Data> GetAll(){
        LinkedList<Data> list = new LinkedList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Foolball,null,null,null,null,null,null,null);

        if (cursor.moveToFirst())
            do {
                int id = cursor.getColumnIndex(ID);
                int teamHome = cursor.getColumnIndex(TeamHome);
                int teamGuest = cursor.getColumnIndex(TeamGuest);
                int goalsHome = cursor.getColumnIndex(GoalsHome);
                int goalsGuest = cursor.getColumnIndex(GoalsGuest);

                Data data = new Data(cursor.getInt(id),cursor.getString(teamHome),cursor.getString(teamGuest),
                        cursor.getInt(goalsHome), cursor.getInt(goalsGuest));
                list.add(data);
            }while (cursor.moveToNext());
        sqLiteDatabase.close();
        return list;
    }


}
