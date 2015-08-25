package net.telesurtv.www.telesur.views.program;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.telesurtv.www.telesur.R;
import net.telesurtv.www.telesur.model.ProgramItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhordan on 19/08/15.
 */


public class ProgramSpinnerAdapter extends BaseAdapter {
    private List<ProgramItem> mItems;
    private Context context;


    public ProgramSpinnerAdapter(Context context) {
        this.context = context;
        mItems = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("DROPDOWN")) {
            view = LayoutInflater.from(context).inflate(R.layout.toolbar_spinner_item_dropdown, parent, false);
            view.setTag("DROPDOWN");
        }

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));

        if (Build.VERSION.SDK_INT < 21)
            textView.setBackgroundColor(context.getResources().getColor(R.color.white));

        return view;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("NON_DROPDOWN")) {
            view = LayoutInflater.from(context).inflate(R.layout.toolbar_spinner_item_actionbar, parent, false);
            view.setTag("NON_DROPDOWN");
        }
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));




        return view;
    }

    private String getTitle(int position) {
        return position >= 0 && position < mItems.size() ? mItems.get(position).getName() : "";
    }


    public void clear() {
        mItems.clear();
    }

    public void addItem(ProgramItem yourObject) {
        mItems.add(yourObject);
    }

    public void addItems(List<ProgramItem> yourObjectList) {
        mItems.addAll(yourObjectList);
    }
}
