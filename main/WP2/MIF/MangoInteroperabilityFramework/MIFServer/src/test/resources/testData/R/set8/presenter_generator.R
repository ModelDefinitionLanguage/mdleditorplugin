### something interesting

getPresenter <- function(n = 1) {

    # pool of presenter names

    cnames <- c("Rich", "Gemma", "Andy", 
        "Chibisi", "John", "Brandon", "Sarah", 
        "Jack", "Liz", "Matt", "Chris")
    # "Jian", "Jinjing", "Sophia"
    # "Beany", "Rory", "Nicky"

    # those who have done one recently

    dnames <- c("Chris", "Chibisi")

    # eligible presenters

    enames <- cnames[!cnames %in% dnames]

    # generate n presenters from eligible presenters

    fname <- sample(cnames, n)

    return(fname)

}

# choose presenter

getPresenter()

