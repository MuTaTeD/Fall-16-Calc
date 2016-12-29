package pk.edu.ucp.fall16_mad_c;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class AudioActivity extends AppCompatActivity
{
	private Button btnFwd, btnPaue, btnPlay, btnRwd;
	private MediaPlayer mediaPlayer;
	private double startTime = 0;
	private double finalTime = 0;
	private Handler myHandler = new Handler();;
	private int forwardTime = 5000;
	private int backwardTime = 5000;
	private SeekBar seekbar;
	private TextView txtSongName, txtCurrDuration, txtTotalDuration;

	public static int oneTimeOnly = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_audio);

		btnPaue = (Button) findViewById(R.id.btnPause);
		btnPlay = (Button) findViewById(R.id.btnPlay);


		txtSongName=(TextView)findViewById(R.id.txtSongName);
		txtCurrDuration=(TextView)findViewById(R.id.txtCurrDuration);
		txtTotalDuration=(TextView)findViewById(R.id.txtTotalDuration);

		mediaPlayer = MediaPlayer.create(this, R.raw.song);
		seekbar=(SeekBar)findViewById(R.id.seekBar);

		seekbar.setClickable(true);
		btnPaue.setEnabled(false);

		seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				if(!fromUser)
				{
					return;
				}
				mediaPlayer.seekTo(progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{

			}
		});
	}

	public void onPlayButtonAction(View view)
	{
		mediaPlayer.start();
		txtSongName.setText("Song.mp3");
		MediaPlayer.TrackInfo[] trackInfo = mediaPlayer.getTrackInfo();
		finalTime = mediaPlayer.getDuration();
		startTime = mediaPlayer.getCurrentPosition();

		if (oneTimeOnly == 0) {
			seekbar.setMax((int) finalTime);
			oneTimeOnly = 1;
		}
		txtTotalDuration.setText(String.format("%d min, %d sec",
				TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
				TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
						TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime)))
		);

		txtCurrDuration.setText(String.format("%d min, %d sec",
				TimeUnit.MILLISECONDS.toMinutes((long) startTime),
				TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
						TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime)))
		);

		seekbar.setProgress((int)startTime);
		myHandler.postDelayed(UpdateSongTime,100);
		btnPaue.setEnabled(true);
		btnPlay.setEnabled(false);
	}

	public void onPauseButtonAction(View view)
	{
		mediaPlayer.pause();
		btnPaue.setEnabled(false);
		btnPlay.setEnabled(true);
	}

	public void onFwdButtonAction(View view)
	{
		int temp = (int)startTime;

		if((temp + forwardTime) <= finalTime)
		{
			startTime = startTime + forwardTime;
			mediaPlayer.seekTo((int) startTime);
		}
		else
		{
		}
	}

	public void onRwdButtonAction(View view)
	{
		int temp = (int)startTime;

		if((temp - backwardTime) >= 0)
		{
			startTime = startTime - backwardTime;
			mediaPlayer.seekTo((int) startTime);
		}
		else
		{
		}
	}

	private Runnable UpdateSongTime = new Runnable() {
		public void run() {
			startTime = mediaPlayer.getCurrentPosition();
			txtCurrDuration.setText(String.format("%d min, %d sec",

					TimeUnit.MILLISECONDS.toMinutes((long) startTime),
					TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
							TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
									toMinutes((long) startTime)))
			);
			seekbar.setProgress((int)startTime);
			myHandler.postDelayed(this, 100);
		}
	};

}
