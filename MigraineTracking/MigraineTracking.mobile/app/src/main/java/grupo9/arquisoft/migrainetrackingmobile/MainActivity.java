package grupo9.arquisoft.migrainetrackingmobile;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import com.google.gson.Gson;

import grupo9.arquisoft.migrainetrackingmobile.dtos.PacienteDTO;


public class MainActivity extends ActionBarActivity {

    public final static String TAG="grupo9.migraintracking";
    private boolean password;
    private String jsonLogin;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson=new Gson();
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
            if(usuario.equals("")||!usuario.contains("@")||!usuario.contains("."))
            {
                new AlertDialog.Builder(this).setTitle("Error de autenticación").setMessage("Ingrese un usuario válido").setNeutralButton("Cerrar", null).show();
                return;
            }
            EditText claveEdit = (EditText) findViewById(R.id.contrasenia_edit);
            String claveapp = claveEdit.getText().toString();
            if(claveapp.equals(""))
            {
                new AlertDialog.Builder(this).setTitle("Error de autenticación").setMessage("Ingrese una contraseña válida").setNeutralButton("Cerrar", null).show();
                return;
            }
            EditText cedulaEdit= (EditText) findViewById(R.id.cedula_edit);
            String cedula=cedulaEdit.getText().toString();
            try
            {
                if(cedula.equals(""))
                {
                    new AlertDialog.Builder(this).setTitle("Error de autenticación").setMessage("Ingrese una cédula válida").setNeutralButton("Cerrar", null).show();
                    return;
                }
                long ced=Long.parseLong(cedula);
            }
            catch (Exception e)
            {
                new AlertDialog.Builder(this).setTitle("Error de autenticación").setMessage("Ingrese una cédula válida").setNeutralButton("Cerrar", null).show();
                return;
            }
            PacienteDTO pacienteDTO=new PacienteDTO();
            pacienteDTO.setUsername(usuario);
            pacienteDTO.setPassword(claveapp);
            jsonLogin =gson.toJson(pacienteDTO);
            System.out.println(jsonLogin);
            new obtenerToken().execute("https://migraine-services.herokuapp.com/webresources/auth/signIn");
            Thread.sleep(8500);
            System.out.println("Password: " + password);
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

            try
            {
                RestClient client = new RestClient(urls[0],MainActivity.this);
                client.AddHeader("Content-Type", "application/json");
                client.AddParam(jsonLogin);
                client.Execute(RestClient.RequestMethod.POST);

                if(client.getResponseCode()==200)
                {
                    if(!client.getResponse().startsWith("User"))
                    {
                        password=true;
                    }
                    else
                    {
                        password=false;
                    }
                }
                if(password==true)
                {
                    String tok = client.getResponse().split("\"")[1];
                    return 200 + ":" + tok;
                }
                else
                    return 500+":"+client.getResponse();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return null;
            }
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
                        editor.putString("token", tok);
                        System.out.println(tok);
                        EditText usuarioEdit = (EditText)findViewById(R.id.usuario_edit);
                        String usuario = usuarioEdit.getText().toString();
                        String login=usuario.split("@")[0];
                        editor.putString("USUARIO",login);
                        EditText cedulaEdit=(EditText) findViewById(R.id.cedula_edit);
                        String cedula=cedulaEdit.getText().toString();
                        long ced=Long.parseLong(cedula);
                        editor.putLong("CEDULA", ced);
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
}
