package com.example.myfirstapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 */
public class ShoppingTabFragment extends Fragment {

    // Adapter
    SimpleAdapter recyclerAdapter;

    // RecyclerView
    RecyclerView recyclerView;

    // Field for entering item
    EditText editShoppingItemName;

    // Field for entering price of item
    EditText editShoppingItemPrice;

    // Title of the list area
    TextView listTitle;

    // Button for adding item
    Button addItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        super.onCreate(bundle);
        final View view = inflater.inflate(R.layout.fragment_shopping_tab, container, false);

        editShoppingItemName = view.findViewById(R.id.editItemName);
        editShoppingItemPrice = view.findViewById(R.id.editText);
        listTitle = view.findViewById(R.id.list_title);
        addItem = view.findViewById(R.id.add_button);

        // Change fields based on view
        editShoppingItemPrice.setHint(R.string.edit_text_add_price);
        editShoppingItemPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        editShoppingItemPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                editShoppingItemPrice.removeTextChangedListener(this);
                editShoppingItemPrice.setText("$ " + editShoppingItemPrice.getText().toString());
                editShoppingItemPrice.setSelection(editShoppingItemPrice.getText().length());
            }
        });

        listTitle.setText(R.string.empty_shopping_list);

        // Set up button event
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendTask();
            }
        });

        recyclerAdapter = new SimpleAdapter();

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

        return view;
    }
    // Called when user hits send button
    public void sendTask() {
        String itemName = editShoppingItemName.getText().toString();
        String itemPrice = editShoppingItemPrice.getText().toString();

        if (itemName.replace(" ", "").length() > 0) {
            recyclerAdapter.addToModelList(itemName + " - $" + itemPrice);
        }

        editShoppingItemName.setText("");
        editShoppingItemPrice.setText("");

        listTitle.setText(R.string.shopping_list);

    }


}
