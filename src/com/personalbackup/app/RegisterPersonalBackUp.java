package com.personalbackup.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPersonalBackUp extends Activity
{
	private Button tijiaoBtn=null;
	private Button backIndexBtn=null;
	private EditText usernameE=null;
	private EditText userpasswordE=null;
	private EditText userpassword2E=null;
	private EditText passwordTishiE=null;
	private String username=null;
	private String userpassword1=null;
	private String userpassword2=null;
	private String userpasswordTishi=null;
	  @Override
	    public void onCreate(Bundle savedInstanceState) 
	      {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.register);
	        usernameE=(EditText)findViewById(R.id.riusername);
	        usernameE.setOnFocusChangeListener(new EditText.OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					username=usernameE.getText().toString();
					
				}
			});
	        userpasswordE=(EditText)findViewById(R.id.riuserpassword);
	        userpasswordE.setOnFocusChangeListener(new EditText.OnFocusChangeListener(){

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					 userpassword1=userpasswordE.getText().toString();
				}});
	        userpassword2E=(EditText)findViewById(R.id.rriuserpassword);
	        userpassword2E.setOnFocusChangeListener(new EditText.OnFocusChangeListener(){

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					  userpassword2=userpassword2E.getText().toString();
			   }});
	        passwordTishiE=(EditText)findViewById(R.id.rritishipassword);
	        passwordTishiE.setOnKeyListener(new EditText.OnKeyListener(){

				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					// TODO Auto-generated method stub
					userpasswordTishi=passwordTishiE.getText().toString();
					return false;
				}

				
					  
			});
	      
	      
	        //�ύ��ť�¼�
	        /**
	         * ���¼���ɵ������У�������������������Ƿ�һ��
	         * �û����������Ƿ�Ϊ��
	         * �����ݿ��������
	         */
	        tijiaoBtn=(Button)findViewById(R.id.tijiao);
	        tijiaoBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					userInformationCheck();
					
				}
			});
	        //������ҳ��ť�¼�
	        backIndexBtn=(Button)findViewById(R.id.backindex);
	        backIndexBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent =new Intent();
					intent.setClass(RegisterPersonalBackUp.this, LoginPersonalBackUp.class);
					startActivity(intent);
					RegisterPersonalBackUp.this.finish();
				}
			});
          }
	  //�˷������ڼ���û��������Ƿ�Ϊ�գ���������������Ƿ���ͬ
	  public void userInformationCheck()
	  {
		  SQLiteManager manager=new SQLiteManager(RegisterPersonalBackUp.this);
		  
		  if(username.equals("")||userpassword1.equals("")||userpassword2.equals(""))
		  {
			  Toast.makeText(RegisterPersonalBackUp.this, "�û��������ܲ���Ϊ�գ�", Toast.LENGTH_SHORT).show();
			 
			  
		  }
		  else
		  {
		       if(!(userpassword1.equals(userpassword2)))
		         {
		    	   Toast.makeText(RegisterPersonalBackUp.this, "������������벻һ�£����������룡", Toast.LENGTH_SHORT).show();
		    	   userpasswordE.setText("");
		    	   userpassword2E.setText("");
		    	   
		         }
		       else
		       {
		    	   SQLiteManager  sqliteManager=new SQLiteManager(RegisterPersonalBackUp.this);
		    	   sqliteManager.open();
		    	   Cursor cursor=sqliteManager.YanZheng();
		    	   int index=0;
		    	   if(cursor.getCount()<=0)
		    	   { index=0;
		    	   sqliteManager.zhuce(index,username,userpassword1,userpassword2);
		    	   Toast.makeText(RegisterPersonalBackUp.this, "����û��ɹ���", Toast.LENGTH_SHORT).show();
		    	   }else{
			    	   Toast.makeText(RegisterPersonalBackUp.this, "�Բ������Ѿ���ӹ��û��ˣ�ֻ����ӵ��һ���û�������ʹ������ӵ��û�����½��", Toast.LENGTH_SHORT).show();

		    	   }
		    	  
		    	   sqliteManager.close();
		       }
		  }
	  }
	  }
