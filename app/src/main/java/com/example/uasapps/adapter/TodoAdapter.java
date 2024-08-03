package com.example.uasapps.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.uasapps.R;
import com.example.uasapps.models.TodoModel;

import java.util.List;

public class TodoAdapter extends ArrayAdapter<TodoModel> {
    public TodoAdapter(Context context, List<TodoModel> todos) {
        super(context, 0, todos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, parent, false);
        }

        // Get the data item for this position
        TodoModel todo = getItem(position);

        // Lookup the TextView in the item layout and set the title
        TextView titleView = convertView.findViewById(R.id.todo_title);
        titleView.setText(todo.getTitle());

        // Return the completed view to render on screen
        return convertView;
    }
}
