package pk.edu.ucp.fall16_mad_c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class TweenActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tween);
	}

	public void onButtonAnimateAction(View view)
	{
		Button b = (Button) view;
		Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_animate);

		b.startAnimation(a);
	}
}
