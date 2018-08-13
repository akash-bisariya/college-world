package com.one.vms.ui.base


/**
 * @author Akash Bisariya
 * Base Presenter
 */
interface BasePresenter {

    /**
     * Indicates when the view has attached ( created )
     */
    fun onAttach()

    /**
     * Indicates when the view has detached ( destroyed )
     */
    fun onDetach()

    /**
     * success handling
     */
    fun onDbConnectionSuccess()

    /**
     * failure handling
     */
    fun onDbConnectionFailure(exception: Exception)
}