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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.okhttp.Response;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLSocketFactory;

import grupo9.arquisoft.migrainetrackingmobile.dtos.DoctorDTO;
import grupo9.arquisoft.migrainetrackingmobile.dtos.PacienteDTO;
import grupo9.arquisoft.migrainetrackingmobile.extras.DataSecurity;
import grupo9.arquisoft.migrainetrackingmobile.extras.Pinning;
import grupo9.arquisoft.migrainetrackingmobile.extras.PostHttp;


public class RegistrarUsuarioActivity extends ActionBarActivity {

    private String jsonRespuesta;
    private String tipo;
    ProgressDialog dialogo;
    private SSLSocketFactory ssl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PostHttp.createInstance();
        Pinning pin=new Pinning(RegistrarUsuarioActivity.this);
        //ssl=pin.getPinnedCertSslSocketFactory();
        setContentView(R.layout.activity_registrar_usuario);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        tipo=bundle.getString("tipo","");
        if(!tipo.equals("paciente"))
        {
            EditText editId=(EditText)findViewById(R.id.editText4);
            EditText editCed=(EditText)findViewById(R.id.editText5);
            EditText editFecha=(EditText)findViewById(R.id.editText3);
            TextView labelId=(TextView)findViewById(R.id.textView3);
            TextView labelFecha=(TextView)findViewById(R.id.textView2);
            TextView labelCed=(TextView)findViewById(R.id.textView4);
            editId.setVisibility(View.GONE);
            labelId.setVisibility(View.GONE);
            editCed.setVisibility(View.GONE);
            editFecha.setVisibility(View.GONE);
            labelCed.setVisibility(View.GONE);
            labelFecha.setVisibility(View.GONE);

            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            p.addRule(RelativeLayout.BELOW, R.id.editText6);
            p.addRule(RelativeLayout.CENTER_HORIZONTAL);
            Button botonCrear=(Button)findViewById(R.id.button);
            botonCrear.setLayoutParams(p);
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
        EditText txtNombre= (EditText)findViewById(R.id.editText);
        String nombre=txtNombre.getText().toString();
        EditText txtUsuario= (EditText)findViewById(R.id.editText2);
        String usuario=txtUsuario.getText().toString();
        EditText txtFecha= (EditText)findViewById(R.id.editText3);
        EditText txtId=(EditText)findViewById(R.id.editText4);
        EditText txtCedula=(EditText)findViewById(R.id.editText5);
        long cedula=0;
        long id=0;
        EditText txtContrasenia=(EditText)findViewById(R.id.editText6);
        String contrasenia=txtContrasenia.getText().toString();
        Date fecha= new Date();
        try
        {
            cedula=Long.parseLong(txtCedula.getText().toString());
            id=Long.parseLong(txtId.getText().toString());
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
            nuevo.setDoctorid(id);
            nuevo.setUsername(usuario);
            nuevo.setPassword(contrasenia);

            jsonRespuesta = "{\"cedula\":" + nuevo.getCedula() + "," +
                    "\"username\":\"" + nuevo.getUsername() + "\"," +
                    "\"password\":\"" + nuevo.getPassword() + "\"," +
                    "\"name\":\"" + nuevo.getName() + "\"," +
                    "\"birthdate\":" + nuevo.getBirthdate() + "," +
                    "\"doctorid\":" + nuevo.getDoctorid()+ "}";
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
                Response response=PostHttp.run(urls[0],jsonRespuesta,heads,null);
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
                dialogo.dismiss();
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
                id=id.replace("}","");
                dialogo.dismiss();
                if(paciente)
                {
                    if(resp.startsWith("{\"Exception Message\":null"))
                    {
                        new AlertDialog.Builder(RegistrarUsuarioActivity.this).setTitle("No se pudo añadir el paciente").setMessage("No existe el ID del doctor suministrado").setNeutralButton("Cerrar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(RegistrarUsuarioActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }).show();
                    }
                    else {
                        new AlertDialog.Builder(RegistrarUsuarioActivity.this).setTitle("Añadido paciente correctamente").setMessage("Se agregó el paciente").setNeutralButton("Cerrar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(RegistrarUsuarioActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }).show();
                    }
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
