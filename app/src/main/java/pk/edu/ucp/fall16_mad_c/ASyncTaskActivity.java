package pk.edu.ucp.fall16_mad_c;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ASyncTaskActivity extends AppCompatActivity
{
	ProgressBar asyncProgressbar;
	TextView statusTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async_task);

		asyncProgressbar = (ProgressBar) findViewById(R.id.aSyncProgressBar);
		statusTextView = (TextView) findViewById(R.id.statusTextView);
	}

	public void onStartAsyncButton(View view)
	{
		UCPASyncTask myAsycnTaskObject = new UCPASyncTask();
//		String sleepTime = Integer.toString(1000);
//		myAsycnTaskObject.execute(sleepTime);
		myAsycnTaskObject.execute(1000);
	}

	class UCPASyncTask extends AsyncTask<Integer, Integer, Float>
	{
		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			statusTextView.setText("Starting The Work");
		}

		@Override
		protected Float doInBackground(Integer... intArray)
		{
			int timeStep = intArray[0];
			for(int i=0;i<=10; i++)
			{
				try
				{
					Thread.sleep(timeStep);
				} catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				publishProgress(i);
			}
			return 3.2f;
		}

		@Override
		protected void onProgressUpdate(Integer... intArray)
		{
//			int i = Integer.parseInt(values[0]);
			int i = intArray[0];
			asyncProgressbar.setProgress(i);
			statusTextView.setText("Updated: " + i);
		}

		@Override
		protected void onPostExecute(Float v)
		{
			super.onPostExecute(v);
			statusTextView.setText("Work has Finished: " + v);
		}
	}
}


