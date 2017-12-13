package com.app.ardasatata.project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ardasatata on 12/13/17.
 */

public class RadioListAdapter extends ArrayAdapter{

    private final Activity context;
    private final String[] nameArray;

    public RadioListAdapter(Activity context, String[] nameArrayParam){

        super(context,R.layout.listview_row , nameArrayParam);

        this.context = context;
        this.nameArray = nameArrayParam;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.namaRadio);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);

        return rowView;
    };
}
