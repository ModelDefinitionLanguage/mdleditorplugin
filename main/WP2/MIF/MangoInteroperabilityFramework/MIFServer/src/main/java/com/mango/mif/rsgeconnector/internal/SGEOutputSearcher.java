/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.rsgeconnector.internal;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;

/**
 * The idea here is for regular expressions to be injected via Spring into each of the strings in this class
 * so they can then be used to search through various bits of the output from various commands.
 */
public class SGEOutputSearcher {

    private static Logger LOG = Logger.getLogger(SGEOutputSearcher.class);

    /* used to parse the output of the "job accounting command" (in our case qacct) for the "job not found" message */
    private String jobAccountingNotFoundRegexp;

    /* used to parse the output of the "job status command" (in our case qstat) for the "job not found" message */
    private String jobStatusNotFoundRegexp;

    /* used to parse the output of the "job delete command" (in our case qdel) for the "job not found" message */
    private String jobDeleteNotFoundRegexp;

    /* used to parse output from the job submission command and retrieve the job id */
    private String jobSubmittedExtractJobIdRegexp;

    /* used to parse output from the job status command (in our case qstat) */
    private String jobStatusRegexp;

    /* used to parse output from the job accounting command (in our case qacct) */
    private String jobAccountingRegexp;

    /* used to parse output from the job deletion command (in our case qdel) */
    private String jobDeletionRegexp;

    //=========================================================================
    //=========================================================================
    //=========================================================================

    public String getJobAccountingNotFoundRegexp() {
        return jobAccountingNotFoundRegexp;
    }

    public void setJobAccountingNotFoundRegexp(String jobsNotFoundRegexp) {
        this.jobAccountingNotFoundRegexp = jobsNotFoundRegexp;
    }

    public String getJobStatusNotFoundRegexp() {
        return jobStatusNotFoundRegexp;
    }

    public void setJobStatusNotFoundRegexp(String jobStatusNotFoundRegexp) {
        this.jobStatusNotFoundRegexp = jobStatusNotFoundRegexp;
    }

    public String getJobSubmittedExtractJobIdRegexp() {
        return jobSubmittedExtractJobIdRegexp;
    }

    public void setJobSubmittedExtractJobIdRegexp(String jobSubmittedRegexp) {
        this.jobSubmittedExtractJobIdRegexp = jobSubmittedRegexp;
    }

    public String getJobStatusRegexp() {
        return jobStatusRegexp;
    }

    public void setJobStatusRegexp(String jobStatusPropertyRegexp) {
        this.jobStatusRegexp = jobStatusPropertyRegexp;
    }

    public String getJobAccountingRegexp() {
        return jobAccountingRegexp;
    }

    public void setJobAccountingRegexp(String jobAccountingRegexp) {
        this.jobAccountingRegexp = jobAccountingRegexp;
    }

    public String getJobDeletionRegexp() {
        return jobDeletionRegexp;
    }

    public void setJobDeletionRegexp(String jobDeletionRegexp) {
        this.jobDeletionRegexp = jobDeletionRegexp;
    }

    public String getJobDeleteNotFoundRegexp() {
        return jobDeleteNotFoundRegexp;
    }

    public void setJobDeleteNotFoundRegexp(String jobDeleteNotFoundRegexp) {
        this.jobDeleteNotFoundRegexp = jobDeleteNotFoundRegexp;
    }

    //=========================================================================
    //=========================================================================
    //=========================================================================

    public String matchJobId(String input) {
        Preconditions.checkArgument(jobSubmittedExtractJobIdRegexp != null, "job submitted regexp must have been injected by this point");
        Preconditions.checkArgument(input != null, "the input cannot be null");

        LOG.info("*** matchJobId matching \"" + input + "\" with regexp \"" + jobSubmittedExtractJobIdRegexp + "\"");

        String[] matches = match(jobSubmittedExtractJobIdRegexp, input);

        // Remember that matches[0] is the whole input string match, so the array will always have one element.
        // If we are interested in the value in group 1, this will be in position 1 in the array, thus the array
        // will have two elements, zero and one.
        //
        if (matches == null || matches.length < 2) {
            return null;
        }
        return matches[1];
    }

    /**
     * Look for the "jobs not found message" in the output from the accounting command (qacct)
     * @param input The input to scan
     * @return true if there is a match, false otherwise.
     */
    public boolean matchAccountingJobNotFound(String input) {
        Preconditions.checkArgument(jobAccountingNotFoundRegexp != null, "jobs not found regexp for accounting command must have been injected by this point");
        Preconditions.checkArgument(input != null, "the input cannot be null");

        return hasMatch(jobAccountingNotFoundRegexp, input);
    }

    /**
     * Look for the "job not found" message in the output from the status command (qstat)
     * @param input The input to scan
     * @return true if there is a match, false otherwise.
     */
    public boolean matchStatusJobNotFound(String input) {
        Preconditions.checkArgument(jobStatusNotFoundRegexp != null, "jobs not found regexp for status command must have been injected by this point");
        Preconditions.checkArgument(input != null, "the input cannot be null");

        return hasMatch(jobStatusNotFoundRegexp, input);
    }

