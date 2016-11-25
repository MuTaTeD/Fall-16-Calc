package pk.edu.ucp.fall16_mad_c;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FrameAnimationActivity extends AppCompatActivity
{
	boolean isPlaying;
	ImageView frameImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frame_animation);

		isPlaying = false;
		frameImageView = (ImageView) findViewById(R.id.frameImageView);
		frameImageView.setBackgroundResource(R.drawable.frame_animation);
	}

	public void onFrameImageViewClicked(View view)
	{
		AnimationDrawable ad = (AnimationDrawable) frameImageView.getBackground();
		if (!isPlaying)
		{
			ad.start();
			isPlaying = true;
		}
		else
		{
			ad.stop();
			isPlaying = false;
		}
	}
}
