package com.permissioneverywhere;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.os.ResultReceiver;


class NotificationHelper {
    public static final int REQUEST_CODE_PUSH = 77;

    public static void sendNotification(final Context context,
                                        final String[] permissions,
                                        final int requestCode,
                                        final NotificationCustomizer customizer,
                                        final ResultReceiver receiver) {

        final PendingIntent pendingIntent = createPendingIntent(context, permissions, requestCode, receiver);

        final NotificationCompat.Builder notificationBuilder = customizer.assembly(new NotificationCompat.Builder(context))
                .setContentIntent(pendingIntent);

        final NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(requestCode, notificationBuilder.build());
    }

    private static PendingIntent createPendingIntent(final Context context, final String[] permissions, final int requestCode, final ResultReceiver receiver) {
        final Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra(Const.REQUEST_CODE, requestCode);
        intent.putExtra(Const.PERMISSIONS_ARRAY, permissions);
        intent.putExtra(Const.RESULT_RECEIVER, receiver);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);

        return PendingIntent.getActivity(context, REQUEST_CODE_PUSH, intent,
                PendingIntent.FLAG_ONE_SHOT);
    }
}
