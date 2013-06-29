
package com.meanrecipes.extractr.models.ml

object TestFiles{

  val testRoot = "/WEB-INF/training_data/"
 
  val singleRaw = "2012-08-01_orangette_essex_raw.html"

  val rawList = List(
    "2012-08-01_orangette_essex_raw.html",
    "2013-02-22_orangette_the-usual_raw.html",
    "2013-05-03_orangette_im-feeling-daring_raw.html",
    "2012-10-01_orangette_i-stand-by-my-assessment_raw.html",
    "2013-03-24_orangette_we-have-rhythm_raw.html",
    "2013-05-23_orangette_eureka_raw.html",
    "2013-01-25_orangette_a-small-revolution_raw.html",
    "2013-04-07_orangette_april-7_raw.html",
    "2013-06-04_orangette_june-4_raw.htmln"
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

}
