package grupo9.arquisoft.migrainetrackingmobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import grupo9.arquisoft.migrainetrackingmobile.dtos.EpisodioDolorDTO;
import grupo9.arquisoft.migrainetrackingmobile.dtos.MedicamentoDTO;
import grupo9.arquisoft.migrainetrackingmobile.dtos.PacienteDTO;


public class MenuPrincipalActivity extends ActionBarActivity {

    private String idUsuario;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Intent intent = getIntent();
        TextView textView=(TextView)findViewById(R.id.textView);
        SharedPreferences prefs=getSharedPreferences(MainActivity.TAG,MODE_PRIVATE);
        idUsuario=prefs.getString("USUARIO","");
        textView.setText("Bienvenido (a), "+idUsuario);
        token=prefs.getString("token","");
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
        Bundle bundle=new Bundle();
        bundle.putString("USUARIO",idUsuario);
        intent.putExtras(bundle);
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
    public void verEpisodios(View view)
    {
        Intent intent = new Intent(this, VerEpisodiosActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("tipo","CEDULA");
        bundle.putString("id",idUsuario);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
