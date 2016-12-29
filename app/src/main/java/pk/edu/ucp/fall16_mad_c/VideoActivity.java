package pk.edu.ucp.fall16_mad_c;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

public class VideoActivity extends AppCompatActivity
{
	private Button btnFwd, btnPause, btnPlay, btnRwd;
	private MediaPlayer mediaPlayer;
	private double startTime = 0;
	private double finalTime = 0;
	private Handler myHandler = new Handler();
	private int forwardTime = 5000;
	private int backwardTime = 5000;
	private SeekBar seekbar;
	private TextView txtSongName, txtCurrDuration, txtTotalDuration;
	private VideoView videoView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);

		videoView = (VideoView) findViewById(R.id.videoView);
		btnRwd = (Button) findViewById(R.id.btnRwd_1);
		btnPause = (Button) findViewById(R.id.btnPause_1);
		btnPlay = (Button) findViewById(R.id.btnPlay_1);
		btnFwd = (Button) findViewById(R.id.btnFwd_1);

		txtCurrDuration = (TextView) findViewById(R.id.txtCurrDuration_1);
		txtTotalDuration = (TextView) findViewById(R.id.txtTotalDuration_1);
		txtSongName = (TextView) findViewById(R.id.txtSongName_1);

		seekbar = (SeekBar) findViewById(R.id.seekBar_1);

//		mediaPlayer = MediaPlayer.create(this,R.raw.video);

		File dir = Environment.
						getExternalStoragePublicDirectory
								(Environment.DIRECTORY_MOVIES);
		File output = new File(dir, "VideoDemo.mp4");

		mediaPlayer = MediaPlayer.create(this, Uri.fromFile(output));
		btnPause.setEnabled(false);
		btnPlay.setEnabled(true);
	}

	public void onPlayButtonAction(View view)
	{
		mediaPlayer.setDisplay(videoView.getHolder());
		mediaPlayer.start();

		btnPlay.setEnabled(false);
		btnPause.setEnabled(true);
	}

	public void onPauseButtonAction(View view)
	{
		mediaPlayer.pause();
		btnPlay.setEnabled(true);
		btnPause.setEnabled(false);
	}
}
