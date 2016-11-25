package pk.edu.ucp.fall16_mad_c;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;

public class SimpleListActivity extends AppCompatActivity
{
	ListView lstListView;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_list);

		lstListView = (ListView) findViewById(R.id.simpleListView);

		String completeData = loadDataFromFile("mydata.txt");
		ArrayList<String> records = parseRecords(completeData);

		ArrayList<String> items = new ArrayList<String>();
		for(int i= 0; i<records.size();i+=4)
		{
			items.add(records.get(i));
		}

		ArrayAdapter adaptor = new ArrayAdapter(this,
										R.layout.list_item_simple,
										items);
		lstListView.setAdapter(adaptor);
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

	private ArrayList<String> parseRecords(String data)
	{
		ArrayList<String> records = new ArrayList<String>();
		records = new ArrayList<String>();
		String parts[] = data.split("\n");

		for(String line : parts)
		{
			if(line.length() == 0)
			{

			}
			else
			{
				records.add(line);
			}
		}
		return records;
	}
}
