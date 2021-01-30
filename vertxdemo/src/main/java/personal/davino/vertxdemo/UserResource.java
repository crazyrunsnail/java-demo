package personal.davino.vertxdemo;

import io.vertx.core.Future;
import io.vertx.ext.web.RoutingContext;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserResource {

  private final Logger logger = LoggerFactory.getLogger(UserResource.class);

  public void hello(RoutingContext context) {
    PgPool pgPool = (PgPool) context.vertx().getOrCreateContext().get("pgPool");
    logger.debug("" + (pgPool == null));
    if (pgPool == null) {
      context.response().end("error null");
      return;
    }
    pgPool.query("select * from db_user").execute().onSuccess(result -> {
      context.response().end(result.rowCount() + "");
    }).onFailure(err -> {
      context.response().end("empty");
    });
  }
}
