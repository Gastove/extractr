
package com.meanrecipes.extractr.ml

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
1. Start building tests
2. Figure out how to load stemming library
3. Flesh out Tokenizer class, make sure I'm not doing a terrible job.

*/

  private def train(trainingData: Map[String, List[String]]): Map[Tuple2(String, String), Int] = {
    // Generates training data by tokenizing/stemming text, doing a word count, and recording the count of documents of that kind.
    trainingData.mapValues{
      textList => textList.map{ text => Tokenizer.tokenizeText(text) }
        .toList
        .map{ tokenizedTuple => tokenizedTuple._2.map{
          word => ((tokenizedTuple._1, word), tokenizedTuple._2.filterBy(_ == word).length)
        }
      }.toMap
    }
  }

  def classify(classificationText: String): String = {
    val tokenizedText = Tokenizer.tokenizeText(classificationText)
    classes.map{ tclass => calcProbabilityForText(tokenizedText) }.toList.max
  }

  private def calcProbabilityForText(tText: List[String]): Float = {/*Calculates the Baysean probability that a text is a member of a given class*/}

}
