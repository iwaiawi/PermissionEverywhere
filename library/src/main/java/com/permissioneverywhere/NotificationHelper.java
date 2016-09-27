package com.permissioneverywhere;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.os.ResultReceiver;


class NotificationHelper {
    public static final int REQUEST_CODE_PUSH = 77;

    public static void sendNotification(final Context context,
                                        final String[] permissions,
                                        final int requestCode,
                                        final String notificationTitle,
                                        final String notificationText,
                                        final int notificationIcon,
                                        final ResultReceiver receiver) {

        final Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra(Const.REQUEST_CODE, requestCode);
        intent.putExtra(Const.PERMISSIONS_ARRAY, permissions);
        intent.putExtra(Const.RESULT_RECEIVER, receiver);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);

        final PendingIntent pendingIntent = PendingIntent.getActivity(context, REQUEST_CODE_PUSH, intent,
                PendingIntent.FLAG_ONE_SHOT);

        final Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final  NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(notificationIcon)
                .setContentTitle(notificationTitle)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationText))
                .setContentText(notificationText)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setPriority(Notification.PRIORITY_HIGH)
                .setVibrate(new long[0])
                .setContentIntent(pendingIntent);

        final NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(requestCode, notificationBuilder.build());
    }
}
