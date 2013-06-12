
import com.meanrecipes.extractr.ml.Tokenizer.tokenizeText
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class TokenizerSuite extends FunSuite {

  test ("Call tokenizeText on an empty string") {
    val tokenized = tokenizeText("")
    assert(tokenized.length === 0)
  }

  test ("String is broken into correct number of tokens") {
    val tokenized = tokenizeText("cat cat cat cat cat")
    assert(tokenized.length === 5)
  }

  test ("String is stemmed correctly") {
    val tokenized = tokenizeText("community communal commune commune")
    tokenized.foreach{ token => assert(token === "commun") }
  }

  test ("Stop Words are removed correctly") {
    val tokenized = tokenizeText("am is are was were balloon nougat")
    assert((tokenized.contains("am") && tokenized.contains("is")) === false)
    assert((tokenized.contains("balloon") && tokenized.contains("nougat")) === true)
 }

  test ("String representations of numerals are left in") {
    val tokenized = tokenizeText("1 2 3 4 5")
    assert(tokenized.length === 5)
  }

}
