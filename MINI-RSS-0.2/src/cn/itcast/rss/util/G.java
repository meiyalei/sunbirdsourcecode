package cn.itcast.rss.util;

public class G {
	public static final int BG_ALPHA=150;
	public static final int SCAN_TYPE_CHANNEL=0;
	public static final int SCAN_TYPE_SEARCH=1;
	public static final int SEARCH_WAY_TEXT=0;
	public static final int SEARCH_WAY_TITLE=1;
	
	public static final int ADD_TYPE_FEED=1;
	public static final int ADD_TYPE_QQ_ZONE=2;

	/**
	 * ��¼�ֻ��������������������������ʱ�򣬼������״����Ȼ����丳ֵ
	 * 
	 * null ����������״��ʱ����
	 * 0           ��ǰ�ֻ�û�н����κ�����
	 * 1           ��ǰ�ֻ���GPRS��������
	 * 2           ��ǰ�ֻ�ͨ��wifi��������
	 */
	public static Integer NET_STAT;
	
	
	/**
	 * ��¼��ǰ�ֻ���ƽĻ�ֱ��ʵĿ�͸ߣ���������������ʱ�򣬶��� ���и�ֵ��
	 * 0          ��������Ļ�ֱ��ʵ�ʱ�����
	 * ��0     ��Ļ�Ŀ�͸�
	 * 
	 */
	public static int SCREEN_PIX_WIDTH;
	public static int SCREEN_PIX_HEGHT;
	
	
	/**
	 * RSS���������WebWiew��ͼƬ���������ǵĿ�ȱ�׼������Ļ�ֱ��ʻ�ú󣬶�����и�ֵ��
	 * 0          ��������Ļ�ֱ��ʵ�ʱ�����
	 * ��0     ��Ļ�Ŀ�͸�
	 */
	public static int SCAN_WEBVIEW_IMG_WITH;
	
	
	public static final String RECOMM_FEEDS_LINK="http://www.gokuai.com/w/z276vF11H746juZ4/RssFeeds.mp3";
	
	public static final String MAIN_CONFIG_SP_NAME="mainConfig";
	public static final String BG_SP_MANE="mainBg";
	
	
	public static  int BG_ID;
	public static final int SET_BG_REAUEST_CODE=1;
	public static final int SET_BG_OK_RESULT_CODE=10;
}
