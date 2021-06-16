package com.github.pro_arm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClientService extends JobIntentService {
    private static final String TAG = TCPClientService.class.getName();
    private static final int JOB_ID = 655;

    private Socket socket;
    private boolean running = true;
    @NonNull
    private DataInputStream inputStream;
    @NonNull
    private DataOutputStream outputStream;

    String ip;
    int port;

    /**
     * @param context Activity context
     * @param intent  service intent
     */
    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, TCPClientService.class, JOB_ID, intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        ip = intent.getStringExtra(AppConst.IP_EXTRAS);
        Log.d(TAG, "onHandleWork: " + ip);

        port = intent.getIntExtra(AppConst.PORT, 8080);
        Log.d(TAG, "onHandleWork: " + port);

        InetAddress Ip = null;
        try {
            Ip = InetAddress.getByName(ip);
            socket = new Socket(Ip, port);

            //get input and output stream from socket
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            while(running){
                try {
                    Log.i(TAG, "onHandleWork: pre send");
                    outputStream.writeUTF("Hello from client");
                    Log.i(TAG, "onHandleWork: post send");
                    Log.i(TAG, "onHandleWork: "+inputStream.readUTF());
                } catch (IOException e) {
                    Log.e(TAG, "onHandleWork: ", e);

                    inputStream.close();
                    outputStream.close();
                }
            }

        } catch (IOException e) {
            Log.e(TAG, "onHandleWork: ", e);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        running = false;
    }
}
