package pk.edu.ucp.fall16_mad_c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalcInSteps2Activity extends AppCompatActivity
{
	double d1;
	double d2;
	double result = 0.0;

	TextView t1;
	TextView t2;
	TextView op;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calc_in_steps2);
		t1 = (TextView) findViewById(R.id.lblOp1);
		t2 = (TextView) findViewById(R.id.lblOp2);
		op = (TextView) findViewById(R.id.lblOperation);

		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			String d1Str = extras.getString("op1");
			t1.setText(d1Str);
			d1 = Double.parseDouble(d1Str);

			String d2Str = extras.getString("op2");
			t2.setText(d2Str);
			d2 = Double.parseDouble(d2Str);
		}
	}

	public void onButtonAction(View v)
	{
		switch(v.getId())
		{
			case R.id.btn1:
				op.setText("+");
				result = d1 + d2;
				break;
			case R.id.btnOp2:
				op.setText("-");
				result = d1 - d2;
				break;
			case R.id.btn3:
				op.setText("*");
				result = d1 * d2;
				break;
			case R.id.btn4:
				op.setText("/");
				result = d1 / d2;
				break;
			default:
				break;
		}
	}

	public void onDoneAction(View v)
	{
		Intent output = new Intent();
		output.putExtra("MyResult", result);
		output.putExtra("TheOperator", op.getText().toString());
		setResult(RESULT_OK, output);
		finish();
	}
}
