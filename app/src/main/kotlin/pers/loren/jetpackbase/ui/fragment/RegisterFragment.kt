package pers.loren.jetpackbase.ui.fragment

import android.transition.AutoTransition
import android.view.View
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.register_fragment.*
import pers.loren.jetpackbase.R
import pers.loren.jetpackbase.base.ui.BaseFragment

/**
 * Copyright © 2018/11/23 by loren
 */
class RegisterFragment : BaseFragment() {

    override fun initWidgets() {
        sharedElementEnterTransition = AutoTransition()
        sharedElementReturnTransition = AutoTransition()
        register_btn.setOnClickListener { Navigation.findNavController(it).navigateUp() }
    }

    override fun setListeners() {
    }

    override fun onWidgetsClick(v: View) {
    }

    override fun bindLayout() = R.layout.register_fragment
}