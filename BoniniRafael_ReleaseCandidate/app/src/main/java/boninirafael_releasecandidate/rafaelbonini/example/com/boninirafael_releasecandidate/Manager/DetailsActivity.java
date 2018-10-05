package boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        int selectedItemPos = getIntent().getIntExtra("selectedText",0);



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

        editTextInput.setText(savedTexts.get(selectedItemPos));


    }
}
