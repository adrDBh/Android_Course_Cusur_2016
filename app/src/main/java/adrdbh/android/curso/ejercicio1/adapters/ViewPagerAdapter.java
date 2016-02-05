package adrdbh.android.curso.ejercicio1.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import adrdbh.android.curso.ejercicio1.fragments.CameraFragment;
import adrdbh.android.curso.ejercicio1.fragments.ThreadFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    // Charseq array that contains caregory names in the sliding layout...
    private CharSequence mTiles[] = {"Camara", "Hilos"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    // implements sliding from fragments
    public Fragment getItem(int position) {
        if (position == 0) {
            // default value as the new activity opens..
            return CameraFragment.newInstance();
        } else {
            // or if you slide you get the second tab...
            return ThreadFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        // Length of categories displayed in the layout..
        // Not sure if it can also be applied as a array length
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //return slides tiles position
        return mTiles[position];
    }
}
