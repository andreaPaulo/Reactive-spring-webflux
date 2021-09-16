package br.com.digitalinnovation.stockquotesapi;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@EnableScheduling
@Log4j2
@SpringBootApplication
public class StockQuotesApiApplication {
	@Autowired
	private QuoteRepository quoteRepository;

	public static void main(String[] args) {
		SpringApplication.run(StockQuotesApiApplication.class, args);
	}

	@Scheduled(fixedDelay = 1000)
	public void generateData(){
		log.info(quoteRepository.findFirstBySymbolOrderByTimestampDesc("Teste")
				.map(this::generateNewData)
				.orElseGet(this::initializeData)
		);
	}

	private Quote initializeData() {
		return quoteRepository.save(Quote.builder()
				.symbol("Teste")
				.openvalue(0.22222)
				.closeValue(0.22222)
				.timestamp(new Date())
				.build());
	}

	private Quote generateNewData(Quote quote) {
		return quoteRepository.save(Quote.builder()
				.symbol(quote.getSymbol())
				.openvalue(quote.getOpenvalue() * new RandomDataGenerator().nextUniform(-0.10, 0.10))
				.closeValue(quote.getCloseValue() * new RandomDataGenerator().nextUniform(-0.10, 0.10))
				.timestamp(new Date())
				.build());

	}
}

