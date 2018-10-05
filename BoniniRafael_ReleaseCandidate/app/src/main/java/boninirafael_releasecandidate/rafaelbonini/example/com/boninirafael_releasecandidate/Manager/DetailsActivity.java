package boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.R;

import static boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Manager.NewItemActivity.SharedPreferencesKey;
import static boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Manager.NewItemActivity.SharedPreferencesName;

public class DetailsActivity extends AppCompatActivity {


    EditText editTextInput;
    ArrayList<String> savedTexts;
    int selectedItemIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        selectedItemIndex = getIntent().getIntExtra("selectedText",0);



        //load data from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferencesName, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString(SharedPreferencesKey, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        savedTexts = gson.fromJson(json, type);
        if (savedTexts == null) {
            savedTexts = new ArrayList<>();
        }


        editTextInput = findViewById(R.id.editTextInput_ET);

        editTextInput.setText(savedTexts.get(selectedItemIndex));


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
            //todo: save changed item

            String textInput = editTextInput.getText().toString();
            if (!textInput.equals("")){

                savedTexts.set(selectedItemIndex,textInput);

                //save new arraylist


                Log.d(NewItemActivity.class.getSimpleName(), "onClick: " + savedTexts);

                SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferencesName, Context.MODE_PRIVATE);

                Gson gson = new Gson();

                //save array list to shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();

                String json = gson.toJson(savedTexts);
                editor.putString(SharedPreferencesKey, json);
                editor.apply();
            }
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
