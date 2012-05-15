### Spatial Selection

# I am attempting to select all of the contiguous elements of a matrix that meet some criterion. 
# I.e., values that would be contained within an irregular area defined by a "contour" applied around point of interest. 
# So, if I have a matrix x as follows:

set.seed(2814268)
x <- matrix(rnorm(25), nrow=5, ncol=5,
         dimnames=list(c("A","B","C","D","E"), c("v","w","x","y","z")))

x
#           v          w          x         y          z
#A  0.5184795  1.9641285  0.8632044 1.5010397  0.8468490 
#B -1.2402866  0.5211307 -0.1474351 1.3264893  0.1087390 
#C  0.5910275 -1.1708906  0.9440755 1.0970971 -0.2784806 
#D  0.6377495  1.1594035 -0.4217621 1.4021680 -0.6487677 
#E -1.4590833  0.2065765  0.1623669 1.3598283  0.3742821


# how can I select all values in the "pond" of contiguous matrix entries that have values, say <0.6, if my entry of interest is x["A","v"]. 
# In that case, I would like to select the following starred entries:

#           v          w          x         y          z
#A  0.5184795*  1.9641285  0.8632044 1.5010397  0.8468490 
#B -1.2402866*  0.5211307* -0.1474351* 1.3264893  0.1087390 
#C  0.5910275* -1.1708906*  0.9440755 1.0970971 -0.2784806 
#D  0.6377495  1.1594035 -0.4217621* 1.4021680 -0.6487677 
#E -1.4590833  0.2065765*  0.1623669* 1.3598283  0.3742821

# But I would _not_ like to select any of the values in x[,"z"], because, even though they may be <0.6, they are not contiguous with the pond that x["A","v"] is in.


.deriveFromMasterSeed <- function() {
  op <- options(digits.secs = 6)
  seedStr <- as.character(Sys.time())
  seedChar <- strsplit(seedStr, "\\.")
  seed <- as.numeric(seedChar[[1]][2])
  return(seed)
}


newseed <- .deriveFromMasterSeed()
cat(newseed)
set.seed(newseed)

for(i in 1000:1000000) {
set.seed(i)

x <- matrix(rnorm(25), nrow=5, ncol=5,
         dimnames=list(c("A","B","C","D","E"), c("v","w","x","y","z")))

y <- x
y[y >= 0.6] <- NA
if( all( is.na(y[, 4]) ) & !is.na(y[1, 1]) ) break
}


###############################################

set.seed(1520)
x <- matrix(rnorm(25), nrow=5, ncol=5,
         dimnames=list(c("A","B","C","D","E"), c("v","w","x","y","z")))
x
z <- y <- x

y[, ] <- x < 0.6
y
y[, "z"] <- 2
y
z[y != 1] <- NA
z

###############################################



N <- 3
x <- matrix(1:(N^2),nrow=N,ncol=N)

rowdiff <- function(y, z, mat) abs(row(mat)[y] - row(mat)[z])
coldiff <- function(y, z, mat) abs(col(mat)[y] - col(mat)[z])

rook.case <- function(y, z, mat){coldiff(y,z,mat) + rowdiff(y,z,mat) == 1}
bishop.case <- function(y ,z, mat){coldiff(y,z,mat) == 1 & rowdiff(y,z,mat) == 1}
queen.case <- function(y, z, mat){rook.case(y,z,mat) | bishop.case(y,z,mat)}

matrix(as.numeric(sapply(x,function(y)sapply(x,rook.case,y,mat=x))),ncol=N^2,nrow=N^2)
matrix(as.numeric(sapply(x,function(y)sapply(x,bishop.case,y,mat=x))),ncol=N^2,nrow=N^2)
matrix(as.numeric(sapply(x,function(y)sapply(x,queen.case,y,mat=x))),ncol=N^2,nrow=N^2)



###############################################

require(spatstat)
set.seed(1520)
x <- matrix(rnorm(25), nrow=5, ncol=5,
         dimnames=list(c("A","B","C","D","E"), c("v","w","x","y","z")))
x
z <- y <- x

y[, ] <- x < 0.6
y
yi <- as.im(y)
ycOut <- connected(yi, background = 0)
yc <- ycOut$v

z[yc != 1 | is.na(yc)] <- NA
z


