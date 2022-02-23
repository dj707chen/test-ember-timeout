package io.chrisdavenport.testembertimoeut

import cats.data.Kleisli
import cats.effect.kernel.Resource
import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits._
import com.comcast.ip4s._

object TestEmberClientHang extends IOApp.Simple {
  def run: IO[Unit] = EmberClientBuilder.default[IO].build.use(_.statusFromUri(uri"http://localhost:8765")).void
}

// object RunServer extends IOApp.Simple {
//   def run: IO[Unit] =
//     EmberServerBuilder.default[IO].withPort(port"8765").withHttpApp(Kleisli(_ => IO.never)).build.useForever
// }
