package distcomp;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.*;

public class ProcessA extends Thread {

    private final Connection con;
    Session session;
    MessageConsumer consumer;
    MessageProducer producerR, producerF;
    int requestN = 0;

    Random rand;


    public ProcessA() throws JMSException {
        rand = new Random();
        ConnectionFactory factory = JmsProvider.getConnectionFactory();
        this.con = factory.createConnection();
        con.start();

        session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("R");
        producerR = session.createProducer(queue);

        queue = session.createQueue("0");
        consumer = session.createConsumer(queue);

        queue = session.createQueue("F");
        producerF = session.createProducer(queue);
    }

    private void sleepRandomTime() {
        try {
            Thread.sleep((rand.nextInt(4) + 1) * 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {

            while (true) {


                TextMessage message = session.createTextMessage("Request " + requestN + " from Process A");
                message.setIntProperty("producer", 0);
                producerR.send(message);


                message = (TextMessage) consumer.receive();
                if (message == null) {
                    break;
                }
                String res = message.getText();
                System.out.println("Process A: " + res);

                message = session.createTextMessage("Request " + requestN++ + " finished by Process A");
                producerF.send(message);


                sleepRandomTime();



            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void destroy() throws JMSException {
        con.close();
    }

}