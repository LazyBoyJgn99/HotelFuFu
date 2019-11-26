package top.jglo.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.jglo.hotel.test.FaceEngineTest;

@SpringBootApplication
public class HotelFuFuApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HotelFuFuApplication.class, args);

		System.out.println(System.getProperty("java.library.path"));
		new FaceEngineTest();

	}
}
