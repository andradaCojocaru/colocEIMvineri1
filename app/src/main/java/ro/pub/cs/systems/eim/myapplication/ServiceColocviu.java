package ro.pub.cs.systems.eim.myapplication;

// Colocviu1_1Service.java
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.content.Context;
import java.util.Date;
import android.os.Handler;
import android.os.Looper;

public class ServiceColocviu extends Service {
    private Handler handler;
    private Runnable runnable;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String instructions = intent.getStringExtra("instructions");
        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction("ro.pub.cs.systems.eim.myapplication.action.broadcast");
                broadcastIntent.putExtra("message", new Date().toString() + " " + instructions);
                sendBroadcast(broadcastIntent);
            }
        };
        handler.postDelayed(runnable, 5000);
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }
}