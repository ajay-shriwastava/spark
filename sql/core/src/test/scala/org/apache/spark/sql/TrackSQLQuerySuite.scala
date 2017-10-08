package org.apache.spark.sql


import java.io.File
import java.math.MathContext
import java.sql.Timestamp
import java.util.concurrent.atomic.AtomicBoolean

import org.apache.spark.{AccumulatorSuite, SparkException}
import org.apache.spark.scheduler.{SparkListener, SparkListenerJobStart}
import org.apache.spark.sql.catalyst.util.StringUtils
import org.apache.spark.sql.execution.aggregate
import org.apache.spark.sql.execution.joins.{BroadcastHashJoinExec, CartesianProductExec, SortMergeJoinExec}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.internal.SQLConf
import org.apache.spark.sql.test.{SharedSQLContext, TestSQLContext}
import org.apache.spark.sql.test.SQLTestData._
import org.apache.spark.sql.types._

class TrackSQLQuerySuite extends QueryTest with SharedSQLContext {
  import testImplicits._

  setupTestData()

   test("Track SQL Execution") {
    checkAnswer(
      sql(
        """
          |SELECT r.*
          |FROM testData l join testData2 r on (l.key = r.a)
        """.stripMargin),
      Row(1, 1) :: Row(1, 2) :: Row(2, 1) :: Row(2, 2) :: Row(3, 1) :: Row(3, 2) :: Nil)
  } 

}