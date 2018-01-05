package com.example.myfirstapp;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by melissa young on 12/28/17.
 * Decorator dividers for recycler view children
 * Based on: https://www.bignerdranch.com/blog/a-view-divided-adding-dividers-to-your-recyclerview-with-itemdecoration/
 */

public class DividerDecoration extends RecyclerView.ItemDecoration {

    private Drawable divider;

    public DividerDecoration(Drawable divider) {
        this.divider = divider;
    }

    // Changes shape of rect that determines amount of padding on each item's side
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        // Don't want to change anything for the first item
        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }

        outRect.top = divider.getIntrinsicHeight();
    }

    // Determines the bounds of our divider and draws it
    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {

        int dividerLeft = 0;
        int dividerRight = parent.getRight();
        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int dividerTop = child.getBottom() + params.bottomMargin;
            int dividerBottom = dividerTop + divider.getIntrinsicHeight();

            divider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
            divider.draw(canvas);
        }
    }
}
