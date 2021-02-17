package com.adaptris.fxinstaller.helpers;

import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import org.junit.Test;

import com.adaptris.TestUtils;
import com.adaptris.fxinstaller.models.InterlokProject;

public class InterlokInstallerTest {

  @Test
  public void testGenerateAndRun() throws Exception {
    Path resourcesPath = Paths.get(getClass().getResource("/interlok-json.xml").toURI()).getParent();
    Path interlokProjectPath = resourcesPath.resolve("test-project");

    InterlokProject interlokProject = new InterlokProject();
    interlokProject.setOptionalComponents(Collections.singletonList(TestUtils.buildOptionalComponent()));
    interlokProject.setDirectory(interlokProjectPath.toAbsolutePath().toString());
    interlokProject.setVersion("3.10.2-RELEASE");

    new InterlokInstaller().install(interlokProject,
        p -> {
          return null;
        },
        m -> {
          return null;
        }
        );

    assertTrue(Files.isDirectory(interlokProjectPath));
    assertTrue(Files.isDirectory(interlokProjectPath.resolve("config")));
    assertTrue(Files.isRegularFile(interlokProjectPath.resolve("config/adapter.xml")));
    assertTrue(Files.isDirectory(interlokProjectPath.resolve("docs")));
    assertTrue(Files.isRegularFile(interlokProjectPath.resolve("docs/javadocs/interlok-common.jar")));
    assertTrue(Files.isRegularFile(interlokProjectPath.resolve("docs/javadocs/interlok-core.jar")));
    assertTrue(Files.isRegularFile(interlokProjectPath.resolve("docs/javadocs/interlok-json.jar")));
    assertTrue(Files.isDirectory(interlokProjectPath.resolve("lib")));
    assertTrue(Files.isRegularFile(interlokProjectPath.resolve("lib/interlok-common.jar")));
    assertTrue(Files.isRegularFile(interlokProjectPath.resolve("lib/interlok-core.jar")));
    assertTrue(Files.isRegularFile(interlokProjectPath.resolve("lib/interlok-json.jar")));
    assertTrue(Files.isDirectory(interlokProjectPath.resolve("ui-resources")));
    assertTrue(Files.isDirectory(interlokProjectPath.resolve("webapps")));
    assertTrue(Files.isRegularFile(interlokProjectPath.resolve("webapps/interlok.war")));
  }

}
