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

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Fragment for setting up shopping list view and it's recycler view
 */
public class ShoppingTabFragment extends Fragment {

    // Adapter
    private SimpleAdapter recyclerAdapter;

    // Set up unbinder for bindings
    private Unbinder unbinder;

    // RecyclerView
    @BindView(R.id.simple_recycle)
    RecyclerView recyclerView;

    // Field for entering item
    @BindView(R.id.editItemName)
    EditText editShoppingItemName;

    // Field for entering price of item
    @BindView(R.id.editText)
    EditText editShoppingItemPrice;

    // Title of the list area
    @BindView(R.id.list_title)
    TextView listTitle;

    // Button for adding item
    @BindView(R.id.add_button)
    Button addItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        super.onCreate(bundle);

        final View view = inflater.inflate(R.layout.fragment_shopping_tab, container, false);
        unbinder = ButterKnife.bind(this, view);

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
                editShoppingItemPrice.setText(String.format(Locale.US, getString(R.string.money_prefix), editShoppingItemPrice.getText().toString()));
                editShoppingItemPrice.setSelection(editShoppingItemPrice.getText().length());
            }
        });

        listTitle.setText(R.string.empty_shopping_list);

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

    // Since fragments have different view lifecycles, need to unbind the view
    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    // Called when user hits send button
    @OnClick(R.id.add_button)
    public void sendItem() {
        String itemName = editShoppingItemName.getText().toString();
        String itemPrice = editShoppingItemPrice.getText().toString();

        if (itemName.replace(" ", "").length() > 0) {
            recyclerAdapter.addToModelList(itemName + " - " + itemPrice);
        }

        editShoppingItemName.setText("");
        editShoppingItemPrice.setText("");

        listTitle.setText(R.string.shopping_list);

    }
}
