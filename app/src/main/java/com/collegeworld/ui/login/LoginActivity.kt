package com.collegeworld.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.collegeworld.R
import com.google.android.gms.auth.api.signin.*
import com.one.vms.ui.base.BaseActivity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.one.vms.util.RC_GOOGLE_SIGN_IN
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity(),LoginView , View.OnClickListener{
    private var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_sign_in_google.setOnClickListener(this)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    override fun navigateToHome() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(view: View?) {
        when(view!!.id)
        {
            R.id.btn_sign_in_google->
            {
                startActivityForResult(mGoogleSignInClient!!.signInIntent, RC_GOOGLE_SIGN_IN)
            }
        }
    }

    private fun handleSignResult(task: Task<GoogleSignInAccount>?)
    {
        try{
            if(task!=null)
            task.getResult(ApiException::class.java).apply {
                Toast.makeText(this@LoginActivity,displayName+email,Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this@LoginActivity,"Some error occurred",Toast.LENGTH_SHORT).show()
        }
        catch (e:ApiException)
        {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== RC_GOOGLE_SIGN_IN)
        {
            val task: Task<GoogleSignInAccount>? = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignResult(task)
        }
    }


}
