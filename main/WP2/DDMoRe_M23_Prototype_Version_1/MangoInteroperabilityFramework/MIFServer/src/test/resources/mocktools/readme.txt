Stubbed SGE commands for long running tasks from Navigator

1. These need to be copied into a shared folder visible to the grid, e.g:

/usr/global/jholloway/mockgridcommands

2. sge.sh needs to be modified to put these on the path like so:

# Use the mock commands for PsN and NONMEM
#PATH=$SGE_ROOT/bin/$ARCH:$PATH; export PATH
PATH=/usr/global/jholloway/mocktools:$PATH; export PATH

n.b. Jobs will run and NEVER complete with the current mocks