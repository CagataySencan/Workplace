package com.tdonuk.sepetim;

import com.tdonuk.sepetim.util.AppUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SepetimApplication {

	public static void main(String[] args) throws Exception {
		AppUtil.init();
		SpringApplication.run(SepetimApplication.class, args);
	}

}
