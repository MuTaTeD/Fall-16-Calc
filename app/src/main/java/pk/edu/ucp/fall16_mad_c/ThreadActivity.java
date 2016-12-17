package pk.edu.ucp.fall16_mad_c;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

public class ThreadActivity extends AppCompatActivity
{
	ProgressBar theProgressBar;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thread);

		theProgressBar = (ProgressBar) findViewById(R.id.theProgressBar);
	}
	public void onResetButtonAction(View view)
	{
		theProgressBar.setProgress(0);
	}

	public void onStartNoThreadButtonAction(View view)
	{
		for(int i=0;i<=10; i++)
		{
			try
			{
				Thread.sleep(1000);
			} catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			theProgressBar.setProgress(i);
		}
	}

	public void onStartButtonAction(View view)
	{
		ProgressTask pt = new ProgressTask();
		Thread t = new Thread(pt);
		t.start();
	}

	class ProgressTask implements Runnable
	{
		public void run()
		{
			for(int i=0;i<=10; i++)
			{
				try
				{
					Thread.sleep(1000);
				} catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				final int progress = i;
//				runOnUiThread(new Runnable()
//				{
//					@Override
//					public void run()
//					{
//						theProgressBar.setProgress(progress);
//					}
//				});
				UIObject uio = new UIObject();
				uio.prog = progress;
				runOnUiThread(uio);

			}
		}
	}
	class UIObject implements Runnable
	{
		public int prog;
		public void run()
		{
			theProgressBar.setProgress(prog);
		}
	}
}
