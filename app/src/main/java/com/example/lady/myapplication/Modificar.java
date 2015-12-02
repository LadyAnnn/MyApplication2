package com.example.lady.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * Created by LADY on 18/11/2015.
 */
public class Modificar extends Activity{
    MyDB dbHandler;
    EditText nombre_input;
    EditText apellido_input;
    EditText direccion_input;
    EditText numero_input;
    EditText correo_input;
    int idglobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Aqui se hace el retrieve de la base de datos tomando un valor que viene en el intent anterior

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        nombre_input = (EditText) findViewById(R.id.nombre_input);
        apellido_input = (EditText) findViewById(R.id.apellido_input);
        direccion_input = (EditText) findViewById(R.id.direccion_input);
        numero_input = (EditText) findViewById(R.id.numero_input);
        correo_input = (EditText) findViewById(R.id.correo_input);
        dbHandler = new MyDB(this, null, null, 1);
        Personas personas = new Personas();
         Intent i = getIntent(); // gets the previously created intent
        String stringid = i.getStringExtra("id_persona");
        int id = Integer.parseInt(stringid);
        Cursor c = dbHandler.personabyid(id);

        //Vuelve a rellenar los inputs con los valores del cursor
        nombre_input.setText(c.getString(c.getColumnIndexOrThrow("nombre")));
        apellido_input.setText(c.getString(c.getColumnIndexOrThrow("apellido")));
        direccion_input.setText(c.getString(c.getColumnIndexOrThrow("direccion")));
        numero_input.setText(c.getString(c.getColumnIndexOrThrow("numero")));
        correo_input.setText(c.getString(c.getColumnIndexOrThrow("correo")));
        idglobal = c.getInt(c.getColumnIndexOrThrow("_id"));

    }

    public void modificar_clicked(View view){

        Personas persona = new Personas(Integer.parseInt(numero_input.getText().toString()),
                nombre_input.getText().toString(),
                apellido_input.getText().toString(),
                correo_input.getText().toString(),
                direccion_input.getText().toString());
        persona.set_id(idglobal);
        dbHandler.updatepersona(persona);
        confirmacion();
        limpiarcampos();
        finish(); //Termina la actividad y vuelve al menu principal

    }

    public void confirmacion(){

        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Se ha modificado exitosamente!");
        dlgAlert.setTitle("Agregar Persona");
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    //Limpia los valores entrados para efectos de estetica
    public void limpiarcampos(){

        nombre_input.setText("");
        apellido_input.setText("");
        direccion_input.setText("");
        numero_input.setText("");
        correo_input.setText("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modificar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

