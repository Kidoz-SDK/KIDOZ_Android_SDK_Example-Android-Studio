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

###Initiate the Interstitial  FEED ads
 - 	Inside your desired activity or fragment add the following line:
 - 	
This Android application project provides an example of the [KIDOZ][] SDK integration.
It is compiled with Android 4.0 (API level 14) and supports any device running this Android version or higher.

The example application contains the following creative tools:
* Interstitial  FEED ads when clicking on KIDOZ button

Don't forget to use your developers id and application id when initializing the SDK.


For any question or assistance, please contact us at support@kidoz.net.

[KIDOZ]: http://www.kidoz.net


