package com.auto.mobile.moudle.user.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.auto.mobile.R;
import com.auto.mobile.common.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 注册
 */
public class RegisterActivity extends BaseActivity {

    public int T = 10; //倒计时时长
    @BindView(R.id.topBar)
    QMUITopBar topBar;


    private Handler mHandler = new Handler();

    @Override
    protected int getContextViewId() {
        return R.id.register_activity_id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        ButterKnife.bind(this);
        //设置状态栏的颜色为黑色
        setStatusBarLightMode(RegisterActivity.this);
        initTopBar();
    }

    //初始化标题栏
    private void initTopBar(){
        topBar.setTitle(R.string.register);
        topBar.addLeftImageButton(R.mipmap.fanhui,R.id.fanhui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    /*class MyCountDownTimer implements Runnable {

        @Override
        public void run() {

            //倒计时开始，循环
            while (T > 0) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //设置按钮不可点击
                        btnSend.setClickable(false);
                        btnSend.setText(T+"秒后重新发送");
                    }
                });
                try {
                    Thread.sleep(1000); //强制线程休眠1秒，就是设置倒计时的间隔时间为1秒。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                T--;
            }

            //倒计时结束，也就是循环结束
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    //设置按钮可以点击
                    btnSend.setClickable(true);
                    btnSend.setText("重新发送");
                }
            });
            T = 10; //最后再恢复倒计时时长
        }
    }*/
}
