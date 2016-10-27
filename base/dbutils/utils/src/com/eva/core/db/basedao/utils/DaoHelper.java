package com.eva.core.db.basedao.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.eva.core.db.entity.BasePojo;

public class DaoHelper {

	@Deprecated
	public static SqlAndoParams addparamToSql(String sql, BasePojo pojo,
			Map<String, String> map) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		StringBuffer sb = new StringBuffer(sql);
		SqlAndoParams sap = new SqlAndoParams();
		if (pojo != null) {
			Iterator<Entry<String, String>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				Field field = pojo.getClass().getDeclaredField(entry.getKey());
				field.setAccessible(true);
				Object obj = field.get(pojo);
				if (obj != null) {
					sb.append(" and ");
					sb.append(entry.getValue());
					sb.append(" = ?");
					sap.add(obj);
				}
			}
		}
		sap.setSql(sb.toString());
		return sap;
	}

	/**
	 * 暂时只支持两种类型的参数查询，Number型和String型，而且不能进行模糊搜素
	 * 
	 * @param sql
	 * @param pojo
	 * @param map
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String paramSql(String sql, BasePojo pojo,
			Map<String, String> map) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		StringBuffer sb = new StringBuffer(sql);
		if (pojo != null) {
			Iterator<String> keyIt = map.keySet().iterator();
			while (keyIt.hasNext()) {
				String key = keyIt.next();
				Field field = pojo.getClass().getDeclaredField(key);
				field.setAccessible(true);
				Object obj = field.get(pojo);
				if (obj != null) {
					if (field.getType() == java.lang.String.class) {
						sb.append(" and ");
						sb.append(key);
						sb.append("='");
						sb.append(obj);
						sb.append("'");
						continue;
					}
					sb.append(" and ");
					sb.append(key);
					sb.append("=");
					sb.append(obj);
				}
			}
		}
		return sb.toString();
	}
}
