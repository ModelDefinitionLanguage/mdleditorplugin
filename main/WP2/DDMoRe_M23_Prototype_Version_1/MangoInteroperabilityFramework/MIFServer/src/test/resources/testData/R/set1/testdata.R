# SVN revision: $Rev: $
# Date of last change: $LastChangedDate: $
# Last changed by: $LastChangedBy: $
# 
# Original author: ccampbell
# Copyright Mango Solutions, Chippenham, UK
###############################################################################


#' Create NCA datasets using two compartment model
#' 
#' A simple Conc-Time dataset is created with a different levels of noise or data corruption.
#' If good = TRUE there is limited noisiness, otherwise the data are very noisy.
#' First a seed is created that can be used to make data creation reproducible.
#' Next, a series of time courses and PK parameters are generated.
#' Each Subject is uniformly assigned into maxCycle CYCLES.
#' Then Conc values are generated based on a two compartment model.
#' Finally noise is added.
#' 
#' @param NR integer number of rows.
#' @param seednum NULL if not used, or integer to set seed
#' @param writeFiles logical should files be written
#' @param maxCycle
#' @param good logical should data be solvable or horrid
#' @param silent logical should dataframe be returned to global environment
#' @return NULL or dataframe
#' @export
#' @author Mango Solutions
#' @keywords math
#' @examples
#' generateData(NR = 3, seednum = 1434131, writeFiles = FALSE, maxCycle = 2, silent = FALSE)


