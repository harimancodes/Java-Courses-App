package com.example.javacoursesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.javacoursesapp.R;
import com.example.javacoursesapp.model.Module;

import java.util.ArrayList;

public class ModuleAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Module> arrayList;
    public ModuleAdapter(Context context,ArrayList<Module> arrayList){
        this.context=context;
        this.arrayList=arrayList;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
view=inflater.inflate(R.layout.beginner_module_list_view,null);
        TextView name=view.findViewById(R.id.beginner_module_list_text_view);
        name.setText(arrayList.get(position).getModuleName());
        return view;
    }
}
