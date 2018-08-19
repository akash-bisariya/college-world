package com.collegeworld.ui.login

import com.one.vms.ui.base.BaseView

interface LoginView:BaseView {
    fun navigateToHome()
    fun onError()
}