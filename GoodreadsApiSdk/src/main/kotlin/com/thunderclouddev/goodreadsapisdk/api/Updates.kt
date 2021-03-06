package com.thunderclouddev.goodreadsapisdk.api

import org.simpleframework.xml.ElementList

/**
 * Created by David Whitman on 16 Mar, 2017.
 */
internal data class Updates(
        @field:ElementList(inline = true, entry = "update") var updates: ArrayList<Update> = arrayListOf()
)