package pers.loren.jetpackbase.ui.activity

import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.launcher_activity.*
import pers.loren.jetpackbase.R
import pers.loren.jetpackbase.base.ui.BaseActivity
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
 * ******** issues ********
 * 每次切换导航会刷新fragment
 */
class LauncherActivity : BaseActivity() {

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    override fun useTitleBar() = false

    override fun useImmersion() = true

    override fun initWidgets() {
        parent_ll.setPaddingTop(getStatusBarHeight())
        val navController = Navigation.findNavController(this, R.id.main_nav_fragment)
        NavigationUI.setupWithNavController(bottom_nav, navController)
        //会返回到上一次点击的导航页
//        bottom_nav.setOnNavigationItemSelectedListener { item ->
//            onNavDestinationSelected(item, navController)
//        }
        navController.addOnNavigatedListener { _, destination ->
            when (destination.id) {
                R.id.AFragment, R.id.BFragment, R.id.CFragment, R.id.DFragment -> {
                    bottom_nav.visibility = View.VISIBLE
                }
                else -> bottom_nav.visibility = View.GONE
            }
        }
    }

    override fun setListeners() {
    }

    override fun onWidgetsClick(v: View) {
    }

    override fun bindLayout() = R.layout.launcher_activity

}