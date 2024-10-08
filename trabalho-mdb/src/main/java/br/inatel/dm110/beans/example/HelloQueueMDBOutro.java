package br.inatel.dm110.beans.example;

import java.util.logging.Logger;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

//TODO: configure annotation
public class HelloQueueMDBOutro implements MessageListener {

	@Override
	public void onMessage(Message message) {
		//processamento da mensagem
		//TODO: implement it
	}
	
	@Inject
	Logger log;
}
