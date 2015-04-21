package grupo9.arquisoft.migrainetrackingmobile;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import grupo9.arquisoft.migrainetrackingmobile.dtos.MedicamentoDTO;


public class MenuPrincipalActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_USUARIO);
        TextView usuarioView = new TextView(this);
        usuarioView.setTextSize(10);
        usuarioView.setText("Bienvenido, "+message);
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

    public void verEpisodios(View view){
        Intent intent = new Intent(this, VerEpisodiosActivity.class);
        Bundle b = new Bundle();
        b.putString("tipo","TODOS");
        b.putString("id","");
        intent.putExtras(b);
        startActivity(intent);
    }

}
