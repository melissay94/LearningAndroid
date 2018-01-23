package com.example.myfirstapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TasksTabFragment extends Fragment {

    // File Saver
    SimpleListSaver saver;

    // Adapter
    SimpleAdapter recyclerAdapter;

    // RecyclerView
    RecyclerView recyclerView;

    // Title of the list area
    TextView listTitle;

    // Get the button for sending tasks
    Button addButton;

    EditText editText;

    public interface DeleteListItemListener {
        void onDeleteItem(final int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        super.onCreate(bundle);
        final View view = inflater.inflate(R.layout.fragment_tasks_tab, container, false);

        addButton = view.findViewById(R.id.add_button);
        editText = view.findViewById(R.id.editText);
        listTitle = view.findViewById(R.id.list_title);

        // Set up button event
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendTask();
            }
        });

        // Instantiate saver
        saver = new SimpleListSaver(this.getActivity());

        // Read in the tasks
        saver.readFromFile();

        recyclerAdapter = new SimpleAdapter(new DeleteListItemListener() {
            @Override
            public void onDeleteItem(final int position) {
                recyclerAdapter.deleteFromModelList(position);
                saver.removeFromFile(position, listTitle);
            }
        });

        // Instantiate recyclerView
        recyclerView = view.findViewById(R.id.simple_recycle);

        // Manages layout of list to determine how data displays --> This one is vertically linear
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // Determines if the recyclerView has a fixed size. --> Lets framework know that the size will not be affected by the adapter
        recyclerView.setHasFixedSize(true);

        // Attaches our adapter to our recycleView
        recyclerView.setAdapter(recyclerAdapter);

        // Set up item animator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Set up item decorator
        RecyclerView.ItemDecoration itemDivider = new DividerDecoration(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        recyclerView.addItemDecoration(itemDivider);

        // Populate our view
        for (String task : saver.getTasks()) {
            recyclerAdapter.addToModelList(task);
        }

        if (saver.getTasks().size() > 0) {
            listTitle.setText(getString(R.string.task_list) + " (" + saver.getTasks().size() + " left)");
        }

        return view;

    }

    // Called when user hits send button
    public void sendTask() {
        String message = editText.getText().toString();
        if (message.replace(" ", "").length() > 0) {
            recyclerAdapter.addToModelList(message);
            saver.writeToFile(message, Context.MODE_APPEND);
            saver.getTasks().add(message);
        }
        editText.setText("");

        listTitle.setText(getString(R.string.task_list) + " (" + saver.getTasks().size() + " left)" );

    }
}
