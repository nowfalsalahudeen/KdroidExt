

## Documentation

[KDoc-style documentation](https://0xe1f.github.io/KotX/library/)

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

