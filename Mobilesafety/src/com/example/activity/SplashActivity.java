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

		// ��ʼ��UI
		initUI();
		// ��ʼ������
		initData();
	}

	/**
	 * ��ȡ���ݵķ���
	 */
	private void initData() {
		// 1.��ȡӦ�ð汾����
		tv_version_name.setText("�汾���ƣ�" + getVersionName());
		// 2.��ȡ���ذ汾��
		mLocalVersionCode = getVersionCode();
		// 3.��ȡ�������汾��(json)
		// json�����ݰ���
		/**
		 * ���µİ汾���� �°汾��������Ϣ �������汾�� �°汾apk�����ص�ַ
		 */
		checkVersion();
	}

	private void checkVersion() {
		new Thread() {
			public void run() {
				// ���������ȡ���ݣ�����Ϊ����json�����ӵ�ַ
				try {
					// ��װURl��ַ
					URL url = new URL("http://192.168.2.2:8080/update");
					// 2������һ������
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					//3.���ó����������������ͷ��
					connection.setConnectTimeout(2000);
					connection.setReadTimeout(2000);
					
					//Ĭ����get����ʽ
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
	 * ���ذ汾��
	 * 
	 * @return ��0 ����ɹ�
	 */
	private int getVersionCode() {
		// 1.�������߶���
		PackageManager pm = getPackageManager();
		// �Ӱ��Ĺ����߶����л�ȡָ�������Ļ�����Ϣ���汾�����ƺţ�
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
	 * ��ȡ�汾���Ƶķ������嵥�ļ���
	 * 
	 * @return
	 */
	private String getVersionName() {
		// 1.�������߶���
		PackageManager pm = getPackageManager();
		// �Ӱ��Ĺ����߶����л�ȡָ�������Ļ�����Ϣ���汾�����ƺţ�
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
	 * ��ʼ������
	 */
	private void initUI() {
		// TODO Auto-generated method stub
		tv_version_name = (TextView) findViewById(R.id.tv_version_name);
	}

}
