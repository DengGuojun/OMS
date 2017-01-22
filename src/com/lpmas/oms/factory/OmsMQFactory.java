package com.lpmas.oms.factory;

import com.lpmas.framework.mq.MQFactory;
import com.lpmas.framework.mq.MQObject;
import com.lpmas.framework.mq.MQSender;
import com.lpmas.framework.mq.activemq.ActiveMQObject;
import com.lpmas.framework.mq.activemq.ActiveMQSender;
import com.lpmas.oms.config.OmsMQConfig;

public class OmsMQFactory extends MQFactory {

	@Override
	public MQObject getMQObject() {
		return new ActiveMQObject(OmsMQConfig.BROKER_ID);
	}

	@Override
	public MQSender getMQSender() {
		return new ActiveMQSender(OmsMQConfig.BROKER_ID);
	}

}
