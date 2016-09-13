package zk.utils.rules;

import java.io.IOException;
import java.util.Date;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.easyrules.quartz.RulesEngineScheduler;
import org.easyrules.quartz.RulesEngineSchedulerException;

@Rule(name = "timeRule")
public class TimeRule {
	private Date date;

	@Condition
	public boolean check() {
//		if (date == null) {
			date = new Date();
//		}
//		try {
			return date.getTime() % 2 == 0;
//		} finally {
//			date = null;
//		}
	}

	@Action
	public void result() {
		System.out.println("date time is even number:" + date);
	}

	public static final Date NOW = new Date();

	public static final int EVERY_SECOND = 5;

	public static void main(String[] args) throws RulesEngineSchedulerException, IOException {
		// RulesEngine enginX = RulesEngineBuilder.aNewRulesEngine().build();
		// TimeRule tr = new TimeRule();
		// enginX.registerRule(tr);
		// enginX.fireRules();

		RulesEngine enginX = RulesEngineBuilder.aNewRulesEngine().named("time rule engin").withSilentMode(true).build();
		TimeRule tr = new TimeRule();
		enginX.registerRule(tr);
		RulesEngineScheduler scheduler = RulesEngineScheduler.getInstance();
		scheduler.scheduleAtWithInterval(enginX, NOW, EVERY_SECOND);
		scheduler.start();
		System.in.read();
		scheduler.stop();
	}
}
