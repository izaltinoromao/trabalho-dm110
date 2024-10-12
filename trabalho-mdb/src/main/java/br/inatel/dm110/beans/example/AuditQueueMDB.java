package br.inatel.dm110.beans.example;

import br.inatel.dm110.adapters.LocalDateTimeAdapter;
import br.inatel.dm110.api.AuditMessageTO;
import br.inatel.dm110.interfaces.Audit;
import br.inatel.dm110.interfaces.AuditLocal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

import java.time.LocalDateTime;
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

				Gson gson = new GsonBuilder()
						.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
						.create();

				AuditMessageTO auditMessageTO = gson.fromJson(text, AuditMessageTO.class);
				auditBean.saveAudit(auditMessageTO);
			}
		} catch (JMSException e) {
			log.warning("Failed to process JMS message. Error: " + e.getMessage() + " - Message: " + message);
		}
	}

}
