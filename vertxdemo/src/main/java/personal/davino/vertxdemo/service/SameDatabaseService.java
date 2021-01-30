package personal.davino.vertxdemo.service;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

@ProxyGen
@VertxGen
public interface SameDatabaseService {

// A couple of factory methods to create an instance and a proxy

  /*static SameDatabaseService create(Vertx vertx) {
    return new SomeDatabaseServiceImpl(vertx);
  }

  static SameDatabaseService createProxy(Vertx vertx, String address) {
    return new SomeDatabaseServiceVertxEBProxy(vertx, address);
  }*/

  // A method notifying the completion without a result (void)
  void save(String collection, JsonObject document,
            Handler<AsyncResult<Void>> result);

  // A method providing a result (a json object)
  void findOne(String collection, JsonObject query,
               Handler<AsyncResult<JsonObject>> result);

  // Create a connection
  /*void createConnection(String shoeSize,
                        Handler<AsyncResult<MyDatabaseConnection>> resultHandler);
*/
}
