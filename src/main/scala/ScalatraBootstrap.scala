import com.meanrecipes.extractr._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoClientURI
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {

    // MongoDB initialization
    val mongoURI = MongoClientURI(System.getenv("MONGOHQ_URL"))
    val mongoClient = MongoClient(mongoURI)

    // Mount Controllers
    context.mount(new ExtractrServlet, "/*")
    context.mount(new RecipeServlet(mongoClient), "/recipe/*")

  }
}
