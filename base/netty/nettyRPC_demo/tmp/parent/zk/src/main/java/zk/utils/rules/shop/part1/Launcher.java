package zk.utils.rules.shop.part1;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;

import zk.utils.rules.shop.Person;

public class Launcher {
	public static void main(String[] args) {
		Person p = new Person("Tim Booo", 18);
		RulesEngine enginX = RulesEngineBuilder.aNewRulesEngine().named("shop rules").build();
		
		//register rules
		enginX.registerRule(new AgeRule(p));
		enginX.registerRule(new AlcoholRule(p));
		
		enginX.fireRules();
		System.out.println(p);
	}
}
