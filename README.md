# KdroidExt
[ ![Download](https://api.bintray.com/packages/nowfalsalahudeen/KdroidExt/com.nowfal.kdroidext/images/download.svg) ](https://bintray.com/nowfalsalahudeen/KdroidExt/com.nowfal.kdroidext/_latestVersion) [![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-kdroidext-green.svg?style=flat )]( https://android-arsenal.com/details/1/7493 ) [![](https://jitpack.io/v/nowfalsalahudeen/KdroidExt.svg)](https://jitpack.io/#nowfalsalahudeen/KdroidExt) [![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16) [![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://GitHub.com/Naereen/StrapDown.js/graphs/commit-activity)

<img src="img/demo.png" width="160px">

Kotlin library for Android providing useful extensions to eliminate boilerplate code in Android SDK and focus on productivity.


Use the power of Kotlin to make your code smaller and beautiful.

Download
--------

Download latest version with Gradle:
```groovy
implementation 'com.nowfal.kdroidext:kdroidext:1.2.0'
```

Usage
-----
#### Binding views
```kotlin
// instead of findViewById(R.id.textView) as TextView
val textView = find<TextView>(R.id.textView)
```
#### Accessing ViewGroup children
```kotlin
/* instead of:
for (i in 0..layout - 1) {
    layout.getChildAt(i).visibility = View.GONE
}
*/
layout.views.forEach { it.visibility = View.GONE }
```
#### TextWatcher
```kotlin
/* instead of:
editText.addTextChangedListener(object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        before()
    }
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onChange()
    }
    override fun afterTextChanged(s: Editable?) {
        after()
    }
}) */
editText.textWatcher {
    beforeTextChanged { text, start, count, after -> before() }
    onTextChanged { text, start, before, count -> onChange() }
    afterTextChanged { text -> after() }
}
```

#### SearchView extensions
```kotlin
/* instead of:
searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
    override fun onQueryTextChange(q: String): Boolean {
        update(q)
        return false
    }
    
    override fun onQueryTextSubmit(q: String): Boolean {
        return false
    }
}) */
searchView.onQueryChange { query -> update(query) }

/* instead of:
searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
    override fun onQueryTextChange(q: String): Boolean {
        return false
    }
    
    override fun onQueryTextSubmit(q: String): Boolean {
        update(q)
        return false
    }
}) */
searchView.onQuerySubmit { query -> update(query) }
```
#### SeekBar extension
```kotlin
/* instead of:
seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
    override fun onStopTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        mediaPlayer.seekTo(progress)
    }

}) */
seekBar.onProgressChanged { progress, fromUser -> 
    if (fromUser) mediaPlayer.seekTo(progress) 
}
```
#### Using system services
```kotlin
// instead of getSystemService(Context.WINDOW_SERVICE) as WindowManager?
windowManager
// instead of getSystemService(Context.POWER_SERVICE) as PowerManager?
powerManager
// instead of getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
notificationManager
// instead of getSystemService(Context.USER_SERVICE) as UserManager?
userManager
// etc.
```
#### Toast messages
```kotlin
longToast("I'm long toast message!")
toast("Hi, I'm short one!")

longToast(R.string.my_string)
toast(R.string.my_string)
```
#### Layout inflater
```kotlin
// instead of LayoutInflater.from(context).inflate(R.layout.some_layout, null, false)
context.inflateLayout(R.layout.some_layout)
// or
context.inflateLayout(R.layout.some_layout, attachToRoot = true)
```
#### Using Intents
```kotlin
// instead of Intent(this, javaClass<SampleActivity>())
val intent = IntentFor<SampleActivity>(this)
// instead of startActivity(Intent(this, javaClass<SampleActivity>()))
startActivity<SampleActivity>()
// instead of startActivityForResult(Intent(this, javaClass<SampleActivity>()), REQUEST_CODE)
startActivityForResult<SampleActivity>(REQUEST_CODE)
```
#### Logging
```kotlin
// using javaClass.simpleName as a TAG
w("Warn log message")
e("Error log message")
wtf("WTF log message")
// using lambda log method
v { "Verbose log message" }
d { "Debug log message" }
i { "Info log message" }
// or with custom TAG
v("CustomTag", "Verbose log message with custom tag") 
```
#### Threading
```kotlin
// instead of Thread(Runnable { /* long execution */ }).start()
runAsync {
    // long execution
}

// delayed run (e.g. after 1000 millis)
// equals Handler().postDelayed(Runnable { /* delayed execution */ }, delayMillis)
runDelayed(1000) {
    // delayed execution
}

// run on Main Thread outside Activity
// equals Handler(Looper.getMainLooper()).post(Runnable { /* UI update */ })
runOnUiThread {
    // UI update
}

// delayed run on Main Thread
// equals Handler(Looper.getMainLooper()).postDelayed(Runnable { /* delayed UI update */ }, delayMillis)
runDelayedOnUiThread(5000) {
    // delayed UI update
}
```
#### From/To API SDK
```kotlin
// instead of if (Build.VERSION.SDK_INT >= 21) { /* run methods available since API 21 */ }
fromApi(21) {
    // run methods available since API 21
}

// instead of if (Build.VERSION.SDK_INT < 16) { /* handle devices using older APIs */ }
toApi(16) {
    // handle devices running older APIs
}
// or
// instead of if (Build.VERSION.SDK_INT <= 16) { /* handle devices using older APIs */ }
toApi(16, inclusive = true) {
    // handle devices running older APIs
}
```
#### Loading animation from xml
```kotlin
// instead of AnimationUtils.loadAnimation(applicationContext, R.anim.slide_in_left)
loadAnimation(R.anim.slide_in_left)
```
#### Animation listener
```kotlin

/*instead of:
animation.setAnimationListener(object : Animation.AnimationListener{
	override fun onAnimationStart(animation: Animation?) {
		onStart()
	}
	override fun onAnimationEnd(animation: Animation?) {
		onEnd()
	}
	override fun onAnimationRepeat(animation: Animation) {
		onRepeat()
	}
})*/

animation.animListener {
	onAnimationStart { onStart() }
	onAnimationEnd { onEnd() }
	onAnimationRepeat { onRepeat() }
}
```
#### Web intents with url validation
```kotlin
// instead of Intent(Intent.ACTION_VIEW, Uri.parse("http://github.com"))
WebIntent("http://github.com")
```

### Context extensions

```kotlin
  // This extensions are also work in Activities, Fragments, Dialogs and DialogFragments
  
  val myColor = color(R.color.my_color_res)                     // get any color from your recources
  val myString = string(R.string.my_string_res)                 // get any String from your recources
  val myDimen = dimen(R.dimen.my_dimen_res)                     // get any dimension value from your recources as Float
  val myDimenInt = dimenInt(R.dimen.my_dimen_res)               // get any dimension value from your recources as Int
  val myInt = int(R.dimen.my_int_res)                           // get any int value from your recources
  val myFont = font(R.dimen.my_font_res)                        // get any font value from your recources
  val myStringArray = stringArray(R.array.my_string_array_res)  // get any string array from your recources
  val myIntArray = intArray(R.array.my_int_array_res)           // get any int array from your recources
  val myDrawable = drawable(R.drawable.my_drawable_name)        // get any drawable from your recources
  val myDrawable = drawable("my_drawable_name", "com.my.app")   // get any drawable from your recources by String name
  
  // Show toast with context, second parameter is optional, default value is Toast.LENGTH_SHORT
  toast("Some message", Toast.LENGTH_LONG)
  toast(R.string.message_res)
```

### Activity extensions

```kotlin
  /* Replace a fragment in container, optional parameters
    addToBackStack (default value is false)
    backStackName (default value is "")
    inAnimationRes (default value is 0)
    outAnimationRes (default value is 0)
  */
  replaceFragment(fragment, R.id.container)
  
  /* Add a fragment in container, optional parameters
    addToBackStack (default value is false)
    backStackName (default value is "")
    inAnimationRes (default value is 0)
    outAnimationRes (default value is 0)
  */
  addFragment(fragment, R.id.container)
  
  /* Start new activity, optional parameters
    extras (default value is Bundle())
    overrideTransitions (default value is false)
    enterAnimation (default value is 0)
    exitAnimation (default value is 0)
  */
  startActivity(activityToOpen)
  
  /* Start new activity for result, optional parameters
    extras (default value is Bundle())
    overrideTransitions (default value is false)
    enterAnimation (default value is 0)
    exitAnimation (default value is 0)
  */
  startActivityForResult(activityToOpen, requestCode)
  
  /* Start new activity with transitions, optional parameter
    extras (default value is Bundle())
  */
  startActivityWithTransitions(activityToOpen, options)
  
  /* Start new activity for result with transitions, optional parameter
    extras (default value is Bundle())
  */
  startActivityForResultWithTransitions(activityToOpen, requestCode, options)
  
  /* Start new activity for result with transitions, optional parameter
    requestCode (default value is 0)
    extras (default value is Bundle())
  */
  startActivityFromFragmentWithTransitions(activityToOpen, fragmentFrom, options)
  
  /* Start new activity for result with transitions, optional parameter
    extras (default value is Bundle())
  */
  startActivityFromFragmentWithResult(activityToOpen, fragmentFrom, requestCode)
  
  // Pop fragment in back stack
  popFragment()
  popFragment(name, flags)
  popFragment(id, flags)
  
  //Remove fragment
  removeFragment(fragment)
  removeFragmentByTag(tag)
  removeFragmentById(R.id.my_fragment_id)
  
  // Works on API19+
  makeTranslucentStatusBar()
  
  // Works on API23+
  lightStatusBar()
  
  // Works on API19+, removes the previous
  makeNormalStatusBar()
  
  // Works on API19+
  makeTranslucentNavigationBar()
  
  // Works on API26+
  lightNavigation()
  
  // Works on API19+, removes the previous
  makeNormalNavigationBar()
```

### Fragment extensions

```kotlin
  /* Start new activity for result from fragment, optional parameters
    extras (default value is Bundle())
    overrideTransitions (default value is false)
    enterAnimation (default value is 0)
    exitAnimation (default value is 0)
  */
  startActivityForResult(activityToOpen, requestCode)
  
  /* Start new activity for result with transitions, optional parameter
    extras (default value is Bundle())
  */
  startActivityFromFragmentWithResult(activityToOpen, fragmentFrom, requestCode)
  
  // Pop fragment in back stack
  popFragment()
  popFragment(name, flags)
  popFragment(id, flags)
  
  //Remove fragment
  removeFragment(fragment)
  removeFragmentByTag(tag)
  removeFragmentById(R.id.my_fragment_id)
 
```

### Animator extensions

```kotlin
  val animator = ValueAnimator.ofFloat(1f, 10f)
  
  animator.onStart { ... }
  animator.onEnd { ... }
  animator.onCancel { ... }
  animator.onRepeat { ... }
  animator.onResume { ... }                                 // API 19+
  animator.onPause { ... }                                  // API 19+
  
  animator.addListener(
    { /* onStart */},
    { /* onEnd */},
    { /* onCancel */},
    { /* onRepeat */},
  )
  
  animator.addPauseListener(
    { /* onResume */ },
    { /* onPause */ }
  )
  
  val transition = Explode()
  
  transition.onTransitionStart { ... }
  transition.onTransitionEnd { ... }
  transition.onTransitionResume { ... }
  transition.onTransitionPause { ... }
  transition.onTransitionCancel { ... }
  
  transition.addListener(
    { /* onTransitionStart */ },
    { /* onTransitionEnd */ },
    { /* onTransitionResume */ },
    { /* onTransitionPause */ },
    { /* onTransitionCancel */ }
  )
```

### Collections extensions

```kotlin
  val list = ...
  
  val randomItem = list.randomItem()                        // Get random item from any List
```

### Calendar extensions

```kotlin
  val calendar = ...
  
  calendar.getDayOfWeek()
  calendar.getDayOfMonth()
  calendar.getDayOfWeekInMonth()
  calendar.getDayOfYear()
  calendar.getWeekOfMonth()
  calendar.getHourOfDay()
  calendar.getYear()
  calendar.getHour()
  calendar.getMinute()
  calendar.getSecond()
  calendar.getMillisecond()
```

### File extensions

```kotlin
    uri.realPath(context)
    
    string.toUri()
    
    file.toUri()
    file.copyInputStreamToFile(inputStream)
```

### Image extensions

```kotlin
   val bitmap = ...
   
   bitmap.rotate(degrees)
   bitmap.toUriJpeg(context, title, description)
   bitmap.toUriPng(context, title, description)
   bitmap.toUriWebp(context, title, description)
   bitmap.makeCircle()
   bitmap.toDrawable(context)
   uri.toBitmap(context)
  
   val drawable = ...
   
   drawable.toBitmap()
   uri.toDrawable(context)
   
   val imageView = ...
  
   imageView set R.drawable.image_res
   imageView set drawable
   imageView set bitmap
   imageView set icon                                                       // API 23+
   imageView set uri
```

### Text extensions

```kotlin
   val editText = ...
   
   editText.afterTextChanged { editable -> ... }
   editText.beforTextChanged { charSequence, start, count, after -> ... }
   editText.onTextChanged { charSequence, start, before, count -> ... }
   editText.focus()                                                      // focus on this EditText
   editText.requestFocusAndKeyboard()                                    // focus and open keyboard
   editText.clearFocusAndKeyboard()                                      // remove focus and close keyboard
   
   val textView = ...
   
   textView set R.string.some_text_resource
   textView set someString
   textView set someSpannable
```

### View extensions

```kotlin
   val view = ...
   
   view.dismissKeyboard()
   
   /* Create snackbar on view, optional parameters
       duration (default value is Snackbar.LENGTH_SHORT)
       actionName (default value is ")
       actionTextColor (default value is 0)
       action (default value is {})
   */
   view.snackbar(someStringMessage)
   view.snackbar(R.string.some_message_res)
   
   /*Animate different properties of view, optional parameters
       duration (default value is 300)
       repeatCount (default value is 0)
       repeatMode (default value is 0)
   */
   view.animateTranslationX(floatArrayOf( ... ))
   view.animateTranslationY(floatArrayOf( ... ))
   view.animateTranslationZ(floatArrayOf( ... ))            // API 21+
   view.animateScaleX(floatArrayOf( ... ))
   view.animateScaleY(floatArrayOf( ... ))
   view.animateAlpha(floatArrayOf( ... ))
   view.animateRotation(floatArrayOf( ... ))
   view.animateRotationX(floatArrayOf( ... ))
   view.animateRotationY(floatArrayOf( ... ))
   view.animateX(floatArrayOf( ... ))
   view.animateY(floatArrayOf( ... ))
   view.animateZ(floatArrayOf( ... ))                       // API 21+
   
   
   val animator = view.translationXAnimator(floatArrayOf( ... ))
   val animator = view.translationYAnimator(floatArrayOf( ... ))
   val animator = view.translationZAnimator(floatArrayOf( ... ))            // API 21+
   val animator = view.scaleXAnimator(floatArrayOf( ... ))
   val animator = view.scaleYAnimator(floatArrayOf( ... ))
   val animator = view.alphaAnimator(floatArrayOf( ... ))
   val animator = view.rotationAnimator(floatArrayOf( ... ))
   val animator = view.rotationXAnimator(floatArrayOf( ... ))
   val animator = view.rotationYAnimator(floatArrayOf( ... ))
   val animator = view.xAnimator(floatArrayOf( ... ))
   val animator = view.yAnimator(floatArrayOf( ... ))
   val animator = view.zAnimator(floatArrayOf( ... ))                       // API 21+
   
   view width 150                           // set view width
   view hesght 100                          // set view height
   
   view visible flase                       // show or hide view
   
   val linearLayout = ...                   // or some other ViewGroup
   
   linearLayout inflate R.layout.some_layout_to_inflate
   linearLayout.forEach { childView -> }
   linearLayout.forEachIndexed { childView, index -> }
   linearLayout.isEmpty()
   linearLayout.isNotEmpty()
   
   val progressBar = ...
   
   progressBar.loaderColor(colorInt)
   
   val checkBox = ...                       // or some other compund button
   
   checkBox.onChecked { view, isChecked -> ... }
   
   val bottomSheetBehavior = ...
   
   bottomSheetBehavior.onSlide { bottomSheetView, slideOffset -> ... }
   bottomSheetBehavior.onStateChanged { bottomSheetView, newState -> ... }
```

### Dynamic layout extensions

```kotlin
   //Creating a spinner with single function
   val spinner = ...
   
   spinner.create(
      R.layout.spinner_item,
      R.id.text_view_id,
      items,                                    // array or mutable list
      onItemSelected = { item, postion -> ... }
   )
   
   //Creating an AutoCompleteTextView with single function
   val autoCompleteTextView = ...
   
   autoCompleteTextView.create(
      R.layout.auto_complete_text_item,
      R.id.text_view_id,
      items,                                    // array or mutable list
      onItemSelected = { textString, postion -> ... }
   )
   
   //Creating a ListView with single function
   val listView = ...
   
   listView.create(
      R.layout.list_item,
      items,                                    // array or mutable list
      creator = { item, position -> ... },      // write to list binding logic inside this lambda
      itemClick = { item, position -> ... },
      itemLongClick = { item, position -> ... }
   )
   
   //Creating a RecyclerView with single function
   val recyclerView = ...
   
   recyclerView.create(
      R.layout.recycler_list_item,
      items,                                    // array or mutable list
      linearLayoutManager,                      // or some other layout manager
      creator = { item, position -> ... },      // write to list binding logic inside this lambda
      itemClick = { item, position -> ... },
      itemLongClick = { item, position -> ... },
      onScrollTop = { ... },
      onScrollBottom = { ... }
   )
   
   //Creating a typed RecyclerView with single function
   recyclerView.createTypedList(
      mapOf(type1 to R.layout.type1, ...),
      items,                                    // array or mutable list
      linearLayoutManager,                      // or some other layout manager
      itemTypes = { position -> ... }           // declare types of items
      creator = { item, position, type -> ... },      // write to list binding logic inside this lambda
      itemClick = { item, position -> ... },
      itemLongClick = { item, position -> ... },
      onScrollTop = { ... },
      onScrollBottom = { ... }
   )
   
   recyclerView.addItem(item)
   recyclerView.updateItem(item, position)
   recyclerView.removeItem(position)
   
   //Creating a ViewPager with single function
   val viewPager = ...
   
   viewPager.createFragmentPager(
      items,                                    // array or mutable list of Fragments
      supportFragmentManager
   )
   
   viewPager.onPageScrollStateChanged { state -> ... }
   viewPager.onPageScrolled { position, positionOffset, positionOffsetPixels -> ... }
   viewPager.onPageSelected { position -> ... }
```
If you are customizing it and feel that the customizations are generic and would add value for other users of this library: **Pull Requests are most welcome! :-)**

## Usage JPrefHelper for JAVA and KPrefHelper for KOTLIN

> **Declaration of `JPrefHelper` object: (recommended at class level)**

    JPrefHelper sph; 
    
> **Instantiation of the `JPrefHelper` object: (recommended in `onCreate()` method)**
 
        // use one of the following ways to instantiate
        sph = new JPrefHelper(this); //this will use default shared preferences
        sph = new JPrefHelper(this, "myappprefs"); // this will create a named shared preference file
        sph = new JPrefHelper(this, "myappprefs", 0); // this will allow you to specify a mode

> **Putting values into shared preferences**

Fairly simple! Unlike the default way (when using the `SharedPreferences` class) you'll **NOT** need to call `.edit()` and `.commit()` ever time.

        sph.putBoolean("boolKey", true);
        sph.putInt("intKey", 123);
        sph.putString("stringKey", "string value");
        sph.putLong("longKey", 456876451);
        sph.putFloat("floatKey", 1.51f);

        // putStringSet is supported only for android versions above HONEYCOMB
        Set name = new HashSet();
        name.add("Viral");
        name.add("Patel");
        sph.putStringSet("name", name);

That's it! Your values are stored in the shared preferences.

> **Getting values from shared preferences**

Again, just one simple method call with the key name.

        sph.getBoolean("boolKey");
        sph.getInt("intKey");
        sph.getString("stringKey");
        sph.getLong("longKey");
        sph.getFloat("floatKey");

        // getStringSet is supported only for android versions above HONEYCOMB
        sph.getStringSet("name");

## Advanced Usage

**What if the value is not set for a given key already?** : It returns the default value.
 Default values are as follows:
 
        private int intDefaultVal = 0;
        private long longDefaultVal = 0;
        private float floatDefaultVal = 0;
        private boolean boolDefaultVal = false;
        private String stringDefaultVal = "";
        private Set<String> stringSetDefaultVal = null;

**What if i want a different value as default for each type?** : Check the next section to see how to change default value.

**What if i want a different default value only for a partylar key?** : Yes, you can achieve that by passing it as an additional parameter in the get method as follows:
 
        sph.getBoolean("boolKey", true);
        sph.getInt("intKey", -1);
        sph.getString("stringKey", "my custom default string");
        sph.getLong("longKey", -222);
        sph.getFloat("floatKey", -13.76f);

        // getStringSet is supported only for android versions above HONEYCOMB
        sph.getStringSet("name", new HashSet<String>());
 
> **Setting default values for each data type when no values are set**

Be careful with this as this will set the default value for the data type.

        sph.setBoolDefaultVal(true);
        sph.setFloatDefaultVal(-3.6f);
        sph.setIntDefaultVal(-3);
        sph.setLongDefaultVal(-999);
        sph.setStringDefaultVal("custom default string");
        sph.setStringSetDefaultVal(new HashSet<String>());

> **Registering an `OnSharedPreferenceChangeListener` for the shared preferences** 

Just like you do it for the normal `SharedPreferences` instance:

        JPrefHelper.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                // do what you got to do here
            }
        };
        
        sph.registerListener(listener);

