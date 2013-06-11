
package com.meanrecipes.extractr.ml

object  Tokenizer {

//  private val _stopWords = new StopWords.getWords()

  def tokenizeText(incomingText: String): List[String] = {
    // Tokenizes, cleans, removes stop words, stems
    tokenize(incomingText)
  }

  private def tokenize(incomingText: String): List[String] = {
  /*Split full text into tokens, clean, remove punctuation*/
    "[a-zA-Z]+".r findAllIn incomingText map(_.toLowerCase) toList
  }

//  private def removeStopWords(tokenizedText: List[String]): List[String] = {/*Removes Stop Words*/}

//  private def stem(word: String): String = {/*Runs the stemming process on each word*/}

}

private object StopWords {}
