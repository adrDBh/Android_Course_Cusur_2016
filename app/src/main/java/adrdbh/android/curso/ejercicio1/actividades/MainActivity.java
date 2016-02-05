package adrdbh.android.curso.ejercicio1.actividades;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import adrdbh.android.curso.ejercicio1.R;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // default code [def]
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // end [def]


        // asign value to content_main @ MiBoton2 id
        Button miboton2 = (Button) findViewById(R.id.MiBoton2);
        // asign value to content_main @ px id (Editable text field)
        final EditText ex = (EditText) findViewById(R.id.px);
        // asign value to content_main @ Tx1 id (editable text field)
        final TextView tx = (TextView) findViewById(R.id.Tx1);
        // asign value to content_main @ MiBoton id
        Button miboton = (Button) findViewById(R.id.MiBoton);


        // asign value to "TABS" button in activity_section @ id tabs
        Button tabs = (Button) findViewById(R.id.tabs);

        // Button listener implementation
        miboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // "Toast" On screen notification that shows for a constant time if duration a String message.
                Toast.makeText(MainActivity.this, "Cambiando texto", Toast.LENGTH_SHORT).show();
                // Changes text from  text label gathered by the user
                tx.setText(ex.getText());
            }
        });

        // content main button listener that changes text showed below.
        miboton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent that makes data travel from activity/section
                Intent intento = new Intent(MainActivity.this, ListActivity.class);
                //  TBD
                intento.putExtra("key", ex.getText().toString());
                startActivity(intento);

            }
        });

        tabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(MainActivity.this, SectionActivity.class);
                startActivity(intento);
            }
        });


        Log.d("Main Activity", "App running");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://adrdbh.android.curso.ejercicio1/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://adrdbh.android.curso.ejercicio1/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
