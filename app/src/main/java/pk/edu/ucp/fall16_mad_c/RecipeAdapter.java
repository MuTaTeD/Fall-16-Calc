/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package pk.edu.ucp.fall16_mad_c;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import android.graphics.Typeface;

public class RecipeAdapter extends BaseAdapter {

	public static final String TAG = RecipeAdapter.class.getSimpleName();
	static int createCount = 0;

	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList < Recipe > mDataSource;

	public RecipeAdapter(Context context, ArrayList < Recipe > items) {
		mContext = context;
		mDataSource = items;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * How many items are in the data set represented by this Adapter.
	 *
	 * @return Count of items.
	 */
	@Override
	public int getCount() {
		return mDataSource.size();
	}

	/**
	 * Get the data item associated with the specified position in the data set.
	 *
	 * @param position Position of the item whose data we want within the adapter's
	 *                 data set.
	 * @return The data at the specified position.
	 */
	@Override
	public Object getItem(int position) {
		return mDataSource.get(position);
	}

	/**
	 * Get the row id associated with the specified position in the list.
	 *
	 * @param position The position of the item within the adapter's data set whose row id we want.
	 * @return The id of the item at the specified position.
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	//  UN-OPTIMISED IMPLEMENTATION OF getView()
	/**
	 * Get a View that displays the data at the specified position in the data set. You can either
	 * create a View manually or inflate it from an XML layout file. When the View is inflated, the
	 * parent View (GridView, ListView...) will apply default layout parameters unless you use
	 * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
	 * to specify a root view and to prevent attachment to the root.
	 *
	 * @param position    The position of the item within the adapter's data set of the item whose view
	 *                    we want.
	 * @param convertView The old view to reuse, if possible. Note: You should check that this view
	 *                    is non-null and of an appropriate type before using. If it is not possible to convert
	 *                    this view to display the correct data, this method can create a new view.
	 *                    Heterogeneous lists can specify their number of view types, so that this View is
	 *                    always of the right type (see {@link #getViewTypeCount()} and
	 *                    {@link #getItemViewType(int)}).
	 * @param parent      The parent that this view will eventually be attached to
	 * @return A View corresponding to the data at the specified position.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get view for row item
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = mInflater.inflate(R.layout.list_item_complex, parent, false);
		Log.d(TAG, "CreateCount = " + ++createCount);

		// Get relevant subviews of row view
		TextView titleTextView = (TextView) rowView.findViewById(R.id.recipe_list_title);
		TextView subtitleTextView = (TextView) rowView.findViewById(R.id.recipe_list_subtitle);
		TextView foodTypeTextView = (TextView) rowView.findViewById(R.id.recipe_food_type);
		ImageView thumbnailImageView = (ImageView) rowView.findViewById(R.id.recipe_list_thumbnail);

		//Get corresponding recipe for row
		Recipe recipe = (Recipe) getItem(position);

		// Update row view's textviews to display recipe information
		titleTextView.setText(recipe.title);
		subtitleTextView.setText(recipe.description);
		foodTypeTextView.setText(recipe.foodType);

		if (position % 2 == 0)
		{
			thumbnailImageView.setImageResource(R.drawable.bouncing_ball_00);
		} else
		{
			thumbnailImageView.setImageResource(R.drawable.arrow);
		}

		// Style text views
		Typeface titleTypeFace = Typeface.createFromAsset(mContext.getAssets(),
				"fonts/JosefinSans-Bold.ttf");
		titleTextView.setTypeface(titleTypeFace);
		Typeface subtitleTypeFace = Typeface.createFromAsset(mContext.getAssets(),
				"fonts/JosefinSans-SemiBoldItalic.ttf");
		subtitleTextView.setTypeface(subtitleTypeFace);
		Typeface detailTypeFace = Typeface.createFromAsset(mContext.getAssets(),
				"fonts/Quicksand-Bold.otf");
		foodTypeTextView.setTypeface(detailTypeFace);
		foodTypeTextView.setTextColor(android.support.v4.content.ContextCompat.getColor(mContext, R.color.red));

		return rowView;
	}
}
