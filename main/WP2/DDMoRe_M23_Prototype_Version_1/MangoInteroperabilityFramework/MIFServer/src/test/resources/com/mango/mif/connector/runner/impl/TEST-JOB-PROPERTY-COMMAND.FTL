/bin/ls -l ${job.jobId}

${job.data.get("TEST-PROPERTY")}

${job.executionRequest.requestId}

${job.getRequestOutputDirectory()}
