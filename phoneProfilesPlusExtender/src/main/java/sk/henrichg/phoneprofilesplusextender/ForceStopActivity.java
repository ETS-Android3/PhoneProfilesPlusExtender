package sk.henrichg.phoneprofilesplusextender;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ForceStopActivity extends AppCompatActivity {
    static ForceStopActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        overridePendingTransition(0, 0);
    }

    @Override
    protected  void onStart() {
        super.onStart();
        instance = this;
        //lockScreenOrientation();
    }

    /*
    @Override
    protected void onStop() {
        super.onStop();
        unlockScreenOrientation();
    }
    */

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // do finish all App info activities started with startActivityForResult(intent, 100);
        boolean canFinish = true;
        if (Build.VERSION.SDK_INT >= 30) {
            if (PPPEApplication.deviceIsXiaomi) {
                // IN XIAOMI DEVICES THIS NOT WORKING !!! WHY ???
                canFinish = false;
            }
        }
        if (canFinish)
            finishActivity(100);

        instance = null;
//        Log.e("ForceStopActivity.onDestroy","xxx");
    }

    @Override
    public void finish()
    {
        super.finish();
        overridePendingTransition(0, 0);
    }

    /*
    void lockScreenOrientation() {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        }
    }

    void unlockScreenOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
    }
    */

}
