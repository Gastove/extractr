package com.meanrecipes.extractr

import org.scalatra._
import scalate.ScalateSupport
import com.meanrecipes.extractr.models.ml._
import com.meanrecipes.extractr.models._
import java.io.File
import scala.language.postfixOps
import org.json4s.{DefaultFormats, Formats} // JSON-related libraries
import org.scalatra.json._ // JSON handling support from Scalatra

class ExtractrServlet extends MeanRecipesExtractrStack with ScalateSupport with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats

  get("/test-recipe/") {
    contentType = formats("json")
    RecipeData.oneRecipe
  }

  get("/") {
    // Totally just some test nonsense woo.
    contentType = "text/html"
    val lolsHeaders = List("Col One", "Col Two", "Col Three", "Col Four", "Col 5")
    val lols = List(
      1 to 5 toList,
      6 to 10 toList,
      11 to 15 toList
    )

    jade("/table", "title" -> "Oh hai",  "tableHeaders" -> lolsHeaders, "tableData" -> lols)

  }

  get("/print-classifications/") {
    val testRoot = TestFiles.testRoot
    val trainingData: Map[String, List[String]] = Map(
      "recipe" -> TestFiles.trainRecipes.map{ url => testRoot + "recipes/" + url },
      "chaff" -> TestFiles.trainChaff.map{ url => testRoot + "chaff/" + url }
    )
    val classifier = new NaiveBayesClassifier(List("recipe", "chaff"), trainingData)
    // val classifications = TestFiles.rawList(0)
    //   .map(DigestHTML.processFileToListOfLists(_))
    //   .flatMap{ lol.map{ item => (item, classifier.classify(item)) } }

//  jade("/mapTable", "tableHeaders" -> List("Path", "Classification"), "tableData" -> classifications)

  }

  get("/test-ml/") {
    contentType = "text/html"

    val testRoot = TestFiles.testRoot

    val trainingData: Map[String, List[String]] = Map(
      "recipe" -> TestFiles.trainRecipes.map{ url => testRoot + "recipes/" + url },
      "chaff" -> TestFiles.trainChaff.map{ url => testRoot + "chaff/" + url }
    )

    jade("/mapTable", "tableHeaders" -> List("Classification", "Path"), "mapTableData" -> trainingData, "title" -> "ORLY?")

//    val classifier = new Classifier(List("recipe", "chaff"), trainingData)

//    rawList.foldLeft("")( (acc, testURL) => 
//      new DigestHTML(testURL).textHeap.foldLeft("")( (acc, toTestText) =>
//        if (classifier.classify(toTestText.toString) == "recipe") toTestText + acc else acc
//      ) + acc
//    )

  }
  
}
