package boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.FaceTracker;

import android.content.pm.ActivityInfo;
import android.media.FaceDetector;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.R;

public class FaceTrackingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_tracking);



        if (savedInstanceState == null){


            InteractionListFragment interactionListFragment = new InteractionListFragment().newInstance();

            getFragmentManager().beginTransaction()
                    .replace(R.id.list_fragment_container,interactionListFragment,InteractionListFragment.TAG)
                    .commit();
        }


            FaceTrackerFragment faceTrackerFragment = new FaceTrackerFragment().newIntance();

            getFragmentManager().beginTransaction()
                    .replace(R.id.tracking_fragment_container,faceTrackerFragment,faceTrackerFragment.TAG)
                    .commit();




    }


    public void goUpOnList(){
        android.app.FragmentManager fm = getFragmentManager();

        //if you added fragment via layout xml
        InteractionListFragment fragment = (InteractionListFragment) fm.findFragmentByTag("InteractionListFrag.TAG");
        fragment.moveUP();

    }

    public void goDownOnList(){


        android.app.FragmentManager fm = getFragmentManager();

        //if you added fragment via layout xml
        InteractionListFragment fragment = (InteractionListFragment) fm.findFragmentByTag("InteractionListFrag.TAG");
        fragment.moveDown();
    }

    public void clickSelectedItem(){

        android.app.FragmentManager fm = getFragmentManager();

        //if you added fragment via layout xml
        InteractionListFragment fragment = (InteractionListFragment) fm.findFragmentByTag("InteractionListFrag.TAG");
        fragment.clickItem();

    }
}