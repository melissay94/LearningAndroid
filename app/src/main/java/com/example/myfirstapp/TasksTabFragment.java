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

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TasksTabFragment extends Fragment {

    // File Saver
    private SimpleListSaver saver;

    // Adapter
    private SimpleAdapter recyclerAdapter;

    // Unbinder
    private Unbinder unbinder;

    // RecyclerView
    @BindView(R.id.simple_recycle)
    RecyclerView recyclerView;

    // Title of the list area
    @BindView(R.id.list_title)
    TextView listTitle;

    // Button for sending tasks
    @BindView(R.id.add_button)
    Button addButton;

    // Field for entering task name
    @BindView(R.id.editText)
    EditText editTaskName;

    public interface DeleteListItemListener {
        void onDeleteItem(final int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        super.onCreate(bundle);
        final View view = inflater.inflate(R.layout.fragment_tasks_tab, container, false);

        unbinder = ButterKnife.bind(this, view);

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
            listTitle.setText(String.format(Locale.US, getString(R.string.task_list), String.valueOf(saver.getTasks().size())));
        }

        return view;

    }

    // Since fragments have different view lifecycles, need to unbind the view
    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    // Called when user hits send button
    @OnClick(R.id.add_button)
    public void sendTask() {
        String message = editTaskName.getText().toString();
        if (message.replace(" ", "").length() > 0) {
            recyclerAdapter.addToModelList(message);
            saver.writeToFile(message, Context.MODE_APPEND);
            saver.getTasks().add(message);
        }
        editTaskName.setText("");

        listTitle.setText(String.format(Locale.US, getString(R.string.task_list), String.valueOf(saver.getTasks().size())));

    }
}
