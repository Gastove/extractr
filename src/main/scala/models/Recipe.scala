
package com.meanrecipes.extractr.models

case class Recipe(
  recipe_name: String, 
  ingredients: List[Map[String, String]],
  directions: List[Map[String, String]]
)

object RecipeData {

  val oneRecipe = Recipe(
    "Mean Martini",
    List(
      Map(
        "ingredient_name" -> "Gin",
        "quantity" -> "2 parts"
      ),
      Map(
        "ingredient_name" -> "Vermouth",
        "quantity" -> "1 part"
      )
    ),
    List(
      Map(
        "ordinality" -> "1",
        "body" -> "Shake all ingredients over ice"
      ),
      Map(
        "ordinality" -> "2",
        "body" -> "Serve up with optional lemon twist or olive"
      )
    )
  )
  

  val list_of_recipes = List()

}
