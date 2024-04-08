package ro.pub.cs.systems.eim.myapplication;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread {
    private Context context = null;
    private boolean isRunning = true;

    private String message = null;



    public ProcessingThread(Context context, String text) {
        this.context = context;
        message = text;

    }

    @Override
    public void run() {
        Log.d("thread", "Thread has started!  ");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("thread", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("ro.pub.cs.systems.eim.myapplication.action.broadcast");
        intent.putExtra("instructions", new Date(System.currentTimeMillis()) + " Message: " +
                message);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}

