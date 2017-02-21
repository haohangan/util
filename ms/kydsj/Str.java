package ez;

/**
 * @author guihao
 * @date 2017年2月21日下午3:12:38
 * @desc 
 */
public class Str {
	
	    static class P{
		   int ip = 9;

		@Override
		public String toString() {
			return "P [ip=" + ip + "]";
		}
	}
	
    String str = "abcd";
    int a = 10;
    Integer b = new Integer(10);
    char[] c = new char[]{'g','b','k'};
    P p = new P();
    
    public static void main(String[] args) {
    	Str s = new Str();
    	s.set(s.c, s.str,s.a,s.b,s.p);
    	System.out.println(s.str);
    	System.out.println(s.c);
    	System.out.println(s.a);
    	System.out.println(s.b);
    	System.out.println(s.p);
	}
    
    void set(char[] c,String str,int a,Integer b,P p){
    	c[1] = 'A';
    	str = "hello";
    	a = 100;
    	b = new Integer(100);
//    	p.ip = 100;
    	p = new P();
    	p.ip = 100;
    }
    
  /*  传值传引用都不够准确，可以理解成传引用变量的副本值。引用变量分为字面值引用变量（即基本数据类型引用变量）和对象引用变量 。 详情需要了解数据类型使用机制和堆栈的概念：http://www.cnblogs.com/alexlo/archive/2013/02/21/2920209.html
    	　　对象引用变量：即普通java对象的引用变量 ，如 String a = "abc" , a就是对象引用变量。java 是不能直接操作对象的，只能通过对“对象引用的操作”来操作对象。而对象的引用的表示就是对象变量。可以多个对象引用变量指向同一个对象。
    	　　字面值引用变量：即普通数据类型的引用变量 ，如 int b = 1 , b就是字面值引用变量。可以有多个字面值引用变量指向同一字面值，但其中一个引用修改字面值，不会影响另一个引用字面值，这点要与对象引用区别开。
    	　　Example1的结果是最容易理解的，因为此时向方法check(int a) 传递的是一个整型变量，而整型变量是基本数据类型的一种。当向一个方法传递基本数据类型时（基本数据类型包 括 byte,int,short,char,float,double以及boolean），传递的只是该数据内容的一个副本(确切可以理解成字面值引 用变量的副本)，因此无论方法针对该副本值做怎样的改变（或者是说对字面值），都不会影响到被传入的数据本身。


    	　　Example2的结果也是比较容易理解的，因为此时向方法check(StringBuffer obj)传递的是一个StringBuffer变量，这个变量是对象型数据类型的一种。当向一个方法传递对象型数据类型（包括String, StringBuffer，类对象引用，接口引用和数组等）时，传递的是该数据对象的某个引用变量（的副本）而不是对象内容本身，因此，在将引用变量x传入方法时，obj和x便同时对原来x所引用的对象（这个对象的内容是”Hello  ”）具有了引用关系，也就是说，obj和x都是对象（即”Hello  ”）的引用，由于JAVA对于对象的访问是通过访问对象的引用来完成的，因此，当方法对obj的引用对象内容进行改变时，实际上也是在针对x所引用的对象的内容进行改变，这自然导致了读者所看到的结果。
    	 

    	 
    	　　Example3 , 和Excample2 一样，传递的是String 对象的引用的副本值，所以obj和x都是对象（即”Hello  ”）的引用。关键点，String 是final 不可变的，即String类型对象是不可变的，当然也就不可以通过引用对该对象进行任何改变了。而 obj=“JAVA” 这句，并没有改变“HELLO” 这个对象，而是将obj这个引用变量重新指向新的对象。*/
}
