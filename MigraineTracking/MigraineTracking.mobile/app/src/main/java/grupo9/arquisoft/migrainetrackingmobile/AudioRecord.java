package grupo9.arquisoft.migrainetrackingmobile;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

/**
 * Created by Santiago on 4/29/2015.
 */
public class AudioRecord extends Activity {

    private static final String LOG_TAG = "AudioRecord";
    private static String mFileName = null;

    private RecordButton mRecordButton = null;
    private MediaRecorder mRecorder = null;

    private playButton mPlayButton = null;
    private MediaPlayer mPlayer = null;

    private void onRecord(boolean start)
    {
        if(start)
        {
            startRecording();
        }
        else
        {
            stopRecording();
        }
    }

    private void onPlay(boolean start)
    {
        if(start)
        {
            startPlaying();
        }
        else
        {
            stopPlaying();
        }
    }

    private void startPlaying()
    {
        mPlayer = new MediaPlayer();
        try
        {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        }
        catch(IOException e)
        {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying()
    {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording()
    {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try
        {
            mRecorder.prepare();
        }
        catch(IOException e)
        {
            Log.e(LOG_TAG,"prepare() failed");
        }
        mRecorder.start();
    }

    private void stopRecording()
    {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    class RecordButton extends Button
    {
        boolean mStartRecording = true;

        OnClickListener clicker = new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onRecord(mStartRecording);
                if(mStartRecording)
                {
                    setText("Stop Recording");
                }
                else
                {
                    setText("Start Recording");
                }
                mStartRecording = !mStartRecording;
            }
        };

        public RecordButton(Context ctx)
        {
            super(ctx);
            setText("Start recording");
            setOnClickListener(clicker);
        }
    }

    class playButton extends Button
    {
        boolean startPlaying = true;

        OnClickListener click = new OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlay(startPlaying);
                if(startPlaying)
                {
                    setText("Stop Playing");
                }
                else
                {
                    setText("Start Playing");
                }
                startPlaying = !startPlaying;
            }
        };

        public playButton(Context ctx)
        {
            super(ctx);
            setText("Start Playing");
            setOnClickListener(click);
        }
    }

    public AudioRecord()
    {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "audiorecord.3gb";
    }
}
