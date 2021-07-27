package com.mehmetkaya.utils.epoxy

import android.view.View
import android.view.ViewParent
import androidx.viewbinding.ViewBinding
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.mehmetkaya.utils.exts.unsafeLazy
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.util.concurrent.ConcurrentHashMap

// Taken from: https://github.com/airbnb/epoxy/blob/master/kotlinsample/src/main/java/com/airbnb/epoxy/kotlinsample/helpers/ViewBindingEpoxyModelWithHolder.kt
abstract class ViewBindingEpoxyModel<in T : ViewBinding> : EpoxyModelWithHolder<ViewBindingHolder>() {

    @Suppress("UNCHECKED_CAST")
    override fun bind(holder: ViewBindingHolder) {
        val viewBinding = holder.viewBinding as T
        viewBinding.bind()
    }

    @Suppress("UNCHECKED_CAST")
    override fun unbind(holder: ViewBindingHolder) {
        val viewBinding = holder.viewBinding as T
        viewBinding.unbind()
    }

    abstract fun T.bind()

    open fun T.unbind() = Unit

    override fun createNewHolder(parent: ViewParent) = ViewBindingHolder(this::class.java)
}

// Static cache of a method pointer for each type of item used.
private val BindingMethodByClass = ConcurrentHashMap<Class<*>, Method>()

@Suppress("UNCHECKED_CAST")
@Synchronized
/* synthetic */ internal fun getBindMethodFrom(javaClass: Class<*>) = BindingMethodByClass.getOrPut(javaClass) {
    val actualTypeOfThis = getSuperclassParameterizedType(javaClass)
    val viewBindingClass = actualTypeOfThis.actualTypeArguments[0] as Class<ViewBinding>

    viewBindingClass.getDeclaredMethod("bind", View::class.java)
}

private fun getSuperclassParameterizedType(klass: Class<*>): ParameterizedType {
    val genericSuperclass = klass.genericSuperclass
    val parameterizedType = genericSuperclass as? ParameterizedType

    return parameterizedType ?: getSuperclassParameterizedType(genericSuperclass as Class<*>)
}

class ViewBindingHolder(private val epoxyModelClass: Class<*>) : EpoxyHolder() {
    // Using reflection to get the static binding method.
    // Lazy so it's computed only once by instance, when the 1st ViewHolder is actually created.
    private val bindingMethod by unsafeLazy { getBindMethodFrom(epoxyModelClass) }

    lateinit var viewBinding: ViewBinding

    override fun bindView(itemView: View) {
        // The 1st param is null because the binding method is static.
        viewBinding = bindingMethod.invoke(null, itemView) as ViewBinding
    }
}
