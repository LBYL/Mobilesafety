package com.example.activity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.mobilesafety.R;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

public class SplashActivity extends Activity {
	private TextView tv_version_name;
	private int mLocalVersionCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		// 初始化UI
		initUI();
		// 初始化数据
		initData();
	}

	/**
	 * 获取数据的方法
	 */
	private void initData() {
		// 1.获取应用版本名称
		tv_version_name.setText("版本名称：" + getVersionName());
		// 2.获取本地版本号
		mLocalVersionCode = getVersionCode();
		// 3.获取服务器版本号(json)
		// json中内容包含
		/**
		 * 更新的版本名称 新版本的描述信息 服务器版本号 新版本apk的下载地址
		 */
		checkVersion();
	}

	private void checkVersion() {
		new Thread() {
			public void run() {
				// 发送请求获取数据，参数为请求json的连接地址
				try {
					// 封装URl地址
					URL url = new URL("http://192.168.2.2:8080/update");
					// 2。开启一个连接
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					//3.设置常见请求参数（请求头）
					connection.setConnectTimeout(2000);
					connection.setReadTimeout(2000);
					
					//默认是get请求方式
					connection.setRequestMethod("POST");
					
					//
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
		// TODO Auto-generated method stub

	}

	/**
	 * 返回版本号
	 * 
	 * @return 非0 代表成功
	 */
	private int getVersionCode() {
		// 1.包管理者对象
		PackageManager pm = getPackageManager();
		// 从包的管理者对象中获取指定报名的基本信息（版本名，称号）
		try {
			PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	/**
	 * 获取版本名称的方法：清单文件中
	 * 
	 * @return
	 */
	private String getVersionName() {
		// 1.包管理者对象
		PackageManager pm = getPackageManager();
		// 从包的管理者对象中获取指定报名的基本信息（版本名，称号）
		try {
			PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 初始化方法
	 */
	private void initUI() {
		// TODO Auto-generated method stub
		tv_version_name = (TextView) findViewById(R.id.tv_version_name);
	}

}
