package com.checkinternet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.checkinternet.Network.Net;
import com.checkinternet.Network.NetChange;

public class MainActivity extends AppCompatActivity {

    NetChange nChg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openWifi();

    }


    public void onClickAll(View view) {

        switch (view.getId()) {
            case R.id.buttonCheck:
                Net.NetCheck(this, (TextView) findViewById(R.id.TextViewInfo));

                // 解除接收系統 Broadcast
                try {
                    unregisterReceiver(nChg);
                } catch (IllegalArgumentException e) {
                    Log.d("TAG_Broadcast", "已經取消過了");
                }
                break;
            case R.id.buttonAuto:
                nChg = new NetChange();

                // 註冊接收系統 Broadcast
                IntentFilter inf = new IntentFilter();
                inf.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
                registerReceiver(nChg, inf);
                break;
        }
    }


    public void openWifi(){

        //若wifi狀態為關閉則將它開啟
        WifiManager wfm = ((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE));
        if (!wfm.isWifiEnabled()) {

            new AlertDialog.Builder(this)
                    .setMessage("應用程式要求開啟 Wi-Fi 功能。")
                    .setPositiveButton("允許", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE)).setWifiEnabled(true);
                        }
                    })
                    .setNegativeButton("拒絕", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ;
                        }
                    })
                    .show();

        }

    }


}




