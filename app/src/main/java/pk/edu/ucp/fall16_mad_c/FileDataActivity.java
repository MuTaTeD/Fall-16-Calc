package pk.edu.ucp.fall16_mad_c;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.InputStream;

public class FileDataActivity extends AppCompatActivity
{
	TextView lblFileData;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_data);

		lblFileData = (TextView) findViewById(R.id.lblFileData);
	}

	public void loadButtonAction(View v)
	{
		lblFileData.setText(loadDataFromFile("mydata.txt"));
	}

	private String loadDataFromFile(String filename)
	{
		String fileData = "";
		AssetManager asstManager = getAssets();
		try
		{
			InputStream input = asstManager.open(filename);

			int size = input.available();

			byte[] buffer = new byte[size];
			input.read(buffer);
			input.close();

			fileData = new String(buffer);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return fileData;
	}
}
