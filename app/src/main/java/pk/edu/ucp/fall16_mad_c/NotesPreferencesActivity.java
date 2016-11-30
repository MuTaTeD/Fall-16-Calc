package pk.edu.ucp.fall16_mad_c;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class NotesPreferencesActivity extends PreferenceActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
}
