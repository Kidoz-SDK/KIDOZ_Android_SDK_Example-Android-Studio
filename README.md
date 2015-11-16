
<a href="url"><img src="https://github.com/Kidoz-SDK/Kidoz_Android_SDK_Example/blob/master/graphics/App%20icon.png" align="left" height="72" width="72" ></a>

KIDOZ SDK Sample App
=================================
**KIDOZ SDK and the sample App is compatible with Android 4.0 (API level 14) and above.**

*Updated to KIDOZ SDK version 0.2.0* 

This Android application project provides an example of the [KIDOZ](http://www.kidoz.net) SDK integration.

The example application contains the following creative tools:
* KIDOZ's Feed view content tool - the `FeedView`
* KIDOZ's Feed Button view content tool - the `FeedButton`
* KIDOZ's Feed Panel content tool - the `FeedPanel`

###Running the sample app
1. Clone (or Download) the project (download button located on the right) and unzip the downloaded .zip file
2. Launch `Android Studio`, click `File` --> `Open`, navigate to `kidoz_sdk_sample` project and click `OK`
3. Once the project finished syncing click the `Run` button

####IMPORTANT
This demo application uses `buildToolsVersion "22.0.1"`. if your `Android Studio` is not updated with this version you can follow one of this steps (or both):

 - 	Update `buildToolsVersion`

1. Inside `Android Studio` click the `SDK Manager` icon
2. In the left side menu, navigate to `Appearance & Behavior` --> `System Settings` --> `Android SDK`
3. Click the `SDK Tools` tab
4. Check the `Android SDK Build Tools` and click `OK` 

</br>
 - 	Configure the demo application `build.gradle` `android` section with your `buildToolsVersion` 


```gradle
android {
	//Change this two parameters according to your buildToolsVersion 
	//You can check which version is installed inside the SDK Manager settings
 	compileSdkVersion 22 
 	buildToolsVersion "22.0.1"
}
``` 

</br>
KIDOZ SDK - Getting Started
=================================

 - 	Read the full KIDOZ SDK documentation and `Best Practices` on [KIDOZ SDK](http://kidoz.net/marketing/newsletter/sdk/SDK.pdf) website

The easiest way to use the SDK is following this 3 steps:

1. Include the `KIDOZ SDK` library inside your project
2. Init the SDK
3. Add `KIDOZ Feed View Button` to your `Main Activity`

Once the above 3 steps are correctly done the `Feed View` will be launched when the `Feed Button` is clicked.

####Include the library
On android studio you can include the library directly in your Gradle project:

 - 	Add the following line to your app's module `build.gradle` `dependencies` section:
```gradle
dependencies {
	// your app's other dependencies
	compile 'com.kidoz.sdk:KidozSDK:0.2.0'
}
``` 

#### AndroidMainifest.xml  Defenitions (IMPORTANT)
For correct flow of the SDK , add the folowing line in Your `AndroidMainifest.xml` file.For each `Activity` that uses the SDK functionality.
```xml
 android:configChanges="screenLayout|screenSize|orientation|keyboardHidden|keyboard"
``` 
Example:
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="your.package.name">
    
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

    <application>
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


####Initializing the SDK
The SDK should be initialized only once. 
When initializing the SDK, please make sure to use your given `publisherID` and `securityToken`, which can be retrieve by contacting with SDK@kidoz.net.
If your project extends `Application` you can initialized the SDK inside Application's onCreate otherwise initialized it inside your Main Activity's onCreate.

 - 	Inside your `Application` onCreate add the following line:

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
 - Inside your `Main Activity` onCreate add the following line:

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

`PaneView` is a customized special view that can slide in/out of the screen (both in horizontal and vertical layout) with minimal interference to user experience.
The `PanelView` can be place on one of four sides of the activity screen - `PANEL_TYPE.TOP`,`PANEL_TYPE.BOTTOM`,`PANEL_TYPE.RIGHT`,`PANEL_TYPE.LEFT` 
</br>
The `PanelView` can be controled via a special `Handle` button can that can be located in any of the 3 following positions -  
`HANDLE_POSITION.START`,`HANDLE_POSITION.CENTER`,`HANDLE_POSITION.END` depending on the `PaneView` initial Screen location.

You can add the `PaneView` either by adding it to your xml layout file OR by creating a new instance programmatically and adding it to the Main layout view.

####Add `PaneView` directly to the xml layout:
 
> main_activity_layout.xml

```xml
 <com.kidoz.sdk.api.PanelView
        android:id="@+id/kidozPanel_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
	
```
The `PaneView` should be added on top of exising layout for correct flow.

```java
    private PanelView mPanelView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        /** Get reference to Kidoz Panel View */
        mPanelView = (PanelView) findViewById(R.id.kidozPanel_view);
        
        mPanelView.setOnPanelViewEventListener(new IOnPanelViewEventListener() {
            @Override
            public void onPanelViewCollapsed() {

            }

            @Override
            public void onPanelViewExpanded() {

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

- The `PaneView` is Added by default in the Bottom of user screen with `PANEL_TYPE.BOTTOM` configuration type, witch can be changed in run time along side with handle position.  
<br/>

<a href="url"><img src="https://s3.amazonaws.com/kidoz-cdn/sdk/sdk_panel_layout.jpg" align="right" height="250" width="221" ></a>

```java
    mPanelView.setPanelConfiguration(PANEL_TYPE.BOTTOM, HANDLE_POSITION.CENTER);
``` 

- You can change Color of the pannel on runtime by using:
```java
  mPanelView.setPanelColor(Color.parseColor("#d95e38"));
```

- To invoke `PaneView` programmatically use:
```java
  mPanelView.expandPanelView();
```
and

```java
  mPanelView.collapsePanelView();
```

- To check the `PaneView` current view state use:
```java
  mPanelView.getIsPanelViewExpanded();
```

For advanced use the developer can add a Custom `Handle` View to a panel and design it as desired.

- Example

```java
 Button myButton = new Button(this);
        myButton.setText("My Handle");
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPanelView.getIsPanelViewExpanded()) {
                    mPanelView.collapsePanelView();
                } else {
                    mPanelView.expandPanelView();
                }
            }
        });

        mPanelView.setPanelConfiguration(PANEL_TYPE.BOTTOM, HANDLE_POSITION.CENTER,myButton);
