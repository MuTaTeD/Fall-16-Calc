package pk.edu.ucp.fall16_mad_c;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class FileRecordsActivity extends AppCompatActivity
{
	TextView lblFileData;
	ArrayList<String> recordData;
	int currPos;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_records);

		lblFileData = (TextView) findViewById(R.id.lblFileData);
	}

	public void loadButtonAction(View v)
	{
		parseRecords(loadDataFromFile("mydata.txt"));
		lblFileData.setText("File Load Successful...");
	}

	public void nextButtonAction(View v)
	{
		if(recordData.size() > currPos+3)
		{
			StringBuilder strBuilder = new StringBuilder();

			String title = recordData.get(currPos++);
			String desc = recordData.get(currPos++);
			String size = recordData.get(currPos++);
			String type = recordData.get(currPos++);

			strBuilder.append("Title: \n");
			strBuilder.append(title + "\n\n");

			strBuilder.append("Description: \n");
			strBuilder.append(desc + "\n\n");

			strBuilder.append("Serving Size: \n");
			strBuilder.append(size + "\n\n");

			strBuilder.append("Food Type: \n");
			strBuilder.append(type + "\n\n");

			lblFileData.setText(strBuilder.toString());
		}
		else
		{
			lblFileData.setText("Complete File Viewed...");
			currPos = 0;
		}
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

	private void parseRecords(String data)
	{
		recordData = new ArrayList<String>();
		currPos = 0;
		String parts[] = data.split("\n");

		for(String line : parts)
		{
			if(line.length() == 0)
			{

			}
			else
			{
				recordData.add(line);
			}
		}
	}
}
