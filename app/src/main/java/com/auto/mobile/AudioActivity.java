package com.auto.mobile;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.auto.mobile.common.base.BaseActivity;
import com.auto.mobile.common.dialog.SoundDialog;
import com.auto.mobile.common.voice.VoiceManager;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioActivity extends BaseActivity {
    @BindView(R.id.play)
    Button play;
    @BindView(R.id.stop)
    Button stop;
    @BindView(R.id.btn_get)
    Button btnGet;
    @BindView(R.id.btn_play)
    Button btnPlay;


    @Override
    protected int getContextViewId() {
        return 0;
    }

    MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ButterKnife.bind(this);
        try {
            mediaPlayer.setDataSource(this, Uri.parse("http://pek717tnq.bkt.clouddn.com/icon_20180905110243.amr"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.play, R.id.stop,R.id.btn_play})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.play:
                new SoundDialog(AudioActivity.this, R.style.ActionSheetDialogStyle, new SoundDialog.OnItemClickListener() {
                    @Override
                    public void onClick(Dialog dialog, int position) {

                    }
                }).show();
                break;
            case R.id.stop:

                break;
            case R.id.btn_play:

                break;
        }
    }



}
