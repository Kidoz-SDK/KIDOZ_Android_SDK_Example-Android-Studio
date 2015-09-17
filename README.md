<a href="url"><img src="https://github.com/Kidoz-SDK/Kidoz_Android_SDK_Example/blob/master/graphics/App%20icon.png" align="left" height="72" width="72" ></a>

</br>
KIDOZ SDK Sample App
=================================

*Updated to KIDOZ SDK version 0.1.2* 

This Android application project provides an example of the [KIDOZ](http://www.kidoz.net) SDK integration.
It is compiled with Android 4.0 (API level 14) and supports any device running this Android version or higher.

The example application contains the following creative tools:
* Interstitial content tool - the `Feed View`
* KIDOZ's default button - the `KIDOZ Button`

###Running the sample app
1. Download the project (download button located on the right) and unzip the downloaded .zip file
2. Launch `Android Studio`, click `File` --> `Open`, navigate to `kidoz_sdk_sample` project and click `OK`
3. Once the project finished syncing click the `Run` button

KIDOZ SDK - Getting Started
=================================
 - 	Read the full KIDOZ SDK documentation and `Best Practices` on [KIDOZ SDK](http://kidoz.net/marketing/newsletter/sdk/SDK.pdf) site

###Integration
####Include the library
On android studio you can include the library directly in your Gradle project:

 - 	Add the following to your app's `build.gradle`:
```gradle
dependencies {
	    compile 'com.kidoz.sdk:KidozSDK:0.1.2'
}
``` 

###Initializing the KIDOZ SDK
When initializing the SDK, please make sure to use your given `publisherID` and `securityToken`, which can be retrieve from your account on [developers portal](http://www.kidoz.net).

 - 	Inside your main activity onCreate add the following line:
```java
@Override 
protected void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	KidozSDK.initialize(getApplicationContext(), "publisherID", "securityToken");
	//the rest of your main activity onCreate
}
```
###Integrating the KIDOZ Button
You can add the ```KIDOZ Button``` to your layout xml file or create a new instance programmatically.

 - 	Add ```KIDOZ Button``` directly inside xml:
 
 ```xml
	<com.kidoz.sdk.api.KidozButtonView
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:id="@+id/kidozButton">
	</com.kidoz.sdk.api.KidozButtonView>
```

 - 	Add ```KIDOZ Button``` programmatically:

```java
//Inflate your root view for example a simple RelativeLayout
RelativeLayout rootView = findViewById(R.id.Your_RelativeLayout_ID);
RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
KidozButtonView kidozBtn = new KidozButtonView(context);
rootView.addView(kidozBtn, params);
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

#Launching the Feed Interstitial View
The ```Interstitial View``` can be launched by clicking on a Kidoz Button View or any other view with an onClick listener or some other way depending on your own application logics. For example when a game is stopped or anywhere else inside your app as long as your target class have a reference to the InterstitialView instance.
</br>
Simply launch the ```Interstitial View``` by calling the method showView() on the InterstitialView instance.
```java
Button openInterstitialViewButton = findViewById(R.id.OpenInterstitialViewButton);
openInterstitialViewButton.setOnClickListener(new OnClickListener()
{
	mInterstitialView.showView();
}
```







</br>For any question or assistance, please contact us at support@kidoz.net.

</br>
License
--------

    Copyright 2015 KIDOZ, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


