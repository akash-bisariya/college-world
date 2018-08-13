package com.one.vms.ui.base


/**
 * @author Akash Bisariya
 * Implementation class
 */
open class BasePresenterImpl : BasePresenter{
    override fun onDbConnectionFailure(exception: Exception) {

    }

    override fun onDbConnectionSuccess() {

    }



    private var mIsViewAttached: Boolean = false

    override fun onAttach() {
        mIsViewAttached = true
    }

    override fun onDetach() {
        mIsViewAttached = false
    }

    /**
     * Checks if the view is attached
     *
     * @return true if view is attached else false
     */
    fun isViewAttached(): Boolean {
        return mIsViewAttached
    }
}