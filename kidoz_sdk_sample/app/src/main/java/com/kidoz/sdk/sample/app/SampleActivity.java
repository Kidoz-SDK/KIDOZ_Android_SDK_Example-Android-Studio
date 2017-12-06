package com.kidoz.sdk.sample.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kidoz.sdk.api.FlexiView;
import com.kidoz.sdk.api.KidozInterstitial;
import com.kidoz.sdk.api.KidozSDK;
import com.kidoz.sdk.api.PanelView;
import com.kidoz.sdk.api.interfaces.FlexiViewListener;
import com.kidoz.sdk.api.interfaces.IOnPanelViewEventListener;
import com.kidoz.sdk.api.interfaces.SDKEventListener;
import com.kidoz.sdk.api.ui_views.flexi_view.FLEXI_POSITION;
import com.kidoz.sdk.api.ui_views.interstitial.BaseInterstitial;
import com.kidoz.sdk.api.ui_views.kidoz_banner.KidozBannerListener;
import com.kidoz.sdk.api.ui_views.new_kidoz_banner.BANNER_POSITION;
import com.kidoz.sdk.api.ui_views.new_kidoz_banner.KidozBannerView;

/**
 * Created by KIDOZ.
 */
public class SampleActivity extends Activity
{


    /**
     * Kidoz Panel instance
     */
    private PanelView mPanelView;

    /**
     * Flexi View instance
     */
    private FlexiView mFlexiView;

    /**
     * Kidoz interstitial instance
     */
    private KidozInterstitial mKidozInterstitial;

    /**
     * Kidoz rewarded instance
     */
    private KidozInterstitial mKidozRewarded;

    /**
     * Kidoz banner instance
     */
    private KidozBannerView mKidozBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /** Set main view layout containing Kidoz Feed View Button */
        setContentView(R.layout.activity_sample);

        /**
         * Initiate kidoz sdk with valid publisher id and security token
         */
        initKidozSDK();

        /** Initiate Panel view */
        initFeedPanel();

        /** Flexi Point view */
        initFlexiView();

        /** Interstitial Sample */
        initInterstitial();

        /** Rewarded Sample */
        initRewarded();

