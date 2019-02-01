package com.app.drylining.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.app.drylining.R;
import com.app.drylining.model.ForgotpasswordModel;
import com.app.drylining.retrofit.ApiClient;
import com.app.drylining.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    private ProgressDialog pdialog;
    private static final String TAG = "ForgotPasswordActivity";
    private EditText txtEmail;
    private TextView lblError, msgError, btnBack, lblTitle;
    private Button btnSubmit;
    String mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initialize();
    }

    private void initialize() {
        lblTitle = (TextView) findViewById(R.id.lbl_title);
        txtEmail = (EditText) findViewById(R.id.txt_email);
        lblError = (TextView) findViewById(R.id.lbl_error);
        msgError = (TextView) findViewById(R.id.msg_error);
        btnBack = (TextView) findViewById(R.id.btn_back);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        lblError.setVisibility(View.GONE);
        backButton();
        btnSubmitClick();

    }

    private void btnSubmitClick() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void backButton() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lIntent = new Intent();
                lIntent.setClass(ForgotPasswordActivity.this, MainActivity.class);
                startActivity(lIntent);
                finish();
            }
        });
    }


    public void submit() {
        mEmail = txtEmail.getText().toString().trim();
        if (mEmail.equals("")) {
            msgError.setVisibility(View.VISIBLE);
            lblError.setVisibility(View.VISIBLE);
        } else if (!mEmail.matches(("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))) {
            Toast.makeText(ForgotPasswordActivity.this, "Please enter valid email Address", Toast.LENGTH_SHORT).show();
        } else {
            apicall();
        }
    }

    private void apicall() {
        showProgressDialog();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<ForgotpasswordModel> modelCall = apiService.forgatePassword(mEmail);
        modelCall.enqueue(new Callback<ForgotpasswordModel>() {
            @Override
            public void onResponse(Call<ForgotpasswordModel> call, Response<ForgotpasswordModel> response) {
                cancelProgressDialog();
                msgError.setVisibility(View.GONE);
                lblError.setVisibility(View.GONE);
                if (response.body().getStatus() == 0) {
                    Toast.makeText(ForgotPasswordActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForgotpasswordModel> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                msgError.setVisibility(View.GONE);
                lblError.setVisibility(View.GONE);
                cancelProgressDialog();
            }
        });
    }

    private void showProgressDialog() {
        if (!isFinishing() && pdialog == null) {
            pdialog = ProgressDialog.show(this, getResources().getString(R.string.alert_title_loading), getResources()
                    .getString(R.string.alert_body_wait));
        }
    }

    private void cancelProgressDialog() {
        if (pdialog != null && !isFinishing()) {
            pdialog.dismiss();
            pdialog = null;
        }
    }

}
