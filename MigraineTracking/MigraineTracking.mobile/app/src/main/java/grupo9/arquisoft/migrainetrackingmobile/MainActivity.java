package grupo9.arquisoft.migrainetrackingmobile;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import grupo9.arquisoft.migrainetrackingmobile.dtos.MedicamentoDTO;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_USUARIO = "grupo9.arquisoft.migrainetrackingmobile.USUARIO";
    public final static String TAG="grupo9.migraintracking";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        RestClient restClient= new RestClient("https://migraine-services.herokuapp.com/poblar");
        restClient.AddHeader("content-type","application/json");

        try
        {
            restClient.Execute(RestClient.RequestMethod.GET);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        TextView texto= (TextView)findViewById(R.id.texto);
        texto.setText(restClient.getResponse());
=======
       // new EjecutarUrl().execute("https://migraine-services.herokuapp.com/poblar");
>>>>>>> origin/master
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void login(View view){

        RadioButton pacientes = (RadioButton)findViewById(R.id.pacientesRadio);
        RadioButton doctores = (RadioButton) findViewById(R.id.doctoresRadio);
        if(pacientes.isChecked())
        {
            Intent intent = new Intent(this, MenuPrincipalActivity.class);
            EditText usuarioEdit = (EditText)findViewById(R.id.usuario_edit);
            String usuario = usuarioEdit.getText().toString();
            if(usuario.equals("mp.mancipe10")||usuario.equals("s.abisambra125")||usuario.equals("pa.otoya575")||usuario.equals("hf.vargas10"))
            intent.putExtra(EXTRA_USUARIO, usuario);
            startActivity(intent);
        }
        else if (doctores.isChecked())
        {
            Intent intent = new Intent(this, MenuDoctorActivity.class);
            EditText usuarioEdit = (EditText)findViewById(R.id.usuario_edit);
            String usuario = usuarioEdit.getText().toString();
            if(usuario.equals("mp.mancipe10")||usuario.equals("s.abisambra125")||usuario.equals("pa.otoya575")||usuario.equals("hf.vargas10"))
            intent.putExtra(EXTRA_USUARIO, usuario);
            startActivity(intent);
        }
        else
        {

        }
    }

}
