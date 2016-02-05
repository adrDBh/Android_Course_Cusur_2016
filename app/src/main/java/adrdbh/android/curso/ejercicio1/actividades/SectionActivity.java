package adrdbh.android.curso.ejercicio1.actividades;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import adrdbh.android.curso.ejercicio1.R;
import adrdbh.android.curso.ejercicio1.adapters.ViewPagerAdapter;
import adrdbh.android.curso.ejercicio1.utils.SlidingTabLayout;

public class SectionActivity extends AppCompatActivity {

    //  View pager adapter variable private variable impelemtation/helper
    private ViewPagerAdapter adapter;
    // work with fragments to handle page sliding
    private ViewPager pager;
    //
    private SlidingTabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        // Calls support manager (not sure how this work yet though) and initializes variable to its value
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        // attaches "pager" activity_section xml layout
        pager = (ViewPager) findViewById(R.id.pager);
        // tabs desing from "tabs" id from activity_section
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        // Not sure how this works either needs research to be made...
        pager.setAdapter(adapter);

        // Insignificant change to line code, to test commits in github...

        // Asigns color to tab sliding layout
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                // Can apply certain colors made by the classes
                // or can add custom one by adding color label to res/values/colors
                return getResources().getColor(R.color.micolor);
            }

            @Override
            public int getDividerColor(int position) {
                return 0;
            }
        });
        // ----------------------- !
        tabs.setViewPager(pager);
    }
}
