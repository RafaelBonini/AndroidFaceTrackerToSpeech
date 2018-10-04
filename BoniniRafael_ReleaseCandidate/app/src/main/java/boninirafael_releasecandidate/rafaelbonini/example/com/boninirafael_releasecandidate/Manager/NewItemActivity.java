package boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.R;

public class NewItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.saveitem_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.saveItem){

            //todo: save new information
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
