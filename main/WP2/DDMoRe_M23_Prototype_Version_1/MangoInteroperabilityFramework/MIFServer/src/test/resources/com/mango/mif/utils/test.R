tmp <- read.csv("data.csv", header = TRUE)
tmp['COL_A'] = 12
write.csv(tmp,file = "result-test.csv", row.names=FALSE)