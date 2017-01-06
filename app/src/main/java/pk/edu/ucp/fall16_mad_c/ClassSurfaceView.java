package pk.edu.ucp.fall16_mad_c;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by awais on 22/08/2016.
 */
public class ClassSurfaceView extends SurfaceView implements Runnable
{
	Thread t;
	SurfaceHolder holder;
	Boolean isPaused;

	int maxWidth;
	int maxHeight;

	Bitmap icon;
	int x;
	int y;

	int xInc;
	int yInc;

	public ClassSurfaceView(Context context)
	{
		super(context);
		init();
	}

	public ClassSurfaceView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}
	public ClassSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init()
	{
		holder = getHolder();

		x = 0;
		y = 0;

		Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.arrow);
		icon = Bitmap.createScaledBitmap(b, 120, 120, false);

		xInc = 5;
		yInc = 5;
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

			maxWidth = this.getWidth();
			maxHeight = this.getHeight();

			Canvas c = holder.lockCanvas();
			c.drawARGB(255, 150, 150, 10);
			c.drawBitmap(icon, x - (icon.getWidth() / 2), y - (icon.getHeight() / 2), null);
			holder.unlockCanvasAndPost(c);

			x += xInc;
			y += yInc;

			if(x >= maxWidth || x <= 0)
			{
				xInc *= -1;
			}
			if(y >= maxHeight || y <= 0)
			{
				yInc *= -1;
			}
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