[KIDOZ](http://www.kidoz.net) SDK
=================================

*Updated to KIDOZ SDK version 0.1.0* 

The `KIDOZ SDK` was built with a lot of thought and caring in mind for all parties – the kids who consume the content, their parents who wish to have peace in mind, the advertisers who wish to get more traffic and YOU, who wants to monetize your app.

The SDK will help you generate:
 - 	<b>Monetization</b> – For each promoted content click/impression, you get paid!
 - 	<b>Longer session time</b> – integrating the content in the right spot will result with kids spending more time in your app.
 - 	<b>Retention</b> – By enriching your app with kids' friendly content, both parents and kids are likely to come back more often.

##Inside your SDK
The SDK you are about to integrate contains great tools and capabilities to help you monetize your app.

###Content Tool: The Feed
[(https://raw.githubusercontent.com/2359media/EasyAndroidAnimations/master/demo.png)](http://www.youtube.com/watch?v=qb63BYjTebU)

#Integration
When integrating the SDK with your application, please make sure to use the latest SDK version, which can be downloaded from our [developers portal](http://www.kidoz.net).

###Include the library
On android studio you can include the library directly in your Gradle project:

 - 	Add the following to your app's `build.gradle`:
```gradle
repositories {
	    maven { url "https://dl.bintray.com/eugine/maven" }
}

dependencies {
	    compile 'com.kidoz.sdk:KidozSDK:0.1.0'
}
``` 

###Initiate the KIDOZ SDK
When initiating the SDK, please make sure to use your given `publisherID` and `securityToken`, which can be retrieve from your account on [developers portal](http://www.kidoz.net).

 - 	Inside your main activity onCreate add the following line:
```java
@Override protected void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	KidozSDK.initialize(getApplicationContext(), "publisherID", "securityToken");
	//the rest of your main activity onCreate
}
```

###Creating an instance of Interstitial View
 - 	Inside your desired activity or fragment create an instance of `InterstitialView` by adding the following lines:

> MainActivity.java

```java
public class MainActivity extends FragmentActivity
{
	InterstitialView mInterstitialView;
	
	private void initInterstitialView()
	{
		mInterstitialView = new InterstitialView.Builder(MainActivity.this, getSupportFragmentManager()).build();
	}
}
```	

You can add a listener if you want to be informed when the `InterstitialView` is dismissed and/or open by adding the following code:

```java
mInterstitialView.setOnInterstitialViewEventListener(new IOnInterstitialViewEventListener()
{
	@Override public void onDismissView()
	{
		// Will be called when the InterstitialView is closed
	}

	@Override public void onReadyToShow()
	{
		// Will be called when the InterstitialView is open
	}
});
```

###Launching the Interstitial View
 - 	Launching the Interstitial View is very simple. call the method showView() on the InterstitialView instance as describe bellow from anywhere you want, for example - a button's click listener:

```java
Button openInterstitialViewButton = findViewById(R.id.OpenInterstitialViewButton);
openInterstitialViewButton.setOnClickListener(new OnClickListener()
{
	mInterstitialView.showView();
}
```

#Demo
This Android application project provides an example of the [KIDOZ](http://www.kidoz.net) SDK integration.
It is compiled with Android 4.0 (API level 14) and supports any device running this Android version or higher.

The example application contains the following creative tools:
* Interstitial  FEED ads when clicking on KIDOZ button

Don't forget to use your developers id and application id when initializing the SDK.




</br>For any question or assistance, please contact us at support@kidoz.net.
