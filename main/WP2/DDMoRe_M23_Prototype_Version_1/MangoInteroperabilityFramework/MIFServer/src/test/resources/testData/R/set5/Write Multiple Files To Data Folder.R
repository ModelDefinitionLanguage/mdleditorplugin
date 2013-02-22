# Exports multiple files to Data folder
# Should run and create datasets "ExportData1.csv" to "ExportData99.csv" in "Data" folder

myDf <- read.csv("./pkData.csv")

for (i in 1:99) {
	myFile <- paste("./ExportData", i, ".csv", sep="")
	write.csv(myDf[sample(1:nrow(myDf), 10),], myFile, row.names = F)
}
