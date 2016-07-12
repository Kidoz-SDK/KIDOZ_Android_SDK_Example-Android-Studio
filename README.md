
<a href="url"><img src="https://github.com/Kidoz-SDK/Kidoz_Android_SDK_Example/blob/master/graphics/App%20icon.png" align="left" height="72" width="72" ></a>

KIDOZ SDK + Sample App
=================================
**KIDOZ SDK and the sample App are compatible with Android 4.0 (API level 14) and above.**

*Updated to KIDOZ SDK version 0.5.8* 

### [API Javadoc](https://s3.amazonaws.com/kidoz-cdn/sdk/APIDocumentation/Android/StandardAndroid/0.5.6/html/annotated.html)

This Android application project provides an example of the [KIDOZ](http://www.kidoz.net) SDK integration.
The example application contains the following creative tools:
+ KIDOZ Feed view content tool - the `FeedView`
+ KIDOZ Feed Button view content tool - the `FeedButton`
+ KIDOZ Feed Panel content tool - the `PanelView`
+ KIDOZ Banner content tool - the `KidozBanner`
+ KIDOZ Flexi Point content tool - the `FlexiView`
+ KIDOZ Interstitial View content tool - the `KidozInterstitial`

###Running the Sample App
1. Clone (or Download) the project (download button located on the right) and unzip the downloaded .zip file
2. Launch `Android Studio`, click `File` --> `Open`, navigate to `kidoz_sdk_sample` project and click `OK`
3. Once the project has finished syncing click the `Run` button

####IMPORTANT
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
2. Initiate the SDK
3. Add KIDOZ `FeedButton` to your Main Activity

Once the above 3 steps are correctly done the `FeedView` will be launched when the `FeedButton` is clicked.

####Include the library
On Android Studio you can include the library directly in your Gradle project:

 - 	Add the following line to your app's module `build.gradle` dependencies section:
```groovy
dependencies {
	// your app's other dependencies
	compile 'com.kidoz.sdk:KidozSDK:0.5.8'
}
``` 

#### AndroidMainifest.xml  Definitions (IMPORTANT)
For correct flow of the SDK, add the following line in your `AndroidMainifest.xml` file, for each `Activity` that uses the SDK functionality.
```groovy
 android:configChanges="screenLayout|screenSize|orientation|keyboardHidden|keyboard"
``` 

Also add the following permissions:

```xml
 <uses-permission android:name="android.permission.INTERNET" />
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="22"/>
 <!-- android:maxSdkVersion="22" is used to avoid permission handling in Android 6.0 and above  -->  
``` 

###### IMPORTANT: Hardware Acceleration must be turned ON!
```groovy
 <application android:hardwareAccelerated="true">
``` 

Example:
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="your.package.name">
    
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="22"/>
    <!-- android:maxSdkVersion="22" is used to avoid permission handling in Android 6.0 and above  -->  

    <application android:hardwareAccelerated="true">
        <activity
            ...
            android:configChanges="screenLayout|screenSize|orientation|keyboardHidden|keyboard"
            ...
          >
	</activity>
        ...
    </application>
</manifest>
``` 


###initialize the SDK
The SDK should be initialized only once. 
When initializing the SDK, please make sure to use your given `publisherID` and `securityToken`. To receive the credentials please sign up [HERE](http://accounts.kidoz.net/publishers/register?utm_source=&utm_content=&utm_campaign=&utm_medium=).
</br>
If your project extends `Application` you can initialize the SDK inside Application's onCreate otherwise initialize it inside your Main Activity's onCreate.

 - 	Inside your Application `onCreate` add the following line:

> YourApplication.java

```java
public class MyApplication extends Application{
   	@Override 
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate();
		KidozSDK.initialize(this, "publisherID", "securityToken");
		//the rest of your application onCreate
	}
    ...
}
```
 - Inside your Main Activity `onCreate` add the following line:

> MainActivity.java

```java
@Override 
protected void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	KidozSDK.initialize(getApplicationContext(), "publisherID", "securityToken");
	//the rest of your main activity onCreate
	...
}
```


#KIDOZ Panel
<a href="url"><img src="https://s3.amazonaws.com/kidoz-cdn/sdk/panel_view_sample_image.png" align="right" height="121" width="200" ></a>

`PanelView` is a customized special view that can slide in/out of the screen with minimal interference to user experience.
The `PanelView` can be placed on one of four sides of the activity screen: 
</br>
+ PANEL_TYPE.TOP
+ PANEL_TYPE.BOTTOM

Only for `NoNe Family` PanelView
+ PANEL_TYPE.LEFT 
+ PANEL_TYPE.RIGHT 

</br>

The `PanelView` can be controlled by a special `Handle` button that can be located in one of the 3 following positions, depending on the `PanelView` initial screen location.

</br>
+ HANDLE_POSITION.START
+ HANDLE_POSITION.CENTER
+ HANDLE_POSITION.END

For NO handle at all use:
+ HANDLE_POSITION.NONE
 
<!--<a href="url"><img src="https://s3.amazonaws.com/kidoz-cdn/sdk/sdk_panel_layout.jpg" align="center" height="500" width="433" ></a>-->

`PanelView` can be added either by adding it to your xml layout file OR by creating a new instance programmatically and adding it to the Main layout view.

####Add `PanelView` directly to the xml layout:
 
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

####Adding the `PanelView` programmatically

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
- You can change Color of the `PanelView` on runtime by using:
```java
  mPanelView.setPanelColor(Color.parseColor("#d95e38"));
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

