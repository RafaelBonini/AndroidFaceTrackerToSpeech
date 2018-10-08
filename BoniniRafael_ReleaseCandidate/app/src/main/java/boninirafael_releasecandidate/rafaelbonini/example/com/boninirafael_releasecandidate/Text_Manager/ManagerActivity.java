package boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Text_Manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.R;

import static boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Text_Manager.NewItemActivity.SharedPreferencesKey;
import static boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Text_Manager.NewItemActivity.SharedPreferencesName;

public class ManagerActivity extends AppCompatActivity {

    public static final String TAG = ManagerActivity.class.getSimpleName();

    ListView textList;
    ArrayList<String> texts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        textList = findViewById(R.id.list);

        setListFromStoredData();



        textList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(TAG, "onItemClick: pos   " + position + " id: " + id);

                Intent intent = new Intent(ManagerActivity.this,DetailsActivity.class);

                intent.putExtra("selectedText",position);

                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListFromStoredData();
    }

    //get data stored use the data to create an adapter and set it as the list adapter
    public void setListFromStoredData(){

        ArrayList<String> stringArraylist;

        //load data from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferencesName, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString(SharedPreferencesKey, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        stringArraylist = gson.fromJson(json, type);
        if (stringArraylist == null) {
            stringArraylist = new ArrayList<>();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                stringArraylist
        );

        textList.setAdapter(adapter);
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