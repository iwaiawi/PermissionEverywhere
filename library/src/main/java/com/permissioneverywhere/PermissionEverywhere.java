package com.permissioneverywhere;

import android.app.Notification;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Farruxx on 30.04.2016.
 */
public class PermissionEverywhere {
    public static PermissionRequest getPermission(final Context context,
                                                  final String[] permissions,
                                                  final int requestCode,
                                                  final String notificationTitle,
                                                  final String notificationText,
                                                  @DrawableRes final int notificationIcon) {
        return getPermission(context, permissions, requestCode, new NotificationCustomizer() {
            @Override
            public NotificationCompat.Builder assembly(final NotificationCompat.Builder builder) {
                // default customizer
                return builder.setSmallIcon(notificationIcon)
                        .setContentTitle(notificationTitle)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationText))
                        .setContentText(notificationText)
                        .setAutoCancel(true)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setVibrate(new long[0]);
            }
        });
    }

    public static PermissionRequest getPermission(final Context context,
                                                  final String[] permissions,
                                                  final int requestCode,
                                                  final NotificationCustomizer customizer) {
        return new PermissionRequest(context, permissions, requestCode, customizer);
    }
}
