package com.thunderclouddev.goodreadsapisdk.api

import org.simpleframework.xml.Element

/**
 * @author David Whitman on 11 Mar, 2017.
 */
internal class Request(
        @field:Element(name = "authentication") var authentication: Boolean,
        @field:Element(name = "key") var key: String,
        @field:Element(name = "method") var method: String
) {
    constructor() : this(false, "", "")
}