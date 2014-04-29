package com.pest.suite;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestLauncher {

	Launcher launcher;

	@Test
	public void testUsageMessage() {
		launcher = new Launcher(new String[] {});
		assertEquals("Usage: java -jar pestsuite-1.0-SNAPSHOT.jar", launcher.getUsageMessage());
	}
	
	@Test
	public void testUsageMessage2() {
		launcher = new Launcher(new String[] {});
		assertEquals("Usage: java -jar pestsuite-1.0-SNAPSHOT.jar", launcher.getUsageMessage());
	}
	
	@Test
	public void testUsageMessage3() {
		launcher = new Launcher(new String[] {});
		assertEquals("Usage: java -jar pestsuite-1.0-SNAPSHOT.jar", launcher.getUsageMessage());
	}
	
	@Test
	public void testUsageMessage4() {
		launcher = new Launcher(new String[] {});
		assertEquals("Usage: java -jar pestsuite-1.0-SNAPSHOT.jar", launcher.getUsageMessage());
	}
	
	@Test
	public void testProcessArgsWithEmptyArray() {
		launcher = new Launcher(null);
		assertFalse(launcher.processArgs(new String[] {}));
	}
	
	@Test
	public void testProcessArgsWithSingleArgument() {
		launcher = new Launcher(null);
		assertTrue(launcher.processArgs(new String[] {"ASDA"}));
	}
	
	@Test
	public void testProcessArgsWithMultipleArguments() {
		launcher = new Launcher(null);
		assertTrue(launcher.processArgs(new String[] {"ASDA","TESCO"}));
	}

}
