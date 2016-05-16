package com.example.yoyoxie.contextmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    TextView tvlist;
    EditText etcont;
    Button buback;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        tvlist = (TextView) findViewById(R.id.tv_td_list);
        etcont = (EditText) findViewById(R.id.et_td_cont);
        buback = (Button) findViewById(R.id.bu_td_back);
        bundle = getIntent().getExtras();
        etcont.setText(bundle.getString("name"));
        buback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                bundle.putString("back", etcont.getText().toString());
                intent.setClass(ThirdActivity.this, MainActivity.class);
                intent.putExtras(bundle);
                setResult(2, intent);
                finish();
            }
        });
    }
}
