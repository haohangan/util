package objcreate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestObject extends Object implements Cloneable,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6783493252384840621L;
	
	private int num;

	public TestObject(int num) {
		super();
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	@Override
	public String toString() {
		return "TestObject [num=" + num + "]";
	}


	public TestObject clone(){
		TestObject to = null;
		try {
			to =  (TestObject) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return to;
	}

	
	public byte[] writeToArr() throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(this);
		return baos.toByteArray();
	}
	
	public static TestObject readFromArr(byte[] arr) throws IOException, ClassNotFoundException{
		ByteArrayInputStream oais = new ByteArrayInputStream(arr);
		ObjectInputStream ois = new ObjectInputStream(oais);
		TestObject to = (TestObject) ois.readObject();
		return to;
	}
}
