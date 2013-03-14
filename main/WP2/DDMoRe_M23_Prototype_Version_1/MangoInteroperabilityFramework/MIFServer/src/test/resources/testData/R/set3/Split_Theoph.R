Theo1 <- Theoph[!Theoph$Subj %in% c(1, 5, 9), ]
Theo1 <- Theo1[, c(1, 3, 4, 5, 2)]
Theo1$conc <- round(Theo1$conc * pi, 2)
Theo1$conc[c(6, 36, 72)] <- NA
Theo1$Time <- round(Theo1$Time * exp(1), 2)
Theo1$Subject <- rep(1:9, each = 11)
Theo1$Dose <- Theo1$Dose * 3 - 1
d1 <- Theo1[Theo1$Time == 0, ]
d1$conc <- NA
Theo1$Dose <- NA
Theo1$Wt <- NA
Theo1 <- rbind(d1, Theo1)
Theo1 <- Theo1[order(Theo1$Subject, Theo1$Time), ]
Theo1[is.na(Theo1)] <- "."
Theo1$Subject <- sprintf("%9s", Theo1$Subject)
Theo1$Dose <- sprintf("%9s", Theo1$Dose)
Theo1$Time <- sprintf("%9s", Theo1$Time)
Theo1$conc <- sprintf("%9s", Theo1$conc)
Theo1$Wt <- sprintf("%9s", Theo1$Wt)
write.table(Theo1, "HedgehogAntag.dat",
  quote = FALSE, row.names = FALSE, col.names = FALSE)



Theo2 <- Theoph[!Theoph$Subj %in% c(7, 10, 12), ]
Theo2 <- Theo2[, c(1, 3, 4, 5, 2)]
Theo2$conc <- round(Theo2$conc * 2^(1/2), 2)
Theo2$conc[c(13, 41, 47)] <- NA
Theo2$Time <- round(Theo2$Time * 3^(1/2), 2)
Theo2$Subject <- rep(1:9, each = 11)
Theo2$Dose <- Theo2$Dose * 2
d2 <- Theo2[Theo2$Time == 0, ]
d2$conc <- NA
Theo2$Dose <- NA
Theo2$Wt <- NA
Theo2 <- rbind(d2, Theo2)
Theo2 <- Theo2[order(Theo2$Subject, Theo2$Time), ]
Theo2[is.na(Theo2)] <- "."
Theo2$Subject <- sprintf("%9s", Theo2$Subject)
Theo2$Dose <- sprintf("%9s", Theo2$Dose)
Theo2$Time <- sprintf("%9s", Theo2$Time)
Theo2$conc <- sprintf("%9s", Theo2$conc)
Theo2$Wt <- sprintf("%9s", Theo2$Wt)
write.table(Theo2, "LY2523355.dat",
  quote = FALSE, row.names = FALSE, col.names = FALSE)



