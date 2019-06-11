/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark

import java.io._
import java.nio.ByteBuffer
import java.util.zip.GZIPOutputStream

import scala.io.Source

import org.apache.hadoop.fs.Path
import org.apache.hadoop.io._
import org.apache.hadoop.io.compress.DefaultCodec
import org.apache.hadoop.mapred.{FileAlreadyExistsException, FileSplit, JobConf, TextInputFormat, TextOutputFormat}
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.{FileSplit => NewFileSplit, TextInputFormat => NewTextInputFormat}
import org.apache.hadoop.mapreduce.lib.output.{TextOutputFormat => NewTextOutputFormat}

import org.apache.spark.internal.config.IGNORE_CORRUPT_FILES
import org.apache.spark.rdd.{HadoopRDD, NewHadoopRDD}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.util.Utils

class TrackFileOperations extends SparkFunSuite with LocalSparkContext {
  var tempDir: File = _

  def time[R](block: => R, str: String): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time to execute: " + str +  " is " + (t1 - t0)/1000000 + "ms")
    result
}
  
  override def beforeEach() {
    super.beforeEach()
    tempDir = Utils.createTempDir()
  }

  override def afterEach() {
    try {
      Utils.deleteRecursively(tempDir)
    } finally {
      super.afterEach()
    }
  }

  test("text files") {
    
    
    println("===TrackFileOperations:test(\"text files\") calling SparkContext(\"local\", \"test\")\n")
    sc = time ({ new SparkContext("local", "test") }, "Creating Spark Context")
    println("===TrackFileOperations:test(\"text files\") creating outputDir\n")
    val outputDir = new File(tempDir, "output").getAbsolutePath
    println("===TrackFileOperations:test(\"text files\") calling sc.makeRDD(1 to 4)\n")
    val nums = sc.makeRDD(1 to 4)
    println("===TrackFileOperations:test(\"text files\") calling nums.saveAsTextFile(outputDir)\n")
    nums.saveAsTextFile(outputDir)
    
    // Read the plain text file and check it's OK
    /*
    val outputFile = new File(outputDir, "part-00000")
    val bufferSrc = Source.fromFile(outputFile)
    Utils.tryWithSafeFinally {
      val content = bufferSrc.mkString
      assert(content === "1\n2\n3\n4\n")
      // Also try reading it in as a text file RDD
      assert(sc.textFile(outputDir).collect().toList === List("1", "2", "3", "4"))
    } {
      bufferSrc.close()
    }
    * 
    */
  }
}