> Unregistering the `OnSharedPreferenceChangeListener` (if registered)

Simply unregister the `listener` you registered above.

        sph.unregisterListener(listener);


#DateTimeUtils

### setTimeZone

``setTimeZone`` allow you to define your time zone by default it's ``UTC``

```java
DateTimeUtils.setTimeZone("UTC");
```
### formatDate

``formatDate`` is a method that allow you to convert ``date object`` to ``string`` or ``timeStamp`` to date and vice-versa.

#### Date string to Date object 

```java
// MySQL/SQLite dateTime example
Date date = DateTimeUtils.formatDate("2017-06-13 04:14:49");
// Or also with / separator
Date date = DateTimeUtils.formatDate("2017/06/13 04:14:49");
// MySQL/SQLite date example
Date date = DateTimeUtils.formatDate("2017-06-13");
// Or also with / separator
Date date = DateTimeUtils.formatDate("2017/06/13");
```
#### Date object to date string MySQL/SQLite

```java
String date = DateTimeUtils.formatDate(new Date());
```

#### timeStamp to Date object

By default it will considere given timeStamp in milliseconds but in case you did retrieve the timeStamp from server wich usually will be in seconds supply ``DateTimeUnits.SECONDS`` to tell the fonction about

```java
// Using milliseconds
Date date = DateTimeUtils.formatDate(1497399731000);
// Using seconds (Server timeStamp)
Date date = DateTimeUtils.formatDate(1497399731,DateTimeUnits.SECONDS);
```

