package com.example.myfirstapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by melissa young on 12/12/17.
 * Part of recyclerView tutorial
 * Object that represents each item in collection, used to display it.
 */

public class SimpleViewHolder extends RecyclerView.ViewHolder  {

    @BindView(R.id.simple_text)
    TextView taskNameView;

    // Constructor
    public SimpleViewHolder(final View itemView, final TasksTabFragment.DeleteListItemListener listener) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        final Button deleteButton = itemView.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDeleteItem(getAdapterPosition());
            }
        });
    }

    // Binds data to textView
    public void bindData(final SimpleViewModel viewModel) {
        taskNameView.setText(viewModel.getSimpleText());
    }
}
