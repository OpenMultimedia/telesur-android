package net.telesurtv.www.telesur.views.videos;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.telesurtv.www.telesur.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhordan on 15/07/15.
 */
public class FragmentAdapterVideo extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();
    private final List<Integer> fragmentIcons = new ArrayList<>();
    private Context context;

    public FragmentAdapterVideo(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }

    public void addFragment(Fragment fragment, String title, int icon) {
        fragmentList.add(fragment);
        fragmentTitles.add(title);
        fragmentIcons.add(icon);
    }


    public View getIconCustomTab(int position) {

        View tab = LayoutInflater.from(context).inflate(R.layout.item_custom_tab, null);
        ImageView tabImage = (ImageView) tab.findViewById(R.id.image_view_tab_icon);
        TextView tabText = (TextView) tab.findViewById(R.id.txt_tab_text);
        tabText.setText(fragmentTitles.get(position));
        tabImage.setBackgroundResource(fragmentIcons.get(position));

        if (position == 0)
            tab.setSelected(true);

        return tab;
    }


}
