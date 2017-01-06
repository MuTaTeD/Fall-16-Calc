package pk.edu.ucp.fall16_mad_c;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SurfaceClassActivity extends AppCompatActivity
{

	ClassSurfaceView theSurfaceView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_surface_class);

		theSurfaceView = (ClassSurfaceView)findViewById(R.id.theSurfaceView);

	}

	@Override
	protected void onPause()
	{
		super.onPause();
		theSurfaceView.pause();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		theSurfaceView.resume();
	}



}
