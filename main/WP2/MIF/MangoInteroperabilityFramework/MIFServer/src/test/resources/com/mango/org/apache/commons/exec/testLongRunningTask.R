tmp <- read.csv("data.csv", header = TRUE)
tmp['COL_A'] = 12

for(i in c(1:1000)) {
for(j in c(1:1000)) {
print (i+j);
}
}


write.csv(tmp,file = "result-testLongRunningTask.csv", row.names=FALSE)