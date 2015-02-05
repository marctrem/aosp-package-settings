/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.*;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Index;
import com.android.settings.search.Indexable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarcsSettings extends SettingsPreferenceFragment
                           implements Indexable {

    private static final String LOG_TAG = "MarcsSettings";

    private static final String KEY_DARE_SWITCH = "dare_switch";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.marcs_settings);
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {

        if(preference.getKey().equals(KEY_DARE_SWITCH)) {
            HelloDialogFragment hdf = new HelloDialogFragment();
            hdf.show(getFragmentManager(), LOG_TAG);
            return false;
        }

        Log.i(LOG_TAG, "ClickClick");

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    /**
     * For Search.
     */
    public static final SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
        new BaseSearchIndexProvider() {

            @Override
            public List<SearchIndexableResource> getXmlResourcesToIndex(
                    Context context, boolean enabled) {
                final SearchIndexableResource sir = new SearchIndexableResource(context);
                sir.xmlResId = R.xml.marcs_settings;
                return Arrays.asList(sir);
            }

//            @Override
//            public List<String> getNonIndexableKeys(Context context) {
//                final List<String> keys = new ArrayList<String>();
//                return keys;
//            }


//            private boolean checkIntentAction(Context context, String action) {
//                final Intent intent = new Intent(action);
//
//                // Find the activity that is in the system image
//                final PackageManager pm = context.getPackageManager();
//                final List<ResolveInfo> list = pm.queryIntentActivities(intent, 0);
//                final int listSize = list.size();
//
//                for (int i = 0; i < listSize; i++) {
//                    ResolveInfo resolveInfo = list.get(i);
//                    if ((resolveInfo.activityInfo.applicationInfo.flags &
//                            ApplicationInfo.FLAG_SYSTEM) != 0) {
//                        return true;
//                    }
//                }
//
//                return false;
//            }
        };


    public class HelloDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Maple Syrup")
                    .setMessage(R.string.marcs_settings_message)
                    .setPositiveButton(R.string.dlg_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Switch the dare switch back to off.
                            ((SwitchPreference) findPreference(KEY_DARE_SWITCH)).setChecked(false);
                        }
                    });

            return builder.create();
        }
    }
}

