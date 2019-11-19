package com.personalbackup.app;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class LoginPersonalBackUp extends Activity{
	private EditText username=null;//�û���
	private EditText userpassword=null;//����
	private Button   dengluBtn=null;//��½��ť
	private Button   exitBtn=null;//�˳���ť
	private Button   registerBtn=null;//ע���û���ť
	private Button   serchpwdBtn=null;//�һ����밴ť
	   /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        /*************************��ȡ��Ӧ����*******************************/
        username=(EditText)findViewById(R.id.inusername);
        userpassword=(EditText)findViewById(R.id.inuserpassword);
        //��½��ť���¼�
        dengluBtn=(Button)findViewById(R.id.denglu);
        dengluBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			    String	name=username.getText().toString();
			    String  pwd=userpassword.getText().toString();
				boolean flag=false;
				SQLiteManager sqliteManager=new SQLiteManager(LoginPersonalBackUp.this);
		    	sqliteManager.open();
		    	Cursor cursor=sqliteManager.YanZheng();
		    	while(cursor.moveToNext())
		    	{
		    		if(cursor.getString(cursor.getColumnIndex("username")).equals(name)&&cursor.getString(cursor.getColumnIndex("userpassword")).equals(pwd))
		    		{
		    			flag=true;
		    		}
		    		
		    	}
		    	
		    	sqliteManager.close();
				if(flag)
				{
					System.out.println("��½�ɹ���");
					Intent intent=new Intent();
					intent.putExtra("username",name);
					intent.setClass(LoginPersonalBackUp.this, Mian_personalBackUp.class);
					startActivity(intent);
					LoginPersonalBackUp.this.finish();
				}
				else
				{
					Toast.makeText(LoginPersonalBackUp.this,"������û����������������룡",Toast.LENGTH_SHORT).show();
					username.setText("");
					userpassword.setText("");
				}
			}
		});
        //�˳���ť���¼�
        exitBtn=(Button)findViewById(R.id.exit);
        exitBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			new LoginPersonalBackUp().finish();
			}
		});
        //����û���ť���¼�
        registerBtn=(Button)findViewById(R.id.register);
        registerBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(LoginPersonalBackUp.this, RegisterPersonalBackUp.class);
				startActivity(intent);
			}
		});
        //Ѱ�����밴ť���¼�
        serchpwdBtn=(Button)findViewById(R.id.retrievepassword);
        serchpwdBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(LoginPersonalBackUp.this,FindPasswordPersonalBackUp.class);
				startActivity(intent);
				LoginPersonalBackUp.this.finish();
			}
		});
    }
}
