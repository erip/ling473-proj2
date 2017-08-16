import sbt._

object Dependencies {
  private val scalaTestVersion = "3.0.1"

  private val scalatic = "org.scalactic" %% "scalactic" % scalaTestVersion
  private val scalatest = "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
  private val scalaz = "org.scalaz" %% "scalaz-core" % "7.2.14"

  val projectTwoDependencies: Seq[ModuleID] = Seq(
    scalatic,
    scalatest,
    scalaz
  )
}
