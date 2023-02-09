package com.example.footballchampionship;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBMatches {

    private static final String DATABASE_NAME = "simple.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tableMatches";
    private static final String TABLE_TEAMS = "tableTeams";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TEAMHOME = "TeamНоme";
    private static final String COLUMN_TEAMGUAST = "TeamGuest";
    private static final String COLUMN_GOALSHOME = "GoalsHome";
    private static final String COLUMN_GOALSGUAST = "GoalsGuast";

    private static final String COLUMN_IDTEAM = "idTeam";
    private static final String COLUMN_NAMETEAM = "nameTeam";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_TEAMHOME = 1;
    private static final int NUM_COLUMN_TEAMGUAST = 2;
    private static final int NUM_COLUMN_GOALSHOME = 3;
    private static final int NUM_COLUMN_GOALSGUEST = 4;

    private SQLiteDatabase mDataBase;

    public DBMatches(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public long insert(int teamhouse,int teamguest,int goalshouse,int goalsguest) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_TEAMHOME, teamhouse);
        cv.put(COLUMN_TEAMGUAST, teamguest);
        cv.put(COLUMN_GOALSHOME, goalshouse);
        cv.put(COLUMN_GOALSGUAST,goalsguest);
        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    public int update(Matches md) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_TEAMHOME, md.getTeamhouse());
        cv.put(COLUMN_TEAMGUAST, md.getTeamguest());
        cv.put(COLUMN_GOALSHOME, md.getGoalshouse());
        cv.put(COLUMN_GOALSGUAST,md.getGoalsguest());
        return mDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?",new String[] { String.valueOf(md.getId())});
    }

    public void deleteAll() {
        mDataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(long id) {
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public ArrayList<String> GetAll(){

        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = mDataBase.query(TABLE_TEAMS,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                int nameTeam = cursor.getColumnIndex(COLUMN_NAMETEAM);
                list.add(cursor.getString(nameTeam));

            }while (cursor.moveToNext());
        }
        return list;
    }




    public Matches select(long id) {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        mCursor.moveToFirst();
        int TeamHome = mCursor.getInt(NUM_COLUMN_TEAMHOME);
        int TeamGuest = mCursor.getInt(NUM_COLUMN_TEAMGUAST);
        int GoalsHome = mCursor.getInt(NUM_COLUMN_GOALSHOME);
        int GoalsGuest=mCursor.getInt(NUM_COLUMN_GOALSGUEST);
        return new Matches(id, TeamHome, TeamGuest, GoalsHome,GoalsGuest);
    }

    public ArrayList<Matches> selectAll() {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Matches> arr = new ArrayList<>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                int TeamHome = mCursor.getInt(NUM_COLUMN_TEAMHOME);
                int TeamGuest = mCursor.getInt(NUM_COLUMN_TEAMGUAST);
                int GoalsHome = mCursor.getInt(NUM_COLUMN_GOALSHOME);
                int GoalsGuest=mCursor.getInt(NUM_COLUMN_GOALSGUEST);
                arr.add(new Matches(id, TeamHome, TeamGuest, GoalsHome,GoalsGuest));
            } while (mCursor.moveToNext());
        }
        return arr;
    }
    public String getTeam(int id){
        Cursor mCursor = mDataBase.query(TABLE_TEAMS, null, COLUMN_IDTEAM + " = ?", new String[]{String.valueOf(id)} , null, null, null, null);
        mCursor.moveToFirst();
        String ret = mCursor.getString(1);
        return ret;

    }

    private class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TEAMHOME+ " INTEGER, " +
                    COLUMN_TEAMGUAST + " INTEGER, " +
                    COLUMN_GOALSHOME + " INT,"+
                    COLUMN_GOALSGUAST+" INT);";

            db.execSQL(query);
             query = "CREATE TABLE " + TABLE_TEAMS + " (" +
                    COLUMN_IDTEAM + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAMETEAM+ " TEXT);";

            db.execSQL(query);
            ContentValues contentValues = new ContentValues();
            String[]nameTeam = {"Ахмат", "Динамо М", "Зенит", "Локомотив", "Ростов", "Спартак М", "Торпедо М"};
            for (int i = 0; i < nameTeam.length; i++) {
                contentValues.clear();
                contentValues.put(COLUMN_NAMETEAM, nameTeam[i]);
                db.insert(TABLE_TEAMS, null, contentValues);
            }


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}
