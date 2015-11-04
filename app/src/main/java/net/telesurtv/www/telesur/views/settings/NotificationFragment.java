package net.telesurtv.www.telesur.views.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.pushwoosh.PushManager;
import com.pushwoosh.SendPushTagsCallBack;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.storage.Preferences;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * Created by Jhordan on 07/31/15.
 */
public class NotificationFragment extends Fragment implements View.OnClickListener {


    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    private static final String KEY_1 = "1";
    private static final String KEY_2 = "2";
    private static final String KEY_3 = "3";
    private static final String KEY_4 = "4";
    private static final String KEY_5 = "5";

    private static final String VALUE_P = "P";
    private static final String VALUE_L = "L";
    private static final String VALUE_M = "M";
    private static final String VALUE_D = "D";
    private static final String VALUE_C = "C";


    private CheckBox checkBoxP, checkBoxL, checkBoxM, checkBoxD, checkBoxC;
    private HashMap<String, String> storageHashMap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings_notification, container, false);

        setUpViews(rootView);
        initializeCheckBoxPreference();

        return rootView;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.checkBox1:
                setCheckedValue(KEY_1, view);
                break;

            case R.id.checkBox2:
                setCheckedValue(KEY_2, view);
                break;

            case R.id.checkBox3:
                setCheckedValue(KEY_3, view);
                break;

            case R.id.checkBox4:
                setCheckedValue(KEY_4, view);
                break;

            case R.id.checkBox5:
                setCheckedValue(KEY_5, view);
                break;

            case R.id.btn_sent:
                setupHashMap();
                Preferences.setPushWooshTag(getActivity(), getSortedHashMapStorage());
                Notification.sentTagsToPushWoosh(getActivity(),Preferences.getPushWooshTag(getActivity()));
                break;


        }


    }

    // initialize views
    private void setUpViews(View rootView) {

        checkBoxP = (CheckBox) rootView.findViewById(R.id.checkBox1);
        checkBoxL = (CheckBox) rootView.findViewById(R.id.checkBox2);
        checkBoxM = (CheckBox) rootView.findViewById(R.id.checkBox3);
        checkBoxD = (CheckBox) rootView.findViewById(R.id.checkBox4);
        checkBoxC = (CheckBox) rootView.findViewById(R.id.checkBox5);

        checkBoxP.setOnClickListener(this);
        checkBoxL.setOnClickListener(this);
        checkBoxM.setOnClickListener(this);
        checkBoxD.setOnClickListener(this);
        checkBoxC.setOnClickListener(this);

        rootView.findViewById(R.id.btn_sent).setOnClickListener(this);
    }

    // checked storage
    private void setCheckedValue(String key, View view) {

        CheckBox checkBox = (CheckBox) view;
        if (checkBox.isChecked())
            Preferences.setCheckBoxPreference(getActivity(), key, true);
        else
            Preferences.setCheckBoxPreference(getActivity(), key, false);

    }

    // intialize preferences checkbox
    private void initializeCheckBoxPreference() {
        setCheckedFromPreference(checkBoxP, KEY_1);
        setCheckedFromPreference(checkBoxL, KEY_2);
        setCheckedFromPreference(checkBoxM, KEY_3);
        setCheckedFromPreference(checkBoxD, KEY_4);
        setCheckedFromPreference(checkBoxC, KEY_5);
    }

    private void setCheckedFromPreference(CheckBox checkBox, String key) {
        if (Preferences.getCheckBoxPreference(getActivity(), key))
            checkBox.setChecked(true);
        else
            checkBox.setChecked(false);

    }

    private void setHashMapStorage(String key, String value) {

        if (Preferences.getCheckBoxPreference(getActivity(), key))
            storageHashMap.put(key, value);
        else
            storageHashMap.remove(key);

    }

    private void setupHashMap() {
        setHashMapStorage(KEY_1, VALUE_P);
        setHashMapStorage(KEY_2, VALUE_L);
        setHashMapStorage(KEY_3, VALUE_M);
        setHashMapStorage(KEY_4, VALUE_D);
        setHashMapStorage(KEY_5, VALUE_C);
    }

    private String getSortedHashMapStorage() {
        String counter = "";
        SortedSet<String> keys = new TreeSet<>(storageHashMap.keySet());

        for (String valor : keys)
            counter += storageHashMap.get(valor);

        return counter;
    }


}



