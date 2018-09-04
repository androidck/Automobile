package com.auto.mobile.common.util;

import android.util.Log;

import java.util.HashMap;

/**
 * 获取音频时长工具类
 */
public class MediaPlayerUtil {

    /**
     * 获取音频时长工具类
     * @param mUri 音频的地址
     * @return
     */
    public static String getRingDuring(String mUri){
        String duration=null;
        android.media.MediaMetadataRetriever mmr = new android.media.MediaMetadataRetriever();
        try { if (mUri != null) { HashMap<String, String> headers=null;
            if (headers == null) { headers = new HashMap<String, String>();
                headers.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-CN; MW-KW-001 Build/JRO03C) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 UCBrowser/1.0.0.001 U4/0.8.0 Mobile Safari/533.1"); }
            mmr.setDataSource(mUri, headers); }
            duration = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION); }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            mmr.release();
        }
        Log.d("audio",duration+"'");
        return duration;
    }
}
