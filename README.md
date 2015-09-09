[KIDOZ][] SDK Example Project
======================================

*Updated to KIDOZ SDK version 0.1.0* 

`KIDOZ SDK` is a bla bla bla...

##Integration
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
 - 	Launching the Interstitial View is very simple. call the method bellow from anywhere you want, for example - a button's click listener:

```java
Button openInterstitialViewButton = findViewById(R.id.OpenInterstitialViewButton);
openInterstitialViewButton.setOnClickListener(new OnClickListener()
{
	mInterstitialView.showView();
}
```


The example application contains the following creative tools:
* Interstitial  FEED ads when clicking on KIDOZ button

Don't forget to use your developers id and application id when initializing the SDK.


For any question or assistance, please contact us at support@kidoz.net.

[KIDOZ]: http://www.kidoz.net


