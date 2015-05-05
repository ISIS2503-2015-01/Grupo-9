package grupo9.arquisoft.migrainetrackingmobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private boolean password;
    private String jsonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void login(View view) throws InterruptedException {

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
            EditText claveEdit = (EditText) findViewById(R.id.contrasenia_edit);
            String claveapp = claveEdit.getText().toString();
            Gson gson=new Gson();
            PacienteDTO pacienteDTO=new PacienteDTO();
            pacienteDTO.setUsername(usuario);
            pacienteDTO.setPassword(claveapp);
            jsonLogin =gson.toJson(pacienteDTO);
            new obtenerToken().execute("https://migraine-services.herokuapp.com/webresources/auth/login");
            Thread.sleep(1000);
            if(!password) {
                new AlertDialog.Builder(this).setTitle("Error de autenticacion").setMessage("El usuario y/o clave son erradas").setNeutralButton("Cerrar", null).show();
                return;
            }
            else
            {
                bundle.putString("USUARIO", usuario);
                intent.putExtras(bundle);
                startActivity(intent);
            }
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

    private class obtenerToken extends AsyncTask<String, Long, String>
    {
        protected String doInBackground(String... urls)
        {
            RestClient client = new RestClient(urls[0]);
            client.AddHeader("Accept", "application/json");
            client.AddParam(jsonLogin);
            try
            {
                client.Execute(RestClient.RequestMethod.POST);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            System.out.println(client.getResponse());
            Gson gson=new Gson();
            return gson.toJson(client);
        }

        protected void onPostExecute(String response)
        {
            try {
                Gson gson = new Gson();
                RestClient client = gson.fromJson(response, RestClient.class);
                if (client != null)
                {
                    if(client.getResponseCode()!=401)
                    {
                        SharedPreferences.Editor editor = getSharedPreferences(TAG, MODE_PRIVATE).edit();
                        editor.putString("token", client.getResponse());
                        editor.commit();
                        password=true;
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
