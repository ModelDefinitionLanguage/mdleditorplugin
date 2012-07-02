# Exports single pdf document to "Reports" folder
# Should run and create graphics "output1.png" to "output9.png" in "Reports" folder

myDf <- read.csv("../Data/pkData.csv")

write.csv(myDf, "../Data/newData.csv", row.names = F)

png("../Reports/output%0d.png")

for (i in 1:9) {
	hist(rnorm(100), col = sample(colors(), 1), main = "Plot")
}

dev.off()
