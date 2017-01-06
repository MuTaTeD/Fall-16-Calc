package pk.edu.ucp.fall16_mad_c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class DBAssignment extends AppCompatActivity
{
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dbassignment);
		tv = (TextView) findViewById(R.id.dataTextView);
	}

	public void onTheButtonClick(View v)
	{
		DBAssignmentHelper dba = DBAssignmentHelper.getInstance(this);
		ArrayList<String> tables = dba.getTables();
		StringBuilder sb = new StringBuilder();

		for(String tbl : tables)
		{
			sb.append(tbl + "\n");
		}

		tv.setText(sb.toString());

	}
}
