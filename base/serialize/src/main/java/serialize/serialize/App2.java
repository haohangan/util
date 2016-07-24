package serialize.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * Hello world!
 *
 */
public class App2 
{
    public static void main( String[] args ) throws IOException, ClassNotFoundException
    {
        System.out.println( "Hello World!" );
        testUser();
        ListObject lo = new ListObject();
        lo.setSize(10000000);
        List<User> list = new ArrayList<>();
        for(int i = 0;i<1000000;i++){
        	User u = new User(i,"name"+i,'1');
        	list.add(u);
        }
        lo.setList(list);
//        ByteArrayInputStream in = new ByteArrayInputStream(buf)
       
      
        long start = System.currentTimeMillis();
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(lo);
        byte[] bytes = out.toByteArray();
        oos.close();  
        out.close();  
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(in);
        ListObject t = (ListObject) ois.readObject();
        ois.close();
        in.close();
        long end = System.currentTimeMillis();
        System.out.println((end-start)+" ms");
        System.out.println("反序列化结果："+t.getList().get(0));
    }

	private static void testUser() {
		User user = new User(1, "jack", '1');
//        User user2 = new User(2, "tom", '1');
//        User user3 = new User(3, "Marry", '0');
        
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);


        Schema<User> schema = RuntimeSchema.createFrom(User.class);
        
        // 序列化 user 类
        byte[] bytes = ProtostuffIOUtil.toByteArray(user, schema, buffer);
        
        
        
        User t = new User();

        // 将 bytes 反序列化 ， 存储到 t 变量里面
        ProtostuffIOUtil.mergeFrom(bytes, t, schema);

        System.out.println("反序列化结果：" + t);
	}
    
    
}
