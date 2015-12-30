package id.huang.yauhsien;

import java.lang.System;
import android.app.Activity;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.Menu;
import android.net.wifi.WifiManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;

public class HelloAndroidActivity extends Activity {

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(id.huang.yauhsien.R.menu.main, menu);
	return true;
    }

    @Override
    public void onResume() {

	boolean state = !toggleWifi();
	//changeShortcut(state);
	System.exit(0);
    }

    private boolean toggleWifi() {

	WifiManager wifiManager = (WifiManager)this.getSystemService(Context.WIFI_SERVICE);

	if (wifiManager.isWifiEnabled()) {
	    wifiManager.setWifiEnabled(false);
	    return false;
	}
	else {
	    wifiManager.setWifiEnabled(true);
	    return true;
	}
    }

    private void changeShortcut(boolean turnOn) {

	Intent intent = new Intent();

	Intent shortcutIntent = new Intent(getApplicationContext(),
					   HelloAndroidActivity.class);
	shortcutIntent.setAction(Intent.ACTION_MAIN);
	shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
	//shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	//shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

	Drawable drawable = this.getResources()
	    .getDrawable(turnOn? R.drawable.wifi_on: R.drawable.wifi_off);
	Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

	intent.putExtra("duplicate", false);
	intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
	intent.putExtra(Intent.EXTRA_SHORTCUT_NAME,
			(turnOn? "開網路": "關網路"));
	intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, bitmap);

	intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
	getApplicationContext().sendBroadcast(intent);
    }

}
