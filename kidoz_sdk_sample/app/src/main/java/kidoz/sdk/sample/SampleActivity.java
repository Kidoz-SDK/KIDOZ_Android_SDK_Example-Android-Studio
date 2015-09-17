package kidoz.sdk.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.kidoz.sdk.api.InterstitialView;
import com.kidoz.sdk.api.KidozButtonView;
import com.kidoz.sdk.api.KidozSDK;
import com.kidoz.sdk.api.interfaces.IOnInterstitialViewEventListener;


public class SampleActivity extends FragmentActivity {

    /** Kidoz button instance */
    private KidozButtonView mKidozButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Initialize Kidoz SDK with correct publisher id and token (Publisher Id and Token provided by registering a new account via http://kidoz.net/
         * */
        KidozSDK.initialize(SampleActivity.this, "5", "i0tnrdwdtq0dm36cqcpg6uyuwupkj76s");

        /** Set main view layout containing KidozButtonView */
        setContentView(R.layout.activity_sample);

        /** Get reference to KidozButtonView */
        mKidozButtonView = (KidozButtonView) findViewById(R.id.kidozBtn_view);

        /** To add view events listeners do the following...
         *  Also InterstitialView object can be used to open and close the interstitial view manually
         * */
        InterstitialView interstitialView = mKidozButtonView.getInterstitialView();
        interstitialView.setOnInterstitialViewEventListener(new IOnInterstitialViewEventListener() {
            @Override
            public void onDismissView() {

                /**  View has been dismissed by user or action */
                Toast.makeText(SampleActivity.this,"InterstitialView is dismissed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReadyToShow() {

                /** Event is launched moment before the view is opened,
                 *  This allows the developer to stop some process currently running or make any additional actions...
                 * */
                Toast.makeText(SampleActivity.this,"InterstitialView is about to be opened",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
