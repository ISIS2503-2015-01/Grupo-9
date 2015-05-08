package grupo9.arquisoft.migrainetrackingmobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import grupo9.arquisoft.migrainetrackingmobile.dtos.PacienteDTO;


public class RegistrarUsuarioActivity extends ActionBarActivity {

    private String jsonRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
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
        Date fecha= new Date();
        try {
            fecha=new SimpleDateFormat("dd/mm/yyyy").parse(txtFecha.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TextView txtCedula=(TextView)findViewById(R.id.editText5);
        long cedula=Long.parseLong(txtCedula.getText().toString());
        TextView txtContrasenia=(TextView)findViewById(R.id.editText6);
        String contrasenia=txtContrasenia.getText().toString();

        PacienteDTO nuevo=new PacienteDTO();
        nuevo.setName(nombre);
        nuevo.setBirthdate(fecha.getTime());
        nuevo.setCedula(cedula);
        nuevo.setDoctorid(null);

        usuario=md5(usuario);
        nuevo.setUsername(usuario);

        contrasenia=md5(contrasenia);
        nuevo.setPassword(contrasenia);

        //jsonRespuesta=gson.toJson(nuevo);
        jsonRespuesta="{\"doctorid\":"+1+",\"password\":\""+nuevo.getPassword()+"\",\"username\":\""+nuevo.getUsername()+"\",\"cedula\":\""+nuevo.getCedula()+"\",\"birthdate\":"+nuevo.getBirthdate()+",\"name\":\""+nuevo.getName()+"\"}";
        System.out.println(jsonRespuesta);
        new registrar().execute("https://migraine-services.herokuapp.com/webresources/auth/new/paciente");
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
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

    private class registrar extends AsyncTask<String, Long, String> {
        protected String doInBackground(String... urls) {
            RestClient restClient = new RestClient(urls[0]);
            restClient.AddHeader("Content-Type", "application/json");
            restClient.AddHeader("data_hash", DataSecurity.hashCryptoCode(jsonRespuesta));
            restClient.AddParam(jsonRespuesta);
            System.out.println(jsonRespuesta);
            try {
                restClient.Execute(RestClient.RequestMethod.POST);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(restClient.getResponse());
            return restClient.getResponse();
        }

        protected void onPostExecute(String response) {
            Intent intent=new Intent(RegistrarUsuarioActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
