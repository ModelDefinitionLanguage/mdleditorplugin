#!/bin/bash

exit=0

while (( $# > 0 )); do
    case $1 in
        -d)
            shift
            if (( $# == 0 )); then
                echo "Bad usage: -d and no args left" >&2
                exit=2
            else
                dir=$1
                shift
            fi
            if [[ -d $dir ]]; then
                rm -rf $dir || exit=1
            fi
            mkdir -p $dir || exit=1
            ;;
        -c)
            shift
            if (( $# < 2 )); then
                echo "Bad usage: -c and less than two args left" >&2
                exit=2
            else
                fromFile=$1
                shift
                toFile=$1
                shift
            fi
            cp $fromFile $toFile || exit=1
            ;;
         -e)
            shift
            if (( $# <= 1 )); then
                echo "Bad usage: -e missing args" >&2
                exit=2
            else
                contents=$1
                shift
                filename=$1
                shift
                echo "$contents" > $filename || exit=1
                if [[ -f $filename ]]; then
                    chmod +x $filename
                fi
            fi
            ;;
          *)
            echo "Bad usage: unknown arg $1" >&2
            shift
            ;;
    esac
done

exit $exit
