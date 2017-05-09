function Point(x,y){
	this.x = x;
	this.y = y;
}

Point.prototype.toString = function(){
	return '[x='+this.x+',y='+this.y+']';
}

var point = new Point(1,2);
console.info(point.toString());
console.info('typeof Point:'+typeof Point);
console.info('Point === Point.prototype.constructor:'+(Point === Point.prototype.constructor));


/**
*ES6提供了更接近传统语言的写法，引入了Class（类）这个概念，作为对象的模板。通过class关键字，可以定义类。基本上，ES6的class可以看作
只是一个语法糖，它的绝大部分功能，ES5都可以做到，新的class写法只是让对象原型的写法更加清晰、更像面向对象编程的语法而已。上面的代码
用ES6的“类”改写，就是下面这样。
***/
let methodName = "getArea";
class Point2{
	constructor(x,y){
		this.x = x;
		this.y = y;
	}
	
	[methodName](){
		console.info(111);
	}
	
	toString(){
		return '[x='+this.x+',y='+this.y+']';
	}
}

var point2 = new Point2(3,4);
point2.getArea();
console.info(point2.toString());
console.info('typeof Point2:'+typeof Point2);
console.info('Point2 === Point2.prototype.constructor:'+(Point2 === Point2.prototype.constructor));
console.info('point2.constructor === Point2.prototype.constructor:'+(point2.constructor === Point2.prototype.constructor));

//类的所有方法都定义在类的prototype属性上面

console.info('-----------------------------------------------------');
//类的内部所有定义的方法，都是不可枚举的（non-enumerable）
console.info('Object.keys(Point.prototype):'+Object.keys(Point.prototype));//toString

console.info('Object.keys(Point2.prototype):'+Object.keys(Point2.prototype));//空
console.info('Object.getOwnPropertyNames(Point2.prototype):'+Object.getOwnPropertyNames(Point2.prototype));

console.info('-----------------------------------------------------');
/**
一个类必须有constructor方法，如果没有显式定义，一个空
的constructor方法会被默认添加
*/

//与ES5一样，实例的属性除非显式定义在其本身（即定义在this对象上），否则都是定义在原型上（即定义在class上）。
//与ES5一样，类的所有实例共享一个原型对象。
//Class不存在变量提升（hoist），这一点与ES5完全不同 ?? 什么意思
//还有一种方法是利用Symbol值的唯一性，将私有方法的名字命名为一个Symbol值

//Class之间可以通过extends关键字实现继承，这比ES5的通过修改原型链实现继承，要清晰和方便很多。
// function Super() {}
 
// function Sub() {}
// Sub.prototype = new Super();
// Sub.prototype.constructor = Sub;
 
// var sub = new Sub();
 
// Sub.prototype.constructor === Sub; // ② true
// sub.constructor === Sub; // ④ true
// sub.__proto__ === Sub.prototype; // ⑤ true
// Sub.prototype.__proto__ == Super.prototype; // ⑦ true
//ES5中这种最简单的继承，实质上就是将子类的原型设置为父类的实例。
//ES6和ES5的继承是一模一样的，只是多了class 和extends ，ES6的子类和父类，子类原型和父类原型，通过__proto__ 连接。
// http://keenwon.com/1524.html

class ColorPoint extends Point2{
	constructor(x,y,color){
		super(x,y);
		this.color = color;
	}
	
	toString(){
		return '[color:]'+this.color +' '+super.toString();
	}
}

var cp = new ColorPoint(5,6,'red');
console.info(cp.toString());
console.info('cp instanceof ColorPoint:'+(cp instanceof ColorPoint));
console.info('cp instanceof Point2:'+(cp instanceof Point2));
console.info('cp instanceof Point:'+(cp instanceof Point));

//https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Inheritance_and_the_prototype_chain

