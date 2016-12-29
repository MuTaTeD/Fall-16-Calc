package pk.edu.ucp.fall16_mad_c;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class DatabaseActivity extends AppCompatActivity
{
	private final String TAG = "SQLiteActivity";
	private SQLiteDatabase localDB;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}

	public void onButtonPress(View view)
	{
		SQLiteDatabase theDB = DatabaseHelper.getInstance(this.getApplicationContext()).getWritableDatabase();

		String theSQL = "Select ROWID AS _id, title, value, Awais" +
				" from constants where value >= " +
				SensorManager.STANDARD_GRAVITY;

		Cursor results = theDB.rawQuery(theSQL, null);
		while(results.moveToNext())
		{
			int recID = results.getInt(0);
			String title = results.getString(1);
			Double value = results.getDouble(2);
			String Awais = results.getString(3);

			Log.d(TAG, "ID: " + recID + " Title: " + title + " Value: " + value + " Awais: " + Awais);
		}
		results.close();

	}
}
