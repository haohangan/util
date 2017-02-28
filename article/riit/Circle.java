package org.eva.id.tetsshape;

/**
 * @author guihao
 * @date 2017年2月28日上午11:44:44
 * @desc 
 */
public class Circle extends Shape{
	public String name ="Circle";
    public static String length ="Circle length";
    
    public void printType(){
    	System.out.println("Circle");
    }
    
    public static void printName(){
    	System.out.println("this is a Circle");
    }
    
    public String getName() {
		return name;
	}

	public static void main(String[] args) {
    	Shape shape = new Circle();
    	System.out.println(shape.name);
    	System.out.println(shape.getName());
    	System.out.println(shape.length);
    	shape.printType();
    	shape.printName();
    	System.out.println("---------------");
    	Circle circle = new Circle();
    	System.out.println(circle.name);
    	System.out.println(circle.getName());
    	System.out.println(circle.length);
    	circle.printType();
    	circle.printName();
	}
}
