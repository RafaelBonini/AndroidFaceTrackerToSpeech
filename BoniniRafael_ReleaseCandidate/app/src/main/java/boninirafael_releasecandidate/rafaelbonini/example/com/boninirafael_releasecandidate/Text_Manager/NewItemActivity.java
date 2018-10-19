package boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Text_Manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.R;

public class NewItemActivity extends AppCompatActivity {

    public static final String SharedPreferencesName = "shared preferences";
    public static final String SharedPreferencesKey = "textInput";


    EditText textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        textInput = findViewById(R.id.textInput_ET);
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

            //todo: check and save new information

            String input = textInput.getText().toString();

            if (!input.equals("")){

                ArrayList<String> texts;

                //load data from shared preferences
                SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferencesName, Context.MODE_PRIVATE);

                Gson gson = new Gson();
                String json = sharedPreferences.getString(SharedPreferencesKey, null);
                Type type = new TypeToken<ArrayList<String>>() {
                }.getType();
                texts = gson.fromJson(json, type);
                if (texts == null) {
                    texts = new ArrayList<>();
                }




                //add new item to arraylist
                texts.add(input);

                Log.d(NewItemActivity.class.getSimpleName(), "onClick: " + texts);

                //save array list to shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                json = gson.toJson(texts);
                editor.putString(SharedPreferencesKey, json);
                editor.apply();


                finish();
            }else{
                Toast.makeText(this,"Enter Text First, Please",Toast.LENGTH_SHORT).show();
            }


        }

        return super.onOptionsItemSelected(item);
    }
}