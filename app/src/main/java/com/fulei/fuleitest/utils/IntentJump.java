package com.fulei.fuleitest.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by gcl on 2017/7/31.
 */

public class IntentJump {

    public static void intentJump(Activity context, Class<?> clzz) {
        Intent intent = new Intent(context, clzz);
        context.startActivity(intent);
    }


}
