package com.example.myfirstapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 */
public class ShoppingTabFragment extends Fragment {

    EditText editShoppingItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        super.onCreate(bundle);
        final View view = inflater.inflate(R.layout.fragment_shopping_tab, container, false);

        editShoppingItem = view.findViewById(R.id.editText);
        editShoppingItem.setHint(R.string.edit_text_add_item);


        return inflater.inflate(R.layout.fragment_shopping_tab, container, false);
    }
}
