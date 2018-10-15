package boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.FaceTracker;

import android.app.ListFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.R;

import static boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Text_Manager.NewItemActivity.SharedPreferencesKey;
import static boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Text_Manager.NewItemActivity.SharedPreferencesName;

public class InteractionListFragment extends ListFragment {

    public static final String TAG = "InteractionListFragment.TAG";

    public InteractionListFragment newInstance(){
        return new InteractionListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_interactionlist,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<String> savedTexts;

        //load data from shared preferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SharedPreferencesName, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString(SharedPreferencesKey, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        savedTexts = gson.fromJson(json, type);
        if (savedTexts == null) {
            savedTexts = new ArrayList<>();
        }

        ArrayAdapter adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                savedTexts
        );

        setListAdapter(adapter);
    }
}
