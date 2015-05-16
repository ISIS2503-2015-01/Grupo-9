package grupo9.arquisoft.migrainetrackingmobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.squareup.okhttp.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import grupo9.arquisoft.migrainetrackingmobile.dtos.DoctorDTO;
import grupo9.arquisoft.migrainetrackingmobile.dtos.PacienteDTO;
import grupo9.arquisoft.migrainetrackingmobile.extras.DataSecurity;
import grupo9.arquisoft.migrainetrackingmobile.extras.PostHttp;


public class RegistrarUsuarioActivity extends ActionBarActivity {

    private String jsonRespuesta;
    private String tipo;
    ProgressDialog dialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        tipo=bundle.getString("tipo","");
        if(tipo.equals("paciente"))
        {
            EditText editId=(EditText)findViewById(R.id.editText4);
            TextView labelId=(TextView)findViewById(R.id.textView3);
            editId.setVisibility(View.VISIBLE);
            labelId.setVisibility(View.VISIBLE);
        }
        else
        {
            EditText editId=(EditText)findViewById(R.id.editText4);
            TextView labelId=(TextView)findViewById(R.id.textView3);
            editId.setVisibility(View.INVISIBLE);
            labelId.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registrar_usuario, menu);
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

    public void registrarUsuario(View view)
    {
        TextView txtNombre= (TextView)findViewById(R.id.editText);
        String nombre=txtNombre.getText().toString();
        TextView txtUsuario= (TextView)findViewById(R.id.editText2);
        String usuario=txtUsuario.getText().toString();
        TextView txtFecha= (TextView)findViewById(R.id.editText3);
        TextView txtCedula=(TextView)findViewById(R.id.editText5);
        long cedula=0;
        TextView txtContrasenia=(TextView)findViewById(R.id.editText6);
        String contrasenia=txtContrasenia.getText().toString();
        Date fecha= new Date();
        try
        {
            cedula=Long.parseLong(txtCedula.getText().toString());
            fecha=new SimpleDateFormat("dd/mm/yyyy").parse(txtFecha.getText().toString());
            if(!usuario.contains("@")||!usuario.contains("."))
                throw new Exception();
            if(contrasenia.length()<9)
                throw new Exception();

        } catch (Exception e)
        {
            e.printStackTrace();
            new AlertDialog.Builder(this).setTitle("Error de creación").setMessage("Los campos no son correctos").setNeutralButton("Cerrar", null).show();
            return;
        }

        if(tipo.equals("paciente"))
        {
            PacienteDTO nuevo = new PacienteDTO();
            nuevo.setName(nombre);
            nuevo.setBirthdate(fecha.getTime());
            nuevo.setCedula(cedula);
            nuevo.setDoctorid(null);
            nuevo.setUsername(usuario);
            nuevo.setPassword(contrasenia);

            jsonRespuesta = "{\"cedula\":" + nuevo.getCedula() + "," +
                    "\"username\":\"" + nuevo.getUsername() + "\"," +
                    "\"password\":\"" + nuevo.getPassword() + "\"," +
                    "\"name\":\"" + nuevo.getName() + "\"," +
                    "\"birthdate\":" + nuevo.getBirthdate() + "," +
                    "\"doctorid\":" + 1 + "}";
            System.out.println(jsonRespuesta);
            new registrar(true).execute("https://migraine-services.herokuapp.com/webresources/auth/new/paciente");
        }
        else
        {
            DoctorDTO nuevo=new DoctorDTO();
            nuevo.setName(nombre);
            nuevo.setPassword(contrasenia);
            nuevo.setUsername(usuario);

            jsonRespuesta = "{\"id\":" + null + "," +
                    "\"username\":\"" + nuevo.getUsername() + "\"," +
                    "\"password\":\"" + nuevo.getPassword() + "\"," +
                    "\"name\":\"" + nuevo.getName() + "\""+ "}";
            System.out.println(jsonRespuesta);
            new registrar(false).execute("https://migraine-services.herokuapp.com/webresources/auth/new/doctor");
        }
    }

    private class registrar extends AsyncTask<String, Long, String> {
        private boolean paciente;
        public registrar(boolean paciente)
        {
            this.paciente=paciente;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            dialogo=ProgressDialog.show(RegistrarUsuarioActivity.this,"Espera un momento...","Cargando...",true);
            dialogo.setCancelable(true);
        }
        protected String doInBackground(String... urls) {
            try
            {
                String hash= DataSecurity.hashCryptoCode(jsonRespuesta);
                String hashNoSpaces=hash.replaceAll("\\s+", "");
                Map<String, String> heads= new HashMap<String,String>();
                heads.put("Content-Type", "application/json");
                heads.put("Data_hash", hashNoSpaces);
                heads.put("Accept","application/json");
                Response response=new PostHttp().run(urls[0],jsonRespuesta,heads);
                String resp=response.body().string();
                System.out.println(resp);
                return response.code()+"-"+resp;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(String response)
        {
            String code=response.split("-")[0];
            String resp=response.split("-")[1];
            if(code=="500")
            {
                new AlertDialog.Builder(RegistrarUsuarioActivity.this).setTitle("Error de creación").setMessage("No se pudo crear el usuario").setNeutralButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(RegistrarUsuarioActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }).show();
            }
            else
            {
                String id=resp.split("\":")[2];
                dialogo.dismiss();
                if(paciente) {
                    new AlertDialog.Builder(RegistrarUsuarioActivity.this).setTitle("Añadido paciente correctamente").setMessage("Se agregó el paciente").setNeutralButton("Cerrar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(RegistrarUsuarioActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }).show();
                }
                else
                {
                    new AlertDialog.Builder(RegistrarUsuarioActivity.this).setTitle("Añadido doctor correctamente").setMessage("Su identificador es: "+id).setNeutralButton("Cerrar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(RegistrarUsuarioActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }).show();
                }
            }
    }
}}
