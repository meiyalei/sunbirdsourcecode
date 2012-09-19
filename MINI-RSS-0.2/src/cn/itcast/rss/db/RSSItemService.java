package cn.itcast.rss.db;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.itcast.rss.rsslib4j.RSSChannel;
import cn.itcast.rss.rsslib4j.RSSImage;
import cn.itcast.rss.rsslib4j.RSSItem;

public class RSSItemService {

	private DBOpenHelper helper;
	private SQLiteDatabase wdb;

	public RSSItemService(Context context) {
		helper = new DBOpenHelper(context);
	}

	/**
	 * ����в���һ��ʵ�塣
	 * 
	 * @param RSSChannel
	 */
	public void save(RSSItem r) {
		wdb = helper.getWritableDatabase();
		String sql = "INSERT INTO item(channel_id, title, link, author, guid, category, pub_date, comment, desc, save_nano) values(?,?,?,?,?,?,?,?,?,?)";
		Object[] bindArgs = new Object[] { r.getChannelId(), r.getTitle(),
				r.getLink(), r.getAuthor(), r.getGuid(), r.getCategory(),
				r.getPubDate(), r.getComments(), r.getDescription(),
				System.nanoTime() };
		wdb.execSQL(sql, bindArgs);
		wdb.close();
	}

	public void delete(int id) {
		wdb = helper.getWritableDatabase();
		String sql = "DELETE FROM book_channel WHERE _id=" + id;
		wdb.execSQL(sql);
		wdb.close();
	}

	public void update(RSSItem r) {

	}

	public RSSItem getById(int id) {
		return null;
	}

	public void batchSave(LinkedList<RSSItem> items) {
		if (items != null || items.size() > 0) {
			for (RSSItem item : items) {
				this.save(item);
			}
		}
	}

	/**
	 * ����ָ���Ĳ���ָ��Channel Id���ݿ��д洢��Item��������
	 * 
	 * @param cId
	 * @return num item������ 0 ��ʾһ��Ҳû�С�
	 */
	public int getItemNum(int cId) {
		int num = 0;
		String sql = "SELECT COUNT(*) FROM item WHERE channel_id=" + cId;
		Cursor cursor = null;
		try {
			cursor = wdb.rawQuery(sql, null);
			cursor.moveToFirst();
			num = new Long(cursor.getLong(0)).intValue();

		} finally {
			cursor.close();
		}
		return num;
	}

	/**
	 * ɾ��ָ��channel id�� ָ����item�Դ洢�µ����ݡ�
	 * 
	 * @param id
	 * @param deleNum
	 */
	public void deleteToSotre(Integer cId, int deleNum) {
			String sql = "DELETE FROM item WHERE save_nano IN(SELECT save_nano FROM item ORDER BY save_nano limit 0,? ) and channel_id=?";
			String[] selectionArgs = new String[] { String.valueOf(deleNum),
					String.valueOf(cId) };
			wdb.rawQuery(sql, selectionArgs);
		
	}

	public LinkedList<RSSItem> getListByCId(int channelId) {
		wdb = helper.getWritableDatabase();
		Cursor cursor=null;
		LinkedList<RSSItem> iList = new LinkedList<RSSItem>();
		try {
			String sql = "SELECT * FROM item WHERE channel_id=" + channelId;
			cursor = wdb.rawQuery(sql, null);
			while (cursor.moveToNext()) {
				iList.add(curCursor2Item(cursor));
			}
		} finally{
			cursor.close();
			wdb.close();
		}
		return iList;
	}

	/**
	 * ���α굱ǰ��ָ��������¼ȡ������װ��RSSChannel����
	 * 
	 * @return channel
	 */
	private RSSItem curCursor2Item(Cursor c) {
		RSSItem i = null;
		if (c != null) {
			// _id channel_id title link author guid category pub_date comment
			// desc save_nano
			i = new RSSItem();
			i.setId(c.getInt(c.getColumnIndex("_id")));
			i.setChannelId(c.getInt(c.getColumnIndex("channel_id")));
			i.setTitle(c.getString(c.getColumnIndex("title")));
			i.setLink(c.getString(c.getColumnIndex("link")));
			i.setAuthor(c.getString(c.getColumnIndex("author")));
			i.setGuid(c.getString(c.getColumnIndex("guid")));
			i.setCategory(c.getString(c.getColumnIndex("category")));
			i.setPubDate(c.getString(c.getColumnIndex("pub_date")));
			i.setComments(c.getString(c.getColumnIndex("comment")));
			i.setDescription(c.getString(c.getColumnIndex("desc")));
			i.setSaveNano(c.getLong(c.getColumnIndex("save_nano")));
			i.setReaded(c.getInt(c.getColumnIndex("readed")));
		}
		return i;
	}
	
	public void deleteByCId(int cId){
		wdb = helper.getWritableDatabase();
		String sql="DELETE FROM item WHERE channel_id="+cId;
		wdb.execSQL(sql);
	}
	
	/**
	 * ����channel_id��ѯĳ��channel�����ж�����Item��¼��
	 * @param chanId
	 * @return
	 */
	public int getItemNumByChanId(int chanId){
		wdb = helper.getWritableDatabase();
		int num=0;
		String sql="SELECT COUNT(*) FROM item WHERE channel_id="+chanId;
		Cursor cursor = null;
		try {
			cursor = wdb.rawQuery(sql, null);
			if(cursor.moveToFirst()){
				num=cursor.getInt(0);
			}
		} finally {
			cursor.close();
			wdb.close();
		}
		return num;
	}
	
	/**
	 * ����channel_id��ѯĳ��channel�����ж�����δ����Item��¼��
	 * @param chanId
	 * @return
	 */
	public int getUnreadItemNumByChanId(int chanId){
		wdb = helper.getWritableDatabase();
		int num=0;
		String sql="SELECT COUNT(*) FROM item WHERE channel_id="+chanId+" AND readed=0";
		Cursor cursor = null;
		try {
			cursor = wdb.rawQuery(sql, null);
			if(cursor.moveToFirst()){
				num=cursor.getInt(0);
			}
		} finally {
			cursor.close();
			wdb.close();
		}
		return num;
	}
	
	/**
	 * ����channel_id��ѯĳ��channel�����ж������Ѷ���Item��¼��
	 * @param chanId
	 * @return
	 */
	public int getReadedItemNumByChanId(int chanId){
		wdb = helper.getWritableDatabase();
		int num=0;
		String sql="SELECT COUNT(*) FROM item WHERE channel_id="+chanId+" AND readed=1";
		Cursor cursor = null;
		try {
			cursor = wdb.rawQuery(sql, null);
			if(cursor.moveToFirst()){
				num=cursor.getInt(0);
			}
		} finally {
			cursor.close();
			wdb.close();
		}
		return num;
	}
	/**
	 * ����item_id�޸Ķ�Ӧitem��readed��ֵΪ1��
	 * @param chanId
	 * @return
	 */
	public void setItemReaded(int itemId){
		wdb = helper.getWritableDatabase();
		String sql="UPDATE item SET readed=1 WHERE _id="+itemId;
		wdb.execSQL(sql);
		wdb.close();
	}
}
