package com.example.lady.myapplication.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lady.myapplication.R;
import com.example.lady.myapplication.entidades.Personas;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by LADY on 18/11/2015.
 */
public class AdapterPersonas extends ArrayAdapter<Personas> {
    Context CONTX;
    List<Personas> personases;

    public AdapterPersonas(Context context, int resource, List<Personas> objects) {
        super(context, resource, objects);
        CONTX=context;
        personases = objects;
    }
    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        if (row == null)
        {

            LayoutInflater vi = (LayoutInflater)CONTX.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = vi.inflate(R.layout.layout_item_persona, null);
        }

        TextView tvNombre = (TextView) row.findViewById (R.id.textNombreIn);
        TextView tvNumero = (TextView) row.findViewById (R.id.textNumeroIn);
        tvNombre.setText(personases.get(position).get_nombre());
        tvNumero.setText(personases.get(position).get_numero()+"");

        return row;
    }
}
