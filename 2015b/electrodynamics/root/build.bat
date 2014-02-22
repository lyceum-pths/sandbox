SET STR=main

pdflatex "%STR%.tex"
bibtex "%STR%.aux"
pdflatex "%STR%.tex"
pdflatex "%STR%.tex"

DEL "%STR%.log"
DEL "%STR%.toc"
DEL "%STR%.aux"
DEL "%STR%.out"
DEL "%STR%.blg"
DEL "%STR%.bbl"
DEL "/tex/*.aux"

"%STR%.pdf"