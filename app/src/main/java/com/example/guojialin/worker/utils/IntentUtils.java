package com.example.guojialin.worker.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.guojialin.worker.bean.User;
import com.example.guojialin.worker.ui.AActivity;
import com.example.guojialin.worker.ui.BActivity;
import com.example.guojialin.worker.ui.CActivity;
import com.example.guojialin.worker.ui.DActivity;
import com.example.guojialin.worker.ui.EActivity;
import com.example.guojialin.worker.ui.FActivity;
import com.example.guojialin.worker.ui.GActivity;
import com.example.guojialin.worker.ui.HActivity;
import com.example.guojialin.worker.ui.IActivity;
import com.example.guojialin.worker.ui.JActivity;
import com.example.guojialin.worker.ui.KActivity;
import com.example.guojialin.worker.ui.LActivity;
import com.example.guojialin.worker.ui.MActivity;
import com.example.guojialin.worker.ui.NActivity;
import com.example.guojialin.worker.ui.OActivity;
import com.example.guojialin.worker.ui.PActivity;
import com.example.guojialin.worker.ui.QActivity;
import com.example.guojialin.worker.ui.RActivity;
import com.example.guojialin.worker.ui.SActivity;

public class IntentUtils {


    public static void intoBAvtivity(Context context) {
        Intent intent = new Intent(context, BActivity.class);
        context.startActivity(intent);
    }

    public static void intoCActivity(Context context) {
        Intent intent = new Intent(context, CActivity.class);
        context.startActivity(intent);
    }

    public static void intoDActivity(Context context) {
        Intent intent = new Intent(context, DActivity.class);
        context.startActivity(intent);
    }

    public static void intoEActivity(Context context) {
        Intent intent = new Intent(context, EActivity.class);
        context.startActivity(intent);
    }

    public static void intoFActivity(User user,Context context){
        Intent intent = new Intent(context, FActivity.class);
        intent.putExtra("user",user);
        context.startActivity(intent);
    }
    public static void intoGActivity(Context context){
        Intent intent = new Intent(context, GActivity.class);
        context.startActivity(intent);
    }
    public static void intoHActivity(Context context){
        Intent intent = new Intent(context, HActivity.class);
        context.startActivity(intent);
    }
    public static void intoIActivity(Context context){
        Intent intent = new Intent(context, IActivity.class);
        context.startActivity(intent);
    }
    public static void intoKActivity(Context context){
        Intent intent = new Intent(context, KActivity.class);
        context.startActivity(intent);
    }
    public static void intoLActivity(Context context){
        Intent intent = new Intent(context, LActivity.class);
        context.startActivity(intent);
    }
    public static void intoJActivity(Context context){
        Intent intent = new Intent(context, JActivity.class);
        context.startActivity(intent);
    }
    public static void intoMActivity(Context context){
        Intent intent = new Intent(context, MActivity.class);
        context.startActivity(intent);
    }
    public static void intoNActivity(Context context){
        Intent intent = new Intent(context, NActivity.class);
        context.startActivity(intent);
    }
    public static void intoOActivity(Context context){
        Intent intent = new Intent(context, OActivity.class);
        context.startActivity(intent);
    }
    public static void intoPActivity(Context context){
        Intent intent = new Intent(context, PActivity.class);
        context.startActivity(intent);
    }

    public static void intoQActivity(Context context){
        Intent intent = new Intent(context, QActivity.class);
        context.startActivity(intent);
    }

    public static void intoRActivity(AActivity context) {
        Intent intent = new Intent(context, RActivity.class);
        context.startActivity(intent);
    }
    public static void intoSActivity(AActivity context) {
        Intent intent = new Intent(context, SActivity.class);
        context.startActivity(intent);
    }
}
