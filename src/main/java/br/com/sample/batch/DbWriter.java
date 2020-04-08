package br.com.sample.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sample.batch.repository.TextoRepository;
import br.com.sample.model.Texto;

@Component
public class DbWriter implements ItemWriter<Texto>{

	@Autowired
	private TextoRepository repository;
	
	@Override
	public void write(List<? extends Texto> textos) throws Exception {
		repository.saveAll(textos);
	}

	
}