    /**
     * Look for the "job not found" message in the output from the delete command (qdel)
     * @param input The input to scan
     * @return true if there is a match, false otherwise.
     */
    public boolean matchDeleteJobNotFound(String input) {
        Preconditions.checkArgument(jobDeleteNotFoundRegexp != null, "jobs not found regexp for delete command must have been injected by this point");
        Preconditions.checkArgument(input != null, "the input cannot be null");

        return hasMatch(jobDeleteNotFoundRegexp, input);
    }

    /**
     * Look through the output of the job deletion command.
     * 
     * @param input a line of input from the job status command
     * @return array of matches, possibly null if there is no match
     * @deprecated I am not sure what this function is supposed to do since there is little output from qdel
     */
    public String[] matchJobDeletedOutput(String input) {
        Preconditions.checkArgument(jobDeletionRegexp != null, "jobs deletion regexp must have been injected by this point");
        Preconditions.checkArgument(input != null, "the input cannot be null");

        return match(jobDeletionRegexp, input);
    }

    /**
     * Our input is the output of the job accounting command (i.e. qacct), which we search for pairs of things.  The input
     * will be something like this:<p>
     * qname        all.q<br>
     * hostname     navtst-nonmemc.mango.local<br>
     * group        Domain<br>
     * owner        mrogalski<br>
     * project      NONE<br>
     * department   defaultdepartment<br>
     * jobname      test.sh<br>
     * jobnumber    6607<br>
     * taskid       undefined<p>
     * Note that with this input, there are no colon characters after the first word on each line.
     * 
     * @param input the input from the job accounting (i.e. qacct) command - all of it, newlines, everything
     * @param output the sge output, or most important of all, the map the sge output object holds
     */
    public void matchJobAccountingOutput(String input, SGEOutput output) {
        Preconditions.checkArgument(jobAccountingRegexp != null, "jobs accounting regexp must have been injected by this point");
        Preconditions.checkArgument(input != null, "the input cannot be null");

        scan(Pattern.compile(jobAccountingRegexp), input, output);
    }

    /**
     * Our input is the output of the job status command (in our case qstat), which we search for pairs of things.  The input
     * will be something like this:<p>
     * sge_o_shell:                /bin/bash<br>
     * sge_o_workdir:              /home/tbamford<br>
     * sge_o_host:                 navws-tb<br>
     * account:                    sge<br>
     * mail_list:                  tbamford@navws-tb.mango.local<p>
     * Note that with this input, there ARE colon characters after the first word on each line.
     * 
     * @param input a line of input from the job status command
     * @param output the sge output, or most important of all, the map the sge output object holds
     */
    public void matchJobStatusOutput(String input, SGEOutput output) {
        Preconditions.checkArgument(jobStatusRegexp != null, "jobs status regexp must have been injected by this point");
        Preconditions.checkArgument(input != null, "the input cannot be null");

        scan(Pattern.compile(jobStatusRegexp), input, output);
    }

    //=========================================================================
    //=========================================================================
    //=========================================================================

    /**
     * Scan each line of the input, using the pattern specified, placing the results in the sge output object.
     * @param pattern The regexp
     * @param input The input, consisting of multiple lines
     * @param output The output, the map being the important part.
     */
    private void scan(Pattern pattern, String input, SGEOutput output) {

        Scanner scanner = new Scanner(input).useDelimiter("\n");
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                output.put(matcher.group(1), matcher.group(2));
            } else {
                LOG.debug("NO MATCH \"" + line + "\"");
            }
        }
        scanner.close();
    }

    /**
     * Do we have a match?
     * @param regexp the regexp
     * @param input the input
     * @return true if we do, false otherwise
     */
    private boolean hasMatch(String regexp, String input) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);

        return matcher.find();
    }

    /**
     * Match a regexp against some input and return an array of the matches.  Note that in the returned results,
     * the element at position zero contains the group zero, i.e. the entire matched string.  If you want the
     * value of group 1, you have to look at position 1.
     *
     * @param regexp the regexp to look for
     * @param input the input to search in
     * @return array of matches, zero being group zero, one being group one, etc.
     */
    private String[] match(String regexp, String input) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);

        if(!matcher.matches()) {
            return null;
        }

        // The pattern matches, there are no groups, therefore we must have matched the whole input string.
        if(matcher.groupCount() == 0) {
            return new String[] { input };
        }
        String[] result = new String[matcher.groupCount() + 1];
        for (int i = 0; i <= matcher.groupCount(); i++) {
            result[i] = matcher.group(i);
        }

        return result;
    }

    /**
     * @param line the line of input to test
     * @return true if the line consists soley of the "=" repeated over and over
     */
    private boolean isJustEqualsSigns(String line) {
        Preconditions.checkArgument(line != null, "the line cannot be null");
        boolean result = true;

        for (int i = 0; i < line.length() && result; i++) {
            if (line.charAt(i) != '=') {
                result = false;
            }
        }
        return result;
    }
}
