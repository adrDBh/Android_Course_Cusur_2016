package adrdbh.android.curso.ejercicio1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import adrdbh.android.curso.ejercicio1.R;

/**
 * Created by adrdbh on 2/5/16.
 */
public class CardViewFragment extends Fragment {
    public static CustomListViewFragment newInstance() {
        CustomListViewFragment customListViewFragment = new CustomListViewFragment();

        return customListViewFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_listview_fragment, container, false);
        return null;
    }
}
