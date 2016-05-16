package com.example.yoyoxie.contextmenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static Boolean isExit = false;
    int count;
    SharedPreferences sp;
    SharedPreferences.Editor myeditor;
    TextView tvname, tvage, tvaddress, tvdisplaymessage, tvdisplaypb, tvsex, tvhobby, tvdepart;
    EditText etname, etage, etaddress;
    RadioGroup rgsex;
    RadioButton rbmale, rbfemale;
    CheckBox cbphoto, cbfixness, cbother;
    Spinner spdepart, spspecial;
    Button buhandler, buexit, burun, buhand2, bunext, buresult;
    LinearLayout layoutall;
    ProgressBar pbdown;
    String[] str_depart = {"信息学院", "海运学院", "路桥学院", "汽车学院"};
    ArrayAdapter<String> arr_depart;
    String[][] str_special = {{"物联网应用技术", "计算机信息管理", "计算机网络技术", "ICT创新班", "通信技术"},
            {"船舶驾驶", "船舶设计", "游艇设计"},
            {"道桥监理", "钢结构", "道路信号"},
            {"发动机维修", "汽车销售", "丰田班"}};
    ArrayAdapter<String> arr_special;
    /* Handler my_handler = new Handler() {
         @Override
         public void handleMessage(Message msg) {
             switch (msg.what) {
                 case 1:—
                     int n = (Integer) msg.obj;
                     MainActivity.this.pbdown.setProgress(n);
                     MainActivity.this.tvdisplaypb.setText("已完成" + String.valueOf(n) + "%");
                     if (n >= 100)
                         MainActivity.this.tvdisplaypb.setText("已完成100%");
                     break;
                 default:
                     break;
             }
             super.handleMessage(msg);
         }
     };*/
    private Handler handler1 = new Handler();
    private Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            handler1.postDelayed(runnable1, 100);
            count = count + 1;
            if (count == 101) {
                MainActivity.this.tvdisplaypb.setTextColor(Color.BLUE);
                MainActivity.this.tvdisplaypb.setText("已完成100%");
                handler1.removeCallbacks(runnable1);
            } else {
                MainActivity.this.pbdown.setProgress(count);
                MainActivity.this.tvdisplaypb.setTextColor(Color.BLUE);
                MainActivity.this.tvdisplaypb.setText("已完成" + count + "%");
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
        }
        return false;
    }

    private void exitBy2Click() {
        Timer tExit;
        if (isExit == false) {
            isExit = true;
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);

        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (requestCode == 2) {
                    Bundle bundle = data.getExtras();
                    tvdisplaymessage.setText(bundle.getString("back").toString());
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutall = (LinearLayout) findViewById(R.id.layout_all);
        tvname = (TextView) findViewById(R.id.tv_name);
        tvage = (TextView) findViewById(R.id.tv_age);
        tvaddress = (TextView) findViewById(R.id.tv_address);
        tvdisplaymessage = (TextView) findViewById(R.id.tv_display_message);
        tvdisplaypb = (TextView) findViewById(R.id.tv_display_pb);
        tvsex = (TextView) findViewById(R.id.tv_sex);
        tvhobby = (TextView) findViewById(R.id.tv_hobby);
        tvdepart = (TextView) findViewById(R.id.tv_depart);
        etname = (EditText) findViewById(R.id.et_name);
        etage = (EditText) findViewById(R.id.et_age);
        etaddress = (EditText) findViewById(R.id.et_address);
        cbphoto = (CheckBox) findViewById(R.id.cb_photo);
        cbfixness = (CheckBox) findViewById(R.id.cb_fitness);
        cbother = (CheckBox) findViewById(R.id.cb_others);
        rgsex = (RadioGroup) findViewById(R.id.rg_sex);
        rbmale = (RadioButton) findViewById(R.id.rb_male);
        rbfemale = (RadioButton) findViewById(R.id.rb_female);
        buexit = (Button) findViewById(R.id.bu_exit);
        burun = (Button) findViewById(R.id.bu_run);
        buhandler = (Button) findViewById(R.id.bu_handler);
        bunext = (Button) findViewById(R.id.bu_next);
        buresult = (Button) findViewById(R.id.bu_result);
        buhand2 = (Button) findViewById(R.id.bu_hand2);
        spdepart = (Spinner) findViewById(R.id.sp_depart);
        spspecial = (Spinner) findViewById(R.id.sp_special);
        pbdown = (ProgressBar) findViewById(R.id.pb_down);
        registerForContextMenu(tvname);
        registerForContextMenu(tvage);
        registerForContextMenu(tvaddress);
        registerForContextMenu(tvdisplaymessage);
        sp = getSharedPreferences("my_sp_file", Context.MODE_PRIVATE);
        myeditor = sp.edit();
        etname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    if (etname.getText().toString().equals(""))
                        Toast.makeText(MainActivity.this, "empty_name",
                                Toast.LENGTH_SHORT).show();
                display();
            }
        });
        etage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    if (etage.getText().toString().equals(""))
                        Toast.makeText(MainActivity.this, "empty_age",
                                Toast.LENGTH_SHORT).show();
                display();
            }
        });
        etaddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    if (etaddress.getText().toString().equals(""))
                        Toast.makeText(MainActivity.this, "empty_address",
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
        /*buhand2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_thread thread = new my_thread(my_handler);
                thread.start();
            }
        });*/
        bunext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("name", etname.getText().toString());
                bundle.putString("age", etage.getText().toString());
                intent.putExtras(bundle);
                intent.setClass(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, 1);
                finish();
            }

        });
        arr_depart = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, str_depart);
        spdepart.setAdapter(arr_depart);
        arr_special = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, str_special[0]);
        spspecial.setAdapter(arr_special);
        buhandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(MainActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                display();*/
                handler1.post(runnable1);
            }
        });
        burun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyProgress my_pro = new AsyProgress();
                my_pro.execute(100);
            }
        });
        buexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        buresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                Bundle bundle2 = new Bundle();
                bundle2.putString("name", etname.getText().toString());
                intent2.putExtras(bundle2);
                intent2.setClass(MainActivity.this, ThirdActivity.class);
                startActivityForResult(intent2, 1);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.tv_name:
                menu.setHeaderTitle("请选择颜色");
                menu.add(0, Menu.FIRST, 0, "红色");
                menu.add(0, Menu.FIRST + 1, 0, "黄色");
                menu.add(0, Menu.FIRST + 2, 0, "蓝色");
                menu.add(0, Menu.FIRST + 3, 0, "氰色");
                break;
            case R.id.tv_age:
                menu.setHeaderTitle("请选择颜色");
                menu.add(0, Menu.FIRST + 4, 0, "红色");
                menu.add(0, Menu.FIRST + 5, 0, "黄色");
                menu.add(0, Menu.FIRST + 6, 0, "蓝色");
                menu.add(0, Menu.FIRST + 7, 0, "氰色");
                break;
            case R.id.tv_address:
                menu.setHeaderTitle("请选择颜色");
                menu.add(0, Menu.FIRST + 8, 0, "红色");
                menu.add(0, Menu.FIRST + 9, 0, "黄色");
                menu.add(0, Menu.FIRST + 10, 0, "蓝色");
                menu.add(0, Menu.FIRST + 11, 0, "氰色");
                break;
            case R.id.tv_display_message:
                menu.setHeaderTitle("请选择颜色");
                menu.add(0, Menu.FIRST + 12, 0, "红色");
                menu.add(0, Menu.FIRST + 13, 0, "黄色");
                menu.add(0, Menu.FIRST + 14, 0, "蓝色");
                menu.add(0, Menu.FIRST + 15, 0, "氰色");
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu menu0 = menu.addSubMenu(0, Menu.FIRST, 0, "改变全局背景");
        SubMenu menu1 = menu.addSubMenu(0, Menu.FIRST + 1, 0, "更换TextView背景");
        SubMenu menu2 = menu.addSubMenu(0, Menu.FIRST + 6, 0, "数据存储");
        menu0.add(0, Menu.FIRST + 2, 0, "背景1");
        menu0.add(0, Menu.FIRST + 3, 0, "背景2");
        menu1.add(0, Menu.FIRST + 4, 0, "小背景1");
        menu1.add(0, Menu.FIRST + 5, 0, "小背景2");
        menu2.add(0, Menu.FIRST + 7, 0, "SP存储");
        menu2.add(0, Menu.FIRST + 8, 0, "文件存储");
        menu2.add(0, Menu.FIRST + 9, 0, "DB存储");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST + 2:
                layoutall.setBackgroundResource(R.drawable.ghbg);
                break;
            case Menu.FIRST + 3:
                layoutall.setBackgroundResource(R.drawable.xjbj);
                break;
            case Menu.FIRST + 4:
                tvdisplaymessage.setBackgroundResource(R.drawable.xsbg);
                break;
            case Menu.FIRST + 5:
                tvdisplaymessage.setBackgroundResource(R.drawable.xybg);
            case Menu.FIRST + 7:
                myeditor.putString("name", etname.getText().toString());
                myeditor.putString("age", etage.getText().toString());
                myeditor.putString("address", etaddress.getText().toString());
                myeditor.commit();
                Toast.makeText(this, "写入sp文件成功!", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST:
                tvname.setTextColor(Color.RED);
                break;
            case Menu.FIRST + 1:
                tvname.setTextColor(Color.YELLOW);
                break;
            case Menu.FIRST + 2:
                tvname.setTextColor(Color.BLUE);
                break;
            case Menu.FIRST + 3:
                tvname.setTextColor(Color.CYAN);
                break;
            case Menu.FIRST + 4:
                tvage.setTextColor(Color.RED);
                break;
            case Menu.FIRST + 5:
                tvage.setTextColor(Color.YELLOW);
                break;
            case Menu.FIRST + 6:
                tvage.setTextColor(Color.BLUE);
                break;
            case Menu.FIRST + 7:
                tvage.setTextColor(Color.CYAN);
                break;
            case Menu.FIRST + 8:
                tvaddress.setTextColor(Color.RED);
                break;
            case Menu.FIRST + 9:
                tvaddress.setTextColor(Color.YELLOW);
                break;
            case Menu.FIRST + 10:
                tvaddress.setTextColor(Color.BLUE);
                break;
            case Menu.FIRST + 11:
                tvaddress.setTextColor(Color.CYAN);
                break;
            case Menu.FIRST + 12:
                tvdisplaymessage.setTextColor(Color.RED);
                break;
            case Menu.FIRST + 13:
                tvdisplaymessage.setTextColor(Color.YELLOW);
                break;
            case Menu.FIRST + 14:
                tvdisplaymessage.setTextColor(Color.BLUE);
                break;
            case Menu.FIRST + 15:
                tvdisplaymessage.setTextColor(Color.CYAN);
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void display() {
        String str, str_sex, str_hobby = "";
        if (rbmale.isChecked())
            str_sex = rbmale.getText().toString();
        else
            str_sex = rbfemale.getText().toString();
        if (cbphoto.isChecked())
            str_hobby = str_hobby + cbphoto.getText().toString();
        if (cbfixness.isChecked())
            str_hobby = str_hobby + cbfixness.getText().toString();
        if (cbother.isChecked())
            str_hobby = str_hobby + cbother.getText().toString();
        str = "你输入的信息如下:" + "\r\n"
                + "1." + MainActivity.this.tvname.getText().toString()
                + MainActivity.this.etname.getText().toString() + "\r\n"
                + "2." + MainActivity.this.tvage.getText().toString()
                + MainActivity.this.etage.getText().toString() + "\r\n"
                + "3." + MainActivity.this.tvaddress.getText().toString()
                + MainActivity.this.etaddress.getText().toString() + "\r\n"
                + "4." + MainActivity.this.tvsex.getText() + str_sex + "\r\n"
                + "5." + MainActivity.this.tvhobby.getText() + str_hobby + "\r\n"
                + "6." + MainActivity.this.tvdepart.getText().toString()
                + spdepart.getSelectedItem().toString()
                + "--" + spspecial.getSelectedItem().toString();
        tvdisplaymessage.setText(str);
    }

/*    class my_thread extends Thread {
        Handler handler;

        public my_thread(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            Message mymsg = handler.obtainMessage();
            for (int i = 1; i <= 100; i++) {
                mymsg.what = 1;
                mymsg.obj = i;
                handler.sendMessage(mymsg);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }
    }*/

    private class AsyProgress extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPostExecute(String s) {
            MainActivity.this.tvdisplaypb.setText(s);
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            MainActivity.this.pbdown.setProgress(values[0]);
            MainActivity.this.tvdisplaypb.setText("已完成" + String.valueOf(values[0]) + "%");
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Integer... params) {
            for (int i = 1; i <= 100; i++) {
                this.publishProgress(i);
                try {
                    Thread.sleep(params[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "已完成" + String.valueOf(100) + "%";
        }
    }

}

