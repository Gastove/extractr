
package com.meanrecipes.extractr.ml

object  Tokenizer {

  private val _stopWords = new StopWords.getWords()

  def tokenizeText(incomingText: String): List[String] = {
    // Tokenizes, cleans, removes stop words, stems
    return List("")
  }

  private def tokenize(incomingText: String): List[String] = {/*Split full text into tokens*/}

  private def clean(tokenizedText: List[String]): List[String] = {/*Removes punctuation, converts to lower case*/}

  private def removeStopWords(tokenizedText: List[String]): List[String] = {/*Removes Stop Words*/}

  private def stem(word: String): String = {/*Runs the stemming process on each word*/}

}

private object StopWords {}
