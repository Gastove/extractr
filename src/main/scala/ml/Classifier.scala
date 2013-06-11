
package com.meanrecipes.extractr.ml

import scala.math.round

class Classifier(
  val classes: List[String] // The Buckets to classify into
    , val trainingData: Map[String, List[String]] // Training data for the classifier
    ) {

  private lazy val trainingSet = train(trainingData)

/*
Hokay, so. This is a Naive Bayes Classifier. That means:

So:

1. Train
 pass in text and category it belongs to; tokenize/stem text; do word count of text; for category, term, store count; store number of documents in category

Classification is:
 1. Which every category a document is most likely to belong to.
 2. For each category, try classifying the text as a member of that category.
 3. Tokenize/stem document (only actually needs to be done once, pass object to each category)
 4. The document probability is the product of the weighted average probability of each word blonging to the class in question.
 5. The word weighted average probability is the product of a weight and a prior plus the product of the total count of a term in all categories and the basic probability of a term in the current class.
 6. The probability of a term in the current class is 0 if the term has never been encountered, or it's the count of the term in the class / number of training documents for that class.

TODO
==========
1. Start building tests -- check.
2. Figure out how to load stemming library
3. Flesh out Tokenizer class, make sure I'm not doing a terrible job.

*/

  /*
   tokenize list of docs, returns list of tokens for every document
   take list of tokens, do word count, return tuple of word -> count
   final product is Map[class -> List(Tuple(Word, Count))]
   */

  private def train(trainingData: Map[String, List[String]]): Map[String, List[Tuple2[String,Int]]] = {
    // Generates training data by tokenizing/stemming text, doing a word count, and recording the count of documents of that kind.
    
    
    trainingData.mapValues{
      textList => textList.flatMap{ text => Tokenizer.tokenizeText(text) }
    }.map{
      tokenTuple => (tokenTuple._1, tokenTuple._2.groupBy(identity)
        .map{tup => (tup._1, round(tup._2.size/trainingData(tokenTuple._1).length).toInt)}.toList)
    }

//    mapValues(round(sum(words) / count(docs), 0)
  
  }
  

  def classify(classificationText: String): String = {
    val tokenizedText = Tokenizer.tokenizeText(classificationText)
    classes.map{ tclass => calcProbabilityForText(tokenizedText, tclass) }.toList.sortWith(_._2 > _._2)(0)._1
  }

  private def calcProbabilityForText(tText: List[String], testClass: String): Tuple2[String, Float] = {
  /*Calculates the Baysean probability that a text is a member of a given class*/
    
    (testClass, 0.0.toFloat)
  }



}
