# Project 2

## Task

Given a directory containing a SGML-tagged corpus, write a program to generate a unigram model and print the results in descending order based on frequency.

## Results

The top 15 words are

| word | frequency |
|------|---------|
| the  | 4398064 |
| a    | 2032214 |
| to   | 1893205 |
| of   | 1888409 |
| and  | 1759680 |
| in   | 1486132 |
| that | 814646  |
| for  | 793617  |
| is   | 712518  |
| on   | 564762  |
| by   | 559404  |
| with | 512398  |
| he   | 494957  |
| it   | 484405  |
| at   | 463595  |

## Approach

Because the definition of a unigram was slightly different than a traditional unigram's definition, some preprocessing became necessary.

For each line in every file, I split on whitespace, removed all tags from those post-split tokens, parsed out all acceptable words by a RegEx (`[A-Za-z']+`).

Because some words started or ended with apostrophes and we only want internal apostrophes, trimming prefixed and suffixed apostrophes needed to be done.

At this point, all words were converted to lowercase and empty strings were removed. Finally, all words were aggregated into a map.

This was done for each file and the resultant unigram models were aggregated.

## Tools

For this project, I used primarily the Scala standard library with the exception of two libraries:

- Scalaz, a functional library for Scala, which I used to facilitate merging of per-file unigram maps.
- Scalatest, a unit testing library for Scala.

## Testing

Unit tests were written to cover both file processing (i.e., per-file unigram models) and directory processing (i.e., merged unigram models).

## To do

Write more tests to cover more edge cases.
