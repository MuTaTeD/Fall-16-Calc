package pk.edu.ucp.fall16_mad_c;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

public class SurfaceAnimationActivity extends AppCompatActivity implements View.OnTouchListener
{
	MyView theView;
	Bitmap icon;
	float x;
	float y;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.arrow);
		icon = Bitmap.createScaledBitmap(b, 120, 120, false);
		x = 0;
		y = 0;

		FrameLayout theLayout = new FrameLayout(this);

		theView = new MyView(this);
		theView.setOnTouchListener(this);

		theLayout.addView(theView);

		setContentView(theLayout);

	}

	@Override
	protected void onPause()
	{
		super.onPause();
		theView.pause();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		theView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		switch(event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_MOVE:
				x = event.getX();
				y = event.getY();

				break;

			default:
				break;
		}

		return true;
	}

	public class MyView extends SurfaceView implements Runnable
	{
		Thread t = null;
		SurfaceHolder holder;
		Boolean isPaused = true;
		long prevRunTime = System.currentTimeMillis();

		public MyView(Context context)
		{
			super(context);
			holder = getHolder();
		}

		@Override
		public void run()
		{
			while(isPaused == false)
			{
				if(!holder.getSurface().isValid())
				{
					continue;
				}

				Canvas c = holder.lockCanvas();
				c.drawARGB(255, 150, 150, 10);
				c.drawBitmap(icon, x - (icon.getWidth() / 2), y - (icon.getHeight() / 2), null);

				x+=3;
				y+=5;

				holder.unlockCanvasAndPost(c);

			}
		}

		public void pause()
		{
			isPaused = true;
			while(true)
			{
				try
				{
					t.join();
				} catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				break;
			}
		}

		public void resume()
		{
			isPaused = false;
			t = new Thread(this);
			t.start();
		}
	}
}
