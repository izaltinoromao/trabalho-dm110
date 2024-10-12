package br.inatel.dm110.beans;

import br.inatel.dm110.api.AuditMessageTO;
import com.google.gson.Gson;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.jms.TextMessage;

import java.util.logging.Logger;

@Stateless
public class AuditQueueSender {

    private static Logger log = Logger.getLogger(AuditQueueSender.class.getName());

    @Resource(lookup = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/jms/queue/dm110queue")
    private Queue queue;

    public void sendTextMessage(AuditMessageTO auditMessageTO) {
        try (JMSContext context = connectionFactory.createContext();) {
            TextMessage txtMsg = context.createTextMessage(new Gson().toJson(auditMessageTO));
            log.info("Sending message to queue: " + txtMsg.getText());
            context.createProducer().send(queue, txtMsg);
            log.info("Message sent");
        } catch (Exception e) {
            log.warning("Error sending message: " + e.getMessage());
        }
    }

}
