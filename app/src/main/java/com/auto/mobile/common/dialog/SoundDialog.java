package com.auto.mobile.common.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.auto.mobile.R;
import com.auto.mobile.common.application.MyApplication;
import com.auto.mobile.common.qiniu.token.Auth;
import com.auto.mobile.common.util.IsInternet;
import com.auto.mobile.common.view.VoiceLineView;
import com.auto.mobile.common.voice.VoiceManager;
import com.auto.mobile.moudle.system.SettingActivity;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.json.JSONObject;

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
    //声音曲线
    private VoiceLineView voicLine;
    private EnRecordVoiceListener enRecordVoiceListener;

    //录音管理器
    private VoiceManager voiceManager;

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

    /**
     * 设置监听
     *
     * @param enRecordVoiceListener
     */
    public void setEnrecordVoiceListener(EnRecordVoiceListener enRecordVoiceListener) {
        this.enRecordVoiceListener = enRecordVoiceListener;
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
        //初始化音频管理器
        voiceManager =VoiceManager.getInstance(mContext);
    }

    //初始化界面
    private void initView(){
        tvTime=findViewById(R.id.tv_time);
        btn_lu=findViewById(R.id.btn_lu);
        voicLine=findViewById(R.id.voicLine);
        btn_lu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d("sound","松开结束");
                        //按住开始录音
                        if(voiceManager!=null){
                            voiceManager.startVoiceRecord(Environment.getExternalStorageDirectory().getPath()+"/VoiceManager/audio");
                            voiceListener();
                        }
                        //录音监听
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("sound","按下录音");
                        //结束录音
                        if(voiceManager!=null){
                            voiceManager.stopVoiceRecord();
                        }
                        break;
                }
                return true;
            }
        });
    }
    //停止上传录音
    private void stop(final String path){
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
                        uploadImg2QiNiu(path);
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

    //录音监听
    private void voiceListener(){
        voiceManager.setVoiceRecordListener(new VoiceManager.VoiceRecordCallBack() {
            @Override
            public void recDoing(long time, String strTime) {
                tvTime.setText(strTime);
            }

            @Override
            public void recVoiceGrade(int grade) {
                voicLine.setVolume(grade);
            }

            @Override
            public void recStart(boolean init) {
                voicLine.setContinue();
            }

            @Override
            public void recPause(String str) {
                voicLine.setPause();
            }

            @Override
            public void recFinish(long length, String strLength, String path) {
                stop(path);
                if(enRecordVoiceListener != null){
                    enRecordVoiceListener.onFinishRecord(length, strLength, path);
                }
            }
        });

    }

    //上传照片到七牛云
    private void uploadImg2QiNiu(String fileCropUri) {
        if (IsInternet.isNetworkAvalible(getContext())==false){
            final QMUITipDialog tipDialog = new QMUITipDialog.Builder(mContext)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                    .setTipWord("网络错误")
                    .create();
            tipDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    tipDialog.dismiss();
                }
            },1500);
            dismiss();
        }else {
            Configuration config = new Configuration.Builder()
                    .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认 256K
                    .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认 512K
                    .connectTimeout(10) // 链接超时。默认 10秒
                    .responseTimeout(60) // 服务器响应超时。默认 60秒
                    .zone(FixedZone.zone0) // 设置区域，指默认 Zone.zone0 <span style="font-size:14px;"><strong><span style="color:#FF0000;">注：这步是最关键的 当初错的主要原因也是他 根据自己的地方选</span></strong></span>
                    .build();
            UploadManager uploadManager = new UploadManager();
            // 设置图片名字
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String key = "icon_" + sdf.format(new Date())+".amr";
            uploadManager.put(fileCropUri, key, Auth.create(MyApplication.AccessKey, MyApplication.SecretKey).uploadToken("chebangbang"), new UpCompletionHandler() {
                @Override
                public void complete(String key, ResponseInfo info, JSONObject res) {
                    // info.error中包含了错误信息，可打印调试
                    // 上传成功后将key值上传到自己的服务器
                    if (info.isOK()) {
                        String headpicPath = "http://pek717tnq.bkt.clouddn.com/" + key;
                        Log.d("luyinUrl",headpicPath);
                    }
                }
            }, null);
        }
    }

    public interface OnItemClickListener{
        void onClick(Dialog dialog, int position);
    }

    /**
     * 结束回调监听
     */
    public interface EnRecordVoiceListener {
        void onFinishRecord(long length, String strLength, String filePath);
    }
}
