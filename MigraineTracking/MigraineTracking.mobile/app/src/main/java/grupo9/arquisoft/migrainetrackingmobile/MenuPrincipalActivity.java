package grupo9.arquisoft.migrainetrackingmobile;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import grupo9.arquisoft.migrainetrackingmobile.dtos.MedicamentoDTO;


public class MenuPrincipalActivity extends ActionBarActivity {

    private String idUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        idUsuario=bundle.getString("USUARIO");

        new buscarNombre().execute("https://migraine-services.herokuapp.com/pacientes/"+idUsuario);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_principal, menu);
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
    public void registrarEpisodio(View view){
        Intent intent = new Intent(this, RegistrarEpisodioActivity.class);
        startActivity(intent);
    }
    public void grabarEpisodio(View view){
        Intent intent = new Intent(this, GrabarEpisodioActivity.class);
        startActivity(intent);
    }
    public void escucharGrabaciones(View view){
        Intent intent = new Intent(this, EscucharGrabacionesActivity.class);
        startActivity(intent);
    }
    public void agregarMedicamento(View view){
        Intent intent = new Intent(this, AgregarMedicamentoActivity.class);
        startActivity(intent);
    }
    public void agregarHabito(View view){
        Intent intent = new Intent(this, AgregarHabitoActivity.class);
        startActivity(intent);
    }

    public void verAnalisis(View view){
        Intent intent = new Intent(this, VerAnalisisActivity.class);
        startActivity(intent);
    }

    private class buscarNombre extends AsyncTask<String, Long, String> {
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
            TextView textView=(TextView)findViewById(R.id.textView);
            textView.setText("Bienvenido, "+idUsuario);
        }
    }
}