### formatWithStyle

``formatWithStyle``  allow to parse date into localized format using most common style

#### Date object to localized date

```java
DateTimeUtils.formatWithStyle(new Date(), DateTimeStyle.FULL); // Tuesday, June 13, 2017
DateTimeUtils.formatWithStyle(new Date(), DateTimeStyle.LONG); // June 13, 2017
DateTimeUtils.formatWithStyle(new Date(), DateTimeStyle.MEDIUM); // Jun 13, 2017
DateTimeUtils.formatWithStyle(new Date(), DateTimeStyle.SHORT); // 06/13/17
```

#### Date string to localized date

```java
DateTimeUtils.formatWithStyle("2017-06-13", DateTimeStyle.FULL); // Tuesday, June 13, 2017
DateTimeUtils.formatWithStyle("2017-06-13", DateTimeStyle.LONG); // June 13, 2017
DateTimeUtils.formatWithStyle("2017-06-13", DateTimeStyle.MEDIUM); // Jun 13, 2017
DateTimeUtils.formatWithStyle("2017-06-13", DateTimeStyle.SHORT); // 06/13/17
```

### formatWithPattern

``formatWithPattern`` allow to define your own parse pattern following  ``SimpleDateFormat`` scheme

#### Date string as source

```java
DateTimeUtils.formatWithPattern("2017-06-13", "EEEE, MMMM dd, yyyy"); // Tuesday, June 13, 2017
```

