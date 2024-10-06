package com.NotebookPB.HopeFinish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class HopeFinishApplication {

	public static void main(String[] args) {

		String relativePath = "src/main/java/notebook/resources/NoteDBase";
		Path path = Paths.get(relativePath).toAbsolutePath();
		String url =  "jdbc:h2:file:"+ path;

		System.out.println(url);

		// Устанавливаем абсолютный путь в системную переменную окружения
		System.setProperty("DB_URL", "jdbc:h2:file:" + path);

		SpringApplication.run(HopeFinishApplication.class, args);
	}

}
