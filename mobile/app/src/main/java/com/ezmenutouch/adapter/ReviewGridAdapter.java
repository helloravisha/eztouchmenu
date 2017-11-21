package com.ezmenutouch.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ezmenutouch.R;
import com.ezmenutouch.vo.Review;

import java.util.ArrayList;

/**
 * Adapater class for setting the videos.
 *
 *
 */
public class ReviewGridAdapter extends BaseAdapter {
    Context context;
    int layoutResourceId;
    ArrayList<Review> data = new ArrayList<Review>();

    public ReviewGridAdapter(Context context, int layoutResourceId,
                                 ArrayList<Review> data) {
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View gridView = null;
        if (row == null) {


            gridView = new View(context);


        } else {

            gridView = (View) convertView;

        }
        gridView = inflater.inflate(R.layout.review, null);
        TextView reviewText = (TextView) gridView.findViewById(R.id.reviewText);


        Review review = data.get(position);
        reviewText.setText(review.getReview());

        return gridView;
    }
}