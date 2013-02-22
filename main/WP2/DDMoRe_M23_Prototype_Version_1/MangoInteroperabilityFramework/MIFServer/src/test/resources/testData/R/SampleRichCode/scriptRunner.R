
# Set Working Directory
#setwd("C:/test1/SampleRichCode")

# Wrapped Script Runner
executeEnv <- new.env()

# Set initial environment in which to run script
with(executeEnv, {

	# Accept name of Script to run
	scriptName <- "Scripts/Write Multiple Graphics to Report Folder.R"
	#scriptName <- "Scripts/Error Script 1 - Intentional Error Thrown.R"

	# Get current working directory
	currDir <- getwd()

	# Set up execution directory name
	execDir <- "_Output"
	if (file.exists(execDir)) unlink(execDir, recursive = T, force = T)
	dir.create(execDir)

	# Set paths to output + error files
	stderrFile.name <- file.path( execDir, "script.R.err" ) 
	stdoutFile.name <- file.path( execDir, "script.R.out" ) 
	file.create(stderrFile.name, stdoutFile.name)

	# Get list of files with current time/date stamps
	currentFiles <- list.files(recursive = T)
	allFilesThen <- file.info(currentFiles)$mtime
	names(allFilesThen) <- currentFiles

	# Set up standard error and standard output of the user code
	stderrFile <- file( stderrFile.name , open = "w" )
	stdoutFile <- file( stdoutFile.name , open = "w" )
	sink( stdoutFile)                   # standard output
	sink( stderrFile, type = "message") # standard error

	# Close any devices
	graphics.off()

	# Set working directory to the script directory
	setwd(dirname(scriptName))

})

# Call the Script
.execOutcome <- try(source(basename(get("scriptName", executeEnv)), echo = T))
.execOutcome <- if (class(.execOutcome) == "try-error") "Failed" else "Success"

# Wrap up following execution of the script
with(executeEnv, {

	# Set directory back to the root directory
	setwd(currDir)

	# Find new files
	currentFiles <- list.files(recursive = T)
	allFilesNow <- file.info(currentFiles)$mtime
	names(allFilesNow) <- currentFiles

	# Look for new files
	newFiles <- if (length(allFilesNow) > length(allFilesThen)) {
		newFiles <- setdiff(names(allFilesNow), names(allFilesThen))
	} else character(0)

	# Updated files
	bothThere <- intersect(names(allFilesThen), names(allFilesNow))
	isupdated <- allFilesThen[bothThere] != allFilesNow[bothThere]
	isupdated <- bothThere[isupdated]

	# Report back the new or updated files
	if (length(newFiles)) newFiles <- newFiles [ dirname(newFiles) != "_Output" ]
	if (length(isupdated)) isupdated <- isupdated [ dirname(isupdated) != "_Output" ]

	cat(paste("NEWFILES: ", paste(newFiles, collapse=","), "\n", sep=""), file = file.path(execDir, "outputfiles.txt"))
	cat(paste("UPDATEDFILES: ", paste(isupdated, collapse=","), "\n", sep=""), file = file.path(execDir, "outputfiles.txt"), append = TRUE)
	cat(paste("OUTCOME: ", .execOutcome, "\n", sep=""), file = file.path(execDir, "outputfiles.txt"), append = TRUE)

	# Close of sinking to error/output files
	sink()                   # standard output
	sink(type = "message")   # standard error		
	
	graphics.off()
	close( stderrFile )
	close( stdoutFile )
	closeAllConnections()
})

rm(list = objects())


