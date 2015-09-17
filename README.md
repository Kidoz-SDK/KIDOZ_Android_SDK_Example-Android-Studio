<a href="url"><img src="https://github.com/Kidoz-SDK/Kidoz_Android_SDK_Example/blob/master/graphics/App%20icon.png" align="left" height="72" width="72" ></a>

KIDOZ SDK Sample App
=================================

*Updated to KIDOZ SDK version 0.1.2* 

This Android application project provides an example of the [KIDOZ](http://www.kidoz.net) SDK integration.
It is compiled with Android 4.0 (API level 14) and supports any device running this Android version or higher.

The example application contains the following creative tools:
* Interstitial content tool - the `Feed View`
* KIDOZ's default button - the `KIDOZ Button`

###Running the sample app
1. Clone (or Download) the project (download button located on the right) and unzip the downloaded .zip file
2. Launch `Android Studio`, click `File` --> `Open`, navigate to `kidoz_sdk_sample` project and click `OK`
3. Once the project finished syncing click the `Run` button

</br>
KIDOZ SDK - Getting Started
=================================

 - 	Read the full KIDOZ SDK documentation and `Best Practices` on [KIDOZ SDK](http://kidoz.net/marketing/newsletter/sdk/SDK.pdf) website

<a href="url"><img src="https://kidoz-cdn.s3.amazonaws.com/sdk/btn_animation.gif" align="right" height="72" width="72" ></a>

The easiest way to use the SDK is following this 3 steps:

1. Include the `KIDOZ SDK` library inside your project
2. Init the SDK
3. Add `KIDOZ Button` to your `Main Activity`

Once the above 3 steps are correctly done the `Feed View` will be launched when the `KIDOZ Button` is clicked.

####Include the library
On android studio you can include the library directly in your Gradle project:

 - 	Add the following line to your app's module `build.gradle` `dependencies` section:
```gradle
dependencies {
	// your app's other dependencies
	compile 'com.kidoz.sdk:KidozSDK:0.1.2'
}
``` 
####Initializing the SDK
In order to use the SDK and get promoted content and more (marketing text) - when initializing the SDK, please make sure to use your given `publisherID` and `securityToken`, which can be retrieve by contacting with SDK@kidoz.net.

 - 	Inside your `Main Activity` onCreate add the following line:

> MainActivity.java

```java
@Override 
protected void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	KidozSDK.initialize(getApplicationContext(), "publisherID", "securityToken");
	//the rest of your main activity onCreate
}
```

####Adding the KIDOZ Button
You can add the `KIDOZ Button` to your layout xml file or create a new instance programmatically.

 - 	Add `KIDOZ Button` directly inside xml:
 
> main_activity_layout.xml

```xml
	<com.kidoz.sdk.api.KidozButtonView
		android:id="@+id/KidozButtonView"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content">
	</com.kidoz.sdk.api.KidozButtonView>
```

 - 	Add `KIDOZ Button` programmatically:
  	
 
> MainActivity.java

```java
KidozButtonView kidozButtonView = new KidozButtonView(MainActivity.this);
yourViewGroup.addView(kidozButtonView);
```

For advanced use of the `Feed View` you can get a reference to `InterstitialView` by calling this method on the `KidozButtonView` reference:

```java
InterstitialView interstitialView = kidozButtonView.getInterstitialView();
```
Refer to `Advance` section for a better look on `InterstitialView` and what can be done with it.

#Advance
####Creating an instance of the `Feed View`
 - 	Inside your `Activity` or `Fragment` create an instance of `InterstitialView` by adding the following lines:

```java
InterstitialView mInterstitialView = new InterstitialView.Builder(MainActivity.this, getSupportFragmentManager()).build();
```

You can implement `IOnInterstitialViewEventListener` interface if you want to be informed when the `InterstitialView` is dismissed and/or about to be open by adding the following lines:

```java
mInterstitialView.setOnInterstitialViewEventListener(new IOnInterstitialViewEventListener()
{
	@Override public void onDismissView()
	{
		// Will be called when the InterstitialView is closed
		// This is a good time to resume your game
	}
	
	@Override public void onReadyToShow()
	{
		// Will be called when the InterstitialView is about to open
		// This is a good time to pause your game
	}
});
```

 - 	Your `Main Activity` should be now look similar to this:

> MainActivity.java

```java
public class MainActivity extends FragmentActivity
{
	//Feed View reference
	private InterstitialView mInterstitialView;
	
	@Override 
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		KidozSDK.initialize(getApplicationContext(), "publisherID", "securityToken");
		// For a cleaner code init the InterstitialView in a saperated method
		initInterstitialView();
		
		//the rest of your main activity onCreate
	}
	
	private void initInterstitialView()
	{
		mInterstitialView = new InterstitialView.Builder(MainActivity.this, getSupportFragmentManager()).build();
		mInterstitialView.setOnInterstitialViewEventListener(new IOnInterstitialViewEventListener()
		{
			@Override public void onDismissView()
			{
				// Will be called when the InterstitialView is closed
				// This is a good time to resume your game
			}
		
			@Override public void onReadyToShow()
			{
				// Will be called when the InterstitialView is about to open
				// This is a good time to pause your game
			}
		});
	}
}
```

####Launching the Interstitial View
The `Feed View` can be launched by calling the method `showView` on the `InterstitialView` instance:
```java
mInterstitialView.showView();
```

You can call the `showView` method from anywhere inside your `Main Activity` depends on your app's flow, For example: when a game is stopped or when a user clicks a button.

It's recommended to use KIDOZ's default button - the `KIDOZ Button` which is a custom animatable button.

clicking on a Kidoz Button View or any other view with an onClick listener or some other way depending on your own application logics. For example when a game is stopped or anywhere else inside your app as long as your target class have a reference to the InterstitialView instance.
</br>
Simply launch the ```Interstitial View``` by calling the method showView() on the InterstitialView reference.

</br>For any question or assistance, please contact us at SDK@kidoz.net.

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


