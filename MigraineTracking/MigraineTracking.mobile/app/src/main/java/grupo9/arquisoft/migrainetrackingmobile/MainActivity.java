package grupo9.arquisoft.migrainetrackingmobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import com.google.gson.Gson;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import grupo9.arquisoft.migrainetrackingmobile.dtos.DoctorDTO;
import grupo9.arquisoft.migrainetrackingmobile.dtos.PacienteDTO;
import grupo9.arquisoft.migrainetrackingmobile.extras.PostHttp;


public class MainActivity extends ActionBarActivity {

    public final static String TAG="grupo9.migraintracking";
    private String jsonLogin;
    private Gson gson;
    ProgressDialog dialogo;
    private String id;

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
    public void login(View view) throws InterruptedException
    {
        RadioButton pacientes = (RadioButton)findViewById(R.id.pacientesRadio);
        RadioButton doctores = (RadioButton) findViewById(R.id.doctoresRadio);
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
        if(pacientes.isChecked())
        {
            PacienteDTO pacienteDTO=new PacienteDTO();
            pacienteDTO.setUsername(usuario);
            pacienteDTO.setPassword(claveapp);
            jsonLogin =gson.toJson(pacienteDTO);
            new obtenerToken(true).execute("https://migraine-services.herokuapp.com/webresources/auth/signIn");
        }
        else if (doctores.isChecked())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Ingrese su ID");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    id = input.getText().toString();
                }
            });
            builder.show();

            long ids;
            try
            {
                if(id==""|id==null)
                {
                    throw new Exception();
                }
                ids=Long.parseLong(id);
            }
            catch(Exception e)
            {
                new AlertDialog.Builder(this).setTitle("Error de autenticación").setMessage("Ingrese una ID válido").setNeutralButton("Cerrar", null).show();
                return;
            }

            DoctorDTO doctorDTO = new DoctorDTO();
            doctorDTO.setUsername(usuario);
            doctorDTO.setPassword(claveapp);
            doctorDTO.setId(ids);
            jsonLogin = gson.toJson(doctorDTO);
            new obtenerToken(false).execute("https://migraine-services.herokuapp.com/webresources/auth/signIn");
        }
        else
        {
            new AlertDialog.Builder(this).setTitle("Error").setMessage("Seleccione un tipo de perfil").setNeutralButton("Cerrar", null).show();
            return;
        }
    }

    public void crear(View view)
    {
        RadioButton pacientes = (RadioButton)findViewById(R.id.pacientesRadio);
        RadioButton doctores = (RadioButton) findViewById(R.id.doctoresRadio);
        if(pacientes.isChecked())
        {
            Intent intent=new Intent(this, RegistrarUsuarioActivity.class);
            startActivity(intent);
        }
        else if(doctores.isChecked())
        {
            new AlertDialog.Builder(this).setTitle("No diponible").setMessage("Crear un doctor no está disponible").setNeutralButton("Cerrar", null).show();
            return;
        }
        else
        {
            new AlertDialog.Builder(this).setTitle("Error").setMessage("Seleccione un tipo de perfil").setNeutralButton("Cerrar", null).show();
            return;
        }
    }

    private class obtenerToken extends AsyncTask<String, Long, String>
    {
        private boolean paciente;
        public obtenerToken(boolean paciente)
        {
            this.paciente=paciente;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogo=ProgressDialog.show(MainActivity.this,"Espera un momento...","Cargando...",true);
            dialogo.setCancelable(true);
        }

        protected String doInBackground(String... urls)
        {
            try
            {
                Map<String,String> headers=new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Accept","text/plain");
                Response response=new PostHttp().run(urls[0],jsonLogin,headers);
                String respuesta=response.body().string();
                int cod=response.code();
                if(cod==200)
                {
                    String tok = respuesta.split("\"")[1];
                    return 200 + ":" + tok;
                }
                else
                    return 500+":"+respuesta;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(String response)
        {
            try
            {
                String tok=response.split(":")[1];
                String resp=response.split(":")[0];
                    if(resp.equals("200"))
                    {
                        SharedPreferences.Editor editor = getSharedPreferences(TAG, MODE_PRIVATE).edit();
                        editor.putString("token", tok);
                        EditText usuarioEdit = (EditText)findViewById(R.id.usuario_edit);
                        String usuario = usuarioEdit.getText().toString();
                        String login=usuario.split("@")[0];
                        editor.putString("USUARIO",login);
                        EditText cedulaEdit=(EditText) findViewById(R.id.cedula_edit);
                        String cedula=cedulaEdit.getText().toString();
                        long ced=Long.parseLong(cedula);
                        editor.putLong("CEDULA", ced);
                        editor.commit();
                        dialogo.dismiss();
                        if(paciente)
                        {
                            Intent intent = new Intent(MainActivity.this, MenuPacienteActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent = new Intent(MainActivity.this, MenuDoctorActivity.class);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        dialogo.dismiss();
                        new AlertDialog.Builder(MainActivity.this).setTitle("Error de autenticación").setMessage("El usuario y/o clave son erradas").setNeutralButton("Cerrar", null).show();
                        return;
                    }
                }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
