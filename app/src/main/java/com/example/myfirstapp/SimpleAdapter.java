package com.example.myfirstapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by melissa young on 12/12/17.
 * Part of the recylcer View tutorial
 * Connects data to recyclerView, determines the viewHolders needed to display data
 * Adapter should be as dumb as possible
 */

public class SimpleAdapter extends RecyclerView.Adapter {

    private List<SimpleViewModel> models = new ArrayList<>();

    private final TasksTabFragment.DeleteListItemListener deleteListItemListener;

    public SimpleAdapter(final TasksTabFragment.DeleteListItemListener deleteListItemListener) {
        this.deleteListItemListener = deleteListItemListener;
    }

    // Initializes viewHolders.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewTypes) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewTypes, parent, false);
        return new SimpleViewHolder(view, deleteListItemListener);
    }

    // Binds viewHolders to the adapter
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((SimpleViewHolder) holder).bindData(models.get(position));
    }

    // Returns the size of the collection
    @Override
    public int getItemCount() {
        return models.size();
    }

    // Returns an int that represents view type
    @Override
    public int getItemViewType(final int position) {
        return R.layout.simple_item;
    }

    // Adds task to the adaptor model list
    public void addToModelList(String newMessage) {
        models.add(new SimpleViewModel(String.format(Locale.US, newMessage)));
        this.notifyDataSetChanged();

    }

    // Deletes a task from the adaptor model list
    public void deleteFromModelList(int index) {
        models.remove(index);
        this.notifyItemRemoved(index);
    }
}
