package com.fulei.fuleitest.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.fulei.fuleitest.R;
import com.fulei.fuleitest.greendao.DaoManager;
import com.fulei.fuleitest.greendao.GoodsWay;
import com.fulei.fuleitest.greendao.GoodsWayDao;
import com.fulei.fuleitest.utils.IntentJump;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button main_second;
    private Button main_button;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();

    }

    private void getData() {
        GoodsWayDao goodsDao = DaoManager.getInstance().getSession().getGoodsWayDao();
        List<GoodsWay> lsit = DaoManager.getInstance().getSession().getGoodsWayDao().queryBuilder().build().list();
        if (lsit.size() > 0) {
            return;
        }
        for (int i = 1; i < 101; i++) {
            GoodsWay goods = new GoodsWay();
            goods.setY(0);
            goodsDao.insert(goods);

        }

    }

    private void initView() {
        main_second = (Button) findViewById(R.id.main_second);
        main_second.setOnClickListener(this);
        main_button = (Button) findViewById(R.id.main_button);
        main_button.setOnClickListener(this);
        back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.main_second:
                IntentJump.intentJump(this, SecondActivity.class);
                break;
            case R.id.main_button:
                break;
            case R.id.back:
                this.finish();
                break;

        }


    }


}
