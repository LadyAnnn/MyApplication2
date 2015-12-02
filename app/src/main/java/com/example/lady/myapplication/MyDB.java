package com.example.lady.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lady.myapplication.entidades.Personas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LADY on 18/11/2015.
 */
public class MyDB extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION= 1;
    public static final String TABLA_PERSONAS= "personas";
    public static final String DATABASE_NAME= "personas";
    public static final String COLUMN_ID= "_id";
    public static final String COLUMN_NUMERO= "telefono";
    public static final String COLUMN_NOMBRE= "nomnbre";
    public static final String COLUMN_APELLIDO= "apellido";
    public static final String COLUMN_DIRECCION= "direccion";
    public static final String COLUMN_CORREO= "correo";

    public MyDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLA_PERSONAS+ " ( "+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NUMERO+ " TEXT, " +
                COLUMN_NOMBRE + " TEXT, " +
                COLUMN_APELLIDO + " TEXT, " +
                COLUMN_DIRECCION + " TEXT, " +
                COLUMN_CORREO + " TEXT " +
                ");" ;
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLA_PERSONAS);
        onCreate(db);
    }
    public long addPersona(Personas personas) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMERO, personas.get_numero());
        values.put(COLUMN_NOMBRE, personas.get_nombre());
        values.put(COLUMN_APELLIDO, personas.get_apellido());
        values.put(COLUMN_DIRECCION, personas.get_direccion());
        values.put(COLUMN_CORREO, personas.get_correo());
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLA_PERSONAS, null, values);


    }

    public long updatepersona(Personas personas){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMERO, personas.get_numero());
        values.put(COLUMN_NOMBRE, personas.get_nombre());
        values.put(COLUMN_APELLIDO, personas.get_apellido());
        values.put(COLUMN_DIRECCION, personas.get_direccion());
        values.put(COLUMN_CORREO, personas.get_correo());
        SQLiteDatabase db = getWritableDatabase();
        return db.update(TABLA_PERSONAS, values, COLUMN_ID + "= ?", new String[]{String.valueOf(personas.get_id())});

    }

    public void borrarPersona(int persona_id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLA_PERSONAS + " WHERE " + COLUMN_ID + " = " + persona_id + ";");
        db.close();
    }

    public Personas personabyid(int id){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLA_PERSONAS + " WHERE " + COLUMN_ID + " = " + id + ";";
        Cursor c = db.rawQuery(query, null);
        Personas persona = new Personas();
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            // The Cursor is now set to the right position

            persona.agregarWithId(c.getInt(c.getColumnIndexOrThrow(COLUMN_ID)), c.getString(c.getColumnIndexOrThrow(COLUMN_NOMBRE)), c.getString(c.getColumnIndexOrThrow(COLUMN_DIRECCION)), c.getString(c.getColumnIndexOrThrow(COLUMN_APELLIDO)), c.getInt(c.getColumnIndexOrThrow(COLUMN_NUMERO)), c.getString(c.getColumnIndexOrThrow(COLUMN_CORREO)));

        }


        return persona;
    }

    public Cursor listarpersonas(){
        SQLiteDatabase db = getReadableDatabase();
        String query = ("SELECT * FROM " + TABLA_PERSONAS + " WHERE 1 ORDER BY " + COLUMN_APELLIDO + ";");
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public ArrayList<Personas> getPersonases(){
        ArrayList<Personas> personases =new ArrayList<Personas>();
        SQLiteDatabase db = getReadableDatabase();
        String query = ("SELECT * FROM " + TABLA_PERSONAS + "  ORDER BY " + COLUMN_APELLIDO + ";");
        Cursor c = db.rawQuery(query, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            // The Cursor is now set to the right position
            Personas persona = new Personas();
            persona.agregarWithId(c.getInt(c.getColumnIndexOrThrow(COLUMN_ID)),c.getString(c.getColumnIndexOrThrow(COLUMN_NOMBRE)), c.getString(c.getColumnIndexOrThrow(COLUMN_DIRECCION)), c.getString(c.getColumnIndexOrThrow(COLUMN_APELLIDO)), c.getInt(c.getColumnIndexOrThrow(COLUMN_NUMERO)), c.getString(c.getColumnIndexOrThrow(COLUMN_CORREO)));
            personases.add(persona);
        }
        return  personases;
    }

    public ArrayList<Personas> getPersonasesByText(String s){
        ArrayList<Personas> personases =new ArrayList<Personas>();
        SQLiteDatabase db = getReadableDatabase();
        String query = ("SELECT * FROM " + TABLA_PERSONAS + " WHERE "+COLUMN_NOMBRE+" LIKE '%"+s+"%';");
        Cursor c = db.rawQuery(query, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            // The Cursor is now set to the right position
            Personas persona = new Personas();
            persona.agregarWithId(c.getInt(c.getColumnIndexOrThrow(COLUMN_ID)),c.getString(c.getColumnIndexOrThrow(COLUMN_NOMBRE)), c.getString(c.getColumnIndexOrThrow(COLUMN_DIRECCION)), c.getString(c.getColumnIndexOrThrow(COLUMN_APELLIDO)), c.getInt(c.getColumnIndexOrThrow(COLUMN_NUMERO)), c.getString(c.getColumnIndexOrThrow(COLUMN_CORREO)));
            personases.add(persona);
        }
        return  personases;
    }
}
