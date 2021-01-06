/**
 * 
 */
package com.joyjits;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author joyjitsaha
 *
 */
public class RepoReader {

   public static void main(String args[]) {

      String rootDir = "/tmp/semanticmodeler/bitechtestadmin-c26b2622-f428-42bb-93fa-167d734ccae0/local";
      Instant start = Instant.now();
      try (Stream<Path> paths = Files.walk(Paths.get(rootDir))) {
         paths.filter(file -> file.toString().endsWith(".json")).forEach(file -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
               System.out.println(file.toString());
               JsonNode node = mapper.readTree(new File(file.toString()));
            } catch (Exception e) {
               throw new RuntimeException();
            }
         });
      } catch (IOException e) {
         e.printStackTrace();
      }
      Instant finish = Instant.now();
      long timeElapsed = Duration.between(start, finish).toMillis();
      System.out.println("time " + timeElapsed);
   }
}
