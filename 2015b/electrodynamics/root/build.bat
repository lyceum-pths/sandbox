SET STR=main

pdflatex "%STR%.tex"
makeindex "%STR%.idx"
bibtex "%STR%.aux"
makeindex "%STR%.idx"
pdflatex "%STR%.tex"

DEL "%STR%.log"
DEL "%STR%.toc"
DEL "%STR%.aux"
DEL "%STR%.out"
DEL "%STR%.blg"
DEL "%STR%.bbl"
DEL "%STR%.ilg"
DEL "/tex/*.aux"

"%STR%.pdf"