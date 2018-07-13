package co.gradeup.infinixindicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

public class InfinixAdapter extends FragmentPagerAdapter {

    public InfinixAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 14;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();

        ColorFragment colorFragment = new ColorFragment();

        bundle.putInt("index", position);

        colorFragment.setArguments(bundle);
        return colorFragment;
    }
}
