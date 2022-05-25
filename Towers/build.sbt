lazy val mygame =
  (project in file("."))
    .enablePlugins(ScalaJSPlugin, SbtIndigo) // Enable the Scala.js and Indigo plugins
    .settings( // Standard SBT settings
      name := "mygame",
      version := "0.0.1",
      scalaVersion := "3.1.1",
      organization := "org.mygame"
    )
    .settings( // Indigo specific settings
      showCursor := true,
      title := "My Game",
      gameAssetsDirectory := "assets",
      windowStartWidth := 720, // Width of Electron window, used with `indigoRun`.
      windowStartHeight := 480, // Height of Electron window, used with `indigoRun`.
      libraryDependencies ++= Seq(
        "io.indigoengine" %%% "indigo" % "0.12.1",
        "io.indigoengine" %%% "indigo-extras" % "0.12.1",
        "io.indigoengine" %%% "indigo-json-circe" % "0.12.1",
      )
    )