#### Date object as source

```java
DateTimeUtils.formatWithPattern(new Date(), "EEEE, MMMM dd, yyyy"); // Tuesday, June 13, 2017
```

 ### isToday

``isToday`` Tell whether or not a given date is today date 

```java
// Date object as source
boolean state = DateTimeUtils.isToday(new Date());
// Date String as source
boolean state = DateTimeUtils.isToday("2017-06-15 04:14:49");
```

### isYesterday

``isYesterday`` Tell whether or not a given date is yesterday date 

```java
// Date object as source
boolean state = DateTimeUtils.isYesterday(new Date());
// Date String as source
boolean state = DateTimeUtils.isYestrday("2017-06-15 04:14:49");
```

### getDateDiff

``getDateDiff`` give you the difference between two date in days, hours, minutes, seconds or milliseconds ``DateTimeUnits`` 

```java
// Dates can be date object or date string
Date date = new Date();
String date2 = "2017-06-13 04:14:49";
// Get difference in milliseconds
int diff = DateTimeUtils.getDateDiff(date,date2, DateTimeUnits.MILLISECONDS);
// Get difference in seconds
int diff = DateTimeUtils.getDateDiff(date,date2, DateTimeUnits.SECONDS);
// Get difference in minutes
int diff = DateTimeUtils.getDateDiff(date,date2, DateTimeUnits.MINUTES);
// Get difference in hours
int diff = DateTimeUtils.getDateDiff(date,date2, DateTimeUnits.HOURS);
// Get difference in days
int diff = DateTimeUtils.getDateDiff(date,date2, DateTimeUnits.DAYS);
```

