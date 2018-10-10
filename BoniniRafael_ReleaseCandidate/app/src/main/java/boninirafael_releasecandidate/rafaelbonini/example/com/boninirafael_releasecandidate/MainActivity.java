package boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.FaceTracker.FaceTrackingActivity;
import boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Text_Manager.ManagerActivity;

public class MainActivity extends AppCompatActivity {


    Button managerButton, startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        managerButton = findViewById(R.id.manager_button);
        startButton = findViewById(R.id.start_button);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FaceTrackingActivity.class));
            }
        });
        managerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,ManagerActivity.class));
            }
        });
    }
}
