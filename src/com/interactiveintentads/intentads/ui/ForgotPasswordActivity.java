package com.interactiveintentads.intentads.ui;

import android.app.Activity;
import android.app.AlertDialog;
import com.parse.ParseException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.interactiveintentads.intentads.R;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class ForgotPasswordActivity extends Activity {
	
	    protected EditText mEmailField;
	    protected Button mPasswordResetBtn;

	    String TAG = ForgotPasswordActivity.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_password);
	

	mEmailField = (EditText) findViewById(R.id.emailField);
    mPasswordResetBtn = (Button) findViewById(R.id.passwordResetBtn);

    mPasswordResetBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email = mEmailField.getText().toString();
            email = email.trim();

            if ( email.isEmpty() ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this);
                builder.setMessage(R.string.forgot_pw_error_msg)
                        .setTitle(R.string.oops)
                        .setPositiveButton(android.R.string.ok, null);

                AlertDialog dialog = builder.create();
                dialog.show();
            } else {  // try to send password reset email
                ParseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
                    
                	@Override
                    public void done(ParseException e) {
                        if ( e  == null ) {
                            Toast.makeText(ForgotPasswordActivity.this, R.string.pw_reset_success, Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this);
                            builder.setMessage(e.getMessage())
                                    .setTitle(R.string.oops)
                                    .setPositiveButton(android.R.string.ok, null);

                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });
            }
        }
    });


}



}
