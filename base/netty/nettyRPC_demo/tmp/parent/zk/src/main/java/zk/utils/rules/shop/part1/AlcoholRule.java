package zk.utils.rules.shop.part1;

import org.easyrules.core.BasicRule;

import zk.utils.rules.shop.Person;

public class AlcoholRule extends BasicRule{
	private Person person;
	
	public AlcoholRule(Person person) {
		super("AlcoholRule", "Children are not allowed to buy alcohol", 2);
		this.person = person;
	}

	@Override
	public boolean evaluate() {
		return !person.isAdult();
	}

	@Override
	public void execute() throws Exception {
		System.out.printf("Shop: Sorry %s, you are not allowed to buy alcohol",person.getName());
		System.out.println();
	}
	
	
}
