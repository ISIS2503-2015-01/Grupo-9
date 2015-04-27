package grupo9.arquisoft.migrainetrackingmobile;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import grupo9.arquisoft.migrainetrackingmobile.dtos.SintomaDTO;


public class AgregarSintomaActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_sintoma);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agregar_sintoma, menu);
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

    public void guardar(View view)
    {
        Intent intent = new Intent(this, RegistrarEpisodioActivity.class);
        try {
            EditText nombre = (EditText) findViewById(R.id.nombreSintoma_edit);
            EditText localizacion = (EditText) findViewById(R.id.localizacionSintoma_edit);
            EditText intensidad = (EditText) findViewById(R.id.intensidadSintoma_edit);
            String nomb = nombre.getText().toString();
            String loca = localizacion.getText().toString();
            String inte = intensidad.getText().toString();
            SintomaDTO nuevo = new SintomaDTO(nomb);
        }
        catch(Exception e)
        {

        }
        startActivity(intent);
    }

    public void crearOtroSintoma(View view)
    {
        Intent intent = new Intent(this, AgregarSintomaActivity.class);
        try {
            EditText nombre = (EditText) findViewById(R.id.nombreSintoma_edit);
            EditText localizacion = (EditText) findViewById(R.id.localizacionSintoma_edit);
            EditText intensidad = (EditText) findViewById(R.id.intensidadSintoma_edit);
            String nomb = nombre.getText().toString();
            String loca = localizacion.getText().toString();
            String inte = intensidad.getText().toString();
            SintomaDTO nuevo = new SintomaDTO(nomb);
        }
        catch(Exception e)
        {

        }
        startActivity(intent);
    }


}
