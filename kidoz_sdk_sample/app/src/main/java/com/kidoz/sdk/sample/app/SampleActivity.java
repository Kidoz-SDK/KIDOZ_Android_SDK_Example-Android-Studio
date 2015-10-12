package com.kidoz.sdk.sample.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.kidoz.sdk.api.FeedButton;
import com.kidoz.sdk.api.FeedView;
import com.kidoz.sdk.api.KidozSDK;
import com.kidoz.sdk.api.PanelView;
import com.kidoz.sdk.api.interfaces.IOnFeedViewEventListener;
import com.kidoz.sdk.api.interfaces.IOnPanelViewEventListener;
import com.kidoz.sdk.api.ui_views.panel_view.HANDLE_POSITION;
import com.kidoz.sdk.api.ui_views.panel_view.PANEL_TYPE;


public class SampleActivity extends Activity
{

    /**
     * Kidoz Button instance
     */
    private FeedButton mFeedButton;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /** Initialize Kidoz SDK with correct publisher id and token (Publisher Id and Token provided by registering a new account via http://kidoz.net/
         * */
        KidozSDK.initialize(SampleActivity.this, "1", "Nhm59VTJVYnZNJYnbCKNoU27mp7CvRg6");

        /** Set main view layout containing Kidoz Feed View Button */
        setContentView(R.layout.activity_sample);

        /** Feed View example */
        initFeedView();

        /** Panel View example */
        initPanelView();
    }

    private void initFeedView()
    {
        /** Get reference to Kidoz Feed View button */
        mFeedButton = (FeedButton) findViewById(R.id.kidozBtn_view);

        /** To add view events listeners do the following...
         *  Also FeedView object can be used to open and close the FeedView view manually
         * */
        FeedView feedView = mFeedButton.getFeedView();
        feedView.setOnFeedViewEventListener(new IOnFeedViewEventListener()
        {
            @Override public void onDismissView()
            {
                /**  View has been dismissed by user or action */
                Toast.makeText(SampleActivity.this, "Feed View is dismissed", Toast.LENGTH_SHORT).show();
            }

            @Override public void onReadyToShow()
            {
                /** Event is launched moment before the view is opened,
                 *  This allows the developer to stop some process currently running or make any additional actions...
                 * */
                Toast.makeText(SampleActivity.this, "Feed View is ready to be shown..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initPanelView()
    {
        /** Get reference to Kidoz Panel View */
        PanelView panelView = (PanelView) findViewById(R.id.PanelView);
        panelView.setPanelConfiguration(PANEL_TYPE.LEFT, HANDLE_POSITION.CENTER);

        /** To add Panel View events listeners do the following... */
        panelView.setOnPanelViewEventListener(new IOnPanelViewEventListener()
        {
            @Override public void onPanelViewCollapsed()
            {
                /**  Panel View has been collapsed by user or action */
                Toast.makeText(SampleActivity.this, "Panel View is collapsed", Toast.LENGTH_SHORT).show();
            }

            @Override public void onPanelViewExpanded()
            {
                /**  Panel View has been expanded by user or action */
                Toast.makeText(SampleActivity.this, "Panel View is expanded", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
