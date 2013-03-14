package com.mango.mif.utils;

import static org.junit.Assert.*;

import java.io.File;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class ExternalisePropertiesTest {
	@Resource(name="notOverwritten")
	String notOverwritten;
	@Resource(name="overwritten")
	String overwritten;
	@Resource(name="userDir")
	String userDir;
	@Resource(name="testWorkingDirectory")
	File testWorkingDirectory;
	@Test
	public void shouldOverwritePropertiesFromFirstListedLocationIfOverwrittenLaterOne() {
		assertEquals("value-from-second-file",overwritten);
	}

	@Test
	public void shouldNotOverwritePropertiesFromFirstListedLocationIfLaterDoNotOverwrite() {
		assertEquals("value-from-first-file",notOverwritten);
	}

	@Test
	public void shouldInstantiateFileFromTwoStringAttributes() {
		assertNotNull(testWorkingDirectory);
	}
}
