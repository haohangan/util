package demo.nboot;

import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author guihao
 * @date 2016年12月2日上午11:08:06
 * @desc
 */
public class AppHelper {

	/**
	 * 等待命令
	 */
	public static void waitCommand() {
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		boolean continued = true;// 是否持续运行的状态量

		while (continued) {
			System.out.print("#> ");
			String nextLine = scanner.nextLine();
			String command = nextLine.trim();
			if ("h".equalsIgnoreCase(command) || "help".equalsIgnoreCase(command)) {
				printHelp();
			} else if ("quit".equalsIgnoreCase(command)) {
				continued = false;// 不想继续运行此server，修改状态量即可
			} else if ("list".equalsIgnoreCase(command)) {
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
