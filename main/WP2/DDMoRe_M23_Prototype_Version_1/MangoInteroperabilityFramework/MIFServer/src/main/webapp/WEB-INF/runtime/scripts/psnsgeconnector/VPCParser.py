#!/usr/bin/env python

import os, re, time, sys
from optparse import OptionParser
from decimal import *

"""Provides parsing functions for PsN VPC runs"""
class VPCParser:

	VERSION_OPTION_INFO = "version_and_option_info.txt"
	NM_RUN_DIR = "NM_run2"
	OUTPUT_FILE = "OUTPUT"

	"""Constructor"""
	def __init__(self, root_dir="vpc"):
		self.root_dir = root_dir		
		self.version_file = os.path.join(root_dir, self.VERSION_OPTION_INFO)
		self.logfile = os.path.join(self.root_dir, self.NM_RUN_DIR)
		self.logfile = os.path.join(self.logfile, self.OUTPUT_FILE)

	"""Get a PsN command line option, specified for the run, from the version_and_option_info.txt"""
	def __get_option(self, option_name):				
		for line in open(self.version_file, 'r'): 		
			match = re.match(".*" + option_name + "=([0-9]+).*", line)
			if match: 
				return match.group(1)

	"""Get the number of models completed"""
	def get_num_completed(self):
		num_completed = 0
		try:
			for line in open(self.logfile, 'r'): 		
				match = re.search("SUBPROBLEM NO.:[0-9]*?", line)	
				if match: 
					num_completed += 1
		finally:
			return num_completed

	"""Get the summary message"""
	def print_summary(self, num_completed, total_num):
		return "Simulating (%s/%s)" % (num_completed, total_num)

	"""Parse summary"""
	def parse_summary(self):
		if not (os.path.exists(self.logfile) and os.path.exists(self.version_file)):
			return "STARTUP"
		num_completed = self.get_num_completed()
		total_num = self.__get_option("-samples")
		return self.print_summary(num_completed, total_num)

# Main method
if __name__ == "__main__":	
	desc="""Provides running info for the PsN 3.4.2 VPC function."""
	parser = OptionParser(description=desc)
	parser.add_option("-r", "--rootdir", type="string", action="store", dest="rootdir", default=".", help="Root directory")
	(options, args) = parser.parse_args()
	
	parser = VPCParser(options.rootdir)
	print parser.parse_summary()
