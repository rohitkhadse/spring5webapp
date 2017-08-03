package com.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.springframework.spring5webapp.model.Author;
import com.springframework.spring5webapp.model.Book;
import com.springframework.spring5webapp.model.Publisher;
import com.springframework.spring5webapp.repositories.AuthorRepository;
import com.springframework.spring5webapp.repositories.BookRepository;
import com.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;
	
	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initData();
		
	}
	
	public void initData(){
		
		Publisher harperCollins = new Publisher();
		Publisher tataHill = new Publisher();
		
		harperCollins.setName("Harper Collins");
		harperCollins.setAddress("New york");
		
		tataHill.setName("Tata Hill");
		tataHill.setAddress("Houston");
		
		publisherRepository.save(harperCollins);
		publisherRepository.save(tataHill);
		
		//eric
		Author eric = new Author("Eric","Evans");
		Book ddd = new Book("Domain Driven Design","12312",harperCollins);
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		
		authorRepository.save(eric);
		bookRepository.save(ddd);
		
		//Rodin
		Author rod= new Author("Rodin","Johnson");
		Book noEJB = new Book("J2EE Development without EJB","23432",tataHill);
		rod.getBooks().add(noEJB);
		
		authorRepository.save(rod);
		bookRepository.save(noEJB);

	}

	
}
