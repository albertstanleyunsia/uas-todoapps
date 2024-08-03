package com.example.uasapps;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uasapps.adapter.TodoAdapter;
import com.example.uasapps.models.TodoModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String filename = "todo.json";
    private ArrayList<TodoModel> items;
    private ArrayAdapter<TodoModel> todoAdapter;
    private ListView lvItems;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<TodoModel>();
        todoAdapter = new TodoAdapter(this, items);
        lvItems.setAdapter(todoAdapter);
        readItems();
        setupListViewListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        TodoModel newItem = new TodoModel(itemText);
        todoAdapter.add(newItem);
        etNewItem.setText("");
        items.add(newItem);
        writeItems();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Remove the item within array at position
                        todoAdapter.remove(items.get(pos));
                        items.remove(pos);
                        writeItems(); //
                        // Refresh the adapter
                        return true;
                    }
                });
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, filename);

        try (FileReader reader = new FileReader(todoFile)) {
            Type todoListType = new TypeToken<ArrayList<TodoModel>>(){}.getType();
            items = gson.fromJson(reader, todoListType);
            if (items == null) {
                items = new ArrayList<>();
            }

            todoAdapter.addAll(items);
        } catch (IOException e) {
            items = new ArrayList<>();
            e.printStackTrace();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, filename);

        try (FileWriter writer = new FileWriter(todoFile)) {
            gson.toJson(items, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}