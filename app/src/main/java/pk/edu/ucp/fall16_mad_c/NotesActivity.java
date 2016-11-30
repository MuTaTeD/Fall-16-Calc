package pk.edu.ucp.fall16_mad_c;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class NotesActivity extends AppCompatActivity
{
	EditText txtNotes;
	final int MENU_ITEM = 0;
	final int SETTING_INFO = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes);

		txtNotes = (EditText) findViewById(R.id.txtNotesData);

		if(savedInstanceState != null)
		{
			String note = savedInstanceState.getString("NOTE");
			txtNotes.setText(note);
		}

		loadPreferences();
		updateNotesText();
	}

	@Override
	public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)
	{
		outState.putString("NOTE", txtNotes.getText().toString());
		super.onSaveInstanceState(outState, outPersistentState);
	}

	private void savePreferences()
	{
		SharedPreferences sharedPrefs = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor spEditor = sharedPrefs.edit();
		spEditor.putString("NOTE", txtNotes.getText().toString());
		spEditor.apply();
	}

	private void loadPreferences()
	{
		SharedPreferences sharedPrefs = getPreferences(MODE_PRIVATE);
		String spNote = sharedPrefs.getString("NOTE", "EMPTY");
		if(!spNote.equals("EMPTY"))
		{
			txtNotes.setText(spNote);
		}
	}

	@Override
	protected void onStop()
	{
		savePreferences();
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add(0, MENU_ITEM, Menu.NONE, "Settings");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if(item.getItemId() == MENU_ITEM)
		{
			Intent myIntent = new Intent(this, NotesPreferencesActivity.class);
			startActivityForResult(myIntent, SETTING_INFO);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode == SETTING_INFO)
		{
			updateNotesText();

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void updateNotesText()
	{
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		Boolean val = prefs.getBoolean("prefs_bold_check", false);
		if(val)
		{
			txtNotes.setTypeface(null, Typeface.BOLD);
		}
		else
		{
			txtNotes.setTypeface(null, Typeface.NORMAL);
		}

		String sizeStr = prefs.getString("prefs_txt_size", "16");
		Float txtSize = Float.parseFloat(sizeStr);
		txtNotes.setTextSize(txtSize);
	}
}