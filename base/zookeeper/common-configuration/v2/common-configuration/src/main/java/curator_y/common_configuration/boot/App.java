package curator_y.common_configuration.boot;

import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import curator_y.common_configuration.curator.CuratorStart;

/**
 * 注册中心
 *
 */
@SpringBootApplication
public class App {
	{
		Logger.getGlobal().setLevel(Level.INFO);
	}

	/**
	 * 程序启动点
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		CuratorStart curator = context.getBean(CuratorStart.class);
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
				curator.list();
			} else {
				unknown();
			}
		}
		scanner.close();
		SpringApplication.exit(context);
		System.out.println("server stoped");
	}

	private static void printHelp() {
		System.out.println("注册中心服务命令列表:\n");
		System.out.println("list: list all registered app");
		System.out.println("quit: stop the server");
		System.out.println();
	}

	private static void unknown() {
		System.out.println("无法识别此命令，输入 help 查看列表");
		System.out.println();
	}
}
