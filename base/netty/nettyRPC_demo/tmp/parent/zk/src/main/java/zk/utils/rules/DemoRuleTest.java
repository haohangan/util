package zk.utils.rules;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;

public class DemoRuleTest {
	public static void main(String[] args) {
		RulesEngine enginX = RulesEngineBuilder.aNewRulesEngine().build();
		DemoRule dr = new DemoRule();
		dr.setInput("yes");
		enginX.registerRule(dr);
		enginX.fireRules();
		dr.setInput("no");
		enginX.registerRule(dr);
		enginX.fireRules();
	}
}
