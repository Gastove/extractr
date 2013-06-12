
package com.meanrecipes.extractr.ml

import breeze.text.analyze.PorterStemmer


object  Tokenizer {

  // Tokenizes, cleans, removes stop words, stems
  def tokenizeText(incomingText: String): List[String] = {
    tokenize(incomingText)
      .map{PorterStemmer(_)}
      .foldLeft(List[String]())( (acc, word) => if (_stopWords contains word) acc else word :: acc )
  }

  //Split full text into tokens, clean, remove punctuation
  private def tokenize(incomingText: String): List[String] = {
    "[a-zA-Z0-9]+".r findAllIn incomingText map(_.toLowerCase) toList
  }

  //A list of words to remove from incoming strings pre-classification. Sorta arbitrary.
  private val _stopWords = List("a","able","about","across","after","all","almost","also","am","among","an","and","any","are","as","at","be","because","been","but","by","can","cannot","could","dear","did","do","does","either","else","ever","every","for","from","get","got","had","has","have","how","however","i","if","in","into","is","it","its","just","least","let","like","likely","may","me","might","most","must","my","neither","no","nor","not","of","off","often","on","only","or","other","our","own","rather","said","say","says","should","since","so","some","than","that","the","their","them","then","there","these","they","this","tis","to","too","twas","us","wants","was","we","were","what","when","where","which","while","who","whom","why","will","with","would","yet","you","your")

}


