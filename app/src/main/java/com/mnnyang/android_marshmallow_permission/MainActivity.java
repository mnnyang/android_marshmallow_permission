package com.mnnyang.android_marshmallow_permission;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                1.简单使用
//                checkCallPermission();
//                2.使用封装工具
                callAction();
            }
        });

    }

    private void callAction() {
        RequestPermission.with(this)
                .permissions(Manifest.permission.CALL_PHONE)
                .request(new RequestPermission.Callback() {
                    @Override
                    public void onGranted() {
                        call();
                    }

                    @Override
                    public void onDenied() {
                        Toast.makeText(MainActivity.this,
                                "拒绝了权限", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    /**
     * 打电话操作
     */
    @SuppressLint("MissingPermission")
    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
        startActivity(intent);
    }

    /**
     * 检查是否有权限
     */
    private void checkCallPermission() {
        /*1. 检查权限*/
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            /*2. 申请权限*/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
                return;
            }
        }
        /*有权限 执行操作*/
        call();
    }

    /**
     * 申请回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        RequestPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);

        /*3. 对申请回调处理*/
//        if (requestCode == 1 && grantResults.length > 0) {
//            ArrayList<String> deniedPermissions = new ArrayList<>();
//            for (int i = 0; i < permissions.length; i++) {
//                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {

                    /*5. 被决绝 提示用户*/
//                    Toast.makeText(this, "拒绝了权限", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            }

            /*4. 成功申请 执行操作*/
//            call();
    }
}
