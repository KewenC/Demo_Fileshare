package com.kewenc.demo_fileshare;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserManager.sUserId = 2;
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SecondActivity.class);
                User user = new User(0, "jake", true);
                user.book = new Book();
                intent.putExtra("extra_user", (Serializable) user);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
//        Log.d(TAG, "UserManage.sUserId=" + UserManager.sUserId);
        persistToFile();

        super.onResume();
    }

    private void persistToFile() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                User user = new User(1, "hello world", false);
                File dir = new File(MyConstants.CHAPTER_2_PATH);
                Log.d(TAG,"File的路径："+MyConstants.CHAPTER_2_PATH);
                if (!dir.exists()) {
                        boolean aa=dir.mkdirs();
                    Log.d(TAG,"mkdirs-"+aa);
//                        Log.d(TAG,""+"createFile");
//                    if(dir.mkdirs()){
//                        //do something
//                        Log.d(TAG,""+"createFile");
//                    }else{
//                        //do something else
//                        Log.d(TAG,""+"no createFile");
//                    }
                }

                Log.d(TAG,"exists-"+dir.exists());
//                File cachedFile = new File(MyConstants.CACHE_FILE_PATH);
//                if (!cachedFile.exists()){
//                    try {
//                        cachedFile.createNewFile();
//                    }catch (Exception e){
//                        Log.d(TAG,"cache:::::errer!");
//                    }
//
//                }
//                Log.d(TAG,"cache:::::"+cachedFile.exists());
//                ObjectOutputStream objectOutputStream = null;
//                try {
//                    Log.d(TAG, "persist user:" + user);
//                    objectOutputStream = new ObjectOutputStream(
//                            new FileOutputStream(cachedFile));
//                    objectOutputStream.writeObject(user);
//                    Log.d(TAG, "persist user:" + user);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    MyUtils.close(objectOutputStream);
//                }
            }
        }).start();
    }
}
