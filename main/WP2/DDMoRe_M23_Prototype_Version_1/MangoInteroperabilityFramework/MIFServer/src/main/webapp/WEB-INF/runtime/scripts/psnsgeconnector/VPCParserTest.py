#!/usr/bin/env python

import unittest
import tempfile
import os
import time

from VPCParser import VPCParser

# Tests for VPCParser
class VPCParserTest(unittest.TestCase):

	MIFDIR_PREFIX = "mifdir-"

	@classmethod
	def setUpClass(self):

		# Set the rootdirectory/vpc directory up
		self.rootdirname = os.path.join(tempfile.gettempdir(), self.MIFDIR_PREFIX + self.randomname())
		os.makedirs(self.rootdirname)
		
		print "Test directory structure in " + self.rootdirname

		self.vpcdirname = os.path.join(self.rootdirname, "vpc")
		os.makedirs(self.vpcdirname)

		self.parser = VPCParser(self.vpcdirname)

		self.versionfile = os.path.join(self.vpcdirname, self.parser.VERSION_OPTION_INFO)
		open(self.versionfile, 'w').close()

		self.nmdir = os.path.join(self.vpcdirname, self.parser.NM_RUN_DIR)
		os.makedirs(self.nmdir)

		self.outputfile = os.path.join(self.nmdir, self.parser.OUTPUT_FILE)
		open(self.outputfile, 'w').close()

	@classmethod
	def randomname(self):
		return str(int(round(time.time() * 1000)))

	def writecontent(self, filename, *parts):
		try: 
			f = open(filename, 'w')
			for part in parts:
				f.write(part + "\n")
		finally:
			f.close()

	def test_parse_summary_empty(self):
	   self.writecontent(self.versionfile, "-samples=5")
	   self.writecontent(self.outputfile, "")
	   summary = self.parser.parse_summary()
	   self.assertEqual(summary, "Simulating (0/5)", "Summary message not correct, got " + summary)

	def test_parse_summary(self):
	   self.writecontent(self.versionfile, "-samples=100")
	   self.writecontent(self.outputfile, " PROBLEM NO.:         1     SUBPROBLEM NO.:      1", " PROBLEM NO.:         1     SUBPROBLEM NO.:      2")
	   summary = self.parser.parse_summary()
	   self.assertEqual(summary, "Simulating (2/100)", "Summary message not correct, got " + summary)

if __name__ == '__main__':
	unittest.main()
