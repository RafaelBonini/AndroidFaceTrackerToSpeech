package boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.R;

public class ManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.addtext_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.addItem){

            startActivity(new Intent(ManagerActivity.this,NewItemActivity.class));

        }



        return super.onOptionsItemSelected(item);
    }
}
