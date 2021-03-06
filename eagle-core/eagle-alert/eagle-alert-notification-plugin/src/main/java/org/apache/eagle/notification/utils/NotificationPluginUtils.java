/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.eagle.notification.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigObject;
import org.apache.eagle.log.base.taggedlog.TaggedLogAPIEntity;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;

import java.util.List;
import java.util.Map;

/**
 * Common methods for Notification Plugin
 */
public class NotificationPluginUtils {
	private final static ObjectMapper OBJECT_MAPPER = TaggedLogAPIEntity.buildObjectMapper();
	/**
	 * Fetch Notification specific property value
	 * @param key
	 * @return
	 * @throws Exception
     */
	public static String getPropValue(Config config, String key ) throws Exception {
		if( config.getObject("eagleNotificationProps") == null )
			throw new Exception("Eagle Notification Properties not found in application.conf ");
		ConfigObject notificationConf = config.getObject("eagleNotificationProps");
		return notificationConf.get(key).unwrapped().toString();
	}

	/**
	 * Deserialize Notification Definition and convert all config to Key Value Pairs
	 * @param notificationDef
	 * @return
	 * @throws Exception
     */
	public static List<Map<String,String>> deserializeNotificationConfig( String notificationDef ) throws Exception {
		CollectionType mapCollectionType = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, Map.class);
		return OBJECT_MAPPER.readValue(notificationDef, mapCollectionType);
	}

	/**
	 * Object to JSON String
	 * @param obj
	 * @return
	 * @throws Exception
     */
	public static String objectToStr( Object obj ) throws  Exception {
		return OBJECT_MAPPER.writeValueAsString(obj);
	}
}