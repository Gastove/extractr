
package com.meanrecipes.extractr.ml

import scala.math._

class Classifier(val classes: List[String], val trainingData: Map[String, List[String]]) {

  private val trainingSet = train(trainingData)

  /*
   * Generates training data by tokenizing/stemming text, doing a 
   * word count, and dividing the word count by the number of training
   * documents of a particular kind.
   * 
   * @param -- Map of training data, Correct Class to list of training docs.
   * throws IllegalArgumentException if you try to train a Class the Classifier wasn't instantiated with.
   * 
   *@return -- Map, each key is a tuple of Category, Word, each value is average term appearance in that category.
   */

  private def train(trainingData: Map[String, List[String]]): Map[Tuple2[String, String], Int] = {
    //Check to make sure incoming traning classes match the Classifier's classes
    trainingData.keys.foreach{ key =>
      if (!classes.contains(key)) throw new IllegalArgumentException("Uninstantiated class in training set!")
    }
    //Generate training data
    trainingData.mapValues{ textList => textList.flatMap{ text => Tokenizer.tokenizeText(text) } }
      .map{ tokenTuple =>
      tokenTuple._2.groupBy(identity)
        .mapValues{_.size}
        .map{ wordTup =>
        ((tokenTuple._1, wordTup._1), 1+ceil(wordTup._2/trainingData(tokenTuple._1).length).toInt)
      }
    }.flatten.toMap
  }
  

  def classify(classificationText: String): String = {
    // For a document, calculate the probability that it's a member of every class; return the class that scores highest.
    if (classificationText == "") classificationText
    else {
      val tokenizedText = Tokenizer.tokenizeText(classificationText)
        classes.map{ tclass => calcProbabilityForText(tokenizedText, tclass) }.toList.sortWith(_._2 > _._2)(0)._1
    }
  }

  private def calcProbabilityForText(tText: List[String], testClass: String): Tuple2[String, Double] = {
    //Take the product of the individual word probabiliies to determine document probability
    (testClass, tText.foldLeft(1.0)( (prob, word) => calcProbabilityForWord(word, testClass) * prob ) )
  }

  private def calcProbabilityForWord(word: String, testClass: String): Double = {
    // Get the probability a document belongs to a class given a single word
    val prior = 0.5
    val basic_prob = 0.001 + trainingSet.getOrElse((testClass, word), 0) 
    val totals = classes.map{tClass => trainingSet.getOrElse((tClass, word), 0)}.sum
    (prior + (totals * basic_prob)) / (1 + totals)
  }
}
