#!/usr/bin/env python

import os, re, time, sys
from optparse import OptionParser
from decimal import *

"""Provides parsing functions for PsN SCM runs"""
class SCMParser:

    FORWARD_STARTING=".*Starting scm forward search"
    FORWARD_DONE=".*forward search done"
    ESTIMATE_BASE_MODEL=".*Estimating base model"
    BACKWARD_STARTING=".*Starting scm backward search"
    SCM_DONE=".*scm done"
    CHOSEN_MODEL_OFV=".*CHOSEN_MODEL_OFV\s+([\d\.]+).*"
    UNKNOWN="N/A"

    MIF_STD_ERR="MIF.stderr"
    MODELS_DIR="m1"

    BASE_MODEL_RUNNING_SUMMARY="Base Model Running"
    FORWARD_SEARCH_RUNNING_SUMMARY="Base Model Completed - Forward Search Running"
    FORWARD_SEARCH_COMPLETED_SUMMARY="Base Model Completed - Forward Search Completed"
    BACKWARD_SEARCH_RUNNING_SUMMARY="Base Model Completed - Backward Search Running"
    BACKWARD_SEARCH_COMPLETED_SUMMARY="Base Model Completed - Backward Search Completed"
    BACKWARD_WITH_FWD_SEARCH_RUNNING_SUMMARY="Base Model Completed - Forward Search Completed - Backward Search Running"
    BACKWARD_WITH_FWD_SEARCH_COMPLETED_SUMMARY="Base Model Completed - Forward Search Completed - Backward Search Completed"
    """Constructor"""
    def __init__(self, root_dir="", output_dir="scm", scm_log="scmlog1.txt", final="false"):
        self.root_dir = root_dir          
        self.output_dir = os.path.join(self.root_dir, output_dir)       
        self.models_dir = os.path.join(self.output_dir, self.MODELS_DIR)       
        self.logfile = os.path.join(self.root_dir, self.MIF_STD_ERR)   
	self.scm_logfile = os.path.join(self.root_dir, scm_log)
	self.final = (final=="true")

    """Get summary"""
    def get_summary(self):
        summary="STARTUP"
	if not os.path.exists(self.logfile):
		if not self.final: 
			return summary
		else:
			return self.UNKNOWN
	backward=False
	forward=False
        for line in open(self.logfile, 'r'):           
            match = re.match(self.ESTIMATE_BASE_MODEL, line)
            if match and not os.path.exists(self.models_dir): 
		summary = self.BASE_MODEL_RUNNING_SUMMARY             
            match = re.match(self.FORWARD_STARTING, line)
            if match: 
		summary = self.FORWARD_SEARCH_RUNNING_SUMMARY
		forward = True
            match = re.match(self.FORWARD_DONE, line)
            if match: summary = self.FORWARD_SEARCH_COMPLETED_SUMMARY
            match = re.match(self.BACKWARD_STARTING, line)
            if match:
		if forward: summary = self.BACKWARD_WITH_FWD_SEARCH_RUNNING_SUMMARY
		else: summary = self.BACKWARD_SEARCH_RUNNING_SUMMARY
		backward=True
            match = re.match(self.SCM_DONE, line)
            if match: 
		if not self.final:
			if backward: 
				if forward: summary = self.BACKWARD_WITH_FWD_SEARCH_COMPLETED_SUMMARY
				else: summary = self.BACKWARD_SEARCH_COMPLETED_SUMMARY
			else: summary = self.FORWARD_SEARCH_COMPLETED_SUMMARY
		else: 
			summary = self.get_final_ofv()
        return summary

    """Get final objective funcation value"""
    def get_final_ofv(self):
        ofv=self.UNKNOWN
	if not os.path.exists(self.scm_logfile):
		return ofv
        for line in open(self.scm_logfile, 'r'):                        
            match = re.match(self.CHOSEN_MODEL_OFV, line)
            if match: 
		ofv = match.group(1)
        return ofv

    """Parse summary"""
    def parse_summary(self):
        return self.get_summary()

# Main method
if __name__ == "__main__":  

    # Handle running, failed, complete statuses
    desc="""Provides running info for the PsN 3.4.2 VPC function."""
    parser = OptionParser(description=desc)
    parser.add_option("-r", "--rootdir", type="string", action="store", dest="rootdir", default=".", help="Root directory") 
    parser.add_option("-o", "--outputdir", type="string", action="store", dest="outputdir", default=".", help="SCM output directory") 
    parser.add_option("-l", "--scmlog", type="string", action="store", dest="scmlog", default=".", help="SCM log file") 
    parser.add_option("-f", "--final", type="string", action="store", dest="final", default="false", help="Completed?") 
    (options, args) = parser.parse_args()
    
    parser = SCMParser(options.rootdir, options.outputdir, options.scmlog, options.final)
    print parser.parse_summary()   
