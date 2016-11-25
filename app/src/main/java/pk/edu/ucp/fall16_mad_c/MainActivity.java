package pk.edu.ucp.fall16_mad_c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
	EditText txtOperend1;
	EditText txtOperend2;
	TextView lblResult;

	String TAG = "Calc Activity";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Log.d(TAG, "Inside onCreate: ");
		setContentView(R.layout.activity_main);

		txtOperend1 = (EditText) findViewById(R.id.txtOp1);
		txtOperend2 = (EditText) findViewById(R.id.txtOp2);
		lblResult = (TextView) findViewById(R.id.lblResult);
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

	public void doCalculation(View v)
	{
		try
		{
			double d1 = Double.parseDouble(txtOperend1.getText().toString());
			double d2 = Double.parseDouble(txtOperend2.getText().toString());

			double result = 0.0;

			switch(v.getId())
			{
				case R.id.btnOp1:
					result = d1 + d2;
					break;
				case R.id.btnOp2:
					result = d1 - d2;
					break;
				case R.id.btn3:
					result = d1 * d2;
					break;
				case R.id.btn4:
					result = d1 / d2;
					break;
				default:
					break;
			}
			lblResult.setText(Double.toString(result));
		}
		catch(NumberFormatException ex)
		{
			ex.printStackTrace();
		}
	}

	public void clearValues(View v)
	{
		txtOperend1.setText("");
		txtOperend2.setText("");
		lblResult.setText("0.0");
	}

	public void openViewButtonAction(View v)
	{
		Intent openViewIntent = new Intent(this, My2ndActivity.class);
		openViewIntent.putExtra("MyData", "Awais is Teaching MAD C");
		openViewIntent.putExtra("ResValue", lblResult.getText());
		startActivity(openViewIntent);
	}

}
