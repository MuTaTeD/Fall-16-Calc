package pk.edu.ucp.fall16_mad_c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class My2ndActivity extends AppCompatActivity
{
	TextView resultLabel;
	TextView lblMsg;

	String TAG = "My 2nd Activity";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my2nd);
		Log.d(TAG, "Inside onCreate: ");

		resultLabel = (TextView) findViewById(R.id.resultLabel);
		lblMsg = (TextView) findViewById(R.id.labelMsg);

		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			String msg = extras.getString("ResValue");
			resultLabel.setText(msg);

			String msg2 = extras.getString("MyData");
			lblMsg.setText(msg2);
		}
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		Log.d(TAG, "Inside onStart: ");
	}

	@Override
	protected void onRestart()
	{
		super.onRestart();
		Log.d(TAG, "Inside onRestart: ");
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		Log.d(TAG, "Inside onResume: ");
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		Log.d(TAG, "Inside onPause: ");
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		Log.d(TAG, "Inside onStop: ");
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.d(TAG, "Inside onDestroy: ");
	}

	public void onCloseButtonAction(View v)
	{
		finish();
	}
}
