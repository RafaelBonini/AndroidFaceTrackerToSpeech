package boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.FaceTracker;

import android.content.pm.ActivityInfo;
import android.media.FaceDetector;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.R;

public class FaceTrackingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_tracking);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        if (savedInstanceState == null){

            FaceTrackerFragment faceTrackerFragment = new FaceTrackerFragment().newIntance();

            getFragmentManager().beginTransaction()
                    .replace(R.id.tracking_fragment_container,faceTrackerFragment,faceTrackerFragment.TAG)
                    .commit();



//            InteractionListFragment interactionListFragment = new InteractionListFragment().newInstance();
//
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.list_fragment_container,interactionListFragment,interactionListFragment.TAG)
//                    .commit();
        }
    }
}