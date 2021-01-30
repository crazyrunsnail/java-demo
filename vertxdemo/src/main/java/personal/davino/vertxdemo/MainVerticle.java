package personal.davino.vertxdemo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlConnection;

import java.util.concurrent.TimeUnit;


public class MainVerticle extends AbstractVerticle {

  private final Logger logger = LoggerFactory.getLogger(MainVerticle.class);

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    Router router = Router.router(vertx);

    router.get("/").handler(context -> {
      context.vertx().getOrCreateContext();
      context.response().end("hello");
    });

    router.get("/exception").handler(context -> {
      throw new RuntimeException("!!!");

    }).failureHandler(ctx -> {
      System.out.println("fail!!!");
      ctx.failure().printStackTrace();
      ctx.response().end("fail!!");
    });

    router.get("/pool").handler(new UserResource()::hello);


    PgConnectOptions pgConnectOptions = new PgConnectOptions();
    pgConnectOptions.setHost("localhost");
    pgConnectOptions.setPort(5432);
    pgConnectOptions.setUser("postgres");
    pgConnectOptions.setPassword("postgres1111");
    pgConnectOptions.setDatabase("sample");

    PoolOptions poolOptions = new PoolOptions();
    poolOptions.setMaxSize(10);
    poolOptions.setMaxWaitQueueSize(100);

    PgPool pool = PgPool.pool(vertx, pgConnectOptions, poolOptions);
    /*pool.query("select 1").execute().onFailure(err ->
      System.out.println(err.fillInStackTrace())
    ).onSuccess(result -> {
      System.out.println(result.rowCount());
      System.out.println("success!");
      vertx.getOrCreateContext().put("pgPool", pool);
    });*/
    Future<RowSet<Row>> future = pool.query("select 1").execute().onComplete(as -> {
      if (as.succeeded()) {
        System.out.println("success!!!");
        System.out.println(as.result().rowCount());
      }
    });


    Future<RowSet<Row>> execute = pool.query("select * from db_user").execute();


    /*ConfigStoreOptions fileStore = new ConfigStoreOptions()
      .setType("file")
      .setConfig(new JsonObject().put("path", "conf/config-dev.json"));


    ConfigRetrieverOptions configRetrieverOptions = new ConfigRetrieverOptions();
    configRetrieverOptions.addStore(fileStore);

    ConfigRetriever.create(vertx, configRetrieverOptions).getConfig(x -> {
      if (x.succeeded()) {
        System.out.println(x.result().containsKey("a"));
        System.out.println(x.result().containsKey("b"));
        System.out.println(x.result().getInteger("a"));
        System.out.println(x.result().getString("PATH"));
      }
    });*/


    HttpServer httpServer = vertx.createHttpServer();
    httpServer.requestHandler(router).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        logger.info("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });

//
//    vertx.setPeriodic(1000L, r -> {
//      System.out.println(r);
//    });
//
//    FileSystem fs = vertx.fileSystem();
//
//    Future<Void> fut1 = Future.future(promise -> {
//      System.out.println("fut1");
//      promise.complete();
//    });
//
//    Future<Void> startFuture = fut1
//      .compose(v -> {
//        // When the file is created (fut1), execute this:
//        return Future.<Void>future(promise -> {
//          System.out.println("fut2");
//          promise.complete();
//        });
//      })
//      .compose(v -> {
//        // When the file is written (fut2), execute this:
//        return Future.future(promise -> {
//          System.out.println("fut3");
//          promise.complete();
//        });
//      });
//
//    startFuture.onSuccess(v -> {
//      startPromise.complete();
//    });



//    final Context context = vertx.getOrCreateContext();
//    context.put("data", "hello");
//    context.runOnContext((v) -> {
//      String hello = context.get("data");
//    });

  }
}
