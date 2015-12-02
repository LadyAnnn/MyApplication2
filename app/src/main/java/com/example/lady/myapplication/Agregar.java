package com.example.lady.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lady.myapplication.entidades.Personas;

/**
 * Created by LADY on 18/11/2015.
 */
public class Agregar extends Activity implements View.OnClickListener {


    MyDB dbHandler;
    EditText nombre_input;
    EditText apellido_input;
    EditText direccion_input;
    EditText numero_input;
    EditText correo_input;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        nombre_input = (EditText) findViewById(R.id.nombrea);
        apellido_input = (EditText) findViewById(R.id.apellidoa);
        direccion_input = (EditText) findViewById(R.id.direcciona);
        numero_input = (EditText) findViewById(R.id.Telefonotext);
        correo_input = (EditText) findViewById(R.id.correoa);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        events();
        dbHandler = new MyDB(this, null, null, 1);
    }

    private void events() {
        btnAgregar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnAgregar:
                agregar_clicked();
                break;
        }
    }

    public void agregar_clicked() {

        String nombre = nombre_input.getText().toString();
        String apellidos = apellido_input.getText().toString();
        String correo = correo_input.getText().toString();
        int numero = Integer.parseInt(numero_input.getText().toString());
        String direccion = direccion_input.getText().toString();

        Personas persona = new Personas();
        persona.agregar(nombre, direccion, apellidos, numero, correo);
        if(dbHandler.addPersona(persona)>0){
            confirmacion();
        }else{
            mensajeError();
        }

        limpiarcampos();
    }

    public void limpiarcampos() {
        numero_input.setText("");
        nombre_input.setText("");
        apellido_input.setText("");
        direccion_input.setText("");
        correo_input.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void confirmacion() {

        new AlertDialog.Builder(this)
                .setTitle("Exito")
                .setMessage("Guardado Correctamente")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void mensajeError() {

        new AlertDialog.Builder(this)
                .setTitle(":c")
                .setMessage("Ocurrio un Error :C")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
