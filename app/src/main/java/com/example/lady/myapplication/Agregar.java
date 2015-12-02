package com.example.lady.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

/**
 * Created by LADY on 18/11/2015.
 */
public class Agregar extends Activity {


    MyDB dbHandler;
    EditText nombre_input;
    EditText apellido_input;
    EditText direccion_input;
    EditText numero_input;
    EditText correo_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        nombre_input = (EditText) findViewById(R.id.nombre_input);
        apellido_input = (EditText) findViewById(R.id.apellido_input);
        direccion_input = (EditText) findViewById(R.id.direccion_input);
        numero_input = (EditText) findViewById(R.id.numero_input);
        correo_input = (EditText) findViewById(R.id.correo_input);
        dbHandler = new MyDB(this, null, null, 1);
    }
    public void agregar_clicked(View view) {

        Personas persona = new Personas();
        persona.agregar(
                nombre_input.getText().toString(),
                apellido_input.getText().toString(),
                correo_input.getText().toString(),
                Integer.parseInt(numero_input.getText().toString()),
                direccion_input.getText().toString());
        dbHandler.addPersona(persona);
        confirmacion();
        limpiarcampos();
    }

    public void limpiarcampos() {
        numero_input.setText("");
        nombre_input.setText("");
        apellido_input.setText("");
        direccion_input.setText("");
        correo_input.setText("");
    }

    public void confirmacion() {

        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Se ha agregado exitosamente!");
        dlgAlert.setTitle("Agregar Persona");
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }


}
