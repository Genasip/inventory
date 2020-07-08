package com.interview.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class InventoryApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void testRepository(){
		Product product = new Product("Pepsi", 2);
		productRepository.save(product);
		product = productRepository.getOne("Pepsi");
		Assert.isTrue(!(product==null), "[Assertion failed] - this expression must be true");
		productRepository.delete(product);
	}

}
