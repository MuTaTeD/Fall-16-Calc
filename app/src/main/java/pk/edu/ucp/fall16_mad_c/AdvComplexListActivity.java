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

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import android.content.Context;
import java.io.IOException;
import java.io.InputStream;

public class AdvComplexListActivity extends AppCompatActivity {

	public static final String TAG = AdvComplexListActivity.class.getSimpleName();

	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complex_list);
		mListView = (ListView) findViewById(R.id.complexListView);


		final Context context = this;

		// Get data to display

		String completeData = loadDataFromFile("mydata.txt");
		final ArrayList<Recipe> records = parseRecords(completeData);

		// Create adapter
		AdvRecipeAdapter adapter = new AdvRecipeAdapter(context, records);

		// Create list view

		mListView.setAdapter(adapter);

		// Set what happens when a list view item is clicked
		mListView.setOnItemClickListener(new OnItemClickListener() {

		  @Override
		  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Recipe selectedRecipe = records.get(position);
			  Log.d(TAG, "Pos:   " + position);
			  Log.d(TAG, "Title: " + selectedRecipe.title);
			  Log.d(TAG, "Desc:  " + selectedRecipe.description);
			  Log.d(TAG, "Serves:" + selectedRecipe.servingSize);
			  Log.d(TAG, "Type:  " + selectedRecipe.foodType);

			  if(position == 5)
			  {
				  Intent a = new Intent(getApplicationContext(), CalcInStepActivity.class);
				  startActivity(a);
			  }
		  }

	});



	}

	private String loadDataFromFile(String filename)
	{
	String fileData = "";
	AssetManager asstManager = getAssets();
	try
	{
	  InputStream input = asstManager.open(filename);

	  int size = input.available();

	  byte[] buffer = new byte[size];
	  input.read(buffer);
	  input.close();

	  fileData = new String(buffer);
	}
	catch(Exception e)
	{
	  e.printStackTrace();
	}
	return fileData;
	}

	private ArrayList<Recipe> parseRecords(String data)
	{
		String l1 = "";
		String l2 = "";
		String l3 = "";
		String l4 = "";

		ArrayList<Recipe> records = new ArrayList<Recipe>();
		String parts[] = data.split("\n");

		int lineId = 0;

		for(String line : parts)
		{
			if(line.length() == 0)
			{

			}
			else
			{
				lineId++;
			}
			if(lineId == 1)
			{
				l1 = line;
			}
			else if(lineId == 2)
			{
				l2 = line;
			}
			else if(lineId == 3)
			{
				l3 = line;
			}
			else if(lineId == 4)
			{
				l4 = line;
				lineId = 0;
				Recipe r = new Recipe(l1, l2, l3, l4, "");
				records.add(r);
			}
		}
		return records;
	}


}
