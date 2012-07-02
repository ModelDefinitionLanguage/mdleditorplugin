# Bayesian statistics

# Compute beta values for the beta distribution

# E(theta) = a / (a + b) = 0.5
# Var(theta) = a * b / ((a + b)^2 * (a + b + 1)) = 0.05

# Solve this non-linear system to find a, b for the beta distribution when a = 2 and b = 2


library(nleqslv)

eqf <- function(x) {
    a <- x[1]
    b <- x[2]
    y <- numeric(2)   
    y[1] <- a / (a + b) - 0.5
    y[2] <- a * b /((a + b)^2 * (a + b + 1)) - 0.05
    y
}
       
x.start <- c(1, 1)

nleqslv(x.start, eqf)


# Alternatively

library(Ryacas)

a <- Sym("a")
b <- Sym("b")
m <- Sym("m")
v <- Sym("v")

Solve( List(a / (a + b) == m, a * b / ((a + b)^2 * (a + b + 1)) == v), List(a, b) )
expression(list(list(a == m^2 * (1 - m) / v - m, b == a / m - a)))


beta.parms <- function(m, v) {
   a <- m^2 * (1 - m) / v - m
   b <- a / m - a
   c(a = a, b = b)
}

beta.parms(m = 0.5, v = 0.05)
# a b
# 2 2

