package com.kidoz.sdk.sample.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.kidoz.sdk.api.FeedButton;
import com.kidoz.sdk.api.FeedView;
import com.kidoz.sdk.api.KidozBanner;
import com.kidoz.sdk.api.PanelView;
import com.kidoz.sdk.api.interfaces.IOnFeedViewEventListener;
import com.kidoz.sdk.api.interfaces.IOnPanelViewEventListener;
import com.kidoz.sdk.api.ui_views.kidoz_banner.KidozBannerListener;

/**
 * Created by KIDOZ.
 */
public class SampleActivity extends Activity {

    /**
     * Kidoz Button instance
     */
    private FeedButton mFeedButton;

    /**
     * Kidoz Panel instance
     */
    private PanelView mPanelView;

    /**
     * Kidoz Panel instance
     */
    private KidozBanner mKidozBanner;

    /**
     * Kidoz Family compliant Panel instance
     */
   // private FamilyPanelView mFamilyPanelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Set main view layout containing*/
        setContentView(R.layout.activity_sample);


        /** Initiate Button view */
        initFeedButton();

        /** Initiate Panel view */
        initFeedPanel();

        /** Initiate Banner view */
        initBannerView();
    }



    /** Initiate Button view */
    private void initFeedButton() {
        /** Get reference to KIDOZ Feed View button */
        mFeedButton = (FeedButton) findViewById(R.id.kidozBtn_view);

        /**
         * To add view events listeners do the following... Also FeedView object
         * can be used to open and close the FeedView view manually
         * */
        FeedView feedView = mFeedButton.getFeedView();
        feedView.setOnFeedViewEventListener(new IOnFeedViewEventListener() {
            @Override
            public void onDismissView() {

                /** View has been dismissed by user or action */
                Toast.makeText(SampleActivity.this, "Feed View is dismissed",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReadyToShow() {

                /**
                 * Event is launched moment before the view is opened, This
                 * allows the developer to stop some process currently running
                 * or make any additional actions...
                 * */
                Toast.makeText(SampleActivity.this,
                        "Feed View is ready to be shown..", Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onViewReady() {
                // TODO Auto-generated method stub
                Toast.makeText(SampleActivity.this, "Feed View Ready",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /** Initiate Panel view */
    private void initFeedPanel() {
        /** Get reference to KIDOZ Panel View */
        mPanelView = (PanelView) findViewById(R.id.kidozPanel_view);

        /**
         * To add view events listeners do the following...
         * */
        mPanelView.setOnPanelViewEventListener(new IOnPanelViewEventListener() {
            @Override
            public void onPanelViewCollapsed() {
                /** Panel View Collapsed by user or action */
                Toast.makeText(SampleActivity.this, "PanelView Collapsed",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPanelViewExpanded() {
                /** Panel View Expanded by user or action */
                Toast.makeText(SampleActivity.this, "PanelView Expanded",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPanelReady() {
                /** Panel View Ready action */
                Toast.makeText(SampleActivity.this, "PanelView View Ready",
                        Toast.LENGTH_SHORT).show();
            }
        });

        /** Set panel configuration to a different layout style */
        // mPanelView.setPanelConfiguration(PANEL_TYPE.RIGHT,HANDLE_POSITION.START);
    }

    /** Initiate Banner view */
    private void initBannerView() {
        mKidozBanner = (KidozBanner) findViewById(R.id.kidozBanner_view);
        mKidozBanner.setKidozBannerListener(new KidozBannerListener() {
            @Override
            public void onBannerReady() {
                super.onBannerReady();
                mKidozBanner.showBanner();
            }
        });
    }
}
