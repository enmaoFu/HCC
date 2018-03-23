package com.hcc.app.ui;

import android.os.Handler;

import com.hcc.app.R;
import com.hcc.app.base.BaseAty;
import com.hcc.app.config.UserManeger;
import com.hcc.app.ui.user.LoginAty;

/**
 * @title  欢迎页
 * @date   2018/03/22
 * @author enmaoFu
 */
public class SplashActivity extends BaseAty {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        start();
    }

    @Override
    public boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {

    }

    public void start(){
        Handler mHandle = new Handler();
        mHandle.postDelayed(new Runnable() {
            @Override
            public void run() {
                goActivity();
            }
        }, 1000);
    }

    private void goActivity() {
        setHasAnimiation(false);
        if (UserManeger.isLogin()) {
            startActivity(MainActivity.class, null);
            finish();
        } else {
            startActivity(LoginAty.class, null);
            finish();
        }
        overridePendingTransition(R.anim.aty_in, R.anim.activity_alpha_out);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }


}
