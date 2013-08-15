
package com.meanrecipes.extractr.models

import com.meanrecipes.extractr.models.ml._

// Primary classification model; loads a classifier takes input, returns a specified output.

class Classifier (classifierType: String) {

  val classifier = classifierType match {
    case "NaiveBayes" => ""
  }

}
