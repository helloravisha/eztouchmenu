package com.ezmenutouch.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezmenutouch.R;
import com.ezmenutouch.vo.FoodVideo;

import java.util.ArrayList;

/**
 * 
 *Adpater class for setting the videos.
 *
 */
public class CustomGridViewAdapter extends BaseAdapter implements View.OnClickListener{
	Context context;
	int layoutResourceId;
	ArrayList<FoodVideo> data = new ArrayList<FoodVideo>();


	public CustomGridViewAdapter(Context context, int layoutResourceId,
								 ArrayList<FoodVideo> data) {
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
		gridView = inflater.inflate(R.layout.rowgrid, null);
		TextView movieText = (TextView) gridView.findViewById(R.id.item_text);
		ImageView imageView = (ImageView) gridView.findViewById(R.id.item_image);
		imageView.setOnClickListener(this);
		movieText.setOnClickListener(this);
		FoodVideo trailers = data.get(position);
		imageView.setTag(trailers.getTitle());
		movieText.setTag(trailers.getTitle());
        int trailerCount = position+1;
		movieText.setText("Trailer: "+trailerCount);
		imageView.setImageBitmap(trailers.getImage());
		return gridView;
	}

	@Override
	public void onClick(View v) {

	}
}