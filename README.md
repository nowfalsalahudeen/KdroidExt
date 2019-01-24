# KdroidExt
[![Release](https://jitpack.io/v/jitpack/android-example.svg)](https://jitpack.io/#nowfalsalahudeen/KdroidExt) [![API](https://img.shields.io/badge/API-14%2B-yellow.svg?style=flat-square)](https://android-arsenal.com/api?level=14)

<img src="img/demo.png" width="160px">

Kotlin library for Android providing useful extensions to eliminate boilerplate code in Android SDK and focus on productivity.


Use the power of Kotlin to make your code smaller and beautiful.

Download
--------

Download latest version with Gradle:
```groovy
repositories {
    jcenter()
    maven { url 'https://jitpack.io' }
}

dependencies {
     implementation 'com.github.nowfalsalahudeen:KdroidExt:0.0.1'
}
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