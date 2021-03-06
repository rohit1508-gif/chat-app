package com.example.fireapp.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fireapp.R;
import com.example.fireapp.tab1;
import com.example.fireapp.tab2;
import com.example.fireapp.tab3;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

  
    private String TAB_TITLES[] = new String[]{"Chats","Status","Contacts"};


//    public SectionsPagerAdapter(Context context, FragmentManager fm) {
//        super(fm);
//        mContext = context;
//    }

    public SectionsPagerAdapter(FragmentManager childFragmentManager) {
        super(childFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0 :
               tab1 tab1 = new tab1();
               return tab1;
           case 1:
               tab2 tab2 = new tab2();
               return tab2;
           case  2:
               tab3 tab3 = new tab3();
               return tab3;

       }
       return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}