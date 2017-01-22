package com.lpmas.oms.order.cache;

import com.lpmas.framework.cache.LocalCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.oms.order.config.SalesOrderCacheConfig;
import com.opensymphony.oscache.base.NeedsRefreshException;

public class SalesOrderMQCache {
	// private static Logger log =
	// LoggerFactory.getLogger(SalesOrderMQCache.class);

	public String getDeliveryInfoQueueName(int storeId) {
		String queueName = null;
		String key = SalesOrderCacheConfig.getDeliveryInfoQueueName(storeId);
		Object obj = null;
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			obj = nre.getCacheContent();
			localCache.cancelUpdate(key);
		}
		if (obj != null) {
			queueName = (String) obj;
		}
		return queueName;
	}

	public void setDeliveryInfoQueueName(int storeId, String queueName) {
		String key = SalesOrderCacheConfig.getDeliveryInfoQueueName(storeId);
		LocalCache.getInstance().set(key, queueName, Constants.CACHE_TIME_2_HOUR);
	}
}
