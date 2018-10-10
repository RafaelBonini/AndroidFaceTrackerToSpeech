package boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.FaceTracker;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.R;

public class FaceTrackingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_tracking);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}