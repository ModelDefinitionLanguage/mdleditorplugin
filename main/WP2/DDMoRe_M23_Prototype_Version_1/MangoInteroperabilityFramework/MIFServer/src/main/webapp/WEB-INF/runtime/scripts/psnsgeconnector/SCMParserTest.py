#!/usr/bin/env python

import unittest
import tempfile
import os
import time

from SCMParser import SCMParser

# Tests for SCMParser
class SCMParserTest(unittest.TestCase):

	MIFDIR_PREFIX = "mifdir-"

	@classmethod
	def setUpClass(self):
		self.rootdirname = os.path.join(tempfile.gettempdir(), self.MIFDIR_PREFIX + self.randomname())
		self.temp_mifdir = self.createdir(self.rootdirname)
		self.parser = SCMParser(self.rootdirname)
		self.miferrfile = os.path.join(self.temp_mifdir, self.parser.MIF_STD_ERR)
		open(self.miferrfile, 'w').close()

	@classmethod
	def randomname(self):
		return str(int(round(time.time() * 1000)))

	@classmethod
	def createdir(self, rootdirname):
		temp_rootdir = os.path.join(rootdirname)
		os.makedirs(temp_rootdir)
		temp_mifdir = os.path.join(temp_rootdir, ".MIF")
		os.makedirs(temp_mifdir)
		return temp_mifdir

	def writecontent(self, *parts):

		# Open the file in append mode so each fo the tests adds text then verifies the state/summary
		f = open(self.miferrfile, 'a')
		for part in parts:
			f.write(part + "\n")
		f.close()
	    
	def test_get_summary_forward(self):
		self.writecontent("Starting scm forward search", "Reading data file: simu_rich_2.csv")
		summary = self.parser.get_summary()
		self.assertEqual(summary, "Forward search starting", "Summary not returned correctly, was " + summary)	   

	def test_get_summary_estimation(self):
		self.writecontent("Calculating categorical covariate statistics", "Estimating base model")
		summary = self.parser.get_summary()
		self.assertEqual(summary, "Estimating base model", "Summary not returned correctly, was " + summary)

	def test_get_summary_backward(self):
		self.writecontent("forward search done.", "Starting scm backward search inside forward top level directory")
		summary = self.parser.get_summary()
		self.assertEqual(summary, "Backward search starting", "Summary not returned correctly, was " + summary)

	def test_get_summary_done(self):
		self.writecontent("F:1 .. F:2 ..  done", "scm done")
		summary = self.parser.get_summary()
		self.assertEqual(summary, "SCM done", "Summary not returned correctly, was " + summary)

if __name__ == '__main__':
	unittest.main()