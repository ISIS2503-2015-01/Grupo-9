package grupo9.arquisoft.migrainetrackingmobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import grupo9.arquisoft.migrainetrackingmobile.dtos.PacienteDTO;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_USUARIO = "grupo9.arquisoft.migrainetrackingmobile.USUARIO";
    public final static String TAG="grupo9.migraintracking";
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new poblar().execute("https://migraine-services.herokuapp.com/poblar");


       //new EjecutarUrl().execute("https://migraine-services.herokuapp.com/poblar");

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
            Bundle bundle=new Bundle();
            EditText usuarioEdit = (EditText)findViewById(R.id.usuario_edit);
            String usuario = usuarioEdit.getText().toString();
            if(usuario.equals(""))
            {
                new AlertDialog.Builder(this).setTitle("Error de autenticación").setMessage("Ingrese un usuario válido").setNeutralButton("Cerrar", null).show();
                return;
            }
            EditText claveEdit = (EditText) findViewById(R.id.usuario_edit);
            new buscarClave().execute("https://migraine-services.herokuapp.com/pacientes/" + usuario);
            if(!claveEdit.getText().equals(password))
            {
                new AlertDialog.Builder(this).setTitle("Error de autenticacion").setMessage("El usuario y/0 clave son erradas").setNeutralButton("Cerrar",null).show();
                return;
            }
            bundle.putString("USUARIO", usuario);
            intent.putExtras(bundle);
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
    }

    public void crear(View view)
    {
        Intent intent=new Intent(this, RegistrarUsuarioActivity.class);
        startActivity(intent);
    }

    private class poblar extends AsyncTask<String, Long, String> {
        protected String doInBackground(String... urls) {
            RestClient restClient = new RestClient(urls[0]);
            restClient.AddHeader("Accept", "application/json");
            try {
                restClient.Execute(RestClient.RequestMethod.GET);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(restClient.getResponse());
            return restClient.getResponse();
        }

        protected void onPostExecute(String response) {
            TextView texto = (TextView) findViewById(R.id.texto);
            //texto.setText(response);
        }
    }

    private class buscarClave extends AsyncTask<String, Long, String>
    {
        protected String doInBackground(String... urls)
        {
            RestClient client = new RestClient(urls[0]);
            client.AddHeader("Accept", "application/json");
            try
            {
                client.Execute(RestClient.RequestMethod.GET);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            System.out.println(client.getResponse());
            return client.getResponse();
        }

        protected void onPostExecute(String response)
        {
            String clave = darClave(response);
        }
    }

    public String darClave(String json)
    {
        try
        {
            Gson gson = new Gson();
            PacienteDTO pac = gson.fromJson(json, PacienteDTO.class);
            password = pac.getPassword();
            return pac.getPassword();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            String[] atributos = json.split("\"");
            try
            {
                return atributos[17];
            }
            catch(Exception e1)
            {
                return "";
            }
        }
    }

}
