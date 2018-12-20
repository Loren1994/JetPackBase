package pers.loren.jetpackbase.ui.fragment

import android.transition.AutoTransition
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import kotlinx.android.synthetic.main.login_fragment.*
import pers.loren.jetpackbase.R
import pers.loren.jetpackbase.base.ui.BaseFragment


/**
 * Copyright © 2018/11/23 by loren
 */
class LoginFragment : BaseFragment() {

    override fun initWidgets() {
        val extras = FragmentNavigator.Extras.Builder()
                .addSharedElement(title_tv, "title")
                .addSharedElement(account_tl, "one_input")
                .addSharedElement(pwd_tl, "two_input")
                .addSharedElement(login_btn, "one_btn")
                .build()
        title_tv.text = LoginFragmentArgs.fromBundle(arguments).text
        register_btn.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_LoginFragment_to_registerFragment, null, null, extras) }
        login_btn.setOnClickListener { Navigation.findNavController(it).navigateUp() }
        sharedElementEnterTransition = AutoTransition()
        sharedElementReturnTransition = AutoTransition()
    }

    override fun setListeners() {
    }

    override fun onWidgetsClick(v: View) {
    }

    override fun bindLayout() = R.layout.login_fragment
}