package com.empatica.sample;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.empatica.sample.R;

import java.util.Calendar;

/**
 * Created by GHKwon on 2016-02-17.
 */
public class BroadcastD extends BroadcastReceiver {
    String INTENT_ACTION = Intent.ACTION_BOOT_COMPLETED;

    @Override
    public void onReceive(Context context, Intent intent) {//알람 시간이 되었을때 onReceive를 호출함
        //NotificationManager 안드로이드 상태바에 메세지를 던지기위한 서비스 불러오고

        long curTime = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(curTime);

        Log.e("date","recieved"); // 방송 수신 받는 것 확인.
        Log.e("date",  "curTimeMillis : "+ curTime + "----- " + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY))
                + "시-" + String.valueOf(calendar.get(Calendar.MINUTE)) + "분-" + String.valueOf(calendar.get(Calendar.SECOND))+"초");


        Intent notificationIntent = new Intent(context, ResearchActivity.class);
        //notificationIntent.putExtra("notificationId", 9999); //전달할 값
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // 노티피케이션 클릭 시 이동할 화면 지정.



        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("설문조사를 진행합니다.");
        builder.setContentText("알람을 클릭하면 설문지 화면으로 이동합니다.");
        builder.setColor(Color.RED);
        // 사용자가 탭을 클릭하면 자동 제거
        //builder.setAutoCancel(true);
        builder.setContentIntent(contentIntent);


        // 알림 표시
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }


        // id값은
        // 정의해야하는 각 알림의 고유한 int값
        notificationManager.notify(1, builder.build());


    }
}