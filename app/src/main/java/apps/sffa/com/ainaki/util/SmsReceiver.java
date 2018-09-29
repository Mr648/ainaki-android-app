package apps.sffa.com.ainaki.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

    private static SmsListener mListener;
    private static final String TAG = SmsReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();

        Object[] pdus = (Object[]) data.get("pdus");

        for (int i = 0; i < pdus.length; i++) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

            String sender = smsMessage.getDisplayOriginatingAddress();

            if (CONSTANTS.SMS_SENDER_NUMBER.contains(sender)  ) {
                String messageBody = smsMessage.getMessageBody();
                String verificationCode = messageBody.replaceAll("\\D+", "");
                mListener.onMessageReceived(verificationCode);
            }
        }

    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }
}