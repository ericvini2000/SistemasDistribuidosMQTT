package com.mqtt.client.main;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;



public class Publisher {

    
    public static void main(String[] args) {

        String topic = "Temperature";
        String content = "45";
        int qos = 2;
        String broker = "tcp://test.mosquitto.org:1883";
        String clientId = "JavaSample";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Se conectando com o broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Bumba,conectou!");
            System.out.println("Publicando a mensagem: " + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Mensagem Publicada");
            sampleClient.disconnect();
            System.out.println("Desconectou,até um outro dia!");
                        
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("causa " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
            
        }
    }
}