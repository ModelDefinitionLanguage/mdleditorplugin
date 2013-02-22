#!/usr/bin/env python

import unittest
from BootstrapParser import BootstrapParser

# Tests for BootstrapParser
class BootstrapParserTest(unittest.TestCase):

	# TODO:
	# get_bootstrap_log_info()
	# get_percentage_failed()
	# parse_summary()
	@classmethod
	def setUpClass(self):
		self.bsinfo = ""
		self.bsinfo += "jobId:119182;jobName:success.mod;runFileName:NM_run1;runWorkingDir:./testbs/modelfit_dir1/NM_run4;startTime:10;lastModificationTime:000 "
		self.bsinfo += "jobId:119182;jobName:bs_pr1_4.mod;runFileName:NM_run4;runWorkingDir:./testbs/modelfit_dir1/NM_run4;startTime:50;lastModificationTime:000 "
		self.bsinfo += "jobId:119183;jobName:bs_pr1_5.mod;runFileName:NM_run5;runWorkingDir:./testbs/modelfit_dir1/NM_run5;startTime:1000;lastModificationTime:000"

		self.summary_info = {}
		self.summary_info[1] = "bs_pr1_4.mod"
		self.summary_info[2] = "bs_pr1_5.mod"

		self.parser = BootstrapParser(self.bsinfo, "./")
		self.parser.set_base_model_name("success.mod")
	
	def test_get_number_hanging(self):
		numhanging = self.parser.get_number_hanging(self.summary_info)
		self.assertEqual(numhanging, 1, "Wrong number of hanging processes")

	def test_get_model_times(self):
		times = self.parser.get_model_times(self.summary_info)
		self.assertEqual(times.get("bs_pr1_4.mod"), str(50), "Time was not correct for " + self.summary_info[1])
		self.assertEqual(times.get("bs_pr1_5.mod"), str(1000), "Could not find "  + self.summary_info[2])

	def test_print_summary(self):
		completed = 8
		failed = 20
		total = 10
		hanging = 2
		summary = self.parser.print_summary(completed, failed, total, hanging)
		expected = "%s (%s%% Failed) of %s / %s hanging" % (completed, failed, total, hanging)		
		self.assertEqual(summary, expected, "Summary message not correct, got " + summary)

if __name__ == '__main__':
	unittest.main()
