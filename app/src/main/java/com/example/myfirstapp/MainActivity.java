package com.example.myfirstapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // File Saver
    SimpleListSaver saver;

    // Adapter
    SimpleAdapter adapter;

    // Title of the list area
    TextView listTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate saver
        saver = new SimpleListSaver(this);

        // Instantiate adapter
        adapter = new SimpleAdapter(saver);

        // Instantiate list title
        listTitle = findViewById(R.id.list_title);

        // Read in the tasks
        saver.readFromFile();

        // Instantiate recyclerView
        RecyclerView recyclerView = findViewById(R.id.simple_recycle);

        // Manages layout of list to determine how data displays --> This one is vertically linear
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Determines if the recyclerView has a fixed size. --> Lets framework know that the size will not be affected by the adapter
        recyclerView.setHasFixedSize(true);

        // Attaches our adapter to our recycleView
        recyclerView.setAdapter(adapter);

        // Set up item animator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Set up item decorator
        RecyclerView.ItemDecoration itemDivider = new DividerDecoration(ContextCompat.getDrawable(this, R.drawable.divider));
        recyclerView.addItemDecoration(itemDivider);

        // Populate our view
        for (String task : saver.getTasks()) {
            adapter.addToModelList(task);
        }

        if (saver.getTasks().size() > 0)
            listTitle.setText(getString(R.string.list_title) + " (" + saver.getTasks().size() + " left)" );
    }

    // Called when user hits send button
    public void sendTask(View view) {
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        if (message.replace(" ", "").length() > 0) {
            adapter.addToModelList(message);
            saver.writeToFile(message, Context.MODE_APPEND);
            saver.getTasks().add(message);
        }
        editText.setText("");

        listTitle.setText(getString(R.string.list_title) + " (" + saver.getTasks().size() + " left)" );

    }

}
