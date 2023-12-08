package kafka_message_mgmt.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kafka_message_mgmt.model.master.IOTAssetReading;

@Repository("iotSensorRecieverListenerRepo")
public interface IOTSensorRecieverListener_Repo extends JpaRepository<IOTAssetReading, Long> 
{}