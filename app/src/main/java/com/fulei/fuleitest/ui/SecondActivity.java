package com.fulei.fuleitest.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.davidecirillo.multichoicerecyclerview.Constants;
import com.davidecirillo.multichoicerecyclerview.MultiChoiceAdapter;
import com.davidecirillo.multichoicerecyclerview.MultiChoiceToolbar;
import com.fulei.device.GoodsDevice;
import com.fulei.fuleitest.R;
import com.fulei.fuleitest.adpter.SecondAdapter;
import com.fulei.fuleitest.adpter.SecondAdapter1;
import com.fulei.fuleitest.greendao.DaoManager;
import com.fulei.fuleitest.greendao.GoodsWay;
import com.fulei.fuleitest.greendao.GoodsWayDao;
import com.fulei.fuleitest.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by gcl on 2017/7/31.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView second_back;
    private Button second_xy;
    private Button second_xyback;
    private Button second_ztest;
    private Button second_zback;
    private Button second_out;
    private Button second_outback;
    private Button second_time;
    private EditText second_et_time;
    private RelativeLayout second_testGone;
    private AppCompatCheckBox check_all;
    private AppCompatCheckBox check_fuxuan;
    private Button second_ok;
    private Button second_cancel;
    private Button second_save;
    private Button second_test;
    private Button second_stop;
    private EditText second_x;
    private EditText second_y;
    private EditText second_z;
    private TextView second_id;
    private Button second_setting;
    private TextView s_type;
    private Spinner s_port;
    private Spinner s_bit;
    private Button set_ok;
    private Button set_cancel;
    private RelativeLayout set_layout;
    private RecyclerView second_recycler;
    private List<GoodsWay> goodsList;
    private int position = 0;
    private GoodsWayDao goodsWayDao;
    private GoodsDevice device;
    private SecondAdapter1 adapter;
    private Timer timer = null;
    private List<Integer> testList = new ArrayList<Integer>();
    private int index = 1;
    private int time = 3;
    private String[] port = new String[]{"ttyS1", "ttyS2", "ttyS3", "ttyS4", "ttyS5", "ttyS6", "ttyS7", "ttyS8", "ttyS9", "ttyS10"};
    private String[] bit = new String[]{"300", "600", "1200", "2400", "4800", "9600", "19200", "38400", "43000", "56000", "57600", "115200"};
    private int set_port = 1;
    private String set_bit = "9600";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        goodsWayDao = DaoManager.getInstance().getSession().getGoodsWayDao();
        initDevice();
        initView();
        initRecyclerView();

    }

    private View currentView;

    private void initRecyclerView() {
        goodsList = new ArrayList<GoodsWay>();
        goodsList = DaoManager.getInstance().getSession().getGoodsWayDao().queryBuilder().build().list();
        second_recycler.setLayoutManager(new GridLayoutManager(this, 10, LinearLayoutManager.VERTICAL, false));
        adapter = new SecondAdapter1(goodsList, this);
        second_recycler.setAdapter(adapter);
        adapter.setItemListener(new SecondAdapter1.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                position = postion;
                second_id.setText(postion + 1 + "");
                GoodsWay goods = goodsWayDao.queryBuilder().where(GoodsWayDao.Properties.Id.eq(postion + 1)).unique();
                second_x.setText(goods.getX() + "");
                second_y.setText(goods.getY() + "");
                second_z.setText(goods.getZ() + "");
                if (currentView == null) {
                    currentView = view;
                } else {
                    if (currentView.isFocusableInTouchMode() == true) {
                        currentView.setFocusableInTouchMode(false);
                        currentView.setFocusable(false);
                    }
                    currentView = view;
                }
                if (view.isFocusableInTouchMode() == false) {
                    view.setFocusableInTouchMode(true);
                    view.setFocusable(true);
                }

            }
        });

    }

    private void initDevice() {
        device = new GoodsDevice("GOODS2", "1", "9600");

    }

    //-------------------------------------------------------------------------------------
    private void initView() {
        second_recycler = (RecyclerView) findViewById(R.id.second_review);
        second_id = (TextView) findViewById(R.id.second_id);
        second_back = (ImageView) findViewById(R.id.back);
        second_back.setOnClickListener(this);
        set_layout = (RelativeLayout) findViewById(R.id.second_rl_set);
        set_cancel = (Button) findViewById(R.id.second_set_cancel);
        set_ok = (Button) findViewById(R.id.second_set_ok);
        set_cancel.setOnClickListener(this);
        set_ok.setOnClickListener(this);
        second_setting = (Button) findViewById(R.id.second_setting);
        second_setting.setOnClickListener(this);
        s_type = (TextView) findViewById(R.id.second_type);
        s_port = (Spinner) findViewById(R.id.second_port);
        s_bit = (Spinner) findViewById(R.id.second_bit);
        initSpinner();
        second_out = (Button) findViewById(R.id.second_out);
        second_out.setOnClickListener(this);
        second_outback = (Button) findViewById(R.id.second_outback);
        second_outback.setOnClickListener(this);
        second_save = (Button) findViewById(R.id.second_save);
        second_save.setOnClickListener(this);
        second_xy = (Button) findViewById(R.id.second_xy);
        second_xyback = (Button) findViewById(R.id.second_xyback);
        second_xy.setOnClickListener(this);
        second_xyback.setOnClickListener(this);
        second_zback = (Button) findViewById(R.id.second_zback);
        second_zback.setOnClickListener(this);
        second_ok = (Button) findViewById(R.id.second_ok);
        second_ok.setOnClickListener(this);
        second_cancel = (Button) findViewById(R.id.second_cancel);
        second_cancel.setOnClickListener(this);
        check_all = (AppCompatCheckBox) findViewById(R.id.second_checkAll);
        check_all.setOnClickListener(this);
        check_fuxuan = (AppCompatCheckBox) findViewById(R.id.second_checkFu);
        check_fuxuan.setOnClickListener(this);
        second_ztest = (Button) findViewById(R.id.second_ztest);
        second_testGone = (RelativeLayout) findViewById(R.id.second_testGone);
        second_ztest.setOnClickListener(this);
        second_test = (Button) findViewById(R.id.second_test);
        second_test.setOnClickListener(this);
        second_stop = (Button) findViewById(R.id.second_stop);
        second_stop.setOnClickListener(this);
        second_x = (EditText) findViewById(R.id.second_x);
        second_y = (EditText) findViewById(R.id.second_y);
        second_z = (EditText) findViewById(R.id.second_z);
        second_time = (Button) findViewById(R.id.second_time);
        second_time.setOnClickListener(this);
        second_et_time = (EditText) findViewById(R.id.second_et_time);
    }


    private void initSpinner() {
        s_port.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, port));
        s_port.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                set_port = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        s_bit.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bit));
        s_bit.setSelection(5);
        s_bit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                set_bit = bit[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private int xLine, yLine, zLine;

    @Override
    public void onClick(View view) {
        if (!TextUtils.isEmpty(second_x.getText()) && !TextUtils.isEmpty(second_y.getText())) {
            xLine = Utils.getInteger(second_x.getText().toString());
            yLine = Utils.getInteger(second_y.getText().toString());
        }
        if (!TextUtils.isEmpty(second_z.getText())) {
            zLine = Utils.getInteger(second_z.getText().toString());
        }

        switch (view.getId()) {

            case R.id.back:
                this.finish();
                break;
            case R.id.second_xy:
                if (xLine != 0 && yLine != 0) {
                    Log.e("yobo+++++++++++++++++", 2 + "");
                    int code1 = device.CoorDinateCaseGood(yLine, xLine);
                    if (code1 == -1) {
                        showDialog1("测试失败！", 33, true);
                    }
                }
                Log.e("yobo+++++++++++++++++", 1 + "");
                break;
            case R.id.second_xyback:
                if (xLine != 0 && yLine != 0) {
                    int code2 = device.CoorDinateHome(yLine, xLine);
                    if (code2 == -1) {
                        showDialog1("复位失败！", 33, true);
                    }
                }

                break;
            case R.id.second_ztest:
                if (zLine != 0) {
                    int code3 = device.PreposeMotorCase(zLine);
                    if (code3 == -1) {
                        showDialog1("测试失败！", 33, true);
                    }
                }
                break;
            case R.id.second_zback:
                if (zLine != 0) {
                    int code4 = device.PreposeMotorHome(zLine);
                    if (code4 == -1) {
                        showDialog1("复位失败！", 33, true);
                    }
                }
                break;
            case R.id.second_out:
                if (xLine != 0 && yLine != 0 && zLine != 0) {
                    int code1 = device.goodsSellCase(zLine, yLine, xLine);
                    if (code1 == 1) {
                        timer = new Timer(true);
                        TimerTask task = new TimerTask() {
                            public void run() {
                                Message msg = new Message();
                                int states = device.goodsStatus();
                                msg.what = states;
                                handler.sendMessage(msg);
                            }
                        };
                        //启动定时器
                        timer.schedule(task, 5000, 800);
                    } else {
                        showDialog1("出货失败！！！", -1, true);
                    }
                }
                break;
            case R.id.second_outback:
                int code5 = device.goodsReset();
                if (code5 == -1) {
                    showDialog1("测试失败！", 33, true);
                }
                break;
            case R.id.second_save:
                GoodsWay goods = goodsWayDao.queryBuilder().where(GoodsWayDao.Properties.Id.eq(position + 1)).unique();
                if (!second_x.getText().equals("")) {
                    goods.setX(Integer.valueOf(second_x.getText().toString()));
                }
                if (!second_y.getText().equals("")) {
                    goods.setY(Integer.valueOf(second_y.getText().toString()));
                }
                if (!second_z.getText().equals("")) {
                    goods.setZ(Integer.valueOf(second_z.getText().toString()));
                }
                goodsWayDao.update(goods);
                adapter.notifyItemChanged(position, goodsList.size());
                Toast.makeText(this, "保存成功!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.second_test:

                if (second_testGone.getVisibility() == View.GONE) {
                    second_testGone.setVisibility(View.VISIBLE);
                } else {
                    second_testGone.setVisibility(View.GONE);
                }

                break;
            case R.id.second_checkAll:
                if (check_all.isChecked()) {
                    check_all.setChecked(true);
                    adapter.selectAll();
                    if (check_fuxuan.isChecked()) {
                        check_fuxuan.setChecked(false);
                        adapter.setSingleClickMode(false);
                    }
                } else {
                    check_all.setChecked(false);
                    adapter.deselectAll();
                }
                break;
            case R.id.second_checkFu:
                if (check_fuxuan.isChecked()) {

                    check_fuxuan.setChecked(true);
                    adapter.setSingleClickMode(true);
                    if (check_all.isChecked()) {
                        check_all.setChecked(false);
                        adapter.deselectAll();
                    }
                } else {
                    adapter.setSingleClickMode(false);
                    check_fuxuan.setChecked(false);
                    adapter.deselectAll();
                }
                break;
            case R.id.second_ok:
                testList = adapter.getSelectedItemList();
                if (testList.size() > 0) {
                    test(0);
                    for (int i = 0; i < testList.size(); i++) {
                        Log.e("yobo---------------", testList.get(i) + "");
                    }
                }
                second_stop.setEnabled(true);
                break;
            case R.id.second_cancel:
                if (testList.size() > 0) {
                    testList.clear();
                    adapter.deselectAll();
                }
                second_stop.setText("停止");
                second_stop.setEnabled(false);
                second_testGone.setVisibility(View.GONE);
                check_all.setChecked(false);
                check_fuxuan.setChecked(false);
                break;
            case R.id.second_stop:

//                if (index < 0) {
//                    index = stop_index + 1;
//                    if (testList.size() > 0 && index < testList.size()) {
//                        test(index);
//                    } else {
//                        test(0);
//                    }
//                    second_stop.setText("停止");
//                } else {
                index = -1;
                adapter.deselectAll();
                second_stop.setEnabled(false);
                second_testGone.setVisibility(View.GONE);
                check_all.setChecked(false);
                check_fuxuan.setChecked(false);
                adapter.setSingleClickMode(false);
                //  }
                break;
            case R.id.second_time:
                if (!TextUtils.isEmpty(second_et_time.getText())) {
                    time = Utils.getInteger(second_et_time.getText().toString());
                }
                break;
            case R.id.second_setting:
                if (set_layout.getVisibility() == View.GONE) {
                    set_layout.setVisibility(View.VISIBLE);
                } else {
                    set_layout.setVisibility(View.GONE);
                }
                break;
            case R.id.second_set_ok:
                device = new GoodsDevice("GOODS2", String.valueOf(set_port), set_bit);
                set_layout.setVisibility(View.GONE);
                break;
            case R.id.second_set_cancel:
                set_layout.setVisibility(View.GONE);
                break;
            default:
                break;

        }


    }

    private void test(int position1) {

        Log.e("pop--------------009-", position1 + "");
        int position = testList.get(position1);
        GoodsWay goods1 = goodsWayDao.queryBuilder().where(GoodsWayDao.Properties.Id.eq(position + 1)).unique();
        int code1 = device.goodsSellCase(goods1.getZ(), goods1.getY(), goods1.getX());
        if (code1 == 1) {
            timer = new Timer(true);
            TimerTask task = new TimerTask() {
                public void run() {
                    Message msg = new Message();
                    int states = device.goodsStatus();
                    msg.what = states;
                    handler.sendMessage(msg);
                }
            };
            //启动定时器
            timer.schedule(task, 5000, 2000);
        } else {
            showDialog1("出货失败！！！", -1, true);
        }
    }

    private int msg21 = 0;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg21 > 50) {
                if (timer != null) {
                    timer.cancel();

                    timer = null;
                    msg21 = 0;
                    showDialog1("出货超时！！！", 21, true);
                    return;
                }
            }
            if (msg.what == 25) {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                    msg21 = 0;
                    showDialog1("出货失败！！！", 25, true);
                    if (index < 0) {
                        return;
                    }
                    if (builder != null && builder.isShowing()) {
                        builder.dismiss();
                    }
                    Log.e("pop---------------", "****");
                    if (testList.size() > 0) {
                        if (index < testList.size()) {
                            Log.e("pop---------------", testList.size() + "");
                            Log.e("pop-----------00----", index + "");

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    test(index);
                                    index++;
                                }
                            }, time * 1000);


                        } else {

                            index = 0;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    test(0);
                                    index++;
                                }
                            }, time * 1000);

                        }
                    }
                }
            } else if (msg.what == 22) {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                    msg21 = 0;
                    showDialog1("抱歉，机器发生故障！！！", 22, true);
                }
            } else if (msg.what == 27) {

                showDialog1("正在出货...", 27, false);


            } else if (msg.what == 29) {
                showDialog1("请您把取货门关上！", 29, true);
                msg21 = 0;
            } else if (msg.what == 14) {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                    msg21 = 0;
                    showDialog1("出货成功 ^_^", 14, false);
                }
            } else if (msg.what == 21) {
                msg21++;
                showDialog1("正在出货中，请您稍等！", 21, false);

            }
        }
    };

    private AlertDialog builder = null;

    private void showDialog1(String msg, int code1, boolean isRed) {

        if (builder == null) {
            builder = new AlertDialog.Builder(this).create();
        }
        if (!builder.isShowing()) {
            builder.show();
        }
        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_second);
        TextView title = (TextView) window.findViewById(R.id.dialog_title);
        TextView code = (TextView) window.findViewById(R.id.dialog_code);
        if (isRed) {
            title.setTextColor(getResources().getColor(R.color.red));
        }
        title.setText(msg);
        code.setText("状态码：" + code1);

    }


}
