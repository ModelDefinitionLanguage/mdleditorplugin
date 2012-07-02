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
                dir="$1"
                shift
            fi
            if [[ -d "$dir" ]]; then
                rm -rf "$dir" || exit=1
            fi
            mkdir -p "$dir" || exit=1
            chmod 777 "$dir"
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
         -C)
            shift
            if (( $# < 2 )); then
                echo "Bad usage: -C and less than two args left" >&2
                exit=2
            else
                fromDir="$1"
                shift
                toDir="$1"
                shift
            fi
            (cd "$fromDir"; find . -print | cpio -pdum "$toDir") || exit=1
            ;;
          *)
            echo "Bad usage: unknown arg $1" >&2
            shift
            ;;
    esac
done

exit $exit
