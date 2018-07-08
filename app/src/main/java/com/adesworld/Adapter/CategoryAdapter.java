package com.adesworld.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adesworld.Model.CategoryData;
import com.adesworld.R;

import java.util.ArrayList;

/**
 * Created by ssasa on 07-Jul-18.
 */

public class CategoryAdapter extends BaseAdapter {

    private TextView textView_Name;
    private ArrayList<CategoryData> categoryList;
    private LayoutInflater inflater;

    public CategoryAdapter(ArrayList<CategoryData> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.spinner_category_item, null);
            textView_Name = (TextView) convertView.findViewById(R.id.textView_Name);
            textView_Name.setText(categoryList.get(position).getName());
            return convertView;
    }
}