package git.hashibutogarasu.twitter.launcher;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import git.hashibutogarasu.twitter.launcher.databinding.ActivityMainBinding;

public class FixTwitterActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtwitter);

        Intent intent = getIntent();
        Uri data = intent.getData();

        String twurlscheme = "twitter://" + data.getPath() + "?" + data.getQuery();
        String twurl = "https://twitter.com" + data.getPath() + "?" + data.getQuery();

        try {
            // get the Twitter app if possible
            this.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twurlscheme));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twurl));
        }
        this.startActivity(intent);

        finishAndRemoveTask();
    }
}