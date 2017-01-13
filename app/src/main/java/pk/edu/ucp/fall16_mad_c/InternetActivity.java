package pk.edu.ucp.fall16_mad_c;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class InternetActivity extends AppCompatActivity
{
	private final String _baseURL = "http://172.20.141.106/android-web/";
	private EditText txtName;
	private EditText txtAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internet);
		txtName = (EditText) findViewById(R.id.name);
		txtAddress = (EditText) findViewById(R.id.address);
	}

	public void onGetDataButtonPress(View v)
	{
		String endPoint = "utility.php?task=getAllAddresses";
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();

		String finalEntryPoint = _baseURL + endPoint;
		client.get(finalEntryPoint, params, new TextHttpResponseHandler()
			{
				@Override
				public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable)
				{
					// called when response HTTP status is "4XX" (eg. 401, 403, 404)
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers, String responseString)
				{
					// called when response HTTP status is "200 OK"
					Log.d("Network Activity", responseString);

					try
					{
						JSONObject jsonRootObject = new JSONObject(responseString);
						int responseCode = jsonRootObject.getInt("code");
						String status = jsonRootObject.getString("status");
						JSONArray results = jsonRootObject.getJSONArray("results");

						for(int i=0; i < results.length(); i++)
						{
							JSONObject person = results.getJSONObject(i);
							int pID = Integer.parseInt(person.get("id").toString());
							String pName = person.getString("name");
							String pAddress = person.getString("address");
							double pAge = person.getDouble("age");

							Log.d("Network Activity", "Person Found\nID: " + pID + "\nName: " + pName + "\nAddress: " + pAddress + "\nAge: " + pAge);
						}
					}
					catch(JSONException e)
					{
						e.printStackTrace();
					}
				}
			}
		);
	}

	public void onPostButtonPress(View v)
	{
		final Button b = (Button) v;
		b.setEnabled(false);

		ArrayList<Pair<String, String>> parameters = new ArrayList<Pair<String, String>>();
		parameters.add(new Pair<String, String>("task", "addAddress"));
		parameters.add(new Pair<String, String>("name", txtName.getText().toString()));
		parameters.add(new Pair<String, String>("address", txtAddress.getText().toString()));

		String endPoint = "utility.php";
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();

		for(Pair<String, String> param : parameters)
		{
			params.put(param.first, param.second);
		}

		String finalEntryPoint = _baseURL + endPoint;
		client.post(finalEntryPoint, params, new TextHttpResponseHandler()
				{
					@Override
					public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable)
					{
						// called when response HTTP status is "4XX" (eg. 401, 403, 404)
						Log.d("Network Activity", responseString);
						b.setEnabled(true);
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers, String responseString)
					{
						// called when response HTTP status is "200 OK"
						Log.d("Network Activity", responseString);
						b.setEnabled(true);
					}
				}
		);
	}
}
