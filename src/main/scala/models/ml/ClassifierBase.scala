
package com.meanrecipes.extractr.models.ml

// Abstract base class for other classifiers

abstract trait TrainingDataBase {

  type TrainingData

  def train(Map[String, List[String]): TrainingData

}

abstract class ClassifierBase(val classes: List[String]) {

  def classify(classificationText: String): String

}

