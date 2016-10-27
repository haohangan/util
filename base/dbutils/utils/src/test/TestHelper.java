package test;

import java.lang.reflect.Field;

public class TestHelper {

	public static void printAObject(Object obj)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		StringBuilder sb = new StringBuilder();
		Class<? extends Object> clazz = obj.getClass();
		sb.append(clazz.getSimpleName());
		sb.append(" [ ");
		Field[] fs = clazz.getDeclaredFields();
		for (Field f : fs) {
			f.setAccessible(true);
			if(f.getType()==java.lang.String.class){
				Object value = f.get(obj);
				sb.append(f.getName());
				sb.append(" = '");
				sb.append(value);
				sb.append("',");
				continue;
			}
				Object value = f.get(obj);
				sb.append(f.getName());
				sb.append(" = ");
				sb.append(value);
				sb.append(" ,");
		}
		sb.append(" ] ");
		System.out.println(sb.toString());
	}
	
}
