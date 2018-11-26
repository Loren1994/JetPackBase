package pers.loren.jetpackbase.ui.activity

import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.launcher_activity.*
import pers.loren.jetpackbase.R
import pers.loren.jetpackbase.base.ui.BaseActivity
import pers.loren.jetpackbase.interfaces.IHomeChangeFragment
import pers.victor.ext.getStatusBarHeight
import pers.victor.ext.setPaddingTop


/**
 * Copyright © 2018/10/31 by loren
 * Lifecycle
 * Navigation
 * LiveData
 * ViewModel
 * Paging
 * Room
 * WorkerManager
 */
class LauncherActivity : BaseActivity(), IHomeChangeFragment {

    override fun visible() {
        bottom_nav.visibility = View.VISIBLE
    }

    override fun gone() {
        bottom_nav.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    override fun useTitleBar() = false

    override fun useImmersion() = true

    override fun initWidgets() {
        parent_ll.setPaddingTop(getStatusBarHeight())
        val navController = Navigation.findNavController(this, R.id.main_nav_fragment)
        NavigationUI.setupWithNavController(bottom_nav, navController)
    }

    override fun setListeners() {
    }

    override fun onWidgetsClick(v: View) {
    }

    override fun bindLayout() = R.layout.launcher_activity

}