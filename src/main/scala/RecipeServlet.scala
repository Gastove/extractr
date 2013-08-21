package com.meanrecipes.extractr

import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import com.meanrecipes.extractr.models._
import com.mongodb.casbah.Imports._

class RecipeServlet(mongoClient: MongoClient) extends MeanRecipesExtractrStack with JacksonJsonSupport {

  // Initialize MongoDB Connection with correct DB/Collection
  val mongoCollection = mongoClient("extractr")("recipes")

  // Set JSON Format
  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  post("/add") {
    val recipe  = parsedBody.extract[Recipe]
    mongoCollection += MongoDBObject(recipe.recipe_name -> recipe)
  }

  get("/query/:key/:value") {
    val q = MongoDBObject(params("key") -> params("value"))
    for ( x <- mongoCollection.findOne(q) ) yield x
  }

}
