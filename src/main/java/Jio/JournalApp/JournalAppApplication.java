package Jio.JournalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
public class JournalAppApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(JournalAppApplication.class, args);
	}

	@Bean
	public  PlatformTransactionManager transaction(MongoDatabaseFactory db)
	{
		return new MongoTransactionManager(db);
	}


}
