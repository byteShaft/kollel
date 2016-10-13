package com.byteshaft.kollel;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.byteshaft.kollel.fragments.CalendarFragment;
import com.byteshaft.kollel.fragments.DocumentOneFragment;
import com.byteshaft.kollel.fragments.DocumentTwoFragment;
import com.byteshaft.kollel.fragments.TweetOneFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TwitterAuthConfig authConfig = new TwitterAuthConfig("16eabEYM1yyerYbmqnm9OSbJK",
                "8oCNlQD221N9gPUGFpADF4WUD6eUBqSNegxMzOKOblG0kDjghn");
        Fabric.with(this, new Twitter(authConfig));

        Bundle bundle = getIntent().getExtras();
        String value = bundle.getString("key");

        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                if (tabId == R.id.tab_calender) {
                    loadFragment(new CalendarFragment());

                } else if (tabId == R.id.tab_document) {
                    loadFragment(new DocumentOneFragment());

                } else if (tabId == R.id.tab_tweets_one) {
                    loadFragment(new TweetOneFragment());

                } else if (tabId == R.id.tab_document_two) {
                    loadFragment(new DocumentTwoFragment());
                }
            }
        });

        assert value != null;
        switch (value) {
            case "cal":
                mBottomBar.setDefaultTabPosition(0);
                break;
            case "shi":
                mBottomBar.setDefaultTabPosition(1);
                break;
            case "update":
                mBottomBar.setDefaultTabPosition(2);
                break;
            case "contact":
                mBottomBar.setDefaultTabPosition(3);
                break;
            case "ads":
                mBottomBar.setDefaultTabPosition(4);
                break;
        }
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.container, fragment);
        tx.commit();
    }
}
