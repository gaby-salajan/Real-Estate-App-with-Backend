package com.gabys.frontend.model;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Observable;

public class AppLanguage extends Observable {
    private final ArrayList<String> types = new ArrayList<>(Arrays.asList("EN", "RO", "FR"));
    private String currentLang;

    private int selection = 0;

    private Resources res;
    private DisplayMetrics displayMetrics;
    private Configuration conf;

    public AppLanguage(Context context){
        res = context.getResources();
        displayMetrics = res.getDisplayMetrics();
        conf = res.getConfiguration();
    }

    public void changeLang(String lang){
        currentLang = lang;
        selection = types.indexOf(lang);

        conf.locale = new Locale(currentLang);
        res.updateConfiguration(conf, displayMetrics);

        setChanged();
        notifyObservers();
    }

    public String getLang() {
        return currentLang;
    }

    public int getIndex(){
        return selection;
    }

    public Configuration getConf(){
        return conf;
    }
}
