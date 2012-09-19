package org.androidpn.server.xmpp.message.handler;

import org.androidpn.server.xmpp.session.ClientSession;
import org.androidpn.server.xmpp.session.SessionManager;
import org.xmpp.packet.Message;

public class AcknowlageMessageHandler implements MessageHandler{

	private SessionManager sessionManager;
	public static final String SUBJECT="ACK";
	//�յ��û����ص�ȷ�Ϻ�Ҫɾ��ioSession�д洢��message��ֹͣTimeoutTask��
	public void handle(Message message) {
		//message body  coupon id
		sessionManager=SessionManager.getInstance();
		String msgMessage=message.getBody();
		ClientSession session=sessionManager.getSession(message.getFrom());
		session.removeMessageAndTimeoutTask(msgMessage);
	}
}
