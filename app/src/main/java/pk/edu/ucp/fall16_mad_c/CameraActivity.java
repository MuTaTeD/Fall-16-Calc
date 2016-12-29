package pk.edu.ucp.fall16_mad_c;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class CameraActivity extends AppCompatActivity
{
	private ImageView cameraImageView;

	private static final String EXTRA_FILENAME = "pk.edu.ucp.fall16_mad_c.callcam.EXTRA_FILENAME";
	private static final String PIC_FILENAME = "CameraDemo.jpeg";
	private static final String VID_FILENAME = "VideoDemo.mp4";
	private static final int PIC_REQUEST = 1337;
	private static final int VID_REQUEST = 1338;
	private File output = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		cameraImageView = (ImageView) findViewById(R.id.cameraImageView);
	}

	public void onOpenCamera (View view)
	{
		File dir = Environment.
				getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

		dir.mkdirs();
		output = new File(dir, PIC_FILENAME);
		Log.d("Awais", output.getAbsolutePath());
		if (output.exists())
		{
			output.delete();
		}

		Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));

		startActivityForResult(i, PIC_REQUEST);
	}

	public void onOpenVideo (View view)
	{
		File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);

		dir.mkdirs();
		output = new File(dir, VID_FILENAME);

		if (output.exists())
		{
			output.delete();
		}
		Log.d("Awais", output.getAbsolutePath());
		Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
		i.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

		startActivityForResult(i, VID_REQUEST);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode == PIC_REQUEST && resultCode == RESULT_OK)
		{
			if(output.exists())
			{
				Bitmap myBitmap = BitmapFactory.decodeFile(output.getAbsolutePath());
				Log.d("CallCamera", output.getAbsolutePath());

				cameraImageView.setImageBitmap(myBitmap);
			}
		}
		else if(requestCode == VID_REQUEST && resultCode == RESULT_OK)
		{
			Log.d("CallCamera", output.getAbsolutePath());
			Intent view = new Intent(Intent.ACTION_VIEW).setDataAndType(Uri.fromFile(output), "video/mp4");
			startActivity(view);
		}

	}
}