        /** Banner Sample */
        initBanner();


    }

    private void initKidozSDK()
    {
        KidozSDK.setSDKListener(new SDKEventListener()
        {
            @Override
            public void onInitSuccess()
            {
                //SDK Init | Success().
            }

            @Override
            public void onInitError(String error)
            {
                //SDK Init | Error
            }
        });

        KidozSDK.initialize(this, "5", "i0tnrdwdtq0dm36cqcpg6uyuwupkj76s");
    }



    /**
     * Initiate Panel view
     */
    private void initFeedPanel()
    {
        /** Get reference to KIDOZ Panel View */
        mPanelView = (PanelView) findViewById(R.id.kidozPanel_view);
        //mPanelView.setPanelConfiguration(PANEL_TYPE.TOP, HANDLE_POSITION.START);

        /**
         * To add view events listeners do the following...
         * */
        mPanelView.setOnPanelViewEventListener(new IOnPanelViewEventListener()
        {
            @Override
            public void onPanelViewCollapsed()
            {
                /** Panel View Collapsed by user or action */
                Toast.makeText(SampleActivity.this, "PanelView Collapsed",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPanelViewExpanded()
            {
                /** Panel View Expanded by user or action */
                Toast.makeText(SampleActivity.this, "PanelView Expanded",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPanelReady()
            {
                /** Panel View Ready action */
                Toast.makeText(SampleActivity.this, "PanelView View Ready",
                        Toast.LENGTH_SHORT).show();
            }
        });

        /** Set panel configuration to a different layout style */

    }

    /**
     * Initiate Flexi Point view
     */
    private void initFlexiView()
    {
        mFlexiView = (FlexiView) findViewById(R.id.kidozFlexi_view);
        mFlexiView.setAutoShow(true);
        mFlexiView.setFlexiViewInitialPosition(FLEXI_POSITION.TOP_START);
        mFlexiView.setOnFlexiViewEventListener(new FlexiViewListener()
        {
            @Override
            public void onViewReady()
            {
                super.onViewReady();
                Toast.makeText(SampleActivity.this, "Flexi Ready",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onViewHidden()
            {
                super.onViewHidden();
                Toast.makeText(SampleActivity.this, "Flexi Hidden",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onViewVisible()
            {
                super.onViewVisible();
                Toast.makeText(SampleActivity.this, "Flexi Visible",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Initiate Interstitial
     */
    private void initInterstitial()
    {
        mKidozInterstitial = new KidozInterstitial(this, KidozInterstitial.AD_TYPE.INTERSTITIAL);

        mKidozInterstitial.setOnInterstitialEventListener(new BaseInterstitial.IOnInterstitialEventListener()
        {
            @Override
            public void onClosed()
            {
                Toast.makeText(SampleActivity.this, "Interstitial Closed",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOpened()
            {
                Toast.makeText(SampleActivity.this, "Interstitial Opened",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReady()
            {
                mKidozInterstitial.show();
            }

            @Override
            public void onLoadFailed()
            {
                Toast.makeText(SampleActivity.this, "Interstitial Failed to load",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNoOffers()
            {
                Toast.makeText(SampleActivity.this, "Interstitial No Offers",
                        Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.interstitialBtn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mKidozInterstitial.isLoaded() == false)
                {
                    mKidozInterstitial.loadAd();

                    Toast.makeText(SampleActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
                } else
                {
                    mKidozInterstitial.show();
                }
            }
        });
    }

    /**
     * Initiate Rewarded
     */
    private void initRewarded()
    {
        mKidozRewarded = new KidozInterstitial(this, KidozInterstitial.AD_TYPE.REWARDED_VIDEO);

        mKidozRewarded.setOnInterstitialEventListener(new BaseInterstitial.IOnInterstitialEventListener()
        {
            @Override
            public void onClosed()
            {
                Toast.makeText(SampleActivity.this, "Rewarded Closed",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOpened()
            {
                Toast.makeText(SampleActivity.this, "Rewarded Opened",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReady()
            {
                mKidozInterstitial.show();
            }

            @Override
            public void onLoadFailed()
            {
                Toast.makeText(SampleActivity.this, "Rewarded Failed to load",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNoOffers()
            {
                Toast.makeText(SampleActivity.this, "Rewarded No Offers",
                        Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * Events that invoked for Rewarded  Video Interstitial
         */
        mKidozRewarded.setOnInterstitialRewardedEventListener(new BaseInterstitial.IOnInterstitialRewardedEventListener()
        {
            @Override
            public void onRewardReceived()
            {
                Toast.makeText(SampleActivity.this, "Reward Received",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedStarted()
            {
                Toast.makeText(SampleActivity.this, "Rewarded Video Started",
                        Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.rewardedBtn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mKidozRewarded.isLoaded() == false)
                {
                    mKidozRewarded.loadAd();

                    Toast.makeText(SampleActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
                } else
                {
                    mKidozRewarded.show();
                }
            }
        });
    }

    /**
     * Banner Rewarded
     */
    private void initBanner()
    {
        mKidozBannerView = KidozSDK.getKidozBanner(this);
        mKidozBannerView.setBannerPosition(BANNER_POSITION.TOP);

        mKidozBannerView.setKidozBannerListener(new KidozBannerListener()
        {
            @Override
            public void onBannerViewAdded()
            {
                Log.d("sample", "onBannerViewAdded()");
            }

            @Override
            public void onBannerReady()
            {
                Log.d("sample", "onBannerReady()");
                mKidozBannerView.show();
            }

            @Override
            public void onBannerError(String errorMsg)
            {
                Log.d("sample", "onBannerError(" + errorMsg + ")");
            }

            @Override
            public void onBannerClose()
            {
                Log.d("sample", "onBannerClose()");
            }
        });

        findViewById(R.id.bannerBtn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mKidozBannerView.load();
            }
        });
    }
}
