package kafka_message_mgmt.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import kafka_message_mgmt.model.master.GatewayAssetReading;

@Service("kafkaIOTRecieverListenerServ")
public class KafkaIOTRecieverListener_Service 
{
	private static final Logger logger = LoggerFactory.getLogger(KafkaIOTRecieverListener_Service.class);

	@Autowired
	private KafkaIOTProcessListener_Service kafkaIOTProcessListenerServ;
	
	@KafkaListener(topics = "${topic.name.iotreadingpconsumer}", groupId = "group_id", concurrency = "5")
	public void consume(ConsumerRecord<String, GatewayAssetReading> payload) {
		logger.info("Ad No :" + payload.value().getAssetSeqNo());
		kafkaIOTProcessListenerServ.insertReading(payload.value());
	}

}