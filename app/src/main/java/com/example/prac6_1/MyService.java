package com.example.prac6_1;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        final WindowManager.LayoutParams params = new
                WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);


        View view = LayoutInflater.from(this).inflate(R.layout.banner, null);
        ((TextView) view.findViewById(R.id.textBanner)).setText("BannerText");
        windowManager.addView(view, params);
        params.gravity = Gravity.BOTTOM | Gravity.CENTER;
        windowManager.updateViewLayout(view, params);

        view.findViewById(R.id.closeBanner).setOnClickListener(v -> {
            Intent intent2 = new Intent(this, MainActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
            windowManager.removeView(view);
        });

        return super.onStartCommand(intent, flags, startId);
    }
}