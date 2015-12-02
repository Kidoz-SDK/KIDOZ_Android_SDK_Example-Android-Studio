package com.kidoz.sdk.sample.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


import com.kidoz.sdk.api.FeedButton;
import com.kidoz.sdk.api.FeedView;
import com.kidoz.sdk.api.PanelView;
import com.kidoz.sdk.api.interfaces.IOnFeedViewEventListener;
import com.kidoz.sdk.api.interfaces.IOnPanelViewEventListener;
import com.kidoz.sdk.api.ui_views.panel_view.HANDLE_POSITION;
import com.kidoz.sdk.api.ui_views.panel_view.PANEL_TYPE;

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
     * Kidoz Family compliant Panel instance
     */
   // private FamilyPanelView mFamilyPanelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Set main view layout containing Kidoz Feed View Button */
        setContentView(R.layout.activity_sample);

        /** Get reference to Kidoz Feed View button */
        mFeedButton = (FeedButton) findViewById(R.id.kidozBtn_view);

        /** To add view events listeners do the following...
         *  Also FeedView object can be used to open and close the FeedView view manually
         * */
        FeedView feedView = mFeedButton.getFeedView();
        feedView.setOnFeedViewEventListener(new IOnFeedViewEventListener() {
            @Override
            public void onDismissView() {

                /**  View has been dismissed by user or action */
                Toast.makeText(SampleActivity.this, "Feed View is dismissed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReadyToShow() {

                /** Event is launched moment before the view is opened,
                 *  This allows the developer to stop some process currently running or make any additional actions...
                 * */
                Toast.makeText(SampleActivity.this, "Feed View is ready to be shown..", Toast.LENGTH_SHORT).show();
            }
        });


        /** Get reference to Kidoz Panel View */
        mPanelView = (PanelView) findViewById(R.id.kidozPanel_view);

        /** To add view events listeners do the following...
         * */
        mPanelView.setOnPanelViewEventListener(new IOnPanelViewEventListener() {
            @Override
            public void onPanelViewCollapsed() {

            }

            @Override
            public void onPanelViewExpanded() {

            }

            @Override
            public void onPanelReady() {
                /** Function invoked when the panel is finished initiation and have beed drawn */
            }
        });

        /** Set panel configuration to a different layout style */
        mPanelView.setPanelConfiguration(PANEL_TYPE.BOTTOM, HANDLE_POSITION.END);
    }
}
