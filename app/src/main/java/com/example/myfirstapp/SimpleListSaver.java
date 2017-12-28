package com.example.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by melissa young on 12/18/17.
 * To save internal data
 */

public class SimpleListSaver  {

    private Activity currentActivity;

    // Internal file
    private String dataFileName = "ListData.txt";

    // Array of all the tasks
    private ArrayList<String> tasks = new ArrayList<>();

    // Title of the list
    private TextView listTitle;

    public SimpleListSaver(Activity currentActivity) {
        this.currentActivity = currentActivity;
        listTitle = currentActivity.findViewById(R.id.list_title);
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }

    // Write tasks to a file internally
    public void writeToFile(String newTask, int editMode) {
        FileOutputStream fileOut;
        String separator = System.getProperty("line.separator");

        try {
            fileOut = currentActivity.openFileOutput(dataFileName, editMode);
            OutputStreamWriter writer = new OutputStreamWriter(fileOut);
            writer.write(newTask + separator);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read tasks from internal file
    public void readFromFile() {

        try {
            FileInputStream fileIn = currentActivity.openFileInput(dataFileName);
            InputStreamReader reader = new InputStreamReader(fileIn);
            BufferedReader buffer = new BufferedReader(reader);

            String convertedString;

            while ((convertedString = buffer.readLine()) != null) {
                if (convertedString.length() > 0)
                    tasks.add(convertedString);
            }

            buffer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Remove tasks from internal file
    public void removeFromFile(int index) {
        tasks.remove(index);
        writeToFile("", Context.MODE_PRIVATE);
        for (String task : tasks) {
            writeToFile(task, Context.MODE_APPEND);
        }

        // Change the title
        if (tasks.size() > 0)
            listTitle.setText(currentActivity.getString(R.string.list_title) + " (" + tasks.size() + " left)" );
        else
            listTitle.setText(currentActivity.getString(R.string.empty_list_title));

    }
}
