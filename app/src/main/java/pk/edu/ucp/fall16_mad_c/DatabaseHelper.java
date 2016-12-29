package pk.edu.ucp.fall16_mad_c;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.SensorManager;
import android.util.Log;

import java.io.File;

/**
 * Created by awais on 25/08/2016.
 */


public class DatabaseHelper extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "constants.db";
	private static final int SCHEMA = 1;
	static final String TITLE = "title";
	static final String VALUE = "value";
	static final String TABLE = "constants";

	private static DatabaseHelper _instance =null;

	public synchronized static DatabaseHelper getInstance(Context context)
	{
		if (_instance == null)
		{
			_instance = new DatabaseHelper(context.getApplicationContext());
		}
		return(_instance);
	}

	private DatabaseHelper(Context context)
	{
//		super(context, DATABASE_NAME, null, SCHEMA);

		super(context,
				context.getExternalFilesDir(null).getAbsolutePath() + File.separator + DATABASE_NAME,
				null,
				SCHEMA);
//
		Log.d("SQLiteHelper", "Path: " + this.getReadableDatabase().getPath());
	}


	@Override
	public void onCreate(SQLiteDatabase db)
	{
		Log.d("Awais", "Creating DB and Inserting values");
		db.execSQL("CREATE TABLE constants (title TEXT, value REAL, Awais TEXT);");

		ContentValues cv=new ContentValues();

		cv.put(TITLE, "Gravity, Death Star I");
		cv.put(VALUE, SensorManager.GRAVITY_DEATH_STAR_I);
		cv.put("Awais", "ABC");
		db.insert(TABLE, TITLE, cv);

//		String stmt ="Insert into " + TABLE + " " +
//				"("+TITLE +", "+ VALUE +", Awais) " +
//				"values ('Gravity, Death Star I', " +  SensorManager.GRAVITY_DEATH_STAR_I
//				+ "'ABC');";
//		db.execSQL(stmt);

		cv.put(TITLE, "Gravity, Earth");
		cv.put(VALUE, SensorManager.GRAVITY_EARTH);
		cv.put("Awais", "DEF");
		db.insert(TABLE, TITLE, cv);

		cv.put(TITLE, "Gravity, Jupiter");
		cv.put(VALUE, SensorManager.GRAVITY_JUPITER);
		cv.put("Awais", "GHI");
		db.insert(TABLE, TITLE, cv);

		cv.put(TITLE, "Gravity, Mars");
		cv.put(VALUE, SensorManager.GRAVITY_MARS);
		cv.put("Awais", "JKL");
		db.insert(TABLE, TITLE, cv);

		cv.put(TITLE, "Gravity, Mercury");
		cv.put(VALUE, SensorManager.GRAVITY_MERCURY);
		cv.put("Awais", "MNO");
		db.insert(TABLE, TITLE, cv);

		cv.put(TITLE, "Gravity, Moon");
		cv.put(VALUE, SensorManager.GRAVITY_MOON);
		cv.put("Awais", "PQR");
		db.insert(TABLE, TITLE, cv);

		cv.put(TITLE, "Gravity, Neptune");
		cv.put(VALUE, SensorManager.GRAVITY_NEPTUNE);
		cv.put("Awais", "STU");
		db.insert(TABLE, TITLE, cv);

		cv.put(TITLE, "Gravity, Pluto");
		cv.put(VALUE, SensorManager.GRAVITY_PLUTO);
		cv.put("Awais", "VWX");
		db.insert(TABLE, TITLE, cv);

		cv.put(TITLE, "Gravity, Saturn");
		cv.put(VALUE, SensorManager.GRAVITY_SATURN);
		cv.put("Awais", "YZ_");
		db.insert(TABLE, TITLE, cv);

		cv.put(TITLE, "Gravity, Sun");
		cv.put(VALUE, SensorManager.GRAVITY_SUN);
		cv.put("Awais", "123");
		db.insert(TABLE, TITLE, cv);

		cv.put(TITLE, "Gravity, The Island");
		cv.put(VALUE, SensorManager.GRAVITY_THE_ISLAND);
		cv.put("Awais", "456");
		db.insert(TABLE, TITLE, cv);

		cv.put(TITLE, "Gravity, Uranus");
		cv.put(VALUE, SensorManager.GRAVITY_URANUS);
		cv.put("Awais", "789");
		db.insert(TABLE, TITLE, cv);

		cv.put(TITLE, "Gravity, Venus");
		cv.put(VALUE, SensorManager.GRAVITY_VENUS);
		cv.put("Awais", "0-a");
		db.insert(TABLE, TITLE, cv);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

	}
}