#KIDOZ Feed

KIDOZ `FeedView` is a view that is opened full screen.
 
###Calling FeedView Programmatically  

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

####Launching the Feed View
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

- You can change Feed button size on runtime by using:
```java
 mFeedButton.setFeedButtonSize(200);
```
OR

```java
 mFeedButton.setFeedButtonSizeDp(70);
```

For advanced use of the `FeedView` you can get a reference to `FeedView` class by calling this method on the `FeedButton` reference:

```java
FeedView mFeedView = mFeedButton.getFeedView();
```

We recommend using KIDOZ default button - the `FeedButton` - which is a customizable animated button.

#KIDOZ Banner View
<a href="url"><img src="https://s3.amazonaws.com/kidoz-cdn/sdk/sdk_banner_preview.png" align="right" height="80" width="445" ></a>
`KidozBanner` is a customized interactive banner view with a standard size of `320 * 50` dp
 
You can add the `KidozBanner` either by adding it to your xml layout file OR by creating a new instance programmatically and adding it to the Main layout view.

 - Add `KidozBanner` directly inside xml:

> main_activity_layout.xml

```xml
<com.kidoz.sdk.api.KidozBanner
        android:id="@+id/kidozBanner_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true" >
</com.kidoz.sdk.api.KidozBanner>
	
```

 - Add `KidozBanner` programmatically:
  	
```java
KidozBanner mKidozBanner = new KidozBanner(this);
yourViewGroup.addView(mKidozBanner);
```
 
> MainActivity.java

```java
mKidozBanner = (KidozBanner) findViewById(R.id.kidozBanner_view);
mKidozBanner.setKidozBannerListener(new KidozBannerListener() {
    @Override
    public void onBannerReady() {
        super.onBannerReady();
        /** Call show banner when the view is ready */
        mKidozBanner.showBanner();
    }
});
```
 
- To Show banner use the following line (Don't use `View.setVisibility()`):
	
```java
// Efficient way to hide the banner (Don't use View.setVisibility())
mKidozBanner.showBanner();
``` 
 
- To Hide banner use the following line (Don't use `View.setVisibility()`): 
	
```java
// Efficient way to hide the banner (Don't use View.setVisibility())
mKidozBanner.hideBanner();
```

- To add event listeners to banner view use:
```java 	
 mKidozBanner.setKidozBannerListener(new KidozBannerListener() {
                            @Override
                            public void onBannerReady() {
                                super.onBannerReady();
                                
                            }

                            @Override
                            public void onBannerShow() {
                                super.onBannerShow();
                                
                            }

                            @Override
                            public void onBannerHide() {
                                super.onBannerHide();

                            }

                            @Override
                            public void onBannerContentLoaded() {
                                super.onBannerContentLoaded();

                                }
                            }

                            @Override
                            public void onBannerContentLoadFailed() {
                                super.onBannerContentLoadFailed();

                                }
                            }
                        });
```

#KIDOZ Flexi Point View
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

- Flexi view can be adjusted by:
```java
// Set flexi view can be dragged/moved by user
flexiView.setDraggable(true);

// Set flexi view can be dissmised by user
flexiView.setClosable(true);
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

#KIDOZ Interstitial View
`KidozInterstitial` is a full screen single ad unit.

#### Using Interstitial
To show interstitial ads inside your `Activity` or `Fragment` create an instance of `KidozInterstitial` by adding the following lines:

```java
KidozInterstitial mInterstitial = new KidozInterstitial(this);
```

For interstitial view to work correctly, add the following lines to `AndroidMainifest.xml`  file (MUST):

```xml
 <activity android:name="com.kidoz.sdk.api.ui_views.interstitial.KidozAdActivity"
                  android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"
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

####Launching the Interstitial View
```java
 if (mKidozInterstitial.isLoaded() == false)
 {
    mKidozInterstitial.loadAd();
 } else
 {
    mKidozInterstitial.show();
 }
```

Call `loadAd()` to load current ad instance, and then call `show()` as soon as it's ready.  
 
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


