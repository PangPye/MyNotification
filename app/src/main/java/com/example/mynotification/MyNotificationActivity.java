package com.example.mynotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RemoteViews;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyNotificationActivity extends AppCompatActivity {
    private NotificationManager notificationManager;
    @BindView(R.id.btn_ordinary)
    Button btnOrdinary;
    @BindView(R.id.btn_fold)
    Button btnFold;
    @BindView(R.id.btn_hang)
    Button btnHang;
    @BindView(R.id.rb_public)
    RadioButton rbPublic;
    @BindView(R.id.rb_private)
    RadioButton rbPrivate;
    @BindView(R.id.rb_secret)
    RadioButton rbSecret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notification);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_ordinary, R.id.btn_fold, R.id.btn_hang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ordinary:
                sendOrdinaryNotification();
                break;
            case R.id.btn_fold:
                sendFoldNotification();
                break;
            case R.id.btn_hang:
                sendHangNotification();
                break;
        }
    }

    private void sendHangNotification() {
        Notification.Builder builder = new Notification.Builder(this);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("悬挂通知 ");
        //设置点击事件
        Intent hangIntent = new Intent();
        hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        hangIntent.setClass(this,MyNotificationActivity.class);
        PendingIntent hangpendingIntent= PendingIntent.getActivity(
                this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(hangpendingIntent,true);
        notificationManager.notify(2,builder.build());



    }

    private void sendFoldNotification() {
        Notification.Builder builder = new Notification.Builder(this);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("折叠通知");
        // 用RemoteViews来创建自定义Notification视图
        RemoteViews remoteViews=new RemoteViews(getPackageName(),R.layout.view_fold);
        Notification notification = builder.build();
        //指定展开时的视图
        notification.bigContentView=remoteViews;
        notificationManager.notify(1,notification);
    }

    private void sendOrdinaryNotification() {
        Notification.Builder builder = new Notification.Builder(this);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("普通通知");
        notificationManager.notify(0,builder.build());
    }
}
