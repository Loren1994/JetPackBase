package pers.loren.jetpackbase.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.view.View
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.a_fragment.*
import pers.loren.jetpackbase.R
import pers.loren.jetpackbase.adapters.LatestAdapter
import pers.loren.jetpackbase.base.ext.log
import pers.loren.jetpackbase.base.ui.BaseFragment
import pers.loren.jetpackbase.diff.LatestDiffItemCallback
import pers.loren.jetpackbase.lifecycleObserver.LauncherObserver
import pers.loren.jetpackbase.repository.LatestRepository
import pers.loren.jetpackbase.viewModels.LatestViewModel


/**
 * Copyright © 2018/11/23 by loren
 */
class AFragment : BaseFragment() {

    private val mAdapter by lazy { LatestAdapter(LatestDiffItemCallback()) }
    private val latestVM by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return LatestViewModel(LatestRepository()) as T
            }
        })[LatestViewModel::class.java]
    }

    override fun useTitleBar() = true

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycle.addObserver(LauncherObserver("A"))
    }

    override fun initWidgets() {
        setTitleBarText("主页")
        setTitleBarLeftVisibility(View.INVISIBLE)
        setTitleBarRight("登录")
        setTitleBarRightClick {
            val bundle = LoginFragmentArgs.Builder().setText("登录").build().toBundle()
            Navigation.findNavController(titleBar!!).navigate(R.id.action_AFragment_to_LoginFragment, bundle)
        }
        latest_rv.adapter = mAdapter
        latestVM.page.observe(this, Observer {
            log("UI:${it?.size}")
            mAdapter.submitList(it)
            swipe_layout.isRefreshing = false
        })
        swipe_layout.setOnRefreshListener {
            log("刷新中")
            latestVM.showData()
        }
        swipe_layout.isRefreshing = true
        swipe_layout.postDelayed({ latestVM.showData() }, 1000)
    }

    override fun setListeners() {
    }

    override fun onWidgetsClick(v: View) {
    }

    override fun bindLayout() = R.layout.a_fragment
}