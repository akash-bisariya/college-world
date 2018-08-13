package com.one.vms.ui.base


/**
 * @author Akash Bisariya
 *
 */
interface BaseView {

    /**
     * Checks if Wifi connection is available
     *
     * @return if connectivity is available
     */
    fun isWIFIConnected(): Boolean


    /**
     * Show loader with message
     *
     * @param message message which display with loading dialog
     */
    fun showLoading(stringId: Int)

    /**
     * Hide loader
     */
    fun hideLoading()

    /**
     * Show error message
     *
     * @param stringId the message id
     */
    fun showErrorMessage(stringId: Int)

    /**
     * Show error message.
     *
     * @param message                 the message
     * @param okText                  ok button text
     * @param cancelText              cancel button text
     * @param mOnConfirmationCallback the error handle callback
     */
    fun showErrorMessageWithOption(message: Int, okText: Int, cancelText: Int, mOnConfirmationCallback: OnConfirmationCallback)

    /**
     * Show error message.
     *
     * @param message                 the message
     * @param mOnErrorHandleCallback the error handle callback
     */
    fun showErrorMessage(message: Int, mOnErrorHandleCallback: OnErrorHandleCallback?)

    /**
     * Db query result
     */
    fun onDbQueryResult()


    /**
     * The interface On error handle callback.
     */
    interface OnErrorHandleCallback {
        /**
         * On error callback.
         */
        fun onErrorCallback()
    }

    /**
     * The interface On error handle callback.
     */
    interface OnConfirmationCallback {
        /**
         * On error callback.
         */
        fun onOkCallback()

        /**
         * on cancel
         */
        fun onCancelCallback()
    }
}