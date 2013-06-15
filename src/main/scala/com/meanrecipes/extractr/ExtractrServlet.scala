package com.meanrecipes.extractr

import org.scalatra._
import scalate.ScalateSupport
import com.meanrecipes.extractr.ml._
import java.io.File
import scala.language.postfixOps

class ExtractrServlet extends MeanRecipesExtractrStack with ScalateSupport {

  get("/") {
    contentType = "text/html"
    val lolsHeaders = List("Col One", "Col Two", "Col Three", "Col Four", "Col 5")
    val lols = List(
      1 to 5 toList,
      6 to 10 toList,
      11 to 15 toList
    )

    jade("/table", "tableHeaders" -> lolsHeaders, "tableData" -> lols)

    // <html>
    //   <body>
    //     <h1>Hello, world!</h1>
    //     Say <a href="hello-scalate">hello to Scalate</a>.
    //   </body>
    // </html>
  }

  get("/test-ml/") {
    val testRoot = "/WEB-INF/training_data/"
    val rawList = List(
      "2012-08-01_orangette_essex_raw.html",
      "2013-02-22_orangette_the-usual_raw.html",
      "2013-05-03_orangette_im-feeling-daring_raw.html",
      "2012-10-01_orangette_i-stand-by-my-assessment_raw.html",
      "2013-03-24_orangette_we-have-rhythm_raw.html",
      "2013-05-23_orangette_eureka_raw.html",
      "2013-01-25_orangette_a-small-revolution_raw.html",
      "2013-04-07_orangette_april-7_raw.html",
      "2013-06-04_orangette_june-4_raw.html"
    )
    val trainRecipes = List(
      "2012-08-01_orangette_essex_recipe.html",
      "2013-01-25_orangette_a-small-revolution_recipe.html",
      "2013-03-24_orangette-we-have-rhythm_recipe.html",
      "2012-10-01_orangette_i-stand-by-my-assessment_recipe.html",
      "2013-02-22_orangette_the-usual_recipe.html",
      "2013-05-03_orangette-im-feeling-daring_recipe.html"
    )

    val trainChaff = List(
      "2012-08-01_orangette_essex_chaff.html",
      "2013-02-22_orangette_the-usual_chaff.html",
      "2013-05-03_orangette_im-feeling-daring_chaff.html",
      "2012-10-01_orangette_i-stand-by-my-assessment_chaff.html",
      "2013-03-24_orangette_we-have-rhythm_chaff.html",
      "2013-05-23_orangette_eureka_chaff.html",
      "2013-01-25_orangette_a-small-revolution_chaff.html",
      "2013-04-07_orangette_april-7_chaff.html",
      "2013-06-04_orangette_june-4_chaff.html"
    )



    val trainingData: Map[String, List[String]] = Map(
      "recipe" -> trainRecipes.map{ url => testRoot + "recipes/" + url },
      "chaff" -> trainChaff.map{ url => testRoot + "chaff/" + url }
    )

    val tDataString = trainingData map{tup: Tuple2[String, List[String]] =>
      tup._2.foldLeft(" ")( (acc, path) =>
        "<tr> <td>" + tup._1 + "</td><td>" + path +  "</td></tr>" + acc
      )
    } toList
    

    Ok("<table>" + tDataString(0) + "</table>")


//    val classifier = new Classifier(List("recipe", "chaff"), trainingData)

//    rawList.foldLeft("")( (acc, testURL) => 
//      new DigestHTML(testURL).textHeap.foldLeft("")( (acc, toTestText) =>
//        if (classifier.classify(toTestText.toString) == "recipe") toTestText + acc else acc
//      ) + acc
//    )

  }
  
}
