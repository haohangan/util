package current.pool;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionTest {
   public static void main(String[] args) {
	try{
		exe();
	}catch(FileNotFoundException e){
		System.out.println("FileNotFoundException");
	} catch (IOException e) {
		System.out.println("IOException");
	}catch(Exception e){
		System.out.println("Exception");
	}
}
   
   
   static void exe() throws FileNotFoundException,IOException{
	   throw new IOException();
   }
}
