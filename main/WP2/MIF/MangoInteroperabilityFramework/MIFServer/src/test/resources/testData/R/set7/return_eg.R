
testfun <- function(x, y) {
  df <- data.frame(X = x, Y = y)
  out <- vector(mode = "list", length = 2)
  out[[1]] <- lm(Y~X, data = df)
  out2 <- try(.getlm(df))
  if(is( out2, "try-error" )) return(out)
  out[[2]] <- out2
  return(out)
}

.getlm <- function(df) {
  lmFit <- lm(Y~X, data = df)
  print(lmFit)
  return(lmFit)
}

x = 0:10
y = runif(11)
testfun(x = x, y = y)