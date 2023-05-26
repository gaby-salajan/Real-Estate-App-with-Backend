package com.gabys.frontend.view.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gabys.frontend.R;
import com.gabys.frontend.model.User;
import com.gabys.frontend.view.fragments.PropertyFragment;
import com.gabys.frontend.view.fragments.UserFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private PropertyCardAdapter propertyCardAdapter;
    private UserCardAdapter userCardAdapter;

    private Context context;

    private User loggedUser;

    public ViewPagerAdapter(FragmentManager fm, PropertyCardAdapter prop, UserCardAdapter user, User loggedUser, Context context) {
        super(fm);
        this.propertyCardAdapter = prop;
        this.userCardAdapter = user;
        this.context = context;
        this.loggedUser = loggedUser;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new PropertyFragment(propertyCardAdapter);
        }
        else if (position == 1)
        {
            fragment = new UserFragment(userCardAdapter);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = context.getString(R.string.properties);
        }
        else if (position == 1)
        {
            if(loggedUser.getRole() == 2)
                title = context.getString(R.string.users);
            else
                title = context.getString(R.string.clients);
        }
        return title;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void updateLang() {
        notifyDataSetChanged();
    }
}
