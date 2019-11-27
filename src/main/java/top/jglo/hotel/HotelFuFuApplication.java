package top.jglo.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import top.jglo.hotel.example.FaceEngineTest;

@SpringBootApplication
public class HotelFuFuApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelFuFuApplication.class, args);
	}
}
