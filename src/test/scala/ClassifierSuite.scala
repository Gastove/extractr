import org.scalatest.FunSuite
import com.meanrecipes.extractr.ml.{Classifier, DigestHTML}

class ClassifierSuite extends FunSuite {

    val easyTrainingTexts = Map(
      "cat" -> List(
        "Mr. Cat is a catty cat. He is cuddly and nice and feline. Cat eats food.",
        "I am allergic to cats, and that is a damn shame. But then, they claw up everything.",
        "The cat is by far the cutest pet, but allergies can be a problem, and such sharp claws!"
      ),
      "dog" -> List(
        "I love dogs. Such loyal, noble animals.",
        "The golden retriever is my favorite dog -- so intelligent and easy to train.",
        "I wish all dogs were like collies, sweet and lovable."
      )
    )

    val easyCatTest = "Man, allergies are the worst -- and I'm allergic to the cutest felines."
    val easyDogTest = "Oh wow. That poodle is the most loyal I've ever seen, and what intelligence!"
    val emptyTest = ""

    val classifier = new Classifier(List("cat", "dog"), easyTrainingTexts)

  //}

  test ("Easy dog test") {
    val results = classifier.classify(easyDogTest)
    assert(results.contains("dog") === true)
  }

  test ("Easy cat test") {
    val results = classifier.classify(easyCatTest)
    assert(results.contains("cat") === true)
  }

  test ("Empty String test") {
    val results = classifier.classify(emptyTest)
    assert(results === "")
  }

  test ("Untrained category test") {
    val badTraining = Map("a" -> List("Tra-la"), "b" -> List("Loo-loo"))
    intercept[IllegalArgumentException] {
      val wrongClassifier = new Classifier(List("a"), badTraining)
    }
  }

  test ("HTML Recipe Classification") {
    val path = "/home/rdonaldson/Code/meanrecipes/extractr/lib/training_data/"
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
      "recipe" -> trainRecipes.map{ url => path + "recipes/" + url },
      "chaff" -> trainChaff.map{ url => path + "chaff/" + url }
    )

    val classifier = new Classifier(List("recipe", "chaff"), trainingData)

    // val results = rawList.foldLeft(List[String]())( (acc, testPath) =>
    //   new DigestHTML(path + "raw_html/" + testPath).textHeap.foldLeft("")( (acc, toTestText) =>
    //     if (classifier.classify(toTestText) == "recipe") toTestText + acc else acc
    //   ) :: acc
    // )

    val results = rawList.map{ rawHTML =>
      new DigestHTML(path + "raw_html/" + rawHTML)
        .textHeap
        .map(_.foldLeft("")( (acc, toTestText) =>
          if (classifier.classify(toTestText) == "recipe") toTestText + acc else acc ) )
    }

    results foreach { result => println("======="); println(result) }

    assert(results.length === 6)
  }

}
