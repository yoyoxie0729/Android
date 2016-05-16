package com.example.yoyoxie.contextmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    Button button;
    TextView tvdisp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button = (Button) findViewById(R.id.bt_se_back);
        tvdisp = (TextView) findViewById(R.id.tv_display_message);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = getIntent().getExtras();
                Toast.makeText(SecondActivity.this,
                        bundle.getString("name") + "---" + bundle.getString("age"),
                        Toast.LENGTH_LONG).show();
                intent.setClass(SecondActivity.this, MainActivity.class);
                startActivity(intent);
                setResult(2);
                finish();
            }
        });
    }
}