```
 

</br>


#KIDOZ Feed
##Calling the Feed View Programmatically
####Creating an instance of the `Feed View`
 - 	Inside your `Activity` or `Fragment` create an instance of `FeedView` by adding the following lines:

```java
FeedView mFeed = new FeedView.Builder(MainActivity.this).build();
```

You can implement `IOnFeedViewEventListener` interface if you want to be informed when the `FeedView` is dismissed and/or about to be open by adding the following lines:

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
});
```

 - 	Your `Main Activity` should be now look similar to this:

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
		});
	}
}
```

####Launching the Feed View
The `Feed View` can be launched by calling the method `showView` on the `FeedView` instance:
```java
mFeedView.showView();
```

You can call the `showView` method from anywhere inside your `Main Activity` depends on your app's flow, For example: when a game is stopped or when a user clicks a button.

##Adding the KIDOZ Feed Button
<a href="url"><img src="https://kidoz-cdn.s3.amazonaws.com/sdk/btn_animation.gif" align="right" height="96" width="96" ></a>
You can also call the `Feed View` by adding the `Feed Button` - in this case the `Feed View` will be shown following a click on the `Feed Button`. 
You can add the `Feed Button` either by adding it to your xml layout file OR by create a new instance programmatically.


 - 	Add `FeedButton` directly inside xml:
 
> main_activity_layout.xml

```xml
	<com.kidoz.sdk.api.FeedButton
		android:id="@+id/kidozBtn_view"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"/>
	
```

 - 	Add `FeedButton` programmatically:
  	
 
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

For advanced use of the `Feed View` you can get a reference to `FeedView` by calling this method on the `FeedButton` reference:

```java
FeedView mFeedView = mFeedButton.getFeedView();
```
Refer to the next section for a better look on `FeedView` and how you can call it without using a button from within your own code.

It's recommended to use KIDOZ's default button - the `Feed Button` which is a custom animatable button.

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


