
patrn <- c(1,2,3,4)
exmpl <- c(3,3,4,2,3,1,2,3,4,8,8,23,1,2,3,4,4,34,4,3,2,1,1,2,3,4)

# find the occurence of the pattern returning the starting indices:
# 6, 13, 23


patrn.rev <- rev(patrn)
w <- embed(exmpl, dimension = length(patrn))
w.pos <- apply(w, 1, function(r) all(r == patrn.rev))
which(w.pos)

