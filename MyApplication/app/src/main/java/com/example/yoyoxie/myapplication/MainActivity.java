package com.example.yoyoxie.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvname,tvage,tvaddress,tvdisplay,tvsex,tvhobby,tvdepart,tvspecial;
    EditText etname,etage,etaddress;
    RadioGroup rgsex;
    RadioButton rbmale,rbfemale;
    CheckBox cbphoto,cbfixness,cbother;
    Spinner spdepart,spspecial;
    Button buok,buexit,butest;
    String [] str_depart = {"信息学院","海运学院","路桥学院","汽车学院"};
    ArrayAdapter<String> arr_depart;
    String[][] str_special = {{"物联网应用技术", "计算机信息管理", "计算机网络技术","ICT创新班","通信技术"},
            {"船舶驾驶","船舶设计","游艇设计"},
            {"道桥监理","钢结构","道路信号"},
            {"发动机维修","汽车销售","丰田班"}};
    ArrayAdapter<String> arr_special;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvname = (TextView)findViewById(R.id.tv_name);
        tvage = (TextView)findViewById(R.id.tv_age);
        tvaddress = (TextView)findViewById(R.id.tv_address);
        tvdisplay = (TextView)findViewById(R.id.tv_display);
        tvsex=(TextView)findViewById(R.id.tv_sex);
        tvhobby=(TextView)findViewById(R.id.tv_hobby);
        tvdepart=(TextView)findViewById(R.id.tv_depart);
        tvspecial=(TextView)findViewById(R.id.tv_special);
        etname = (EditText)findViewById(R.id.et_name);
        etage = (EditText)findViewById(R.id.et_age);
        etaddress = (EditText)findViewById(R.id.et_address);
        cbphoto = (CheckBox)findViewById(R.id.cb_photo);
        cbfixness = (CheckBox)findViewById(R.id.cb_fitness);
        cbother = (CheckBox)findViewById(R.id.cb_others);
        rgsex = (RadioGroup) findViewById(R.id.rg_sex);
        rbmale = (RadioButton) findViewById(R.id.rb_male);
        rbfemale = (RadioButton) findViewById(R.id.rb_female);
        buexit = (Button) findViewById(R.id.bu_exit);
        butest = (Button) findViewById(R.id.bu_test);
        buok = (Button)findViewById(R.id.bu_ok);
        spdepart = (Spinner)findViewById(R.id.sp_depart);
        spspecial = (Spinner)findViewById(R.id.sp_special);
        tvname.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tvname.setTextColor(Color.rgb(55,88,99));
                return false;
            }
        });
        tvage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tvage.setTextColor(Color.rgb(55,88,99));
                return false;
            }
        });
        tvaddress.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tvaddress.setTextColor(Color.rgb(55,88,99));
                return false;
            }
        });
        etname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    if(etname.getText().toString().equals(""))
                        Toast.makeText(MainActivity.this,"empty_name",
                                Toast.LENGTH_SHORT).show();
                display();
            }
        });
        etage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    if(etage.getText().toString().equals(""))
                        Toast.makeText(MainActivity.this,"empty_age",
                                Toast.LENGTH_SHORT).show();
                display();
            }
        });
        etaddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    if(etaddress.getText().toString().equals(""))
                        Toast.makeText(MainActivity.this,"empty_address",
                                Toast.LENGTH_SHORT).show();
                display();
            }
        });
        rbmale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                display();
            }
        });
        rbfemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                display();
            }
        });
        cbphoto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                display();
            }
        });
        cbfixness.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                display();
            }
        });
        cbother.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                display();
            }
        });
        spdepart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                arr_special = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_spinner_dropdown_item, str_special[position]);
                spspecial.setAdapter(arr_special);
                display();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spspecial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                display();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        arr_depart=new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,str_depart);
        spdepart.setAdapter(arr_depart);
        arr_special=new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,str_special[0]);
        spspecial.setAdapter(arr_special);
        buok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                display();
            }
        });
        butest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"测试中",Toast.LENGTH_SHORT).show();
            }
        });
        buexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
    public void display(){
        String str,str_sex,str_hobby="";
        if(rbmale.isChecked())
            str_sex=rbmale.getText().toString();
        else
            str_sex=rbfemale.getText().toString();
        if(cbphoto.isChecked())
            str_hobby=str_hobby+cbphoto.getText().toString();
        if(cbfixness.isChecked())
            str_hobby=str_hobby+cbfixness.getText().toString();
        if(cbother.isChecked())
            str_hobby=str_hobby+cbother.getText().toString();
        str="你输入的信息如下"+"\r\n"
                +MainActivity.this.tvname.getText().toString()
                +MainActivity.this.etname.getText().toString()+ "\r\n"
                +MainActivity.this.tvage.getText().toString()
                +MainActivity.this.etage.getText().toString()+ "\r\n"
                +MainActivity.this.tvaddress.getText().toString()
                +MainActivity.this.etaddress.getText().toString()+"\r\n"
                +MainActivity.this.tvsex.getText()+str_sex+"\r\n"
                +MainActivity.this.tvhobby.getText()+str_hobby+"\r\n"
                +MainActivity.this.tvdepart.getText().toString()
                +spdepart.getSelectedItem().toString()+"\r\n"
                +spspecial.getSelectedItem().toString();
        tvdisplay.setText(str);
    }
}
