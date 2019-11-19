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
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class AddInformationBackUp extends Activity
{
   private EditText  asubject=null;
   private EditText  adate=null;
   private EditText  ainformation=null;
   private RadioGroup group=null;
   private RadioButton personal=null;
   private RadioButton learning=null;
   private RadioButton work=null;
   private Button atijiao=null;
   private Button areset=null;
   private Button aback=null;
   private String flag=null;//��־��ӵ���������һ������
   private String flag_name=null;
 	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addinformation);
		Intent intent=getIntent();
		flag_name=intent.getStringExtra("name");
		System.out.println("��Ӽ�¼�����flag_name"+flag_name);
		asubject=(EditText)findViewById(R.id.aisubject);
		adate=(EditText)findViewById(R.id.aidate);
		ainformation=(EditText)findViewById(R.id.aineirong);
		group=(RadioGroup)findViewById(R.id.Group);
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(personal.isChecked())
				{
					flag=new String("1");
				}
				if(learning.isChecked())
				{
					flag=new String("2");
				}
				if(work.isChecked())
				{
					flag=new String("3");
				}
				
				
			}
		});
		personal=(RadioButton)findViewById(R.id.personal);
		learning=(RadioButton)findViewById(R.id.learning);
		work=(RadioButton)findViewById(R.id.work);
		atijiao=(Button)findViewById(R.id.atijiao);
		atijiao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String subject=asubject.getText().toString();
				String date=adate.getText().toString();
				String neirong=ainformation.getText().toString();
				if(!(subject.equals("")&&date.equals("")&&neirong.equals("")))
				{
				SQLiteManager sqliteManager=new SQLiteManager(AddInformationBackUp.this);
		    	sqliteManager.open();
		    	Cursor cursor=sqliteManager.getRecord();
		    	int count=cursor.getCount();
		    	   if(count==0)
		    	    {
		    		   int index=0;
		    		   sqliteManager.addInformation(index,subject, date, neirong, flag);
			           Toast.makeText(AddInformationBackUp.this, "��¼��ӳɹ���", Toast.LENGTH_SHORT).show();
			    	
			    	   sqliteManager.close();
		    	    }
		    	   else{	
		    	   cursor.moveToLast();
		    	   int index=Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")))+1;
		    	
		    	   sqliteManager.addInformation(index,subject, date, neirong, flag);
		           Toast.makeText(AddInformationBackUp.this, "��¼��ӳɹ���", Toast.LENGTH_SHORT).show();
		    	
		    	   sqliteManager.close();
		        	}
				}else
				{
					   Toast.makeText(AddInformationBackUp.this, "��¼����Ϊ�գ�", Toast.LENGTH_SHORT).show();
				}
			}
		});
		areset=(Button)findViewById(R.id.areset);
		areset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				asubject.setText("");
				adate.setText("");
				ainformation.setText("");
			}
		});
		aback=(Button)findViewById(R.id.aback);
		aback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(AddInformationBackUp.this, Mian_personalBackUp.class);
				startActivity(intent);
				AddInformationBackUp.this.finish();
				
			}
		});
		
	}
  
}
