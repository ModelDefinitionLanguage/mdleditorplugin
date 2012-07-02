#!/bin/bash

# This script may be invoked with something like this:
#
#   -r fromDir toDir -d dir1 -d dir2 -d dir3 -c fromFile1 toFile1 -c fromFile2 toFile2
#
# all this means is:
#   copy the empty directory structure from fromDir to toDir
#   create the directories dir1, dir2 and dir3
#   copy the file "fromFile1" to "toFile1"
#   copy the file "fromFile2" to "toFile2"
#
# that's literally all we have to do.
# You will probably get quite weird results if any of the input directories begin with
# "-", but I don't think that's too much of a restriction

exit=0

while (( $# > 0 )); do
    case $1 in
        -r)
            shift
            if (( $# < 2 )); then
                echo "Bad usage: -r and less than two args left" >&2
                exit=2
            else
                fromDir=$1
                shift
                toDir=$1
                shift
            fi
            if [[ ! -d "$toDir" ]]; then
                mkdir -p "$toDir" || exit=3
                chmod 777 "$toDir" || exit=3
            fi
            if [[ -d "$toDir" ]]; then
                (cd "$fromDir"; find . -type d -print | cpio -pdum "$toDir") || exit=1
            fi
            ;;
        -d)
            shift
            if (( $# == 0 )); then
                echo "Bad usage: -d and no args left" >&2
                exit=2
            else
                dir="$1"
                shift
            fi
            if [[ ! -d "$dir" ]]; then
                mkdir -p "$dir" || exit=1
                chmod 777 "$dir" || exit=1
            fi
            ;;
        -C)
            # These are files that need to be copied with a bit more care
            shift
            if (( $# < 2 )); then
                echo "Bad usage: -C and less than two args left" >&2
                exit=2
            else
                fromFile="$1"
                shift
                toFile="$1"
                shift
            fi
            toBase=$(dirname "$toFile")
            if [[ ! -d $toBase ]]; then
                mkdir -p "$toBase" || exit=1
                chmod 777 "$toBase" || exit=1
            fi
            cp "$fromFile" "$toFile" || exit=1
            ;;
        -c)
            shift
            if (( $# < 2 )); then
                echo "Bad usage: -c and less than two args left" >&2
                exit=2
            else
                fromFile="$1"
                shift
                toFile="$1"
                shift
            fi
            cp "$fromFile" "$toFile" || exit=1
            ;;
          *)
            echo "Bad usage: unknown arg $1" >&2
            shift
            ;;
    esac
done

exit $exit
