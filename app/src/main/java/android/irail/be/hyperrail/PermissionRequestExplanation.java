/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package android.irail.be.hyperrail;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * An activity to explain a user why we're asking for a permission.
 */
public class PermissionRequestExplanation extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 0;
    private String preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_request_explanation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        preference = getIntent().getStringExtra("preference");
        ((TextView) findViewById(R.id.text_subtitle)).setText(getIntent().getStringExtra("title"));
        ((ImageView) findViewById(R.id.icon)).setImageDrawable(getResources().getDrawable(getIntent().getIntExtra("icon", R.drawable.ic_location_on_48)));
        ((TextView) findViewById(R.id.text_description)).setText(getIntent().getStringExtra("description"));
        findViewById(R.id.button_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askPermission();
            }
        });
        setContentView(R.layout.activity_permission_request_explanation);
    }

    /**
     * Ask for the permission
     */
    public void askPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_LOCATION);
    }

    /**
     * Either we get permission, or we disable the functionality using a setting, after which this activity is finished
     *
     * @inheritDoc
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Hurray, we're allowed to do this!
                Log.d("PermissionRequest","Got permission!");
            } else {
                // Disable by setting preference to false
                PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(preference, false).apply();
            }
            finish();
        }
    }
}
