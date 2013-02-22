require(lattice)

# test data
cow.cast <- data.frame(TDS = rnorm(20, 2)/10, 
  Mg = rnorm(20, 1)/100, Ca = rnorm(20, 1)/200,
  site = rep(1:4, each = 5))

turkey.cast <- data.frame(TDS = rnorm(21, 2)/10, 
  Mg = rnorm(21, 1)/200, Ca = rnorm(21, 1)/200,
  site = rep(1:3, each = 7))

# item descriptions
objNames <- c("cow.cast", "turkey.cast")
objLabels <- list(main = c('Cow Canyon Creek', 'Turkey Creek'), 
  ylab = 'Total Dissolved Solids (mg/L)', 
  xlab = c('Magnesium (mg/L)', 'Calcium (mg/L)'))

# panel function
cow.panel <- function(...) {
  panel.xyplot(...)
  panel.abline(coef = coef(sublm))
}

# loop for each object
for (i in 1:length(objNames)) {
# get function finds the object that matches the string
  thisdf <- get(objNames[i])
  colNames <- names(thisdf)
  colNum <- length(colNames)
  # nested loop
  for (j in 1:(colNum-2)) {
    # fit lm to subset of data
    sublm <- lm(TDS~get(colNames[j+1]), thisdf)
    #create plot
    subplot <- xyplot(TDS ~ get(colNames[j+1]) | factor(get(colNames[colNum])), 
      data = thisdf, 
      main = paste(objLabels$main[i], ": TDS vs.", 
        substr(objLabels$xlab[j], 1, (nchar(objLabels$xlab[j]) - 7))),
      ylab = objLabels$ylab, xlab = objLabels$xlab[j], 
      panel = cow.panel)
    # create file
    pdf(paste(substr(objNames[i], 1, 3), "-TDS-", colNames[j+1],".pdf", sep = ""))
    print(subplot)
    dev.off()
  }
}

