package pk.edu.ucp.fall16_mad_c;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.File;

import cz.msebera.android.httpclient.Header;
import pk.edu.ucp.fall16_mad_c.MapAPI.MapResponse;

public class ComplexNetworkActivity extends AppCompatActivity
{
	private final String _baseURL = "http://maps.googleapis.com/maps/api/geocode/";
	ImageView fileImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complex_network);

		fileImageView = (ImageView) findViewById(R.id.fileImageView);
	}

	public void theButtonAction(View v)
	{
		String endpoint = "json";
		String url = _baseURL + endpoint;

		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("address", "UCP, Lahore");
		params.put("sensor", "false");

		client.get(url, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response)
			{
				// Root JSON in response is an dictionary i.e { "data : [ ... ] }
				// Handle resulting parsed JSON response here
				Log.d("Complex Network", response.toString());
				Gson gson = new GsonBuilder().create();
//				// Define Response class to correspond to the JSON response returned
				MapResponse theResponse = gson.fromJson(response.toString(), MapResponse.class);
				Log.d("Complex Network", theResponse.toString());
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String res, Throwable t)
			{
				Log.d("Complex Network", "Request Failed: " + statusCode + "\n" + res);
				// called when response HTTP status is "4XX" (eg. 401, 403, 404)
			}
		});
	}

	public void downloadButtonAction(View v)
	{
		fileImageView.setImageDrawable(null);

		File baseDir = getApplicationContext().getExternalFilesDir(null);
		baseDir.mkdirs();
		String fileName = "logo.png";

		File output = new File(baseDir, fileName);
		if(output.exists())
		{
			output.delete();
		}


		String url = "https://www.google.com.pk/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";


		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();

		client.get(url, params, new FileAsyncHttpResponseHandler(output)
		{
			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, File tempFile)
			{
				Log.d("Complex Network", "Request Failed: " + statusCode);
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, File tempFile)
			{
				Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
				fileImageView.setImageBitmap(myBitmap);
			}
		});
	}
}
