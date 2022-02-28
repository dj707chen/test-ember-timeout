# test-ember-timoeut - Test Ember Timeout [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.chrisdavenport/test-ember-timoeut_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.chrisdavenport/test-ember-timoeut_2.12) ![Code of Conduct](https://img.shields.io/badge/Code%20of%20Conduct-Scala-blue.svg)

## [Head on over to the microsite](https://ChristopherDavenport.github.io/test-ember-timoeut)

## Quick Start

To use test-ember-timoeut in an existing SBT project with Scala 2.12 or a later version, add the following dependencies to your
`build.sbt` depending on your needs:

```scala
libraryDependencies ++= Seq(
  "io.chrisdavenport" %% "test-ember-timoeut" % "<version>"
)
```

On one terminal
```
nc -l 8765
```

On another terminal
```
sbt coreJVM/run
```

- ctrl-C to terminate
- ctrl-T to take fiber dump

