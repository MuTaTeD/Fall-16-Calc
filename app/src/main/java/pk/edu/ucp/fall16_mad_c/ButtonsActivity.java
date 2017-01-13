package pk.edu.ucp.fall16_mad_c;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class ButtonsActivity extends AppCompatActivity
{
	LinearLayout myLinearLayout;
	int buttonCount;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buttons);

		myLinearLayout = (LinearLayout) findViewById(R.id.theLinearLayout);
		buttonCount = 0;
	}

	public void addButtonAction(View view)
	{
		Log.d("Awais", "Add New Button Clicked");

		Button btn = new Button(this);
		btn.setText("Button # " + ++buttonCount);
		btn.setId(buttonCount);
		int offset = buttonCount % 3;
		if(offset == 0)
		{
			btn.setTypeface(null, Typeface.BOLD);
		}
		else if(offset == 1)
		{
			btn.setTextColor(Color.RED);
		}
		else
		{

		}

		btn.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				int viewID = view.getId();
				Log.d("Awais-1", "Button Clicked with id: " + viewID);

				Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_animate);
				view.startAnimation(a);
				Intent i = new Intent(getApplicationContext(), InternetActivity.class);
				startActivity(i);

			}
		});
		myLinearLayout.addView(btn);
	}

	public void removeButtonAction(View view)
	{
		if(buttonCount > 0)
		{
			Button btn = (Button) findViewById(buttonCount--);
			myLinearLayout.removeView(btn);
			Log.d("Awais", "Removed Button with id: " + btn.getId());
		}
	}
}
