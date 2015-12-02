package com.example.lady.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lady.myapplication.adapter.AdapterPersonas;
import com.example.lady.myapplication.entidades.Personas;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    MyDB dbHandler;
    FloatingActionButton floatingActionButton;
    ListView listVieContact;
    EditText editTextBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fabAgregar);
        listVieContact = (ListView) findViewById(R.id.listViewcontactos);
        editTextBuscar = (EditText) findViewById(R.id.buscaredit);
        floatingActionButton.setOnClickListener(this);
        dbHandler = new MyDB(this, null, null, 1);
        displayListview();
        buscarDisplay();
    }

    private void buscarDisplay() {
        editTextBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                displayListviewByText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void displayListviewByText(String text) {

        ArrayList<Personas> personasList = dbHandler.getPersonasesByText(text);
        AdapterPersonas adapterPersonas = new AdapterPersonas(this, 0, personasList);
        listVieContact.setAdapter(adapterPersonas);
        listVieContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Personas personas = (Personas) parent.getItemAtPosition(position);
                dialogOpe(personas);
            }
        });


    }

    private void displayListview() {

        ArrayList<Personas> personasList = dbHandler.getPersonases();
        AdapterPersonas adapterPersonas = new AdapterPersonas(this, 0, personasList);
        listVieContact.setAdapter(adapterPersonas);
        listVieContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Personas personas = (Personas) parent.getItemAtPosition(position);
                dialogOpe(personas);
            }
        });


    }

    private void dialogOpe(final Personas personas) {
        final EditText editText = new EditText(this);
        String[] m1 = {"1. Modificar"};
        String[] m2 = {"2. Eliminar"};
        new AlertDialog.Builder(this)
                .setTitle("¿Que desea hacer?")
                .setMessage("Seleccione una opcion: \n 1.Modificar. \n 2.Eliminar")
                .setView(editText)

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        int s = Integer.parseInt(editText.getText().toString());
                        if (s == 1) {
                            Intent intent = new Intent(getApplicationContext(), Modificar.class);
                            intent.putExtra("ID", "" + personas.get_id());
                            startActivity(intent);
                            finish();
                        } else {
                            dbHandler.borrarPersona(personas.get_id());
                        }
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabAgregar:
                startActivity(new Intent(getApplicationContext(), Agregar.class));
                this.finish();
                break;

        }
    }
}
