
package com.meanrecipes.extractr.models.ml

// Abstract base class for other classifiers

abstract trait TrainingDataBase {

  type TrainingData

  def train(classes: List[String], trainingData: Map[String, List[String]]): TrainingData

}

abstract class ClassifierBase(val classes: List[String], val trainingData: AnyRef) extends TrainingDataBase {

  def classify(classificationText: String): String

}

