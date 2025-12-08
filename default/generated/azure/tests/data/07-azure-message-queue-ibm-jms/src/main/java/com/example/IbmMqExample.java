package com.example;

import com.ibm.mq.jms.MQQueueConnectionFactory;

public class IbmMqExample {
    public void connect() {
        try {
            MQQueueConnectionFactory cf = new MQQueueConnectionFactory();
            cf.setHostName("localhost");
            cf.setPort(1414);
            cf.setQueueManager("QM1");
            cf.setChannel("DEV.APP.SVRCONN");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
