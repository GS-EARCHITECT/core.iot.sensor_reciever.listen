package kafka_message_mgmt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import kafka_message_mgmt.model.master.GatewayAssetReading;
import kafka_message_mgmt.model.master.IOTAssetReading;
import kafka_message_mgmt.model.repo.IOTSensorRecieverListener_Repo;

@Service("kafkaIOTProcessListenerServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class KafkaIOTProcessListener_Service 
{
//	private static final Logger logger = LoggerFactory.getLogger(KafkaIOTProcessListener_Service.class);

	@Autowired
	private IOTSensorRecieverListener_Repo iotSensorRecieverListenerRepo;

	@Async
	public synchronized Long insertReading(GatewayAssetReading gatewayAssetReading) 
	{
	return iotSensorRecieverListenerRepo.save(this.setAssetReading(gatewayAssetReading)).getReadingSeqNo();
	}

	private synchronized IOTAssetReading setAssetReading(GatewayAssetReading lDTO) {
		IOTAssetReading lMaster = new IOTAssetReading();
		lMaster.setOnDttm(lDTO.getOnDttm());
		lMaster.setAssetSeqNo(lDTO.getAssetSeqNo());
		lMaster.setReading(lDTO.getReading());
		lMaster.setSensorAssetSeqNo(lDTO.getSensorAssetSeqNo());
		lMaster.setResMeasureSeqNo(lDTO.getResMeasureSeqNo());
		return lMaster;
	}
}