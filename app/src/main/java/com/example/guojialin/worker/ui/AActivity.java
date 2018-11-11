package com.example.guojialin.worker.ui;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.guojialin.worker.IAdditionService;
import com.example.guojialin.worker.IMyBinderPoolInterface;
import com.example.guojialin.worker.R;
import com.example.guojialin.worker.base.BaseActivity;
import com.example.guojialin.worker.base.DBHelper;
import com.example.guojialin.worker.bean.ListNode;
import com.example.guojialin.worker.bean.User;
import com.example.guojialin.worker.contant.Constant;
import com.example.guojialin.worker.ja.ThreadPrinter;
import com.example.guojialin.worker.service.AService;
import com.example.guojialin.worker.service.AdditionService;
import com.example.guojialin.worker.service.SubtractService;
import com.example.guojialin.worker.table.UserTable;
import com.example.guojialin.worker.utils.FileUtils;
import com.example.guojialin.worker.utils.IntentUtils;
import com.example.guojialin.worker.utils.ReserveUtils;
import com.example.guojialin.worker.utils.SearchUtils;
import com.example.guojialin.worker.utils.Solution;
import com.example.guojialin.worker.utils.SortUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class AActivity extends BaseActivity {

    private AService aService; //实例
    private IAdditionService additionService;
    private IMyBinderPoolInterface subtractService;
    private Button button10, button11;
    private final String Tag = AActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        initViews();
        initPermission();
    }

    private void initPermission() {
        if (!hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            requestPermission(Constant.WRITE_EXTERNAL_CODE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            button10.setEnabled(false);
        } else {
            button10.setEnabled(true);
        }
    }

    private void initViews() {
        button10 = (Button) findViewById(R.id.button10);
        button11 = (Button) findViewById(R.id.button11);
    }

    public void toB(View view) {
        IntentUtils.intoBAvtivity(this);
    }

    public void toC(View view) {
        IntentUtils.intoCActivity(this);
    }

    public void toD(View view) {
        IntentUtils.intoDActivity(this);
    }

    public void toE(View view) {
        IntentUtils.intoEActivity(this);
    }

    public void toG(View view) { IntentUtils.intoGActivity(this); }

    public void toH(View view) { IntentUtils.intoHActivity(this); }

    public void toI(View view) { IntentUtils.intoIActivity(this); }

    public void toK(View view) { IntentUtils.intoKActivity(this); }

    public void toL(View view) { IntentUtils.intoLActivity(this); }

    public void toJ(View view) { IntentUtils.intoJActivity(this); }

    public void toM(View view) { IntentUtils.intoMActivity(this); }

    public void toN(View view) { IntentUtils.intoNActivity(this); }

    public void toO(View view) { IntentUtils.intoOActivity(this); }

    public void toP(View view) { IntentUtils.intoPActivity(this); }

    public void toQ(View view) { IntentUtils.intoQActivity(this); }

    public void toR(View view) { IntentUtils.intoRActivity(this); }

    public void toS(View view) { IntentUtils.intoSActivity(this); }

    public void quickSort(View view){
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                int[] orList = {2,6,3,5,4,1,8,45,2};
//                orList = SortUtils.quickSort(orList);
//                for (int i=0;i<orList.length;i++){
//                  System.out.println(orList[i]);
//                }
//            }
//        };
//        Thread thread = new Thread(runnable);
//        thread.start();
        int [] A = new int[]{1,3,-3};
        Solution s = new Solution();
        System.out.println(s.solution(A));
//        Solution solution = new Solution();
//        int[] arr = {3,1,7,2,6,8,4,5};
//        solution.change(arr,0,arr.length-1);
//        System.out.println(Arrays.toString(arr));
    }

    public void binarySearch(View view){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int [] srcAry = new int[]{1,2,3,4,5,6,7,8,9,10};
                int index = SearchUtils.binarySearch(srcAry,8);
                System.out.println("search index : "+index+"");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void threadPrint(View view){
        ThreadPrinter threadPrinter = new ThreadPrinter();
        threadPrinter.startPrint();
    }
    public void reverseList(View view){
        ListNode listNode1 = new ListNode();
        ListNode listNode2 = new ListNode();
        ListNode listNode3 = new ListNode();
        listNode1.data = 10;
        listNode3.data = 100;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = null;
        ListNode listNode = ReserveUtils.reverseList3(listNode1);
        System.out.println("reverse list head data: " + listNode.getData());
    }

    public void toF(View view){
        User user = new User();
        user.setUserName("adam");
        user.setUserNumber(2018);
        IntentUtils.intoFActivity(user,this);
    }

    public void toAdd(View view){
        Intent intent = new Intent(this,AdditionService.class);
        bindService(intent,serviceConnection2,Context.BIND_AUTO_CREATE);
    }

    public void toResult(View view){
        try {
            int result = additionService.add(10,90);
            Log.i(Tag,"10 + 90 = result ：" + result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void toClient(View view){
        Intent intent = new Intent(this,SubtractService.class);
        startService(intent);
    }

    // 子线程处理耗时操作
    public void delDatabase(View view){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                delTestDataFromSQl();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void delTestDataFromSQl() {

    }

    public void insertDatabase(View view){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                insertTestDataFromSQl();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }


    private void insertTestDataFromSQl() {
        ArrayList<User> users = new ArrayList<>();
        Log.i(Tag," start preparing the test data.");
        long cTime = System.currentTimeMillis();
        for (int i = 0; i < 6000; i++){
            User user = new User(i,"test" + i);
            users.add(user);
        }
        long utime = System.currentTimeMillis();
        Log.i(Tag,"data preparation . " + "elapsed time : " + (utime -cTime) + "ms");
        Log.i(Tag," data size : " + users.size());
        long timeOne = System.currentTimeMillis();
        insertMethod2(users);
        long timeSecond = System.currentTimeMillis();
        Log.i(Tag,"insert sql finished ." + " elapsed time: " + (timeSecond - timeOne) + "ms");
    }

    private void insertMethod1(ArrayList<User> users) {
        String sql = "INSERT INTO "+ UserTable.TABLE_NAME + " (" + UserTable.USER_NUMBER + "," + UserTable.USER_NAME + ") VALUES (?, ?)";
        SQLiteStatement statement = DBHelper.getDbInstance().compileStatement(sql);
        Log.i(Tag,"start insert sql ");
        for (int i = 0; i < users.size(); i++){
            statement.bindLong(1,users.get(i).getUserNumber());
            statement.bindString(1,users.get(i).getUserName());
            statement.execute();
            statement.clearBindings();
        }
        DBHelper.closeDB();
    }

    private void insertMethod2(ArrayList<User> users){
        SQLiteDatabase db = DBHelper.getDbInstance();
        //开启一个事务
        db.beginTransaction();
        String sql = "INSERT INTO "+ UserTable.TABLE_NAME + " (" + UserTable.USER_NUMBER + ","
                + UserTable.USER_NAME + ") VALUES (?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        for (int i = 0; i < users.size(); i++){
            statement.bindLong(1,users.get(i).getUserNumber());
            statement.bindString(1,users.get(i).getUserName());
            statement.execute();
            statement.clearBindings();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void getDatabase(View view){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
               queryUserListFromSQl();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void queryUserListFromSQl() {
        synchronized (DBHelper.class){
            SQLiteDatabase db = DBHelper.getDbInstance();
            int i = 0;
            if (db != null){
                Cursor cursor = db.query(UserTable.TABLE_NAME,null,null,null,null,null,UserTable.USER_NUMBER,"10");
                while (cursor.moveToNext()){
                    i++;
                }
                cursor.close();
                Log.i(Tag,"query count: " + i);
            }else {
                Log.i(Tag,"query fail");
            }
        }
    }

    //耗时操作放在子线程中
    public void toFile(View view) {

        File file = FileUtils.creatNewFile();
        try {
            OutputStream outputStream = null;
            //通过子类实例化
            outputStream = new FileOutputStream(file, true);
            String testStr = "this is outputStream demo";
            byte[] strByts = testStr.getBytes();
            outputStream.write(strByts);
            outputStream.close();
            Log.i(Tag, "execute write file finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fromFile(View view) {
        File file = FileUtils.getExistFile("", "20180906_171853.txt");
        if (file == null) {
            Log.i(Tag, "file read fail");
            button11.setEnabled(false);
            return;
        } else {
            try {
                //字节输入流，通过子类实例化
                InputStream inputStream = new FileInputStream(file);
                //开辟空间接收要读取的内容
                byte [] b = new byte[1024];
                int len = inputStream.read(b);
                Log.i(Tag,"show file :" + new String(b,0,len));
                //关闭
                inputStream.close();
            }catch (Exception e){
               e.printStackTrace();
            }

        }
    }

    public void startService(View view) {

        Intent start = new Intent(this, AService.class);
        startService(start);
    }

    public void stopService(View view) {
        Intent stop = new Intent(this, AService.class);
        stopService(stop);
    }

    public void bindService(View view) {
        Intent bind = new Intent(this, AService.class);
        bindService(bind, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void unbindService(View view) {
        unbindService(serviceConnection);
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            AService.ABinder binder = (AService.ABinder) iBinder;
            aService = binder.getService();
            Log.i(Tag, "获取绑定服务实例-" + aService.getClass().getSimpleName());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(Tag, "绑定实例失败。");
        }
    };

    private ServiceConnection serviceConnection2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            additionService = IAdditionService.Stub.asInterface(service);
            Log.i(Tag,"service connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            additionService = null;
            Log.i(Tag,"service disconnected.");
        }
    };

    private ServiceConnection serviceConnection3 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            subtractService = IMyBinderPoolInterface.Stub.asInterface(service);
            Log.i(Tag,"service connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            subtractService = null;
            Log.i(Tag,"service disconnected.");
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constant.WRITE_EXTERNAL_CODE:
                button10.setEnabled(true);
                break;
        }
    }
}
