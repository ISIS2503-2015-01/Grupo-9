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
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import grupo9.arquisoft.migrainetrackingmobile.dtos.DoctorDTO;
import grupo9.arquisoft.migrainetrackingmobile.dtos.PacienteDTO;
import grupo9.arquisoft.migrainetrackingmobile.extras.GetHttp;
import grupo9.arquisoft.migrainetrackingmobile.extras.PostHttp;


public class MainActivity extends ActionBarActivity {

    public final static String TAG="grupo9.migraintracking";
    private String jsonLogin;
    private Gson gson;
    ProgressDialog dialogo;
    private EditText idEdit;
    private DoctorDTO doctorDTO;
    private PacienteDTO pacienteDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson=new Gson();
        RadioGroup group=(RadioGroup)findViewById(R.id.groupRadio);
        idEdit=(EditText)findViewById(R.id.id_edit);
        idEdit.setVisibility(View.INVISIBLE);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.doctoresRadio) {
                    idEdit.setVisibility(View.VISIBLE);
                } else {
                    idEdit.setVisibility(View.INVISIBLE);
                }
            }
        });
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
                throw new Exception();
            }
            long ced=Long.parseLong(cedula);
        }
        catch (Exception e)
        {
            new AlertDialog.Builder(this).setTitle("Error de autenticación").setMessage("Ingrese una cédula válida").setNeutralButton("Cerrar", null).show();
            return;
        }
        String id=idEdit.getText().toString();
        if(pacientes.isChecked())
        {
            pacienteDTO=new PacienteDTO();
            pacienteDTO.setUsername(usuario);
            pacienteDTO.setPassword(claveapp);
            jsonLogin =gson.toJson(pacienteDTO);
            new obtenerToken(true).execute("https://migraine-services.herokuapp.com/webresources/auth/signIn");
        }
        else if (doctores.isChecked())
        {
            long ids;
            try
            {
                if(id==null||id.equals(""))
                {
                    throw new Exception();
                }
                ids=Long.parseLong(id);
            }
            catch(Exception e)
            {
                new AlertDialog.Builder(this).setTitle("Error de autenticación").setMessage("Ingrese un id válido").setNeutralButton("Cerrar", null).show();
                return;
            }
            doctorDTO = new DoctorDTO();
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
            intent.putExtra("tipo","paciente");
            startActivity(intent);
        }
        else if(doctores.isChecked())
        {
            Intent intent=new Intent(this, RegistrarUsuarioActivity.class);
            intent.putExtra("tipo","doctor");
            startActivity(intent);
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
                if(paciente)
                {
                    if(cod==200 && !respuesta.startsWith("User"))
                    {
                        String tok = respuesta.split("\"")[1];
                        Map<String, String> headers1 = new HashMap<>();
                        headers1.put("X_rest_user",tok);
                        Response response1 = new GetHttp().run("https://migraine-services.herokuapp.com/webresources/pacientes/"+idEdit.getText().toString(),headers1);
                        int cod1=response1.code();
                        System.out.println(cod1);
                        if(cod1==200)
                        cod=200;
                    }
                    else
                    {
                        cod=500;
                    }
                }
                else
                {
                    if(cod==200 && !respuesta.startsWith("User"))
                    {
                        String tok = respuesta.split("\"")[1];
                        Map<String, String> headers1 = new HashMap<>();
                        headers1.put("X_rest_user",tok);
                        Response response1 = new GetHttp().run("https://migraine-services.herokuapp.com/webresources/doctores/"+idEdit.getText().toString(),headers1);
                        int cod1=response1.code();
                        System.out.println(cod1);
                        if(cod1==200)
                            cod=200;
                    }
                    else
                    {
                        cod=500;
                    }
                }
                if(cod==200)
                {
                    String tok = respuesta.split("\"")[1];
                    return 200 + ":" + tok;
                }
                else
                    return 500 + ":" + respuesta;
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
                        dialogo.dismiss();
                        if(paciente)
                        {
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this, MenuPacienteActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            String id=idEdit.getText().toString();
                            editor.putLong("ID",Long.parseLong(id));
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this, MenuDoctorActivity.class);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        dialogo.dismiss();
                        new AlertDialog.Builder(MainActivity.this).setTitle("Error de autenticación").setMessage("El usuario y/o clave y/o cédula están errado(s)").setNeutralButton("Cerrar", null).show();
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
