package com.checkinternet.Network;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.checkinternet.R;

/**
 * Created by Felix on 2017/10/9.
 */

public class NetChange extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean chk = Net.NetCheck(context, (TextView) ((Activity) context).findViewById(R.id.TextViewInfo));
        Toast.makeText(context, "Network is Available ? " + chk, Toast.LENGTH_SHORT).show();
    }

}
