package org.eva.english.learner.boot;

import java.io.InputStreamReader;
import java.util.Scanner;

public class BootHelp {
	public static void waitInput() {
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		boolean continued = true;// 是否持续运行的状态量

		while (continued) {
			System.out.print("#> ");
			String nextLine = scanner.nextLine();
			String command = nextLine.trim();
			if ("h".equalsIgnoreCase(command) || "help".equalsIgnoreCase(command)) {
				printHelp();
			} else if ("quit".equalsIgnoreCase(command) || "q".equalsIgnoreCase(command)) {
				continued = false;// 不想继续运行此server，修改状态量即可
			} else {
				unknown();
			}
		}
		scanner.close();
	}

	private static void printHelp() {
		System.out.println("命令列表:\n");
		System.out.println("quit: stop the server");
		System.out.println();
	}

	private static void unknown() {
		System.out.println("无法识别此命令，输入 help 查看列表");
		System.out.println();
	}
}
