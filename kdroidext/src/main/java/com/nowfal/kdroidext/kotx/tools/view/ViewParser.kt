

package com.nowfal.kdroidext.kotx.tools.view

import android.app.Activity
import android.content.Context
import android.util.SparseArray
import androidx.annotation.IdRes
import android.view.View
import android.view.ViewGroup
import com.nowfal.kdroidext.kotx.ext.app.contentView
import com.nowfal.kdroidext.kotx.ext.collections.set
import com.nowfal.kdroidext.kotx.ext.view.children
import java.lang.reflect.Field


/**
 * Parses a view hierarchy and maps out references to specific views by
 * reading annotations.
 *
 * For instance, in an activity, declare a member variable as follows:
 *
 * ```
 * @ViewParser.Leaf(layoutId = R.id.code)
 * private val codeField: EditText? = null
 * ```
 *
 * and have [ViewParser] parse the hierarchy after [Activity.setContentView]:
 *
 * ```
 * setContentView(R.layout.activity_main)
 * parseViewTree()
 * ```
 *
 * When [Leaf.layoutId] is omitted, [ViewParser] will look for a resource
 * id that matches the variable's name, automatically converting camelCase
 * member variable names to underscore-delimited resources id's
 * (e.g. codeField => code_field).
 *
 * Using annotations with [ViewParser] is more efficient than using
 * [View.findViewById] alone, because view tree traversal is done only once.
 */
object ViewParser {

    @Target(AnnotationTarget.FIELD)
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Leaf(@IdRes val layoutId: Int = 0)

    /**
     * Traverses view hierarchy of an [activity], then for each member variable
     * of the activity annotated with [Leaf], assigns a reference to the
     * corresponding [View]. Should be called after [Activity.setContentView].
     *
     * Member variables can be immutable.
     */
    fun parse(activity: Activity) {
        parse(activity.contentView!!, activity)
    }

    /**
     * Traverses view hierarchy of an [activity], then for each member variable
     * of [obj] annotated with [Leaf], assigns a reference to the corresponding
     * [View]. Should be called after [Activity.setContentView].
     *
     * Member variables can be immutable.
     */
    fun parse(activity: Activity, obj: Any) {
        parse(activity.contentView!!, obj)
    }

    /**
     * Traverses hierarchy of [view], then for each member variable
     * of [obj] annotated with [Leaf], assigns a reference to the corresponding
     * [View].
     *
     * Member variables can be immutable.
     */
    fun parse(view: View, obj: Any): View {
        val allFields = ArrayList<Field>()
        obj.javaClass.collectFieldsTo(allFields)

        val viewMap = SparseArray<View>()
        mapOutViewTree(view, viewMap)

        allFields.forEach { field ->
            field.isAccessible = true
            field.getAnnotation(ViewParser.Leaf::class.java)?.let { vattr ->
                @IdRes val id = resolveLayoutId(field, vattr, view.context)
                if (id != 0) field.set(obj, viewMap[id])
            }
        }

        return view
    }

    private fun mapOutViewTree(view: View, array: SparseArray<View>) {
        if (view.id != View.NO_ID) array[view.id] = view
        (view as? ViewGroup)
                ?.children()
                ?.forEach { mapOutViewTree(it, array) }
    }

    @IdRes
    private fun resolveLayoutId(field: Field, vattr: Leaf, context: Context): Int {
        @IdRes val id = vattr.layoutId
        if (id != 0) return id

        val expectedName = field.name.replace("[A-Z]".toRegex()) {
            "_${it.value.toLowerCase()}"
        }
        return context.resources.getIdentifier(expectedName, "id",
                context.packageName)
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun Class<*>.collectFieldsTo(fields: MutableList<Field>) {
        fields += declaredFields
        superclass?.collectFieldsTo(fields)
    }
}

/**
 * Helper extension function - see [ViewParser.parse].
 */
fun Activity.parseViewTree(obj: Any = this) {
    ViewParser.parse(this, obj)
}

/**
 * Helper extension function - see [ViewParser.parse].
 */
fun View.parseViewTree(obj: Any) {
    ViewParser.parse(this, obj)
}
