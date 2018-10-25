cd ~/workspace/opensource/spark/spark/sql/catalyst
mvn test -Dtest=moo -DwildcardSuites=org.apache.spark.sql.catalyst.parser.TrackPlanParserSuite
mvn test -Dtest=moo -DwildcardSuites=org.apache.spark.sql.catalyst.expressions.TrackArithmeticExpressionSuite

ajay@Ajays-Mac:
$cd ~/workspace/opensource/spark/spark/sql/core
$mvn test -Dtest=moo -DwildcardSuites=org.apache.spark.sql.TrackSQLQuerySuite


=================================================================================


+ Thread.currentThread().getStackTrace().foreach(println)






=================================================================================
org.apache.spark.sql.catalyst.plans.logical.basicLogicaloperators.scala
org.apache.spark.sql.execution.basicPhysicalOperators.scala
org.apache.spark.sql.catalyst.analysis.Analyzer.scala
org.apache.spark.sql.catalyst.planning.patterns.scala
org.apache.spark.execution.WholeStageCodegenExec.scala


