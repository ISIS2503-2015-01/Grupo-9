package grupo9.arquisoft.migrainetrackingmobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import grupo9.arquisoft.migrainetrackingmobile.extras.VerDoctoresActivity;


public class MenuDoctorActivity extends ActionBarActivity {

    private long id;
    private String idUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_doctor);
        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        id=Long.parseLong(extras.getString("ID","1"));
        TextView textView=(TextView)findViewById(R.id.textView);
        SharedPreferences prefs=getSharedPreferences(MainActivity.TAG, MODE_PRIVATE);
        idUsuario=prefs.getString("USUARIO","");
        textView.setText("Bienvenido (a), "+idUsuario);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_doctor, menu);
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

    public void verColegas(View view)
    {
        Intent intent= new Intent(this, VerDoctoresActivity.class);
        startActivity(intent);
    }

    public void verEpisodiosPaciente(View view)
    {
        Intent intent= new Intent(this, EpisodiosDoctorActivity.class);
        startActivity(intent);
    }

    public void verTodosPacientes(View view)
    {
        Intent intent=new Intent(this, VerPacientesActivity.class);
        startActivity(intent);
    }

    public void verMisPacientes(View view)
    {
        Intent intent=new Intent(this, VerPacientesActivity.class);
        startActivity(intent);
    }
}
