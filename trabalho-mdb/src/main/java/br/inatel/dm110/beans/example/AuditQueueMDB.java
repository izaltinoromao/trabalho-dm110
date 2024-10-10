package br.inatel.dm110.beans.example;

import br.inatel.dm110.interfaces.Audit;
import br.inatel.dm110.interfaces.AuditLocal;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

import java.util.logging.Logger;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/dm110queue") })
public class AuditQueueMDB implements MessageListener {

	private static Logger log = Logger.getLogger(AuditQueueMDB.class.getName());

	@EJB
	private AuditLocal auditBean;

	@Override
	public void onMessage(Message message) {
		// processamento da mensagem
		try {
			if (message instanceof TextMessage) {
				TextMessage txtMessage = (TextMessage) message;
				String text = txtMessage.getText();
				log.info("Message Received from Queue: " + text);
				auditBean.saveAudit(text);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
