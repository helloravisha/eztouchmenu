package com.ezmenutouch.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ezmenutouch.R;

import com.dd.processbutton.iml.ActionProcessButton;
import com.ezmenutouch.constants.DashBoardConstants;
import com.ezmenutouch.util.ProgressGenerator;
import com.ezmenutouch.util.SharedPreferenceUtil;


import java.util.Random;

public class TableActivity extends Activity implements ProgressGenerator.OnCompleteListener, View.OnClickListener {


    private String TAG = "TableActivity";
    String OTP;
    ProgressGenerator progressGenerator;
    ActionProcessButton validationButton,psu_resend_tv;
    TextView textview_key_0, textview_key_1, textview_key_2, textview_key_3, textview_key_4, textview_key_5, textview_key_6,
            textview_key_7, textview_key_8, textview_key_9, textview_key_clear, textview_key_backspace;
    EditText txtOTP;
    private SharedPreferenceUtil sharedPreferenceUtil = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_table);
        initUI(savedInstanceState);
    }



    //TODO : uday/pramod - logic to render otp validation page needs to be implemented here.
    public void initUI(Bundle savedInstanceState){

        psu_resend_tv = (ActionProcessButton) findViewById(R.id.psu_resend_tv);
        textview_key_0 = (TextView) findViewById(R.id.t9_key_0);
        textview_key_1 = (TextView) findViewById(R.id.t9_key_1);
        textview_key_2 = (TextView) findViewById(R.id.t9_key_2);
        textview_key_3 = (TextView) findViewById(R.id.t9_key_3);
        textview_key_4 = (TextView) findViewById(R.id.t9_key_4);
        textview_key_5 = (TextView) findViewById(R.id.t9_key_5);
        textview_key_6 = (TextView) findViewById(R.id.t9_key_6);
        textview_key_7 = (TextView) findViewById(R.id.t9_key_7);
        textview_key_8 = (TextView) findViewById(R.id.t9_key_8);
        textview_key_9 = (TextView) findViewById(R.id.t9_key_9);
        textview_key_clear = (TextView) findViewById(R.id.t9_key_clear);
        textview_key_backspace = (TextView) findViewById(R.id.t9_key_backspace);
        textview_key_0.setOnClickListener(this);
        textview_key_1.setOnClickListener(this);
        textview_key_2.setOnClickListener(this);
        textview_key_3.setOnClickListener(this);
        textview_key_4.setOnClickListener(this);
        textview_key_5.setOnClickListener(this);
        textview_key_6.setOnClickListener(this);
        textview_key_7.setOnClickListener(this);
        textview_key_8.setOnClickListener(this);
        textview_key_9.setOnClickListener(this);
        textview_key_clear.setOnClickListener(this);
        textview_key_backspace.setOnClickListener(this);

        progressGenerator = new ProgressGenerator(this);
        validationButton = (ActionProcessButton) findViewById(R.id.validationBtn);
        validationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validateOTP()){
                    loadToken();
                    Log.i(TAG," displayUi..");
                    displayUI();
                }else{
                   // wrongOTP();
                }
            }
        });
        psu_resend_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendOTP();
            }
        });
        txtOTP = (EditText) findViewById(R.id.txtOtp);
        txtOTP.setInputType(InputType.TYPE_NULL);
        txtOTP.setText(OTP);
    }

    /**
     * Write the logic , for validating the OTP.
     * @return
     */
     private boolean validateOTP(){
        EditText tableName = (EditText) findViewById(R.id.txtOtp);
        String tableNameInfo = txtOTP.getText().toString().trim();
        Log.i(TAG,"OTP entered "+tableName);
         validationButton.setMode(ActionProcessButton.Mode.ENDLESS);
         SharedPreferenceUtil.setSharedPreferenceString(this,DashBoardConstants.PREFS_NAME, DashBoardConstants.TABLE_NAME,tableNameInfo);
            return true;
    }




    private void resendOTP(){

    }



    public void displayUI() {


        sharedPreferenceUtil = new SharedPreferenceUtil(getSharedPreferences(DashBoardConstants.PREFS_NAME, 0),false);


        Intent managementIntent = new Intent(this, DashboardActivity.class);
        managementIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivityForResult(managementIntent, 0);
        overridePendingTransition(0,0);
        finish();

    }


    private void loadToken(){
        // TODO : Ravi get all the ids and schoolids from the db and load the tokens into firebase
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent managementIntent = new Intent(this, DashboardActivity.class);
        Log.i(TAG,"Displaying MANAGEMENT UI");
        startActivity(managementIntent);
        finish();
    }
    @Override
    public void onComplete() {
    }

    @Override
    public void onClick(View v) {

        if (v.getTag() != null && "number_button".equals(v.getTag())) {
            txtOTP.append(((TextView) v).getText());
            return;
        }

        switch (v.getId()) {


            case R.id.t9_key_clear: {
                // handle clear button
                txtOTP.setText(null);
            }
            break;
            case R.id.t9_key_backspace: {
                // handle backspace button
                // delete one character
                Editable editable = txtOTP.getText();
                int charCount = editable.length();
                if (charCount > 0) {
                    editable.delete(charCount - 1, charCount);
                }
            }
            break;
        }

    }
}
