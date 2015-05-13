package grupo9.arquisoft.migrainetrackingmobile;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
    public void grabarEpisodio(View view)
    {
        new AlertDialog.Builder(this).setTitle("No diponible").setMessage("Grabar un episodio no está disponible").setNeutralButton("Cerrar", null).show();
        return;
    }
    public void escucharGrabaciones(View view){
        new AlertDialog.Builder(this).setTitle("No diponible").setMessage("Escuchar grabaciones no está disponible").setNeutralButton("Cerrar", null).show();
        return;
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
