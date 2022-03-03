package io.chrisdavenport.testembertimoeut

import cats.data.Kleisli
import cats.effect.kernel.Resource
import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits._
import com.comcast.ip4s._
import scala.concurrent.duration._

object TestEmberClientHang extends IOApp.Simple {
  def run: IO[Unit] = EmberClientBuilder.default[IO].build.use(client => 
    IO.realTime.flatMap( start => 
      IO.race(
        client.statusFromUri(uri"http://localhost:8765"),
        IO.sleep(2.seconds)
      ).flatMap( out => 
        IO.realTime.flatMap{end => 
          val took = end - start
          IO.println(s"Took: $took, $out").void
        }
      )
    )
  )
}