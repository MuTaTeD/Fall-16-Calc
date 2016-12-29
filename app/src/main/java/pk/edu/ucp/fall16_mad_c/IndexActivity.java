package pk.edu.ucp.fall16_mad_c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class IndexActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
	}

	int MENU_ITEM = 1;
	int MENU_EXIT = 10;
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add(Menu.NONE, 0, Menu.NONE, "Awais");
		menu.add(Menu.NONE, 1, Menu.NONE, "Lodhi");
		menu.add(Menu.NONE, MENU_EXIT, Menu.NONE, "Exit");

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int val = item.getItemId();
		if(val == MENU_EXIT)
		{
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	public void handleButtonAction(View v)
	{
		Intent theIntent = null;
		switch(v.getId())
		{
			case R.id.btnOpenCalc:
				theIntent = new Intent(this, MainActivity.class);
				break;
			case R.id.btn2StepCalc:
				theIntent = new Intent(this, CalcInStepActivity.class);
				break;
			case R.id.btnTweenAnimation:
				theIntent = new Intent(this, TweenActivity.class);
				break;
			case R.id.btnFrameAnimation:
				theIntent = new Intent(this, FrameAnimationActivity.class);
				break;
			case R.id.btnFileDataActivity:
				theIntent = new Intent(this, FileDataActivity.class);
				break;
			case R.id.btnFileRecordActivity:
				theIntent = new Intent(this, FileRecordsActivity.class);
				break;
			case R.id.btnSimpleListView:
				theIntent = new Intent(this, SimpleListActivity.class);
				break;
			case R.id.btnComplexListView:
				theIntent = new Intent(this, ComplexListActivity.class);
				break;
			case R.id.btnAdvComplexListView:
				theIntent = new Intent(this, AdvComplexListActivity.class);
				break;
			case R.id.btnNotesApp:
				theIntent = new Intent(this, NotesActivity.class);
				break;
			case R.id.btnThreadsActivity:
				theIntent = new Intent(this, ThreadActivity.class);
				break;
			case R.id.btnASyncTaskActivity:
				theIntent = new Intent(this, ASyncTaskActivity.class);
				break;
			case R.id.btnDatabaseActivity:
				theIntent = new Intent(this, DatabaseActivity.class);
				break;
			case R.id.btnCameraActivity:
				theIntent = new Intent(this, CameraActivity.class);
				break;
			case R.id.btnAudioActivity:
				theIntent = new Intent(this, AudioActivity.class);
				break;
			case R.id.btnVideoActivity:
				theIntent = new Intent(this, VideoActivity.class);
				break;
			default:
				break;
		}
		if(theIntent != null)
		{
			startActivity(theIntent);
		}
	}
}