### getTimeAgo

``getTimeAgo`` give ou the elapsed time since a given date, it also offer two print mode the full and short strings ``eg . 3 hours ago | 3h ago`` the strings are localized but at the moment only FR and EN language are available. If you need your langage to be add just let me know :)

```java
String timeAgo = DateTimeUtils.getTimeAgo(context,new Date()); // Full string style will be used
// Short string style
String timeAgo = DateTimeUtils.getTimeAgo(context,"new Date()",DateTimeStyle.AGO_SHORT_STRING ); 
```

### formatTime

``formatTime`` allow you to extract time from date by default it wont show the hours if equal to ``0`` but you can supply ``forceShowHours`` parameter to force hours display

```java
String time = DateTimeUtils.formatTime(new Date()); // 14:49 if hours equals 0 or 04:14:09 if hours witch is wrong when use it on time rather than a duration
// Solution >> force hours display
String time = DateTimeUtils.formatTime(new Date(),true);
// And you can also supplie a date string
String time = DateTimeUtils.formatTime("2017-06-13 04:14:49"); // 04:14:49
```

### millisToTime

``millisToTime`` is usefull when your dealing with duration and want to display for example player duration or current playback position into human readable value.

```java
String time = DateTimeUtils.millisToTime(2515); // It take millis as an argument not seconds
```

