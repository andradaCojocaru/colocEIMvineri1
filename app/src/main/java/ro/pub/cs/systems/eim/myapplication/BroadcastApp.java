package ro.pub.cs.systems.eim.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.TextView;

public class BroadcastApp extends BroadcastReceiver{

    private TextView messageTextView;

    public BroadcastApp(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("instructions");
        if (messageTextView != null) {
            messageTextView.setText(messageTextView.getText().toString() + "\n" + message);
        }
        Log.d("PracticalTest01BroadcastReceiver", message);
    }
}