generateData <- function(NR = 500, seednum = NULL, writeFiles = TRUE, maxCycle = 5, good = TRUE, silent = TRUE)
    {
    # "random" numbers are calculated based on a seed number. 
    # The seed is reset based on each function call. 
    # Starting at a single seed should always produce the same random numbers

    .deriveFromMasterSeed <- function() {
        op <- options(digits.secs = 6)
        seedStr <- as.character(Sys.time())
        seedChar <- strsplit(seedStr, "\\.")
        seed <- as.numeric(seedChar[[1]][2])
        return(seed)
    }
    
    if(is.null(seednum[1])) { seednum <- NA }
    if( !is.numeric(seednum[1]) | is.na(seednum[1])) { 
        seednum <- .deriveFromMasterSeed()
    }
    
    set.seed(seednum[1])    


    ### Features of data

    # experimental design, missing values, etc.
    # cmax can be anywhere
    # numNAs can be 0 to all
    # numZero can be 0 to all
    # numMiscode can be 0 to all
    # gradient positive, negative, zero, infinite
    # data order

    # to compare precision handling provide datasets with a number of decimal places
    # precision in data 
    decPlaces <- c(0, 2, 5, 10)

    ###


    ### Description of Subject Moments

    ### Times

    # first time recorded in subject moment
    # non-zero values may be caused by missing values, preinfusion times being present, etc.
    if(good) { 
        startTime <- 0
        startVals <- rep(0, NR)
        timeMethods <- 1:5
    }
    else {
        startTime <- c(-50, -2, rep(0, 5), 2, 5, 10, 500, 25000)
        startVals <- (sample(startTime, NR, TRUE) + rpois(NR, 2)) * 
          sample(c((-3:4) / 7, 0, pi, 2^0.5, exp(1)), NR, TRUE)
        timeMethods <- 1:10
    }
    # Conc measurements will be made at a number of timepoints
    # this function chooses a distribution for the time points
    # select time progression
    
    
    .generateTimes <- function(t0, n) {
      nm <- n - 1
      switch(sample(timeMethods, 1), 
        t0 + (0:nm) * (sample(1:400, 1) / 17),          # 1 e.g. 0.00000 11.23529 22.47059 33.70588 even space, non integer
        t0 + (0:nm) * (sample(151:178, 1) / 13) +
          c(0, sample(0:9, nm, replace = TRUE)),        # 2 e.g. 0.00000 12.07692 29.15385 37.23077 irregular spacing, non integer
        t0 + (0:nm)^2,                                  # 3 e.g. 0 1 4 9 increasing space, integer
        t0 + (0:nm)+ c(0, rep(sample(2:500, 1), nm)),   # 4 e.g. 0 344 345 346 irregular spacing
        t0 + (0:nm) + c(rep(0, (n - 1)), sample((1:50)*99, 1)), # 5 e.g. 0    1    2 3567 irregular spacing
        t0 + (0:nm) + sample(-2:19, n, replace = TRUE), # 6 e.g. 12 11 21  8 irregular spacing, poss out of sequence
        t0 + (0:nm) * exp(rnorm(n)),                    # 7 e.g. 0.0000000 0.1973239 1.0981791 0.2557382 irregular spacing, poss out of seq
        t0 + (0:nm) * exp(rnorm(n, 1, 1:n)),            # 8 e.g. 0.000000   2.691703 188.549617 123.072186 irregular spacing, poss out of seq
        t0 + (0:nm) * rnorm(n),                         # 9 e.g. 0.000000  0.138082 -1.166663  3.173267 out of sequence, non integer
        t0 + (0:nm) + rpois(n, 7)                       # 10 e.g. 7 11  8 11 out of sequence, integer
      )
    }

    ###

    ### Conc

    # a function that takes time and some parameters to estimate Conc
    # based on a simple model

    # Two Compartment first order absorption

    .pkfun <- function(D, ka, k12, k21, kel, t, V) { 
      A <- (kel + k12 + k21)
      B <- 4 * kel * k21
      alpha <- (A + (A^2 - B)^(1/2)) / 2
      beta  <- (A - (A^2 - B)^(1/2)) / 2
      conc  <- D * ka * ( 
        (k21 - alpha) * (exp(-alpha * t) - exp(-ka * t)) /
          ((ka - alpha) * (beta - alpha)) +
        (k21 - beta) * (exp(-beta * t) - exp(-ka * t)) / 
          ((ka - beta) * (alpha - beta)) 
        ) / V
      return(conc)
    }

    ## <= uncomment next section and plot to see what this looks like

    # typical values
    #Time <- (0:72)
    #D <- 20                # dose
    #V1 <- 29 # 20          # volume of distribution in central compartment
    #
    #ka <- 0.09 # 0.5
    #k12 <- 0.001           # rate constant of transfer to peripheral compartment
    #k21 <- 0.02            # rate constant of transfer to central compartment
    #kel <- 0.008 # 0.05
    #
    #Conc <- TwoCompartment(D = D * 1000, ka = ka, k12 = k12, k21 = k21, 
    #  kel = kel, t = Time, V = V1)
    #plot(Conc ~ Time, type = "l", lwd = 2, col = "red")

    ## <= stop plotting here

    ### 

    ### add noise

    # no noise
    # low noise
    # noise > signal
    # constant cv
    # log

    # a function to add noise to the Conc values

    .generateNoise <- function(conc) {
      n <- length(conc)
      switch(sample(1:5, 1), 
        conc,                                 # 1 e.g. 1 2 3 no error
        conc + rnorm(n, 0, max(conc/1000)),   # 2 e.g. 0.9975734 1.9951054 3.0055641 additive error
        conc + rnorm(n, 0, max(conc)),        # 3 e.g. -1.575806  2.059615  3.578461 large additive error
        conc * exp(rnorm(n, 0, conc/9)),      # 4 e.g. 0.9325237 2.0120667 3.3561892 log normal error
        conc * (1 + rnorm(n, 0, conc/7))      # 5 e.g. 0.6760027 1.3392824 2.5148047 constant coefficient of variation
      )    
    }

    ###

    ### Generate Data

    # NR combinations of subject moments 

    # create a dataframe with one column
    # a value, sometimes zero ~20%, otherwise pos, neg, integer, etc.
    combin <- data.frame(startTime = startVals)

    # number of rows in dataset
    # generated by rounding log-normal distribution
    combin$nr <- round(exp(rnorm(NR, 1.8, 2))) + 1
    
    # assign into maxCycle CYCLES
    combin$cycle <- sample(1:maxCycle, size = NR, replace = TRUE)

    # choose precision of subject moment conc data
    combin$dp <- sample(decPlaces, NR, replace = TRUE)

    # input parameters for subject concentration calculations
    
        combin$ka <- 0.09 * exp(rnorm(NR, 0, 0.01)) # e.g. 0.09069502 0.08972235 0.09030533
        combin$k12 <- 0.001 * exp(rnorm(NR, 0, 0.001)) # e.g. 0.0009999825 0.0010006264 0.0009999472
        combin$k21 <- 0.02 * exp(rnorm(NR, 0.01)) # e.g. 0.01085345 0.02309880 0.05614757
        combin$kel <- 0.008 * exp(rnorm(NR, 0.01)) # e.g. 0.019912145 0.003666268 0.009462710    
        
    if(good) {
        combin$dose <- 10^sample(-1:5, NR, TRUE) * sample(1:207, NR, TRUE) / sample(1:11, NR, TRUE) # e.g. 600.000   0.625  17.625
        combin$volume <- 29 * exp(rnorm(NR, 0, 0.1)) # e.g. 27.92926 24.90164 30.80136

    }
    else {
        combin$dose <- 10^sample(-1:5, NR, TRUE) * sample(-1:207, NR, TRUE) / sample(1:11, NR, TRUE) # e.g. 600.000   0.625  17.625
        combin$volume <- 29 * exp(rnorm(NR)) # e.g. 28.360083 71.927621  6.615087
    }

    # output dataset
    NDR <- sum(combin$nr)
    dataOut <- data.frame(SUBJ = rep(NA, NDR), TIME = NA, CONC = NA, PT = 0, DOSE = 0, INFDUR = 1, CYCLE = 0)
    j <- 1

    # a loop to calculate Conc for each subject moment using parameters defined above
    # first Time is generated
    # then Conc is calculated
    # then noise and precision are added
    for (i in 1:NR) {
      N <- combin[i, "nr"]
      time <- .generateTimes(t0 = combin[i, "startTime"], n = N)
      conc <- .pkfun(D = combin[i, "dose"], 
          ka = combin[i, "ka"], 
          k12 = combin[i, "k12"], 
          k21 = combin[i, "k21"], 
          kel = combin[i, "kel"], 
          t = time, 
          V = combin[i, "volume"]
        )
      concn <- .generateNoise(conc)
      concr <- round(concn, combin[i, "dp"])
      
      dataOut$SUBJ[j:(j + N - 1)] <- i + 1000
      dataOut$TIME[j:(j + N - 1)] <- time
      dataOut$CONC[j:(j + N - 1)] <- concr
      dataOut$DOSE[j:(j + N - 1)] <- combin[i, "dose"]
      dataOut$CYCLE[j:(j + N - 1)] <- combin[i, "cycle"]
      
      j <- j + N
    }

    # add peak/trough coding

    dataOut$PT[dataOut$TIME == 0] <- 1
    dataOut$PT[dataOut$TIME == 2] <- 2

    # not all combinations of parameters will lead to calculable Conc values (~10%)
    # replace NAs in CONC
    
    if(good) {
        yyInd <- is.na(dataOut$CONC) | dataOut$CONC == Inf | dataOut$CONC < 0
        yy <- sum(yyInd)
        repVec <- rep(0, yy)
        for(i in 1:yy) repVec[i] <- "."
        dataOut$CONC[yyInd] <- repVec
        cat(yy, "NA and Inf values replaced in CONC\n")
        
        # replace NAs in TIME, cycling through ".", NA, and ""
        xxInd <- is.na(dataOut$TIME) | dataOut$TIME == Inf | dataOut$TIME < 0
        xx <- sum(xxInd)
        repVec <- rep(0, xx)
        for(i in 1:xx) repVec[i] <- "."
        dataOut$TIME[xxInd] <- repVec
        cat(xx, "NA and Inf values replaced in TIME\n")
    }
    else {
        yyInd <- is.na(dataOut$CONC) | dataOut$CONC == Inf | dataOut$CONC == -Inf
        yy <- sum(yyInd)
        repVec <- rep(0, yy)
        for(i in 1:yy) repVec[i] <- switch(sample(1:7, 1), 0, sample(-100:1000, 1), ".", NA, "", " ", "  ")
        dataOut$CONC[yyInd] <- repVec
        cat(yy, "NA and Inf values replaced in CONC\n")
        
        # replace NAs in TIME, cycling through ".", NA, and ""
        xxInd <- is.na(dataOut$TIME) | dataOut$TIME == Inf | dataOut$TIME == -Inf
        xx <- sum(xxInd)
        repVec <- rep(0, xx)
        for(i in 1:xx) repVec[i] <- switch(sample(1:7, 1), 0, sample(-100:1000, 1), ".", NA, "", " ", "  ")
        dataOut$TIME[xxInd] <- repVec
        cat(xx, "NA and Inf values replaced in TIME\n")
        
        # for testing, miscoded values should be considered
        # add miscoded values
        mis <- expand.grid(LETTERS, letters, 1:20)
        misco <- apply(mis, 1, paste, collapse = "")
        miscode <- c(letters, misco[sample(1:length(misco), 100)], rep(c(" .", ". ", " BLQ", "BLQ ", "bLQ"), 20))

        dataOut$TIME[sample(1:NDR, 3)] <- sample(misco, 3)
        dataOut$CONC[sample(1:NDR, 50)] <- sample(miscode, 50)
        dataOut$PT[sample(1:NDR, 50)] <- sample(-2:3, 50, replace = TRUE)
        dataOut$PT[sample(1:NDR, 5)] <- sample(misco, 5)
    }
    # create files
    # patient info
    
    if(writeFiles) {
        write.csv(combin, paste("Patient_Data_", seednum, ".csv", sep = ""), row.names = FALSE, quote = FALSE)
        cat("write.csv(combin, ", paste("Patient_Data_", seednum, ".csv", sep = ""), ", row.names = FALSE, quote = FALSE)\n")
        # test data
        if(good) { dataName <- "Test_Data_" }
        else { dataName <- "Extreme_Test_Data_" }
        write.csv(dataOut, paste(dataName, seednum, ".csv", sep = ""), row.names = FALSE, quote = FALSE)
        cat("write.csv(dataOut, ", paste(dataName, seednum, ".csv", sep = ""), ", row.names = FALSE, quote = FALSE)\n")
    }
    if( !silent ) { return(dataOut) }
    else { return(invisible(dataOut)) }
}

cat("# copy and paste this code to see what the output is, then edit
# values to create required number of subjects (NR) and number of cycles
# (maxCycle) then set writeFiles = TRUE to create CSV.\n
generateData(NR = 3, writeFiles = FALSE, maxCycle = 2, silent = FALSE)\n")
generateData(NR = 3, writeFiles = TRUE, maxCycle = 2, silent = FALSE);