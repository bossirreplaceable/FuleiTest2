package com.fulei.fuleitest.greendao;

import com.fulei.fuleitest.application.MyApplication;

/**
 * Created by gcl on 2017/8/1.
 */

public class DaoManager {
        private static DaoManager mInstance;
        private DaoMaster mDaoMaster;
        private DaoSession mDaoSession;
        private DaoManager() {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getContext(), "goodsdb", null);
            DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
        public static DaoManager getInstance() {
            if (mInstance == null) {
                mInstance = new DaoManager();
            }
            return mInstance;
        }

        public DaoMaster getMaster() {
            return mDaoMaster;
        }

        public DaoSession getSession() {
            return mDaoSession;
        }

        public DaoSession getNewSession() {
            mDaoSession = mDaoMaster.newSession();
            return mDaoSession;
        }
    }
