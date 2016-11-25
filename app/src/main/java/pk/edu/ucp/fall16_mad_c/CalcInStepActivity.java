package pk.edu.ucp.fall16_mad_c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CalcInStepActivity extends AppCompatActivity
{
	EditText e1;
	EditText e2;
	TextView resText;
	TextView operText;

	private static final int CALC_REQUEST = 1234;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calc_in_step);

		e1 = (EditText) findViewById(R.id.txtOp1);
		e2 = (EditText) findViewById(R.id.txtOp2);
		resText = (TextView) findViewById(R.id.lblResult);
		operText = (TextView) findViewById(R.id.lblOperator);
	}

	public void onClearAction(View v)
	{
		e1.setText("");
		e2.setText("");
		resText.setText("0.0");
	}

	public void onChooseButtonAction(View v)
	{
		if(e1.getText().length() > 0 && e2.getText().length() > 0)
		{
			Intent calcIntent = new Intent(this, CalcInSteps2Activity.class);
			calcIntent.putExtra("op1", e1.getText().toString());
			calcIntent.putExtra("op2", e2.getText().toString());
			startActivityForResult(calcIntent, CALC_REQUEST);

		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == CALC_REQUEST && resultCode == RESULT_OK && data != null)
		{
			double val = data.getDoubleExtra("MyResult", 0.0);
			resText.setText(Double.toString(val));

			operText.setText(data.getExtras().getString("TheOperator"));
		}
	}
}
