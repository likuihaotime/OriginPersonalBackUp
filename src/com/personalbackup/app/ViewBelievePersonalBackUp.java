package com.personalbackup.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ViewBelievePersonalBackUp extends Activity
{
   private EditText  asubject=null;
   private EditText  adate=null;
   private EditText  ainformation=null;
   private RadioGroup group=null;
   private RadioButton personal=null;
   private RadioButton learning=null;
   private RadioButton work=null;
   private Button update=null;
   private Button delete=null;
   private Button backList=null;
   private String flag=null;//��־��ӵ���������һ������
   private String detailRecord=null;
   private String tag=null;
 	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		//��ȡ�Ӽ�¼�б��д��ݹ�������������ڲ���
		Intent intent=getIntent();
		String vsubject=intent.getStringExtra("subject");
		String vdate=intent.getStringExtra("date");
		//��ȡ��Ӧ����������ڵĶ�����ҽ����б��ݵĲ������и�ֵ
		asubject=(EditText)findViewById(R.id.aisubject);
		asubject.setText(vsubject);
		adate=(EditText)findViewById(R.id.aidate);
		adate.setText(vdate);
		//�����������Ӧ�ļ�¼��Ϣ
		SQLiteManager manager=new SQLiteManager(ViewBelievePersonalBackUp.this);
		manager.open();
		Cursor cursor=manager.getRecord();
		boolean vflag=true;
		
		while(cursor.moveToNext())
		{
			if(vflag)
			{
		     	if(cursor.getString(cursor.getColumnIndex("subject")).equals(vsubject))
		    	{
				flag=cursor.getString(cursor.getColumnIndex("flag"));
				detailRecord=cursor.getString(cursor.getColumnIndex("information"));
				vflag=false;
			    }
			}
			else
			{
				break;
			}
		}
		manager.close();
		//�����Ǽ�¼����Ҫ���ݿؼ�����Ļ�ȡ
		ainformation=(EditText)findViewById(R.id.aineirong);
		ainformation.setText(detailRecord);
		group=(RadioGroup)findViewById(R.id.Group);
		group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(personal.isChecked())
					tag=new String("1");
				if(learning.isChecked())
					tag=new String("2");
				if(work.isChecked())
					tag=new String("3");
			}
		});
		//������������ѡ��ť�Ķ����ȡ
		personal=(RadioButton)findViewById(R.id.personal);
		learning=(RadioButton)findViewById(R.id.learning);
		work=(RadioButton)findViewById(R.id.work);
		if(flag.equals("1"))
		{
			personal.setChecked(true);
		}
		else
		{
			personal.setChecked(false);
		}
		if(flag.equals("2"))
		{
			learning.setChecked(true);
		}
		else
		{
			learning.setChecked(false);
		}
		if(flag.equals("3"))
		{
			work.setChecked(true);
		}
		else
		{
			work.setChecked(false);
		}
		//�޸�������¼�İ�ť���¼�
		update=(Button)findViewById(R.id.vupdate);
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String subject=asubject.getText().toString();
				String date=adate.getText().toString();
				String neirong=ainformation.getText().toString();
				SQLiteManager manager=new SQLiteManager(ViewBelievePersonalBackUp.this);
				manager.open();
				int index=0;
				Cursor cursor=manager.getRecord();
				while(cursor.moveToNext())
				{
					if(!asubject.getText().toString().equals(""))
					if(cursor.getString(cursor.getColumnIndex("subject")).equals(asubject.getText().toString()))
					{
						index=Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")));
					}
				}
				
				if(!(subject.equals("")&&date.equals("")&&neirong.equals("")))
				{
					manager.updateRecord(index,subject, date, neirong, tag);
				}else
				{
					Toast.makeText(ViewBelievePersonalBackUp.this, "�޸�ʧ��,��ѡ��Ϊ��ֵ������д����", Toast.LENGTH_LONG).show();
				}
					manager.close();
				Intent intent=new Intent();
				intent.setClass(ViewBelievePersonalBackUp.this, ViewInformationPersonalBackUp.class);
				startActivity(intent);
				ViewBelievePersonalBackUp.this.finish();
			}
		});
		//ɾ��������¼�İ�ť���¼�
		delete=(Button)findViewById(R.id.vdelete);
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteManager manager=new SQLiteManager(ViewBelievePersonalBackUp.this);
				manager.open();
				int index=0;
				Cursor cursor=manager.getRecord();
				while(cursor.moveToNext())
				{
					if(!asubject.getText().toString().equals(""))
					if(cursor.getString(cursor.getColumnIndex("subject")).equals(asubject.getText().toString()))
					{
						index=cursor.getInt(cursor.getColumnIndex("_id"));
					}
				}
				manager.deleteRecord(index);
				manager.close();
				Intent intent=new Intent();
				intent.setClass(ViewBelievePersonalBackUp.this, ViewInformationPersonalBackUp.class);
				startActivity(intent);
				ViewBelievePersonalBackUp.this.finish();
			}
		});
		//���ؼ�¼�б�ť���¼�
		backList=(Button)findViewById(R.id.vbacklist);
		backList.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(ViewBelievePersonalBackUp.this, ViewInformationPersonalBackUp.class);
				startActivity(intent);
				ViewBelievePersonalBackUp.this.finish();
			}
		});
		
	}
  
}
