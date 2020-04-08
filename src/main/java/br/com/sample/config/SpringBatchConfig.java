package br.com.sample.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import br.com.sample.model.Texto;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
			StepBuilderFactory stepBuilderFactory,
			ItemReader<Texto> itemReader,
			ItemProcessor<Texto, Texto> itemProcessor,
			ItemWriter<Texto> itemWriter) {
		
		Step step = stepBuilderFactory.get("ETL-file-load")
				.<Texto, Texto>chunk(10)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
		
		Job job = jobBuilderFactory.get("ETL-Load")
			.incrementer(new RunIdIncrementer()) //sequence of ids for every run
			.start(step) 
			.build();
		
		//multiple steps
		//flow(step) ao inves de start(step)
		//next(step)
		
		return job;
	}
	
	@Bean
	public FlatFileItemReader<Texto> fileItemReader(@Value("${input}") Resource resource){
		
		FlatFileItemReader<Texto> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("FlatFile-Reader");
		flatFileItemReader.setLinesToSkip(0);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}

	@Bean
	public LineMapper<Texto> lineMapper() {
		DefaultLineMapper<Texto> defaultLineMapper = new DefaultLineMapper<>();
		
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
		String[] names = new String[] {"texto"};
		tokenizer.setNames(names);
		Range[] ranges = new Range[] {
				new Range(1,1000)
		};
		tokenizer.setNames(names);
		tokenizer.setColumns(ranges);
		
		BeanWrapperFieldSetMapper<Texto> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Texto.class);

		defaultLineMapper.setLineTokenizer(tokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		
		return defaultLineMapper;
	}
}
