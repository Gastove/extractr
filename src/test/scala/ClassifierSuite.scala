import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import com.meanrecipes.extractr.ml.Classifier

class ClassifierSuite extends FunSuite with BeforeAndAfter {

//  before {
    val trainingTexts = Map(
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

    val classifier = new Classifier(List("cat", "dog"), trainingTexts)

  //}

  test ("Easy dog test") {
    val results = classifier.classify(easyDogTest)
    assert((results.contains("dog") && results.length == 1) === true)
  }

  test ("Easy cat test") {
    val results = classifier.classify(easyCatTest)
    assert((results.contains("cat") && results.length == 1) === true)
  }

  test ("Empty String test") {
    val results = classifier.classify(emptyTest)
    assert(results.length === 0)
  }

}
