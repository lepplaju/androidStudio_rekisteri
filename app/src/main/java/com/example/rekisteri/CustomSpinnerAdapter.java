package com.example.rekisteri;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> items;

    public CustomSpinnerAdapter(Context context, List<String> items) {
        super(context, R.layout.spinner_layout, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_layout, parent, false);

        ImageView imageView = row.findViewById(R.id.spinner_img);

        String imageName = items.get(position);

        // Load the image dynamically using the image name
        int imageResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        Drawable image = ContextCompat.getDrawable(context, imageResourceId);

        imageView.setImageDrawable(image);

        return row;
    }
}

