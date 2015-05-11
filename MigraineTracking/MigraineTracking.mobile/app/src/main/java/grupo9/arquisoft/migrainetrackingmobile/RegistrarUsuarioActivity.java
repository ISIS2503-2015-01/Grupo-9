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


        PacienteDTO nuevo=new PacienteDTO();
        nuevo.setName(nombre);
        nuevo.setBirthdate(fecha.getTime());
        nuevo.setCedula(cedula);
        nuevo.setDoctorid(null);

        nuevo.setUsername(usuario);

        nuevo.setPassword(contrasenia);

        jsonRespuesta="{\"cedula\":"+nuevo.getCedula()+"," +
                "\"username\":\""+nuevo.getUsername()+"\"," +
                "\"password\":\""+nuevo.getPassword()+"\"," +
                "\"name\":\""+nuevo.getName()+"\"," +
                "\"birthdate\":"+nuevo.getBirthdate()+"," +
                "\"doctorid\":"+1+"}";
        System.out.println(jsonRespuesta);
        new registrar().execute("https://migraine-services.herokuapp.com/webresources/auth/new/paciente");
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private class registrar extends AsyncTask<String, Long, String> {
        protected String doInBackground(String... urls) {
            RestClient restClient = new RestClient(urls[0],RegistrarUsuarioActivity.this);
            //restClient.AddHeader("Content-Type", "application/json");
            restClient.AddHeader("data_hash", DataSecurity.hashCryptoCode(jsonRespuesta));
            restClient.AddParam(jsonRespuesta);
            System.out.println(DataSecurity.hashCryptoCode(jsonRespuesta));
            try {
                restClient.Execute(RestClient.RequestMethod.POST);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(restClient.getErrorMessage());
            System.out.println(restClient.getResponse());
            return restClient.getResponse();
        }

        protected void onPostExecute(String response) {

        }
    }
}
