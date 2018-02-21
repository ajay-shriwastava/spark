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

package org.apache.spark.sql.catalyst.expressions

import java.sql.{Date, Timestamp}

import org.apache.spark.SparkFunSuite
import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.catalyst.analysis.TypeCheckResult.TypeCheckFailure
import org.apache.spark.sql.catalyst.dsl.expressions._
import org.apache.spark.sql.types._

class TrackArithmeticExpressionSuite extends SparkFunSuite with ExpressionEvalHelper {

  import IntegralLiteralTestUtils._

  /**
   * Runs through the testFunc for all numeric data types.
   *
   * @param testFunc a test function that accepts a conversion function to convert an integer
   *                 into another data type.
   */
  private def testNumericDataTypes(testFunc: (Int => Any) => Unit): Unit = {
    println("tillhere tndt1")
    testFunc(_.toByte)
    println("tillhere tndt2")
    //testFunc(_.toShort)
    println("tillhere tndt3")
    //testFunc(identity)
    println("tillhere tndt4")
    //testFunc(_.toLong)
    println("tillhere tndt5")
    //testFunc(_.toFloat)
    println("tillhere tndt6")
    //testFunc(_.toDouble)
    println("tillhere tndt7")
    //testFunc(Decimal(_))
  }

 /*
  test("+ (Add)") {
    testNumericDataTypes { convert =>
      println("tillhere 101")
      val left = Literal(convert(1))
      println("tillhere 102")
      val right = Literal(convert(2))
      println("tillhere 103")
      checkEvaluation(Add(left, right), convert(3))
      println("tillhere 104")
      checkEvaluation(Add(Literal.create(null, left.dataType), right), null)
      println("tillhere 105")
      checkEvaluation(Add(left, Literal.create(null, right.dataType)), null)
      println("tillhere 106")

    checkEvaluation(Add(positiveShortLit, negativeShortLit), -1.toShort)
    checkEvaluation(Add(positiveIntLit, negativeIntLit), -1)
    checkEvaluation(Add(positiveLongLit, negativeLongLit), -1L)

    DataTypeTestUtils.numericAndInterval.foreach { tpe =>
      checkConsistencyBetweenInterpretedAndCodegen(Add, tpe, tpe)
    }
  
   }
  
  */
   

      test("+ (Add123)") {
    testNumericDataTypes { convert =>
      println("tillhere 101")
      val left = Literal(1.toByte)
      println("tillhere 102")
      val right = Literal(2.toByte)
      println("tillhere 103")
      checkEvaluation(Add(left, right), 3.toByte)
      println("tillhere 104")
      //checkEvaluation(Add(Literal.create(null, left.dataType), right), null)
      println("tillhere 105")
      //checkEvaluation(Add(left, Literal.create(null, right.dataType)), null)
      println("tillhere 106")
    }
 /*
    checkEvaluation(Add(positiveShortLit, negativeShortLit), -1.toShort)
    checkEvaluation(Add(positiveIntLit, negativeIntLit), -1)
    checkEvaluation(Add(positiveLongLit, negativeLongLit), -1L)

    DataTypeTestUtils.numericAndInterval.foreach { tpe =>
      checkConsistencyBetweenInterpretedAndCodegen(Add, tpe, tpe)
    }
  
  */
    }

}
