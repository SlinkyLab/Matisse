package com.zhihu.matisse.internal.utils;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;

import androidx.annotation.NonNull;

/**
 * @author 工藤
 * @email gougou@16fan.com
 * create at 2018年10月23日12:17:59
 * description:媒体扫描
 */
public class SingleMediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {

    private MediaScannerConnection mMsc;
    private String mPath;
    private ScanListener mListener;

    public interface ScanListener {

        /**
         * scan finish
         */
        void onScanFinish();
    }

    public SingleMediaScanner(Context context, @NonNull String mPath, ScanListener mListener) {
        this.mPath = mPath;
        this.mListener = mListener;
        this.mMsc = new MediaScannerConnection(context, this);
        this.mMsc.connect();
    }

    @Override public void onMediaScannerConnected() {
        try {
            mMsc.scanFile(mPath, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override public void onScanCompleted(String mPath, Uri mUri) {
        mMsc.disconnect();
        if (mListener != null) {
            mListener.onScanFinish();
        }
    }
}
