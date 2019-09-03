package com.example.testovoe4;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import io.reactivex.Observable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class NetworkUsages {

    public boolean isOnline() {
        try {
            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);
            //гугловский dns. Если он упадет - все упадет
            sock.connect(sockaddr, timeoutMs);
            sock.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static Observable<Boolean> isInternetOn(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) throw new AssertionError();
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return Observable.just(activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }

    public void noInternetAlert(Activity activityToShow) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activityToShow);
        builder.setTitle("Произошла ошибка")
                .setMessage("Возможно у вас выключен Wi-fi." +
                        "проверьте соединение с интернетом и попробуйте зайти позже.")
                .setCancelable(false)
                .setNegativeButton("Окей",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
