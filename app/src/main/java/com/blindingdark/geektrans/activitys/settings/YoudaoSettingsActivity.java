package com.blindingdark.geektrans.activitys.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.blindingdark.geektrans.R;
import com.blindingdark.geektrans.tools.Number;
import com.blindingdark.geektrans.trans.youdao.YoudaoSettingsString;

public class YoudaoSettingsActivity extends AppCompatActivity {

    EditText editTextKey;
    EditText editTextKeyfrom;
    EditText editTextDivLine;
    EditText editTextToastTime;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youdao_settings);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        editTextKey = (EditText) findViewById(R.id.edtTxtApiKey);
        String key = preferences.getString(YoudaoSettingsString.youdaoKey, "");
        editTextKey.setText(key);
        editTextKey.addTextChangedListener(keyTextWatcher);

        editTextKeyfrom = (EditText) findViewById(R.id.edtTxtApiKeyfrom);
        String keyfrom = preferences.getString(YoudaoSettingsString.youdaoKeyfrom, "");
        editTextKeyfrom.setText(keyfrom);
        editTextKeyfrom.addTextChangedListener(keyfromTextWatcher);

        editTextDivLine = (EditText) findViewById(R.id.edtTxtDivisionLine);
        String divLine = preferences.getString(YoudaoSettingsString.divisionLine, YoudaoSettingsString.defaultDivLine);
        editTextDivLine.setText(divLine);
        editTextDivLine.addTextChangedListener(divTextWatcher);

        editTextToastTime = (EditText) findViewById(R.id.edtTxtYoudaoToastTime);
        String youdaoToastTime = preferences.getString(YoudaoSettingsString.youdaoToastTime, YoudaoSettingsString.youdaoDefToastTime);
        editTextToastTime.setText(youdaoToastTime);

        editTextToastTime.addTextChangedListener(toastTimeWatcher);

    }

    TextWatcher keyTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            editor.putString(YoudaoSettingsString.youdaoKey, editTextKey.getText().toString());
            editor.commit();

        }
    };
    TextWatcher keyfromTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            editor.putString(YoudaoSettingsString.youdaoKeyfrom, editTextKeyfrom.getText().toString());
            editor.commit();
        }
    };

    TextWatcher divTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            editor.putString(YoudaoSettingsString.divisionLine, editTextDivLine.getText().toString());
            editor.commit();

        }
    };

    TextWatcher toastTimeWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String time = editTextToastTime.getText().toString();

            // 检测是否是合法数字

            if (Number.isLegalToastTime(time)) {
                editor.putString(YoudaoSettingsString.youdaoToastTime, time);
                editor.commit();
            }


        }
    };

}
