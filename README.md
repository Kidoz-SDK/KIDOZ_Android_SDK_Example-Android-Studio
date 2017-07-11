KIDOZ SDK + Sample App
=================================

<a href="url"><img src="https://github.com/Kidoz-SDK/Kidoz_Android_SDK_Example/blob/master/graphics/App%20icon.png" align="left" height="72" width="72" ></a>

[<img src="https://kidoz-cdn.s3.amazonaws.com/wordpress/kidoz_small.gif" width="533px" height="300px">](https://www.youtube.com/watch?v=-ljFjRn7jeM)


**General Notes**
- The Folowing version of Kidoz SDK contains a few small integration breaking changes:
  - Kidoz API now requires you to supply an Activity argument instead of a Context argument. Please make sure any Context 'this' argument you provide Kidoz is of the Activity type. If you're calling Kidoz from an Application class please move the call to your main Activity's onCreate event.
  - Kidoz Interstitial widget now requires you to determine if it's Rewarded or non-rewarded interstitial in constructor instead of in the loadAd() method.
- The Kidoz SDK now provide sdk initialization callbacks, providing information on whether the SDK initialized succesfully or, if failed, for what reason.
- Kidoz SDK and the sample App are compatible with Android 4.2 (API level 17) and above.
- Kidoz SDK version 0.8.1 is available for download through this Github page using the Download button above.




### Running the Sample App
1. Clone (or Download) the project (download button located on the right) and unzip the downloaded .zip file
2. Launch `Android Studio`, click `File` --> `Open`, navigate to `kidoz_sdk_sample` project and click `OK`
3. Once the project has finished syncing click the `Run` button

This Android application project provides an example of the [KIDOZ](http://www.kidoz.net) SDK integration.
The example application contains the following creative tools:

_Recommended units_:
+ KIDOZ Panel content tool - the `PanelView`
+ KIDOZ Interstitial View content tool - the `KidozInterstitial`content tool
+ KIDOZ Rewarded View content tool - the `KidozRewarded`

** Up to version 0.8.0.0: You need to select either Interstital OR Rewarded during application lifetime. From version 0.8.0.0 (included): You can use both Interstital AND Rewarded during application lifetime.

_Deprecated units_:
+ KIDOZ Feed View content tool - the `FeedView`
+ KIDOZ Flexi Point content tool - the `FlexiView`

#### IMPORTANT
This demo application uses `buildToolsVersion "23.0.3"`. If your `Android Studio` is not updated with this version you can follow one of these steps (or both):

 - 	Update `buildToolsVersion`

1. Inside `Android Studio` click the `SDK Manager` icon
2. In the left side menu, navigate to `Appearance & Behavior` --> `System Settings` --> `Android SDK`
3. Click the `SDK Tools` tab
4. Check the `Android SDK Build Tools` and click `OK` 

 - 	Configure the demo application `build.gradle` `android` section with your `buildToolsVersion` 

```groovy
android {
	//Change this two parameters according to your buildToolsVersion 
	//You can check which version is installed inside the SDK Manager settings
 	compileSdkVersion 23 
 	buildToolsVersion "23.0.3"
}
``` 

</br>

KIDOZ SDK - Getting Started
=================================

 - 	Read the full KIDOZ SDK documentation and `Best Practices` on [KIDOZ SDK](http://kidoz.net/marketing/newsletter/sdk/SDK.pdf) website

The easiest way to use the SDK is following these 3 steps:

1. Include the `KIDOZ SDK` library inside your project
2. Initiate the `SDK`
3. Add KIDOZ `FeedButton` to your Main Activity

Once the above 3 steps are correctly done the `FeedView` will be launched when the `FeedButton` is clicked.

#### Include the library
1. Add the Kidoz library jar you downloaded to your projects libs folder.
2. If not present allready please include the following lines to your 'build.gradle' dependencies section:

```groovy
dependencies {
    compile group: 'org.greenrobot', name: 'eventbus', version: '3.0.0'
    compile 'com.android.support:support-v4:23.0.+'
    compile 'com.kidoz.sdk:KidozSDK:0.8.1.0@aar'
}
``` 

#### AndroidMainifest.xml  Definitions (IMPORTANT)
Here are the things that should be added to the AndroidManifest.xml (make sure you add the permissions, receiver and activities bellow):

For correct flow of the SDK, add the following line in your `AndroidMainifest.xml` file, for each `Activity` that uses the SDK functionality.
```groovy
 android:configChanges="screenLayout|screenSize|orientation|keyboardHidden|keyboard"
``` 

Also add the following permissions:

```xml
 <uses-permission android:name="android.permission.INTERNET" />
 
 <!-- android:maxSdkVersion="19" is used to AVOID permission handling in Android 6.0 and above  -->  
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="19"/>
 
``` 

###### NOTICE!!!
In case external SD access is needed and requiered to apply/handle `Android 6.0` Permissions request flow,
add `WRITE_EXTERNAL_STORAGE` permission in the following format in your application `AndroidMainifest.xml` file:

```xml
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"  tools:node="replace"/>
 <!--  tools:node="replace" is used to replace default libray defenition--> 
``` 
To use `tools:node="replace"` add `xmlns:tools="http://schemas.android.com/tools"` in the `<manifest ...  >` tag of `AndroidMainifest.xml` file.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest
    package="com.your.package"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:versionCode="1"
    android:versionName="1">
``` 


###### IMPORTANT: Hardware Acceleration must be turned ON!
```groovy
 <application android:hardwareAccelerated="true">
``` 

Example:
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="your.package.name">
    
    <uses-permission android:name="android.permission.INTERNET" />
    
     <!-- android:maxSdkVersion="19" is used to avoid permission handling in Android 6.0 and above. Note that as of version 19, this permission is not required to perform Kidoz relevant calls. --> 
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="19"/>
   
    <!-- If you need to handle Android 6.0 permissions and access SD storage -->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"  tools:node="replace"/>

    <application android:hardwareAccelerated="true">
        <activity
            ...
            android:configChanges="screenLayout|screenSize|orientation|keyboardHidden|keyboard"
            ...
          >
	</activity>
	
	 <receiver android:name="com.kidoz.sdk.api.receivers.SdkReceiver" android:enabled="true" >
            <intent-filter>
                <action android:name="android.intent.action.PACKAGE_ADDED"/>
                <data android:scheme="package"/>
            </intent-filter>
        </receiver>
        ...
    </application>
</manifest>
``` 


### Initialize the SDK
The SDK should be initialized only once. 
When initializing the SDK, please make sure to use your given `publisherID` and `securityToken`. To receive the credentials please sign up [HERE](http://accounts.kidoz.net/publishers/register?utm_source=&utm_content=&utm_campaign=&utm_medium=).
</br>
Initialize the SDK inside your Main Activity's onCreate.

> MainActivity.java

```java
@Override 
protected void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
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
		KidozSDK.initialize(this, <publisherID>, <securityToken>);
		
	KidozSDK.initialize(this, <publisherID>, <securityToken>);
	//the rest of your main activity onCreate
	...
}
```
# KIDOZ Banner

'KidozBannerView` is a view that shows banner ads.
 
### Calling KidozBannerView Programmatically

* Get a KidozBannerView instance from KidozSDK access point:
```java
KidozBannerView kidozBannerView = KidozSDK.getKidozBanner(<Activity>); 
```

* Set banner position:
```java
kidozBannerView.setBannerPosition(BANNER_POSITION.TOP / BOTTOM); 
```

* Set banner listener:
```java 
kidozBannerView.setKidozBannerListener(new KidozBannerListener()
        {
            @Override
            public void onBannerViewAdded()
            {
                //onBannerViewAdded
            }

            @Override
            public void onBannerReady()
            {
                //onBannerReady
            }

            @Override
            public void onBannerError(String errorMsg)
            {
                //onBannerError
            }

            @Override
            public void onBannerClose()
            {
                //onBannerClose
            }
        }); 
```

* Call banner load before showing:
```java
kidozBannerView.load(); 
```

* Call banner show (banner needs to be ready before showing):

```java
kidozBannerView.show(); 
```

* To hide banner:
```java
kidozBannerView.hide(); 
```

### Showing KidozBannerView in View Hierarchy
* Add KidozBannerView to your layout (in xml):
```xml
            <com.kidoz.sdk.api.ui_views.new_kidoz_banner.KidozBannerView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:id="@+id/<YOUR_ID_NAME>"/>
```	

* Find KidozBannerView instance in your layout by id:
```java
final KidozBannerView kidozBannerView = (KidozBannerView) findViewById(<your banner id>); 
```

* Set banner listener:
```java
kidozBannerView.setKidozBannerListener(new KidozBannerListener()
        {
            @Override
            public void onBannerViewAdded()
            {
                //onBannerViewAdded
            }

            @Override
            public void onBannerReady()
            {
                //onBannerReady
            }

            @Override
            public void onBannerError(String errorMsg)
            {
                //onBannerError
            }

            @Override
            public void onBannerClose()
            {
                onBannerClose
            }
        }); 
```

* Required (atm) - Set banner to not show on layout():
```java
kidozBannerView.setLayoutWithoutShowing(); 
```

* Call banner load before showing:
```java
kidozBannerView.load(); 
```

* Call banner show (banner needs to be ready before showing):

```java
kidozBannerView.show(); 
```

* To hide banner:
```java
kidozBannerView.hide(); 
```

# KIDOZ Panel
<a href="url"><img src="http://kidoz-cdn.s3.amazonaws.com/media/Panel%20Github.jpeg" align="right" height="121" width="200" ></a>

`PanelView` is a customized special view that can slide in/out of the screen with minimal interference to user experience.
The `PanelView` can be placed on Top or Bottom of the activity screen: 
</br>
+ PANEL_TYPE.TOP
+ PANEL_TYPE.BOTTOM
</br>

The `PanelView` can be controlled by a special `Handle` button that can be located in one of the 3 following positions, depending on the `PanelView` initial screen location.

</br>
+ HANDLE_POSITION.START
+ HANDLE_POSITION.CENTER
+ HANDLE_POSITION.END

For NO handle at all use:
+ HANDLE_POSITION.NONE
 

`PanelView` can be added either by adding it to your xml layout file OR by creating a new instance programmatically and adding it to the Main layout view.

#### Add `PanelView` directly to the xml layout:
 
> main_activity_layout.xml

```xml
 <com.kidoz.sdk.api.PanelView
        android:id="@+id/kidozPanel_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
	
```
The `PanelView` should be added on top of the existing layout for the correct flow.

```java
    private PanelView mPanelView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        /** Get reference to Kidoz Panel View */
        mPanelView = (PanelView) findViewById(R.id.kidozPanel_view);
        //mPanelView.setPanelConfiguration(PANEL_TYPE.TOP, HANDLE_POSITION.START);
         
        mPanelView.setOnPanelViewEventListener(new IOnPanelViewEventListener() {
            @Override
            public void onPanelViewCollapsed() {
		/** Function invoked when the panel view is collapsed */
            }

            @Override
            public void onPanelViewExpanded() {
		/** Function invoked when the panel view is expanded */
            }
            
             @Override
            public void onPanelReady() {
                /** Function invoked when the panel is finished initiation and have beed drawn */
            }
        });
        ...
``` 

#### Adding the `PanelView` programmatically

> MainActivity.java

```java
PanelView mPanelView = new PanelView(MainActivity.this);
yourViewGroup.addView(mPanelView);
```

- The `PanelView` is added by default in the bottom of the user screen with `PANEL_TYPE.BOTTOM` configuration type, which can be changed in runtime alongside handle position.  
<br/>

```java
    mPanelView.setPanelConfiguration(PANEL_TYPE.BOTTOM, HANDLE_POSITION.CENTER);
``` 
 
- To invoke `PaneView` programmatically use:
```java
  mPanelView.expandPanelView();
  
  mPanelView.collapsePanelView();
```
- To check the `PanelView` current view state use:
```java
  mPanelView.getIsPanelViewExpanded();
```
</br>

# KIDOZ Feed

KIDOZ `FeedView` is a view that is opened full screen.
 
### Calling FeedView Programmatically  

Refer to the next section for a better look at `FeedView` and how you can call it without using a button from within your own code.
 - Inside your `Activity` or `Fragment` create an instance of `FeedView` by adding the following lines:

```java
FeedView mFeed = new FeedView.Builder(MainActivity.this).build();
```

You can implement `IOnFeedViewEventListener` interface if you want to be informed about `FeedView` events  by adding the following lines:

```java
mFeedView.setOnFeedViewEventListener(new IOnFeedViewEventListener()
{
	@Override public void onDismissView()
	{
		// Will be called when the FeedView is closed
		// This is a good time to resume your game
	}
	
	@Override public void onReadyToShow()
	{
		// Will be called when the FeedView is about to open
		// This is a good time to pause your game
	}
	
	@Override public void onViewReady() 
	{
	       	// Will be called when the FeedView object is ready
		// This is a good time interact with the object
	}
});
```

 - Your Main Activity should now look similar to this:

> MainActivity.java

```java
public class MainActivity extends FragmentActivity
{
	//Feed View reference
	private FeedView mFeedView;
	
	@Override 
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		KidozSDK.initialize(getApplicationContext(), "publisherID", "securityToken");
		// For a cleaner code init the FeedView in a saperated method
		initFeedView();
		//the rest of your main activity onCreate
		...
	}
	
	private void initFeedView()
	{
		mFeedView = new FeedView.Builder(MainActivity.this).build();
		mFeedView.setOnFeedViewEventListener(new IOnFeedViewEventListener()
		{
			@Override public void onDismissView()
			{
				// Will be called when the FeedView is closed
				// This is a good time to resume your game
			}
		
			@Override public void onReadyToShow()
			{
				// Will be called when the FeedView is about to open
				// This is a good time to pause your game
			}
			
			@Override public void onViewReady() 
			{
			       	// Will be called when the FeedView object is ready
				// This is a good time interact with the object
			}
		});
	}
}
```

#### Launching the Feed View
The `FeedView` can be launched by calling the method `showView()` on the `FeedView` instance:
```java
	mFeedView.showView();
```

You can call the `showView()` method from anywhere inside your Main Activity depending on your app's flow,for example: when a game is stopped or when a user clicks a button.

##Adding the KIDOZ FeedButton
<a href="url"><img src="https://kidoz-cdn.s3.amazonaws.com/sdk/btn_animation.gif" align="right" height="96" width="96" ></a>
You can also call the `FeedView` by adding the `FeedButton` - in this case the `FeedView` will be shown following a click on the `FeedButton`. 
You can add the `FeedButton` either by adding it to your xml layout file OR by creating a new instance programmatically.

 - Add `FeedButton` directly inside xml:
 
> main_activity_layout.xml

```xml
	<com.kidoz.sdk.api.FeedButton
	android:id="@+id/kidozBtn_view"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"/>
	
```
 - Add `FeedButton` programmatically:
  	
> MainActivity.java

```java
FeedButton mFeedButton = new FeedButton(MainActivity.this);
yourViewGroup.addView(mFeedButton);
```

For advanced use of the `FeedView` you can get a reference to `FeedView` class by calling this method on the `FeedButton` reference:

```java
FeedView mFeedView = mFeedButton.getFeedView();
```

We recommend using KIDOZ default button - the `FeedButton` - which is a customizable animated button.


# KIDOZ Flexi Point View
<a href="url"><img src="https://s3.amazonaws.com/kidoz-cdn/sdk/flexi_sample_preview.png" align="right" height="300" width="300" ></a>
`FlexiView` is a small interactable single content view, which hovers over the screen content.  

You can add the `FlexiView` either by adding it to your xml layout file OR by creating a new instance programmatically and adding it to the Main layout view.

 - Add `FlexiView` directly inside xml:

> main_activity_layout.xml

```xml
    <com.kidoz.sdk.api.FlexiView
        android:id="@+id/kidozFlexi_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </com.kidoz.sdk.api.FlexiView>
	
```

 - Add `FlexiView` programmatically:
  	
```java
FlexiView mFlexiView = new FlexiView(this);
yourViewGroup.addView(mFlexiView);
```
 
> MainActivity.java

```java
mFlexiView = (FlexiView) findViewById(R.id.kidozFlexi_view);
```
 
- To show Flexi view automatically as soon as it becomes ready add the following line:
```java
// Auto show Flexi View on View initiation ready
mFlexiView.setAutoShow(true);
``` 
 
- To set Flexi view initial anchor position add the following line:
```java
// Set flexi view initial anchor position
flexiView.setFlexiViewInitialPosition(FLEXI_POSITION.TOP_START);
```

- To Show/Hide Flexi view use the following lines:
```java
 // Show flexi view
 flexiView.showFlexiView();
 
 // Hide flexi view
 flexiView.hideFlexiView();
```
 
- To add event listeners to Flexi View use:
```java 	
 mFlexiView.setOnFlexiViewEventListener(new FlexiViewListener() {
    @Override
    public void onViewReady() {
        super.onViewReady();
        
        // Will be called when the FlexiView object is ready
        // This is a good time interact with the object , show it or hide it
    }

    @Override
    public void onViewHidden() {
        super.onViewHidden();

        // Will be called when the FlexiView become INVISIBLE
        // On User or code actions
    }

    @Override
    public void onViewVisible() {
        super.onViewVisible();

        // Will be called when the FlexiView become VISIBLE
    }
});
```

# KIDOZ Interstitial View
`KidozInterstitial` is a full screen single ad unit.

#### Using Interstitial And Rewarded Video Ads
To show interstitial\rewarded video ads inside your `Activity` or `Fragment` create an instance of `KidozInterstitial` by adding the following lines:

```java
KidozInterstitial mInterstitial = new KidozInterstitial(this, <AD_TYPE>);
AD_TYPE = {KidozInterstitial.AD_TYPE.INTERSTITIAL, KidozInterstitial.AD_TYPE.REWARDED_VIDEO}
```

For interstitial/rewarded view to work correctly, add the following lines to `AndroidMainifest.xml`  file (MUST):

```xml
 <activity android:name="com.kidoz.sdk.api.ui_views.interstitial.KidozAdActivity"                  	android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"
 />
``` 

Example:
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="your.package.name">
    ...
    <application>
       <activity android:name="com.kidoz.sdk.api.ui_views.interstitial.KidozAdActivity"
                  android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"
                  />
        ...
    </application>
</manifest>
``` 

You can implement `KidozInterstitial.IOnInterstitialEventListener` interface if you want to be informed about `KidozInterstitial` events by adding the following lines:

```java
 mInterstitial.setOnInterstitialEventListener(new BaseInterstitial.IOnInterstitialEventListener()
    {
        @Override
        public void onClosed()
        {
	   //Informs when interstitial ad view has been close	
        }

        @Override
        public void onOpened()
        {
            //Informs when interstitial ad view has been opened	
        }
        
        @Override
        public void onReady()
        {
            //Lounch Interstitial when ready if needed
            //mKidozInterstitial.show();
        }
        
        @Override
        public void onLoadFailed()
        {
            //Informs when interstitial ad view has failed to load	
        }
    });
```

Additional events that get invoked for the Rewarded Video Interstitial
```java
mKidozInterstitial.setOnInterstitialRewardedEventListener(new BaseInterstitial.IOnInterstitialRewardedEventListener()
{
    @Override
    public void onRewardReceived()
    {
    	//Informs when interstitial rewarded event is invoked (Rewarded video is completed)	
    }

    @Override
    public void onRewardedStarted()
    {
    	//Informs when interstitial rewarded video started event
    }
});
```

Call `loadAd()` to load Interstitial/Rewarded Ad instance  

Call `show()` as soon as it's ready.


#### Launching the Interstitial View
```java
 if (!mKidozInterstitial.isLoaded())
 {
    mKidozInterstitial.loadAd();
 } else
 {
    mKidozInterstitial.show();
 }
```

#### Launching the Rewarded Video Interstitial View
```java
 if (!mKidozInterstitial.isLoaded())
 {
    mKidozInterstitial.loadAd();
 } else
 {
    mKidozInterstitial.show();
 }
```
 
For any question or assistance, please contact us at SDK@kidoz.net.
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


