package com.one.vms.ui.base

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.collegeworld.R
import com.one.vms.util.CommonUtil

/**
 * @author Akash Bisariya
 * Base activity
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_in, R.anim.right_out)
    }


    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val view = currentFocus
        try {
            val ret = super.dispatchTouchEvent(event)
            if (view != null && view is EditText) {
                val w = currentFocus
                val scrcoords = IntArray(2)
                assert(w != null)
                w!!.getLocationOnScreen(scrcoords)
                val x = event.rawX + w.left - scrcoords[0]
                val y = event.rawY + w.top - scrcoords[1]
                if (event.action == MotionEvent.ACTION_UP && (x < w.left || x >= w.right
                                || y < w.top || y > w.bottom)) {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(window.currentFocus!!.windowToken, 0)
                }
            }
            return ret
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }

    override fun isWIFIConnected(): Boolean {
        if(!CommonUtil.isNetworkAvailable(this)){
            showErrorMessage(120)
            return false
        }
        return true
    }


    override fun showLoading(stringId: Int) {

    }

    override fun hideLoading() {
    }

    override fun showErrorMessage(stringId: Int) {
        showErrorMessage(stringId, null)
    }

    override fun showErrorMessageWithOption(message: Int, okText: Int, cancelText: Int, mOnConfirmationCallback: BaseView.OnConfirmationCallback) {
        AlertDialog.Builder(this)
                .setMessage(getString(message))
                .setCancelable(false)

                .setPositiveButton(getString(okText), object :DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        mOnConfirmationCallback.onOkCallback()
                    }

                })
                .setNegativeButton(getString(cancelText), DialogInterface.OnClickListener { dialog, which -> mOnConfirmationCallback.onCancelCallback() })
                .show()

    }

    override fun showErrorMessage(message: Int, mOnErrorHandleCallback: BaseView.OnErrorHandleCallback?) {
        AlertDialog.Builder(this)
                .setMessage(getString(message))
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { dialog, which ->
                    if (mOnErrorHandleCallback != null) {
                        mOnErrorHandleCallback.onErrorCallback()
                    }
                })
                .show()
    }


}
