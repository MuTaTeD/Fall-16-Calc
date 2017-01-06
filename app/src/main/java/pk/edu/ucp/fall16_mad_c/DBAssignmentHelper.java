package pk.edu.ucp.fall16_mad_c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by awais on 02/01/2017.
 */

public class DBAssignmentHelper extends SQLiteOpenHelper
{
	private static Context context = null;
	private static final String DATABASE_NAME = "assigndb.sqlite";
	private static final int SCHEMA = 1;

	private static DBAssignmentHelper _instance =null;

	public synchronized static DBAssignmentHelper getInstance(Context _context)
	{
		if (_instance == null)
		{
			context = _context;
			copyDBIfNeeded();
			_instance = new DBAssignmentHelper(context.getApplicationContext());
		}
		return(_instance);
	}

	private DBAssignmentHelper(Context _context)
	{
//		super(context, DATABASE_NAME, null, SCHEMA);

		super(_context,
				_context.getExternalFilesDir(null).getAbsolutePath() + File.separator + DATABASE_NAME,
				null,
				SCHEMA);

		Log.d("SQLiteHelper", "Path: " + this.getReadableDatabase().getPath());
	}

	private static void copyDBIfNeeded()
	{
		String inPath = Uri.parse("android.resource:///" + context.getPackageName() + "/raw/" + "assigndb").getPath();
		String outPath = context.getExternalFilesDir(null).getAbsolutePath() + File.separator + DATABASE_NAME;

		Log.d("Awais 1: ", inPath);
		Log.d("Awais 2: ", outPath);

		File inFile = new File(inPath);
		File outFile = new File(outPath);

		if(outFile.exists())
		{
			outFile.delete();
		}
		if(!outFile.exists())
		{
			try
			{
				FileUtils.copyFile(inFile, outFile, true);
			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}


	@Override
	public void onCreate(SQLiteDatabase db)
	{
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

	}

	public ArrayList<String> getTables()
	{
		SQLiteDatabase db = _instance.getWritableDatabase();
		ArrayList<String> tblNames = new ArrayList<String>();
		Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

		if (c.moveToFirst()) {
			while ( !c.isAfterLast() ) {
				tblNames.add( c.getString( c.getColumnIndex("name")) );
				c.moveToNext();
			}
		}
		return tblNames;
	}
}
