package grupo9.arquisoft.migrainetrackingmobile;

import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.IOException;

public class GrabarEpisodioActivity extends ActionBarActivity {

    private MediaRecorder recorder;
    private long cedUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grabar_episodio);
        SharedPreferences prefs=getSharedPreferences(MainActivity.TAG,MODE_PRIVATE);
        cedUsuario=prefs.getLong("CEDULA",0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grabar_episodio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void revisarPath()
    {
        try {
            File directorio = Environment.getExternalStorageDirectory();
            File api = new File(directorio.getPath() + "MigraineTracking/");
            if (!api.exists()) {
                api.mkdir();
            }
            String estado = Environment.getExternalStorageState();
            if (estado.equals(Environment.MEDIA_MOUNTED) && api.isDirectory()) {
                String[] dirLista = api.list();
                File nuevo = new File(api.getPath() + cedUsuario + "/" + (dirLista.length + 1) + ".rec");
                startRecording(nuevo);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void startRecording(File file)
    {
        if (recorder != null) {
            recorder.release();
        }
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
        recorder.setOutputFile(file.getAbsolutePath());
        try
        {
            recorder.prepare();
            recorder.start();
        }
        catch (IOException e)
        {
            System.out.println("io problems while preparing [" + file.getAbsolutePath() + "]: " + e.getMessage());
        }
    }
}
