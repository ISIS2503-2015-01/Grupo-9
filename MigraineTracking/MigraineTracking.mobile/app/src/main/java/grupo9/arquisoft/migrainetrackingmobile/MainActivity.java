package grupo9.arquisoft.migrainetrackingmobile;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import grupo9.arquisoft.migrainetrackingmobile.dtos.MedicamentoDTO;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_USUARIO = "grupo9.arquisoft.migrainetrackingmobile.USUARIO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Spinner spinner = (Spinner) findViewById(R.id.perfiles_spinner);
        ArrayAdapter<CharSequence> perfiles = ArrayAdapter.createFromResource(this,R.array.perfiles_spinner, android.R.layout.simple_spinner_item);
        perfiles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(perfiles);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void login(View view){
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        EditText usuarioEdit = (EditText)findViewById(R.id.usuario_edit);
        String usuario = usuarioEdit.getText().toString();
        if(usuario.equals("mp.mancipe10")||usuario.equals("s.abisambra125")||usuario.equals("pa.otoya575")||usuario.equals("hf.vargas10"))
        intent.putExtra(EXTRA_USUARIO, usuario);
        startActivity(intent);
    }
}