### timeToMillis

``timeToMillis`` allow to convert ``time`` string to ``millseconds`` 

```java
int milliseconds = DateTimeUtils.timeToMillis("14:20"); // 860000
```

### Set Image URI in ImageView with cache stratogy (GLIDE)

```setImageUrl(url: String, img: ImageView)```

```

add "implementation 'com.github.bumptech.glide:glide:4.8.0'" in your project for working setImgeUrl function
and if you want to use circleimageview ,add "implementation 'de.hdodenhof:circleimageview:3.0.0'" in your project

``` 

```rateAction(activity: Activity)```

```shareText(subject: String, body: String, mContext: Context)```

```copyToClipboard(context: Context, data: String)```

```setSystemBarTransparent(act: Activity)```

```setSystemBarColor(act: Activity, @ColorRes color: Int) ```

for more functions visit https://github.com/nowfalsalahudeen/KdroidExt/blob/master/kdroidext/src/main/java/com/nowfal/kdroidext/Utils/Tools.kt


###Time and Date Humanizer
`TimeUnit.YEAR`, `TimeUnit.MONTH`, `TimeUnit.WEEK`, `TimeUnit.DAY`, `TimeUnit.HOUR`, `TimeUnit.MINUTE`, `TimeUnit.SECOND`, `TimeUnit.MILLISECOND`

