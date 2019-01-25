package com.nowfal.kdroidext.pref;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Map;
import java.util.Set;

/**
 * Created by Viral Patel on 01-Feb-16.
 */
public class KPrefHelper {

    private final SharedPreferences settings;

    //region Default Values for various types
    private int intDefaultVal = 0;
    private long longDefaultVal = 0;
    private float floatDefaultVal = 0;
    private boolean boolDefaultVal = false;
    private String stringDefaultVal = "";
    private Set<String> stringSetDefaultVal = null;
    //endregion

    //region Getters and Setters for default Values
    public int getIntDefaultVal() {
        return intDefaultVal;
    }

    public void setIntDefaultVal(int intDefaultVal) {
        this.intDefaultVal = intDefaultVal;
    }

    public long getLongDefaultVal() {
        return longDefaultVal;
    }

    public void setLongDefaultVal(long longDefaultVal) {
        this.longDefaultVal = longDefaultVal;
    }

    public float getFloatDefaultVal() {
        return floatDefaultVal;
    }

    public void setFloatDefaultVal(float floatDefaultVal) {
        this.floatDefaultVal = floatDefaultVal;
    }

    public boolean isBoolDefaultVal() {
        return boolDefaultVal;
    }

    public void setBoolDefaultVal(boolean boolDefaultVal) {
        this.boolDefaultVal = boolDefaultVal;
    }

    public String getStringDefaultVal() {
        return stringDefaultVal;
    }

    public void setStringDefaultVal(String stringDefaultVal) {
        this.stringDefaultVal = stringDefaultVal;
    }

    public Set<String> getStringSetDefaultVal() {
        return stringSetDefaultVal;
    }

    public void setStringSetDefaultVal(Set<String> stringSetDefaultVal) {
        this.stringSetDefaultVal = stringSetDefaultVal;
    }
    //endregion

    //region Constructors
    /**
     * Uses the default shared preferences
     */
    public KPrefHelper(Context context) {
        settings = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * creates a new preference file with specified name
     * @param context
     * @param preferenceFileName
     */
    public KPrefHelper(Context context, String preferenceFileName) {
        this(context, preferenceFileName, 0);
    }

    /**
     * creates a new preference file with specified name and mode
     * @param context
     * @param preferenceFileName
     * @param mode
     */
    public KPrefHelper(Context context, String preferenceFileName, int mode) {
        settings = context.getSharedPreferences(preferenceFileName, mode);
    }
    //endregion

    //#TODO Builder Pattern here can be helpful in making the use of this much elegant
    //region Put Methods
    public void putInt(String key, int val) {
        settings.edit().putInt(key, val).apply();
    }

    public void putString(String key, String val) {
        settings.edit().putString(key, val).apply();
    }

    public void putBoolean(String key, boolean val) {
        settings.edit().putBoolean(key, val).apply();
    }

    public void putFloat(String key, float val) {
        settings.edit().putFloat(key, val).apply();
    }

    public void putLong(String key, long val) {
        settings.edit().putLong(key, val).apply();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void putStringSet(String key, Set<String> val) {
        settings.edit().putStringSet(key, val).commit();
    }
    //endregion

    //region Get Methods
    public Map<String, ?> getAll() {
        return settings.getAll();
    }

    public int getInt(String key, int defaultValue) {
        return settings.getInt(key, defaultValue);
    }

    public int getInt(String key) {
        return settings.getInt(key, intDefaultVal);
    }

    public String getString(String key, String defaultValue) {
        return settings.getString(key, defaultValue);
    }

    public String getString(String key) {
        return settings.getString(key, stringDefaultVal);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return settings.getBoolean(key, defaultValue);
    }

    public boolean getBoolean(String key) {
        return settings.getBoolean(key, boolDefaultVal);
    }

    public float getFloat(String key, float defaultValue) {
        return settings.getFloat(key, defaultValue);
    }

    public float getFloat(String key) {
        return settings.getFloat(key, floatDefaultVal);
    }

    public long getLong(String key, long defaultValue) {
        return settings.getLong(key, defaultValue);
    }

    public long getLong(String key) {
        return settings.getLong(key, longDefaultVal);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return settings.getStringSet(key, defaultValue);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String key) {
        return settings.getStringSet(key, stringSetDefaultVal);
    }
    //endregion

    //region Listener registering and unregistering methods
    public void registerListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        settings.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        settings.unregisterOnSharedPreferenceChangeListener(listener);
    }
    //endregion
}