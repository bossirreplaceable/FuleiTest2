package com.fulei.fuleitest.device;

import com.fulei.device.GoodsDevice;

/**
 * Created by gcl on 2017/8/1.
 */

public class FuleiGoodsWay extends GoodsDevice {
    private static GoodsDevice way;

    public FuleiGoodsWay(String devType, String com, String brn) {
        super(devType, com, brn);
        way = new GoodsDevice(devType, com, brn);
    }

    // 构造函数私有化
    private void GoodsDevice() {

    }

    // 提供一个全局的静态方法
    public static GoodsDevice getWayDevice(String devType, String com, String brn) {
        if (way == null) {
            synchronized (FuleiGoodsWay.class) {
                if (way == null) {
                    way = new GoodsDevice(devType, com, brn);
                }
            }
        }
        return way;
    }
}