```kotlin
val units = listOf(DurationHumanizer.TimeUnit.HOUR, DurationHumanizer.TimeUnit.MINUTE)
var options = DurationHumanizer.Options(units = units)
println(humanizer.humanize(97320000, options)) // 27 hours, 2 minutes
// You can also have your own custom units
val customUnit = DurationHumanizer.TimeUnit("o", 5000)
options = DurationHumanizer.Options(
    units = listOf(customUnit),
    language = "cl",
    languages = mapOf("cl" to customLanguage)
)
println(humanizer.humanize(97320000, options)) // 19464 o-abc
```

**round**

Boolean value. Use `true` to [round](https://en.wikipedia.org/wiki/Rounding#Round_half_up) the smallest unit displayed (can be combined with `largest` and `units`).

```kotlin
val options = DurationHumanizer.Options(units = listOf(DurationHumanizer.TimeUnit.HOUR), round = true)
println(humanizer.humanize(97320000, options)) // 27 hours
```

**decimal**

String to substitute for the decimal point in a decimal fraction.

```kotlin
val options = DurationHumanizer.Options(units = listOf(DurationHumanizer.TimeUnit.HOUR), round = false, decimal = ",")
println(humanizer.humanize(97560000, options)) // 27,1 hours
```

**conjunction**

String to include before the final unit. You can also set `serialComma` to `false` to eliminate the final comma.

```kotlin
var options = DurationHumanizer.Options(conjunction = " and ")
println(humanizer.humanize(22141000, options)) // 6 hours, 9 minutes, and 1 second
options = DurationHumanizer.Options(conjunction = " and ", serialComma = false)
println(humanizer.humanize(22141000, options)) // 6 hours, 9 minutes and 1 second
```

Special Thanks to Mohammad Mirrajabi (mirrajabi) for his awsome effort work on humanizer library . Iam just merge his code base into my library for some useful things





## Documentation

## Examples

Starting an activity:
```kotlin
startActivity<FooActivity> {
	putExtra(EXTRA_TITLE, title)
}
```

Showing an `AlertDialog`:
```kotlin
showAlertDialog(R.string.delete_everything) {
	positiveButton(R.string.yes) { _, _ -> deleteAll() }
	negativeButton(R.string.no)
}
```

Showing a `Snackbar`:
```kotlin
showSnackbar(view, R.string.do_you_agree) {
	setAction(R.string.agree) { validateAndSubmit(true) }
}
```

Popping a `Toast`:
```kotlin
showToast(R.string.update_successful)
```

Inflating a `View`:
```kotlin
val layout = container.inflate(R.layout.template_row)
```

Writing to `SharedPreferences`:
```kotlin
defaultSharedPreferences.edit {
	put(PREFS_LAST_SYNC, System.currentTimeMillis())
}
```

Creating a new `Bundle`:
```kotlin
newBundle {
	put(ARG_ANSWER, 42)
}
```

### Fragments

Many helper methods are accessible in a `Fragment` without a reference to the
container activity, safely:

```kotlin
startActivity<FooActivity> {
	putExtra(EXTRA_TITLE, title)
}
```

If `getActivity()` is null, `startActivity<>()` is never executed.

Generally, parameters that can be determined at runtime can be skipped
in the context of a `Fragment`. For instance, for `showSnackbar()`, the `view`
parameter can be omitted, e.g.:
```kotlin
showSnackbar(R.string.error)
```

### Collections

KotX includes a growing list of helpers for collections and lists
that are native to Android. These methods are thread-safe where possible;
e.g. `LruCache.getOrPut()`:
```kotlin
holder.title!!.text = titleCache.getOrPut("title") {
	buildTitle(object)
}
```

Get list of values in a `SparseArray`:
```kotlin
val items: List<String> = sparseArray.values
```

### Preferences

In addition writing to `SharedPreferences` via `Editor` directly, you
can write individual values via delegate properties:
```kotlin
var color: String? by Preferences.sharedPrefs

// Corresponds to Preferences.sharedPrefs.getString("color", "red")
val colorWithDefault: String?
    get() = color ?: "red"

Log.v(LOG_TAG, "color: $colorWithDefault")
color = "blue"
Log.v(LOG_TAG, "color: $colorWithDefault")

// Outputs:
// color: red
// color: blue
```

Delegate properties return nullable types to denote missing values,
even for primitive types such as `Integer`. Writing a `null` value
removes the key/value pair from `SharedPreferences`.

## Safety

Many helpers deal with nullability issues safely. For instance, to
avoid errors in a `Fragment` that's detached from activity,
you can safely get it as a nullable `String`:
```kotlin
getStringSafe(R.string.foo)
```

Similarly `Fragment.resourcesSafe` will return a null `Resources`
reference if the activity is detached.

## Tools

Use `ViewParser` to tag `View` layout ids, then assign them at runtime -
even for read-only properties:

```kotlin
@ViewParser.Leaf(layoutId = R.id.message_text)
private val message: TextView? = null

// You can omit layoutId if the resource identifier matches the variable's
// underscore-delimited version (confirm_button in this case):
@ViewParser.Leaf
private val confirmButton: Button? = null

// ... later, in Fragment.onCreateView() ...

val layout = container.inflate(R.layout.fragment_main)
layout.parseViewTree(this)
message!!.text = "It works!"

// ... or Activity.onCreate() ...

setContentView(R.layout.activity_main)
parseViewTree()
message!!.text = "It works!"
```

Use `ByteArrayBuilder` to build byte arrays in a manner similar to
`StringBuilder` - without resorting to IO streams or direct copying:

```kotlin
val encryptedString = buildByteArray {
	append("Salted__".toByteArray())
	append(salt)
	append(cipher.doFinal(message.toByteArray()))
}.toBase64String()
```

## Date/Time

Java's poor handling of date/time types isn't exactly news. In place of a heavy
reimplementation or simple syntactic sugar, KotX introduces `DateTime` and
`MutableDateTime`, which act as thin wrappers around Java's `Calendar`:

```kotlin
// Basics:
DateTime(1983, 7, 15, 8, 0, 59) // Specific instance using current time zone
DateTime(1983, 7, 15, timeZoneId = "America/Los_Angeles") // or any time zone...
DateTime.now // Get current time/date
DateTime.today // Get today's date with time components set to 0

// Simple formatting using current time zone:
time.toString("MM/dd/yyyy HH:mm:ss")
// Using a custom formatter:
time.toString("MM/dd/yyyy HH:mm:ss".toDateFormat(tzCode, Locale.US))

// Parsing in current time zone:
val parsed = DateTime.parse("7/15/1983", "MM/dd/yyyy")
val wrong = DateTime.tryParse("HELLO!", "MM/dd/yyyy") // Returns null on error

// Math using specific units:
time.addedYears(3) // Return a new DateTime instance
time.addedMonths(6)
time.addedDays(17)
```

In addition to `compareTo` and `equals`, `DateTime` includes additional
helpers using Kotlin's infix syntax:

```kotlin
when {
	time1 on time2 -> // ...
	time1 before time2 -> // ...
	time1 after time2 -> // ...
}
```

`on` (and variants `onOrBefore` and `onOrAfter`) is particularly useful for
performing time zone-agnostic equality checks, since `equals` itself is not time
zone agnostic.

Any methods called on a `DateTime` instance leave the receiver unmodified -
thus `DateTime` is immutable. KotX also includes a mutable subclass called
`MutableDateTime`, which adds functions and properties that modify the object
in-place:

```kotlin
val mutable = MutableDateTime.now
mutable.year = 2043 // ... set a component explicitly
mutable.addDays(17) // ... increment a particular component
```

 Because KotX is an Android library, `DateTime` and `MutableDateTime` are
 both `Parcelable`:

 ```kotlin
 // Using the usual syntax:
dest.writeParcelable(time, 0)
time = source.readParcelable(DateTime::class.java.classLoader)
// or...
mutableTime = source.readParcelable(MutableDateTime::class.java.classLoader)

// Using extension methods:
dest.writeDateTime(time)
time = source.readDateTime()
// or...
mutableTime = source.readMutableDateTime()
```

The hit of `DateTime` on the size of the parcel is minor - it stores only
the number of milliseconds since epoch (`Long`) and a string identifying the
time zone.

`DateTime` and `MutableDateTime` work well with existing types, such as
`Calendar` and `Long` (milliseconds since epoch), and most methods that take
`DateTime` will also accept the `Calendar` and `Long`.

Finally, it's important to note that unlike `Calendar`, the `DateTime`'s first
month is numbered 1, not zero. Any methods expecting/returning the month number
will adjust accordingly.

## Advanced

Sending local broadcasts:
```kotlin
context.broadcastLocal(AUTH_ERROR)
```

Accessing the `TextView` of a `Snackbar`:
```kotlin
showSnackbar(R.string.access_denied) {
	textView?.maxLines = 1
}
```

Reading style attributes:
```kotlin
var textColor = Color.parseColor("#fff")
view.context.withStyledAttributes(R.styleable.GaugeView, attrs) {
	textColor = getColor(R.styleable.GaugeView_textColor, textColor)
}
```

## Etc.

Examples listed here aren't exhaustive, particularly with regard to `Context`.

Thanks for Akop Karapetyan for kotx library.. i just fork him repo for my personal ease of use


## License

> Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at `http://www.apache.org/licenses/LICENSE-2.0`

> Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
