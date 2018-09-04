package com.auto.mobile.common.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.auto.mobile.R;
import com.auto.mobile.moudle.system.SettingActivity;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 录音dialog
 */
public class SoundDialog extends Dialog{

    private Activity mContext;
    private OnItemClickListener mListener;
    private TextView tvTime;
    private ImageButton btn_lu;

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    public SoundDialog(Activity context) {
        super(context);
        this.mContext = context;
    }


    public SoundDialog(Activity context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    public SoundDialog(Activity context, int themeResId, OnItemClickListener mListener) {
        super(context, themeResId);
        this.mContext = context;
        this.mListener = mListener;
    }

    protected SoundDialog(Activity context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.sound_dialog);
        initView();
        getWindow().setWindowAnimations(R.style.ActionSheetDialogAnimation); //设置窗口弹出动画
        //全屏处理
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        WindowManager wm = mContext.getWindowManager();
        lp.width = wm.getDefaultDisplay().getWidth(); //设置宽度
        getWindow().setAttributes(lp);
    }

    //初始化界面
    private void initView(){
        tvTime=findViewById(R.id.tv_time);
        btn_lu=findViewById(R.id.btn_lu);
        btn_lu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d("sound","松开结束");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("sound","按下录音");
                        stop();
                        break;
                }
                return true;
            }
        });
    }

    private void stop(){
        new QMUIDialog.MessageDialogBuilder(mContext)
                .setTitle("结束录音")
                .setMessage("确定要上传录音吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        final QMUITipDialog tipDialog = new QMUITipDialog.Builder(mContext)
                                .setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
                                .setTipWord("取消上传")
                                .create();
                        tipDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tipDialog.dismiss();
                            }
                        },1500);
                        dismiss();
                    }
                })
                .addAction(0, "确定", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        final QMUITipDialog tipDialog = new QMUITipDialog.Builder(mContext)
                                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                                .setTipWord("上传成功")
                                .create();
                        tipDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tipDialog.dismiss();
                            }
                        },1500);
                        dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    public interface OnItemClickListener{
        void onClick(Dialog dialog, int position);
    }
}
