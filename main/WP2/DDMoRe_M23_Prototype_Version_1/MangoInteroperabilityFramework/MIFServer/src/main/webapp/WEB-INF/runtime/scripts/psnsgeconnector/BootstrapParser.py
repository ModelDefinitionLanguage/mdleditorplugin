#!/usr/bin/env python

import os, re, time, sys
from optparse import OptionParser
from decimal import *

"""Provides parsing functions for PsN bootstrap runs"""
class BootstrapParser:

	BOOTSTRAP_LOG_FILE = "bootstraplog1.csv"
	VERSION_FILE = "version_and_option_info.txt"
	MODELFITDIR = "modelfit_dir1"
	CANCELLED_FILE = ".MIF-cancelledJobId"

	"""Constructor"""
	def __init__(self, bsinfoFile, bootstrap_root_dir="bs"):
		self.bsinfoFile = bsinfoFile
		self.bootstrap_root_dir = bootstrap_root_dir
		self.base_model_name=""
		self.nm_dir_prefix = "NM_run"
		self.logfile = os.path.join(self.bootstrap_root_dir, self.BOOTSTRAP_LOG_FILE)
		self.version_file = os.path.join(bootstrap_root_dir, self.VERSION_FILE)
		self.model_fit_dir = os.path.join(bootstrap_root_dir, self.MODELFITDIR)
		self.bsinfo = open(bsinfoFile, 'r').read()

	# Private methods
	"""[private] Determine whether the objective function is present in a bootstraps psn.lst file"""
	def __objfn_present(self, psn_file):
		for line in open(psn_file, 'r'): 						
			match = re.match(".*#OBJV.*?([0-9\.]+)", line)
			if match: return True		
			
		return False

	"""[private] Determine whether the job has been cancelled"""
	def __is_cancelled(self, nmdir):
		return os.path.exists(os.path.join(nmdir, self.CANCELLED_FILE))

	"""[private] Get a PsN command line option, specified for the bootstrap run, from the version_and_option_info.txt"""
	def __get_option(self, option_name):				
		for line in open(self.version_file, 'r'): 		
			match = re.match(option_name + "=([0-9]*)", line)
			if match: 
				return match.group(1)

	def checkpaths(self):
		if not os.path.exists(self.version_file): sys.exit("Could not find version options file " + parser.version_file)
		if not os.path.exists(self.bootstrap_root_dir): sys.exit("Bootstrap directory does not exist " + parser.bootstrap_root_dir)
		if not os.path.exists(self.logfile): sys.exit("Bootstrap file not present at " + parser.logfile)

	# Visiblefortesting
	def set_base_model_name(self, base_model_name):
		self.base_model_name = base_model_name

	"""Get the bootstraploginfo csv entries (completed models)"""
	def get_bootstrap_log_info(self):
		summary_info = {}
		for line in open(self.logfile, 'r'):
			if line:
				line = line.strip()
				res = re.match(r"(.*),(.*.mod),", line)
				if res:
					model_number = res.group(1)
					model_filename = res.group(2)

					# Set the original model name if prefix is not bs
					if not model_filename.startswith("bs_"):
						self.base_model_name = model_filename.strip()
					else:
						summary_info[model_number] = model_filename.strip()
		return summary_info
		
	"""Get percentage of bootstrap children that have failed so far"""
	def get_percentage_failed(self, summary_info, total_num):
		num_failed = 0

		for bs_child_num in summary_info:
			nmdir = os.path.join(self.model_fit_dir, self.nm_dir_prefix + bs_child_num)
			psn_file = os.path.join(nmdir, "psn.lst")
			obj_fn = self.__objfn_present(psn_file)
			is_cancelled = self.__is_cancelled(nmdir)
		 	if not obj_fn or is_cancelled: num_failed += 1			

		if num_failed > 0: 
			percentage_failed = round((Decimal(num_failed) / Decimal(total_num)) * 100,1)
		else: 
			percentage_failed = 0.0

		return percentage_failed

	""" Get the number of bootstrap children that have hung"""
	def get_number_hanging(self, summary_info):
		return len(re.findall(".*status:Hanging;.*", self.bsinfo))
	
	def print_summary(self, num_completed, percentage_failed, total_num, num_hanging):
		return "%s (%s%% Failed) of %s / %s hanging" % (num_completed, percentage_failed, total_num, num_hanging)

	"""Parse summary for the bootstrap"""
	def parse_summary(self):
		if not (os.path.exists(self.logfile) and os.path.exists(self.version_file) and os.path.exists(self.model_fit_dir)):
			return "STARTUP"

		summary_info = self.get_bootstrap_log_info()
		num_completed = len(summary_info)
		total_num = self.__get_option("-samples")
		percentage_failed = self.get_percentage_failed(summary_info, total_num)
		num_hanging = self.get_number_hanging(summary_info)

		return self.print_summary(num_completed, percentage_failed, total_num, num_hanging)

	"""Generate summary for the bootstrap"""
	def generate_summary(self):
		if not (os.path.exists(self.logfile) and os.path.exists(self.version_file) and os.path.exists(self.model_fit_dir)):
			return "STARTUP"
		self.checkpaths();

		return self.parse_summary()
# Main method
if __name__ == "__main__":	
	desc="""Provides running info for the PsN 3.4.2 bootstrap function."""
	
	# TODO: Handle running, failed, complete statuses
	parser = OptionParser(description=desc)
	parser.add_option("-r", "--rootdir", type="string", action="store", dest="rootdir", default=".", help="Root directory")
	parser.add_option("-i", "--bsinfoFile", type="string", action="store", dest="bsinfoFile", default=".", help="File containing Bootstrap info (start time etc...) parsed from grid")
	(options, args) = parser.parse_args()
	
	parser = BootstrapParser(options.bsinfoFile, options.rootdir)
	
	print parser.generate_summary()
