package cn.itcast.rss;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import cn.itcast.rss.db.RSSChannelService;
import cn.itcast.rss.db.RSSItemService;
import cn.itcast.rss.rsslib4j.RSSChannel;
import cn.itcast.rss.rsslib4j.RSSException;
import cn.itcast.rss.rsslib4j.RSSHandler;
import cn.itcast.rss.rsslib4j.RSSItem;
import cn.itcast.rss.rsslib4j.RSSParser;

public class ContentUpdateService extends Service {

	private static final String TAG = "ContentUpdateService";
	private int flag = 0;

	private ServiceBinder binder = new ServiceBinder();
	private RSSChannelService channelService;
	private RSSItemService itemService;

	private ExecutorService threadPool = Executors.newCachedThreadPool();

	@Override
	public void onCreate() {
		super.onCreate();
		channelService = new RSSChannelService(this);
		itemService = new RSSItemService(this);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// if(flag==0){//��ʾ����ʱ��һ�δ�����
		// // ���������ʱ�򣬽��û��Ķ���ȫ������һ�顣
		// Log.i(TAG, "����������");
		// new Thread(new Runnable(){
		// @Override
		// public void run() {
		// List<RSSChannel> cList = channelService.getAll();
		// for (RSSChannel c : cList) {
		// updateChannel(c);
		// }
		// }
		// }).start();
		// flag=1;
		// }

	}

	private class ServiceBinder extends Binder implements
			ContentUpdateServiceBinder {

		@Override
		public void updateChannel(RSSChannel c) throws MalformedURLException, RSSException {
			updateChannelByChannel(c);
		}
		
		public void addChannel(String urlStr) throws MalformedURLException, RSSException {
			addChannelByUrl(urlStr);
		}
	}
	
	
	/**
	 * Service��Activity��ִ�������߳��У�Activityִ�����������ʱ��������ʽ�ģ���������պ���Ҫ��������ʽ�ġ�
	 * ���쳣ȫ���׵�ǰ̨��ǰ̨���Ը��ݲ������쳣���жϾ�����������ֵ�ƫ������û���������
	 * 
	 * @param urlStr
	 * @throws RSSException 
	 * @throws MalformedURLException 
	 */
	
	private void addChannelByUrl(String urlStr) throws MalformedURLException, RSSException {
		RSSHandler handler = new RSSHandler();
		RSSParser.parseXmlFile(new URL(urlStr),handler,false);
		handler.getRSSChannel().setChannelUrl(urlStr);
		handler.getRSSChannel().setUpdateTime(System.currentTimeMillis());
		int id=channelService.saveAndReturnId(handler.getRSSChannel());
		LinkedList<RSSItem> iList = handler.getRSSChannel().getItems();
		for (RSSItem i : iList) {
			i.setChannelId(id);
		}
		itemService.batchSave(iList);
	}
	

	// ����ָ����RSSChannel
	/**
	 * @return num ���µ�������
	 * @throws RSSException 
	 * @throws MalformedURLException 
	 */
	private int updateChannelByChannel(RSSChannel c) throws MalformedURLException, RSSException {
		RSSHandler handler = new RSSHandler();
		handler.setRSSChannel(c);
		RSSParser.parseXmlFile(new URL(c.getChannelUrl()), handler, false);
		handler.getRSSChannel().setUpdateTime(System.currentTimeMillis());
		channelService.update(c);
		LinkedList<RSSItem> iList = c.getItems();
		for (RSSItem i : iList) {
			i.setChannelId(c.getId());
		}
		itemService.deleteByCId(c.getId());
		itemService.batchSave(iList);
		return iList.size();
	}

	private void controlItemNum(RSSChannel c) {
		int storeNum = itemService.getItemNum(c.getId());
		if (storeNum + c.getItems().size() < c.getItemSotrage()) {
			return;
		}
		// ɾ��һЩitem--������Ҫɾ�������������
		int deleNum = storeNum - (c.getItemSotrage() - c.getItems().size());
		itemService.deleteToSotre(c.getId(), deleNum);
	}
}
