package com.lpmas.oms.order.business;

import java.text.MessageFormat;

import com.lpmas.framework.util.StringKit;
import com.lpmas.oms.config.OmsMQConfig;
import com.lpmas.oms.order.cache.SalesOrderMQCache;
import com.lpmas.system.client.cache.ChannelInfoClientCache;

public class SalesOrderMQRouter {

	public String getDeliveryInfoQueueName(int storeId) throws Exception {
		SalesOrderMQCache cache = new SalesOrderMQCache();
		String queueName = cache.getDeliveryInfoQueueName(storeId);

		if (null == queueName) {
			String channleCode = getChannelCodeByStoreId(storeId);
			if (StringKit.isValid(channleCode)) {
				queueName = MessageFormat.format(OmsMQConfig.DELIVERY_INFO_QUEUE, getChannelCodeByStoreId(storeId));
				cache.setDeliveryInfoQueueName(storeId, queueName);
			} else {
				throw new Exception("channelCode not found, storeId=" + storeId);
			}
		}

		return queueName;
	}

	public String getChannelCodeByStoreId(int storeId) {
		ChannelInfoClientCache channelInfoCache = new ChannelInfoClientCache();
		String channelCode = channelInfoCache.getChannelCodeByStoreId(storeId);

		return channelCode;
	}
}
