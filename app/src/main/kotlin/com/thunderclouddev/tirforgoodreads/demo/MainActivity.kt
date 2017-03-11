package com.thunderclouddev.tirforgoodreads.demo

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.thunderclouddev.tirforgoodreads.R

/**
 * Created by David Whitman on 11 Mar, 2017.
 */
class MainActivity : AppCompatActivity(), ActionBarProvider {
    override val actionBar: ActionBar
        get() = supportActionBar!!

    private var router: Router? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.toolbar) as Toolbar)

        router = Conductor.attachRouter(this, findViewById(R.id.controller_container) as ViewGroup,
            savedInstanceState)
        if (!router!!.hasRootController()) {
            router!!.setRoot(RouterTransaction.with(ViewBooksByAuthorController()))
        }
    }

    override fun onBackPressed() {
        if (!router!!.handleBack()) {
            super.onBackPressed()
        }
    }
}