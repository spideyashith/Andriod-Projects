package com.example.broadcastrecivier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMSReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.


        Toast.makeText(context, "you have received an SMS", Toast.LENGTH_SHORT).show();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            Object[] sms = (Object[]) bundle.get("pdus");
            for (int i = 0; i < sms.length; i++) {
                SmsMessage msg = SmsMessage.createFromPdu((byte[]) sms[i]);
                Toast.makeText(context,"Message is"+msg.getDisplayMessageBody()+""
                +msg.getDisplayOriginatingAddress(),Toast.LENGTH_SHORT).show();

                String message = msg.getMessageBody();
                Pattern pattern = Pattern.compile("(\\b\\d{5}\\b)");
                Matcher matcher = pattern.matcher(message);
                int OTP = 0;
                if (matcher.find()){
                    OTP = Integer.parseInt(matcher.group(1));
                }
                Toast.makeText(context,"OTP is"+OTP,
                        Toast.LENGTH_SHORT).show();
                OTPActivity otpact = new OTPActivity();
                otpact.putOTP(OTP);

            }
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
}