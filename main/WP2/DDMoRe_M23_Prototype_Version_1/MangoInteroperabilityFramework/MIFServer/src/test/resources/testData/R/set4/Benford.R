
######################################################################

### Ten Benford - a tale of leading digits

######################################################################


### Random Numbers

x <- runif(1e6) * 1e7 + 1

hist(x, xlab = "Amount (£)")

firstDigit <- as.numeric(substr(x, 1, 1))

cols9 <- hsv(seq(0.1, 0.12, length = 9), s = seq(0.8, 0.1, length = 9), 
  v = seq(0.9, 0.6, length = 9))

png("ben_hist_unif.png", height = 1000, width = 1000, res = 200)
par(mar = c(5, 4, 1, 1) + 0.1)
hist(firstDigit, breaks = 0:9, 
  xlim = c(0, 9), col = cols9, 
  main = "", xlab = "First Digit")
dev.off()

table(firstDigit) / 1e6


######################################################################


### Fibonacci Series

fibfun <- function(n) { 
  x <- rep(1, n)
  for(i in 3:n) x[i] <- x[i - 1] + x[i - 2]
  return(x)
}

x <- fibfun(1e3)
firstDigit <- as.numeric(substr(x, 1, 1))

png("ben_hist_fib.png", height = 1000, width = 1000, res = 200)
par(mar = c(5, 4, 1, 1) + 0.1)
hist(firstDigit, breaks = 0:9, 
  xlim = c(0, 9), col = cols9, 
  main = "", xlab = "First Digit")
dev.off()

table(firstDigit) / 1e3

### Binet's Fibonacci Number Formula

fibbinet <- function(n) {
  nn <- seq(1, n, by = 1)
  # phi <- (1 + 5^(1/2))/2
  # (phi^n - (-phi)^(-n))/5^(1/2)
  x <- ((1 + 5^(1/2))^nn - (1 - 5^(1/2))^nn)/(2^nn * 5^(1/2))
  return(x)
}

z2 <- fibbinet(1e2)

######################################################################


fin <- 1000
out <- matrix(0, nrow = fin, ncol = 9)
out[1:2, 1] <- 1
#cols <- rainbow(fin)
cols <- hsv(seq(0.1, 0.12, length = fin), s = seq(0.5, 0, length = fin), 
  v = seq(0.9, 0.4, length = fin))



png("benford_fibonacci.png", height = 1000, width = 1000, res = 200)
par(mar = c(3, 4, 1, 1) + 0.1, xaxp = c(1, 9, 7))
plot(1:9, rep(0.5, 9), ylim = c(0, 1), type = "n", 
  xlab = "", ylab = "Fraction")


for(num in 3:fin) {
  z <- fibfun(num)
  firstDigit <- factor(substr(z, 1, 1), levels = 1:9)
  fdtab <- table(firstDigit)
  out[num, ] <- fdtab/num

  distrib <- apply(out[1:num, ], 2, mean)
  lines(1:9, distrib, col = cols[num])

}

dev.off()

######################################################################

### power laws

x <- exp(runif(1e6) * 20 + 1)

#hist(x, xlab = "Amount (£)")

firstDigit <- as.numeric(substr(x, 1, 1))

cols9 <- hsv(seq(0.1, 0.12, length = 9), s = seq(0.8, 0.1, length = 9), 
  v = seq(0.9, 0.6, length = 9))

png("ben_hist_expunif.png", height = 1000, width = 1000, res = 200)
par(mar = c(5, 4, 1, 1) + 0.1)
hist(firstDigit, breaks = 0:9, 
  xlim = c(0, 9), col = cols9, 
  main = "", xlab = "First Digit")
dev.off()
table(firstDigit) / 1e8



######################################################################

# http://www.badscience.net/
# Benford figures
# EU financial data - evidence of Greece fraud
# http://epp.eurostat.ec.europa.eu/portal/page/portal/esa95_supply_use_input_tables/data/workbooks

######################################################################

#require(gdata)
#ukpath <- "C:\\Users\\ccampbell\\Documents\\Training\\benford\\United Kingdom_SUIOT_111111.xls"

#yr <- 95:99
#i = 1
#choosesheet <- paste("use", yr[i], sep = "")

#ukdata <- read.xls(ukpath, sheet = choosesheet,
#      na.string = c(".", "NA", "#VALUE!"), as.is = TRUE)

ukdata <- read.table("clipboard", sep = "\t")

ukdata1 <- unlist(ukdata)

ufirst <- as.numeric(substr(ukdata1, 1, 1))

cols9 <- hsv(seq(0.1, 0.12, length = 9), s = seq(0.8, 0.1, length = 9), 
  v = seq(0.9, 0.6, length = 9))

png("ben_hist_uk.png", height = 1000, width = 1000, res = 200)
par(mar = c(5, 4, 1, 1) + 0.1)
hist(ufirst, breaks = 0:9, 
  xlim = c(0, 9), col = cols9, 
  main = "", xlab = "First Digit")
dev.off()

uk <- table(ufirst) / length(ufirst)

######################################################################

dldata <- read.table("clipboard", sep = "\t")

dldata1 <- unlist(dldata)

dfirst <- as.numeric(substr(dldata1, 1, 1))

#cols9 <- hsv(seq(0.1, 0.12, length = 9), s = seq(0.8, 0.1, length = 9), 
#  v = seq(0.9, 0.6, length = 9))

png("ben_hist_dl.png", height = 1000, width = 1000, res = 200)
par(mar = c(5, 4, 1, 1) + 0.1)
hist(dfirst, breaks = 0:9, 
  xlim = c(0, 9), col = cols9, 
  main = "", xlab = "First Digit")
dev.off()
dl <- table(dfirst) / length(dfirst)

######################################################################

grdata <- read.table("clipboard", sep = "\t")

grdata1 <- unlist(grdata)

gfirst <- as.numeric(substr(grdata1, 1, 1))

#cols9 <- hsv(seq(0.1, 0.12, length = 9), s = seq(0.8, 0.1, length = 9), 
#  v = seq(0.9, 0.6, length = 9))

png("ben_hist_gr.png", height = 1000, width = 1000, res = 200)
par(mar = c(5, 4, 1, 1) + 0.1)
hist(gfirst, breaks = 0:9, 
  xlim = c(0, 9), col = cols9, 
  main = "", xlab = "First Digit")
dev.off()
gr <- table(gfirst) / length(gfirst)


######################################################################

benford <- c(0.301, 0.176, 0.125, 0.097, 0.079,
             0.067, 0.058, 0.051, 0.046)

outuk <- ks.test(uk, benford)
outdl <- ks.test(dl, benford)
outgr <- ks.test(gr, benford)




