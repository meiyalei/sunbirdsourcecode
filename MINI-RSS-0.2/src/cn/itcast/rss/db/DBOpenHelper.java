package cn.itcast.rss.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME="";
	private static final int DB_VERSION=4;
	

	// vesion ���ݿ�İ汾�š�
	public DBOpenHelper(Context context) {
		super(context, "rss.db", null, 4);
	}
	

	// ���ݿ��һ�α�������ʱ����ø÷������ʺ϶����ݿ��еı�ṹ���г�ʼ����
	@Override
	public void onCreate(SQLiteDatabase db) {		
		String sql="CREATE TABLE book_channel(_id Integer primary key autoincrement,channel_url varchar(100),title varchar(20),"+
					"img_title varchar(30),img_link varchar(100),img_url varchar(100),desc varchar(50),link varchar(200),"+
					"lang varchar(30),ttl Integer,copyright varchar(100),pub_date varchar(20),"+
					"category varchar(50), item_storage Integer, update_time long)";
		db.execSQL(sql);
		sql="CREATE TABLE item(_id Integer primary key autoincrement,channel_id Integer, title varchar(50), link varchar(100),"+
		           " author varchar(20), guid varchar(200),category varchar(100),pub_date varchar(50),comment varchar(100), "+
		           "desc varchar(300), save_nano long, readed Integer default 0)";
		db.execSQL(sql);
	}

	// ���ݿ�汾�ű����ʱ����ø÷��������ݿ��еı�ṹ�Լ����е���Ϣ���и��У�����Ӧ�����������
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS book_channel");
		db.execSQL("DROP TABLE IF EXISTS item");
		onCreate(db);
	}
	
	

	@Override
	protected void finalize() throws Throwable {
		//TODO ����sqlite���ݿ�android.database.sqlite.DatabaseObjectNotClosedException��̽����
		this.getReadableDatabase().close();
	}
	
	
	
}
