package boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Text_Manager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.R;

import static boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Text_Manager.NewItemActivity.SharedPreferencesKey;
import static boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Text_Manager.NewItemActivity.SharedPreferencesName;

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


        Button deleteButton = findViewById(R.id.deleteItem_Button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this)
                        .setMessage("This action will delete the item. Are you sure?")
                        .setNegativeButton("Cancel",null)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                savedTexts.remove(selectedItemIndex);
                                saveUpdatedList();
                                finish();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });
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

                saveUpdatedList();

            }
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveUpdatedList(){

        SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferencesName, Context.MODE_PRIVATE);

        Gson gson = new Gson();

        //save array list to shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String json = gson.toJson(savedTexts);
        editor.putString(SharedPreferencesKey, json);
        editor.apply();
    }

}
