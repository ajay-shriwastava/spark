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

package org.apache.spark.sql.catalyst.parser

import org.apache.spark.sql.catalyst.FunctionIdentifier
import org.apache.spark.sql.catalyst.analysis.{UnresolvedGenerator, UnresolvedInlineTable, UnresolvedTableValuedFunction}
import org.apache.spark.sql.catalyst.expressions._
import org.apache.spark.sql.catalyst.plans._
import org.apache.spark.sql.catalyst.plans.logical._
import org.apache.spark.sql.types.IntegerType

/**
 * Parser test cases for rules defined in [[CatalystSqlParser]] / [[AstBuilder]].
 *
 * There is also SparkSqlParserSuite in sql/core module for parser rules defined in sql/core module.
 */
class PlanParserSuite extends PlanTest {
  import CatalystSqlParser._
  import org.apache.spark.sql.catalyst.dsl.expressions._
  import org.apache.spark.sql.catalyst.dsl.plans._

  private def assertEqual(sqlCommand: String, plan: LogicalPlan): Unit = {
    comparePlans(parsePlan(sqlCommand), plan)
  }

  private def intercept(sqlCommand: String, messages: String*): Unit = {
    val e = intercept[ParseException](parsePlan(sqlCommand))
    messages.foreach { message =>
      assert(e.message.contains(message))
    }
  }

  test("case insensitive") {
    val plan = table("a").select(star())
    assertEqual("sELEct * FroM a", plan)
    assertEqual("select * fRoM a", plan)
    assertEqual("SELECT * FROM a", plan)
  }

  test("explain") {
    intercept("EXPLAIN logical SELECT 1", "Unsupported SQL statement")
    intercept("EXPLAIN formatted SELECT 1", "Unsupported SQL statement")
  }

  test("simple select query") {
    assertEqual("select 1", OneRowRelation.select(1))
    assertEqual("select a, b", OneRowRelation.select('a, 'b))
    assertEqual("select a, b from db.c", table("db", "c").select('a, 'b))
    assertEqual("select a, b from db.c where x < 1", table("db", "c").where('x < 1).select('a, 'b))
    assertEqual(
      "select a, b from db.c having x < 1",
      table("db", "c").select('a, 'b).where('x < 1))
    assertEqual("select distinct a, b from db.c", Distinct(table("db", "c").select('a, 'b)))
    assertEqual("select all a, b from db.c", table("db", "c").select('a, 'b))
    assertEqual("select from tbl", OneRowRelation.select('from.as("tbl")))
  }

}
