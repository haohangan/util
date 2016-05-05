//StrategyExample test application
//from：维基百科

class StrategyExample {

    public static void main(String[] args) {

        Context context;

        // Three contexts following different strategies
        context = new Context(new FirstStrategy());
        context.execute();

        context = new Context(new SecondStrategy());
        context.execute();

        context = new Context(new ThirdStrategy());
        context.execute();

    }

}

// The classes that implement a concrete strategy should implement this

// The context class uses this to call the concrete strategy
interface Strategy {

    void execute();
    
}

// Implements the algorithm using the strategy interface
class FirstStrategy implements Strategy {

    public void execute() {
        System.out.println("Called FirstStrategy.execute()");
    }
    
}

class SecondStrategy implements Strategy {

    public void execute() {
        System.out.println("Called SecondStrategy.execute()");
    }
    
}

class ThirdStrategy implements Strategy {

    public void execute() {
        System.out.println("Called ThirdStrategy.execute()");
    }
    
}

// Configured with a ConcreteStrategy object and maintains a reference to a Strategy object
class Context {

    Strategy strategy;

    // Constructor
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        this.strategy.execute();
    }

}

/**
应用场景：替换掉if...else
Context: 环境类
Strategy: 抽象策略类
ConcreteStrategy: 具体策略类
http://design-patterns.readthedocs.io/zh_CN/latest/behavioral_patterns/strategy.html

*/