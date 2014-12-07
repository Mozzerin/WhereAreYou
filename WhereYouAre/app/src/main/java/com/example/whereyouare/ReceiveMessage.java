package com.example.whereyouare;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class ReceiveMessage extends BroadcastReceiver {

	private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
	public MainActivity Notification = new MainActivity();

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent != null && intent.getAction() != null
				&& ACTION.compareToIgnoreCase(intent.getAction()) == 0) {
			Object[] pduArray = (Object[]) intent.getExtras().get("pdus");
			SmsMessage[] messages = new SmsMessage[pduArray.length];
			for (int i = 0; i < pduArray.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) pduArray[i]);
			}
	
			// выбираем нужное сообщение

			String sms_body = messages[0].getMessageBody();
			if (sms_body.contains("WAY&")) {
				StringBuilder bodyText = new StringBuilder();
				for (int i = 0; i < messages.length; i++) {
					bodyText.append(messages[i].getMessageBody());
					displayNotification(context);
					MainActivity.updateMessageBox(sms_body);
				}
			}

		}
	}

	private void displayNotification(Context context) {
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification.Builder builder = new Notification.Builder(context);
		Intent intent = new Intent(context, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		builder.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("ContentTitle").setContentText("ContentText")
				.setContentInfo("ContentInfo").setTicker("Ticker")
				.setLights(0xFFFF0000, 500, 500) // setLights (int argb, int
													// onMs, int offMs)
				.setContentIntent(pendingIntent).setAutoCancel(true);
		Notification notification = builder.getNotification();
		notificationManager.notify(R.drawable.ic_launcher, notification);
	}

}
