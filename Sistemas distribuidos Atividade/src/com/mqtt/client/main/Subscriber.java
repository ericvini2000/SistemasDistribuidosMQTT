package com.mqtt.client.main;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class Subscriber implements MqttCallback {

    /** endereço Url do broker */
    private static final String brokerUrl = "tcp://test.mosquitto.org:1883";

    /**  client id. */
    private static final String clientId = "JavaSample";

    /** Bendito tópico lá que você passa na mensagem. */
    private static final String topic = "Temperature";

   
    public static void main(String[] args) {
    	/**
           Se inscreve no broker para receber as mensagens cujo tópico é temperature
         */
        new Subscriber().subscribe(topic);
    }

    /**
     * Subscribe.
     *
     * @param topic
     *            the topic
     */
    public void subscribe(String topic) {
     /* Aqui começa os paranaue para se inscrever,basicamente você aloca um espaço na memória que persiste para realizar a concexão
      * Dai você passa os parâmetros da conexão na função MqttClient()
      * Dps é so charme de retornar através de um print o que está acontecendo, se deu ruim se deu bom etc.
      * A maior dificuldade aqui é sacar o nome das funções padrões dessa biblioteca e quando usá-las para isso
      * o jeito é ler a API e pedir socorro ao StackOverflow
      * 
      * */
        MemoryPersistence persistence = new MemoryPersistence();

        try {

            MqttClient sampleClient = new MqttClient(brokerUrl, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            System.out.println("checando");

            System.out.println("Mqtt ta se conectando com o broker: " + brokerUrl);
            sampleClient.connect(connOpts);
            System.out.println("Bumba,Mqtt Conectado");

            sampleClient.setCallback(this);
            sampleClient.subscribe(topic);

            System.out.println("To inscrito, mãe");
            System.out.println("Ouvindo Broker");

        } catch (MqttException me) {

            System.out.println("Mqtt reason " + me.getReasonCode());
            System.out.println("Mqtt msg " + me.getMessage());
            System.out.println("Mqtt loc " + me.getLocalizedMessage());
            System.out.println("Mqtt causa " + me.getCause());
            System.out.println("Mqtt excep " + me);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.paho.client.mqttv3.MqttCallback#connectionLost(java.lang.
     * Throwable)
     */
    public void connectionLost(Throwable arg0) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.paho.client.mqttv3.MqttCallback#deliveryComplete(org.eclipse.
     * paho.client.mqttv3.IMqttDeliveryToken)
     */
    public void deliveryComplete(IMqttDeliveryToken arg0) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.paho.client.mqttv3.MqttCallback#messageArrived(java.lang.
     * String, org.eclipse.paho.client.mqttv3.MqttMessage)
     */
    public void messageArrived(String topic, MqttMessage message) throws Exception {

        System.out.println("Mqtt topic : " + topic);
        System.out.println("Mqtt msg : " + message.toString());
    }

}
