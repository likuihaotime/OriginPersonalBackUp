package com.personalbackup.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteManager 
{
	private static final String  DB_NAME="PersonalBackUp.db";
	private static final String  USER_TABLE_NAME="User";
	private static final String  RECORD_TABLE_NAME="Record";
	private static final String  TAB_ID="_id";
	private static final String  TAB_USERNAME="username";
	private static final String  TAB_USERPASSWORD="userpassword";
	private static final String  TAB_TISHI="tishi";
	private static final String  TAB_SUBJECT="subject";
	private static final String  TAB_DATE="date";
	private static final String  TAB_INFORMATION="information";
	private static final String  TAB_FLAG="flag";
	private static final int VERSION=1;
	private Context  mContext=null;
    private static final String  CREATE_USER_TABLE="CREATE TABLE User (" +TAB_ID+
    		                                       " autoincreament Integer  primary key   ," 
                                                   +TAB_USERNAME+
    		                                       "  varchar(10) ," +TAB_USERPASSWORD+
    		                                       "  varchar(16),"+TAB_TISHI+
    		                                       "  text)";
    private static final String CREATE_RECORD_TABLE="CREATE TABLE Record(" +TAB_ID+
    		                                       " autoincreatement Integer primary key  ," +TAB_SUBJECT+
    		                                       "  varchar(20)," +TAB_DATE+
    		                                       "  varchar(20) ," +TAB_INFORMATION+
    		                                       "  text ,"+TAB_FLAG+
    		                                       "  varchar(2) "
    		                                          + 
    		                                        ")";
    public SQLiteManager(Context context)
    {
    	mContext=context;
	}
    private SQLiteDatabase mDatabase=null;
    private DatabaseHelper mDatabaseHelper=null;
    private class  DatabaseHelper extends SQLiteOpenHelper
    {

		public DatabaseHelper(Context context) {
			super(context, DB_NAME, null, VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			   db.execSQL(CREATE_USER_TABLE);
               db.execSQL(CREATE_RECORD_TABLE);		
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXIT notes");
			onCreate(db);
			
		}
    }
    /*****************open���ݿ�*************************/
    public void open()
    {
    	mDatabaseHelper=new DatabaseHelper(mContext);
    	mDatabase=mDatabaseHelper.getWritableDatabase();
    }
    /*******************�ر����ݿ�************************************/
    public void close()
    {
    	mDatabase.close();
    	mDatabaseHelper.close();
    }
    //�����Ӧ������
    public void zhuce(Integer index,String username,String userpassword,String tishi)
    {
    	ContentValues values=new ContentValues();
    	values.put(TAB_ID, index);
    	values.put(TAB_USERNAME,username);
    	values.put(TAB_USERPASSWORD,userpassword);
    	values.put(TAB_TISHI,tishi);
    	 mDatabase.insert(USER_TABLE_NAME, null, values);
    }
    //��½��ʱ����֤����
    public Cursor YanZheng()
    {   
    	Cursor cursor= mDatabase.query(USER_TABLE_NAME, new String[]{TAB_ID,TAB_USERNAME,TAB_USERPASSWORD,TAB_TISHI}, null, null, null, null, null);
    	return cursor;
    }
    //��Ӽ�¼
    public void addInformation(Integer index,String subject,String date,String neirong,String flag)
    {
    	ContentValues values=new ContentValues();
    	values.put(TAB_ID, index);
    	values.put(TAB_SUBJECT, subject);
    	values.put(TAB_DATE, date);
    	values.put(TAB_INFORMATION, neirong);
    	values.put(TAB_FLAG, flag);
    	mDatabase.insert(RECORD_TABLE_NAME, null, values);
    }
    //�������м�¼����е����ݣ�����listview����ʾ
    public Cursor  getRecord()
    {
    	Cursor cursor=mDatabase.query(RECORD_TABLE_NAME, new String[]{TAB_ID,TAB_SUBJECT,TAB_DATE,TAB_INFORMATION,TAB_FLAG}, null, null, null, null, null);
    	return cursor;
    	
    }
    //�����ڲ鿴������¼��ʱ�����һ����¼�������޸Ĺ���
    public void updateRecord(Integer index,String subject,String date,String neirong,String flag)
    {
    	ContentValues values=new ContentValues();
    	values.put(TAB_SUBJECT, subject);
    	values.put(TAB_DATE, date);
    	values.put(TAB_INFORMATION, neirong);
    	values.put(TAB_FLAG, flag);
    	mDatabase.update(RECORD_TABLE_NAME, values, "_id="+index, null);
    }
    //�����ڲ鿴������¼��ʱ�����һ����¼������ɾ������

    public void deleteRecord(int id)
    {
    	mDatabase.execSQL("delete from "+RECORD_TABLE_NAME+" where _id="+id);
    	//mDatabase.delete(RECORD_TABLE_NAME, " subject="+subject, null);
    }
}
