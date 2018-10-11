package boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.FaceTracker;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.R;

public class InteractionListFragment extends ListFragment {

    public static final String TAG = "InteractionListFragment.TAG";

    public InteractionListFragment newInstance(){
        return new InteractionListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_interactionlist,container,false);
    }



}
