package com.thunderclouddev.tirforgoodreads

import android.view.View
import com.thunderclouddev.tirforgoodreads.uri.Uri

/**
 * Created by David Whitman on 11 Mar, 2017.
 */
fun Throwable.hasCause(type: Class<*>): Boolean {
    var cause = this

    while (cause.cause != null) {
        cause = cause.cause!!

        if (cause.javaClass.name == type.name) {
            return true
        }
    }

    return false
}

val Any?.simpleClassName: String
    get() = this?.javaClass?.simpleName ?: String.empty

@Suppress("unused")
val String.Companion.empty: String
    get() = ""

fun String?.getOrNullIfBlank() = if (this.isNullOrBlank()) null else this

fun CharSequence?.isNotNullOrBlank() = !this.isNullOrBlank()

fun CharSequence?.getOrDefaultIfNullOrBlank(defaultValue: String) = if (this.isNullOrBlank()) this else defaultValue

val Boolean.visibleOrGone: Int
    get() = if (this) View.VISIBLE else View.GONE

var View.showing: Boolean
    get() = this.visibility == View.VISIBLE
    set(value) {
        this.visibility = value.visibleOrGone
    }

fun <T> Iterable<T>.firstOr(defaultItem: T): T {
    return this.firstOrNull() ?: defaultItem
}

fun String.isUri() = Uri.parse(this).scheme.isNotNullOrBlank() && Uri.parse(this).authority.isNotNullOrBlank()