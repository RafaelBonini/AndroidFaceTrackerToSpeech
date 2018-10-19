package boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.FaceTracker;

import android.app.ListFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Locale;

import boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.R;

import static boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Text_Manager.NewItemActivity.SharedPreferencesKey;
import static boninirafael_releasecandidate.rafaelbonini.example.com.boninirafael_releasecandidate.Text_Manager.NewItemActivity.SharedPreferencesName;

public class InteractionListFragment extends ListFragment {

    public static final String TAG = "InteractionListFrag.TAG";
    int selectedItem = 0;

    public InteractionListFragment newInstance(){
        return new InteractionListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_interactionlist,container,false);
    }

    TextToSpeech toSpeech;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        toSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status == TextToSpeech.SUCCESS){
                    int result = toSpeech.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED ){
                        Toast.makeText(getActivity(),"This language is not supported",Toast.LENGTH_SHORT).show();
                    }



                }
            }
        });


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
//                android.R.layout.simple_list_item_1,
                android.R.layout.simple_list_item_activated_1,
                savedTexts
        );

        setListAdapter(adapter);

        getListView().setItemChecked(0, true);


    }


    public void clickItem(){


//        mList.performItemClick(mList.getChildAt(mActivePosition), mActivePosition, mList.getAdapter().getItemId(mActivePosition));

        Log.d(TAG, "clickItem: " + selectedItem);




        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {




                getListView().performItemClick(getListView(),getListView().getCheckedItemPosition(),getListView().getCheckedItemPosition());

            }
        });
    }


    public void moveDown(){

        Log.d(TAG, "moveDown: ");

        Log.d(TAG, "onActivityCreated: ");

        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {

                int checked = getListView().getCheckedItemPosition();

                int pos = checked+1;

                //if selection goes further than last item go back to first
                if(getListView().getAdapter().getCount() == pos){
                    pos = 0;
                }

                selectedItem = pos;

                getListView().setItemChecked(pos, true);

            }
        });

    }

    public void moveUP(){

        Log.d(TAG, "moveUP: ");


        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {

                int checked = getListView().getCheckedItemPosition();

                int pos = checked-1;

                if (pos < 0){
                    pos = getListView().getAdapter().getCount()-1;
                }

                selectedItem = pos;
                getListView().setItemChecked(pos, true);

            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (toSpeech != null){

            toSpeech.stop();
            toSpeech.shutdown();
        }
    }

    @Override
    public void onListItemClick(final ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id);


        String text = l.getItemAtPosition(position).toString();

        speak(text);

        Log.d(TAG, "onListItemClick: " + l.getItemAtPosition(position) + "  : " + v.getTag(position));

    }

    private void speak(String itemText) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            toSpeech.speak(itemText,TextToSpeech.QUEUE_FLUSH,null,null);
        }else{
            toSpeech.speak(itemText,TextToSpeech.QUEUE_FLUSH,null);
        }

    }
}
