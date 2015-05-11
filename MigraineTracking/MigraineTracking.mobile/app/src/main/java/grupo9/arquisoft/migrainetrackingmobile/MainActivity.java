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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import grupo9.arquisoft.migrainetrackingmobile.dtos.MedicamentoDTO;
import grupo9.arquisoft.migrainetrackingmobile.dtos.PacienteDTO;


public class MainActivity extends ActionBarActivity {

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

            //usuario=md5(usuario);
            //claveapp=md5(claveapp);

            pacienteDTO.setUsername(usuario);
            pacienteDTO.setPassword(claveapp);
            jsonLogin =gson.toJson(pacienteDTO);
            System.out.println(jsonLogin);
            new obtenerToken().execute("https://migraine-services.herokuapp.com/webresources/auth/login");
            Thread.sleep(8000);
            System.out.println("Password: "+password);
            if(password==false) {
                new AlertDialog.Builder(this).setTitle("Error de autenticación").setMessage("El usuario y/o clave son erradas").setNeutralButton("Cerrar", null).show();
                return;
            }
            else
            {
                startActivity(intent);
            }
        }
        else if (doctores.isChecked())
        {
            new AlertDialog.Builder(this).setTitle("No diponible").setMessage("El login de doctor no está disponible").setNeutralButton("Cerrar", null).show();
            return;
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
            RestClient client = new RestClient(urls[0],MainActivity.this);
            client.AddHeader("data_hash", DataSecurity.hashCryptoCode(jsonLogin));
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
            if(client.getResponseCode()==200)
            {
                password=true;
            }
            System.out.println("Resp: "+client.getResponse());
            String tok=client.getResponse().split("\"")[1];
            return client.getResponseCode()+":"+tok;
        }

        protected void onPostExecute(String response)
        {
            try {
                System.out.println(response);
                String tok=response.split(":")[1];
                String resp=response.split(":")[0];
                    if(resp.equals("200"))
                    {
                        password=true;
                        System.out.println("entró");
                        SharedPreferences.Editor editor = getSharedPreferences(TAG, MODE_PRIVATE).edit();
                        //String token=client.getResponse().split("\"")[0];
                        editor.putString("token", tok);
                        System.out.println(tok);
                        EditText usuarioEdit = (EditText)findViewById(R.id.usuario_edit);
                        String usuario = usuarioEdit.getText().toString();
                        String login=usuario.split("@")[0];
                        editor.putString("USUARIO",login);
                        System.out.println(editor.commit());
                    }
                    else
                    {
                        password=false;
                    }
                }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
