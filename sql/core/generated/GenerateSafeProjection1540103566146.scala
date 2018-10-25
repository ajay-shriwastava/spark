/*code for createexternalrow(input[0, int, false], input[1, int, false], StructField(a,IntegerType,false), StructField(b,IntegerType,false)):*/
/* 001 */ public java.lang.Object generate(Object[] references) {
/* 002 */   return new SpecificSafeProjection(references);
/* 003 */ }
/* 004 */
/* 005 */ class SpecificSafeProjection extends org.apache.spark.sql.catalyst.expressions.codegen.BaseProjection {
/* 006 */
/* 007 */   private Object[] references;
/* 008 */   private InternalRow mutableRow;
/* 009 */   private Object[] values;
/* 010 */   private org.apache.spark.sql.types.StructType schema;
/* 011 */
/* 012 */   public SpecificSafeProjection(Object[] references) {
/* 013 */     this.references = references;
/* 014 */     mutableRow = (InternalRow) references[references.length - 1];
/* 015 */
/* 016 */     this.schema = (org.apache.spark.sql.types.StructType) references[0];
/* 017 */
/* 018 */   }
/* 019 */
/* 020 */   public void initialize(int partitionIndex) {
/* 021 */
/* 022 */   }
/* 023 */
/* 024 */
/* 025 */
/* 026 */   public java.lang.Object apply(java.lang.Object _i) {
/* 027 */     InternalRow i = (InternalRow) _i;
/* 028 */
/* 029 */     values = new Object[2];
/* 030 */
/* 031 */     int value1 = i.getInt(0);
/* 032 */     if (false) {
/* 033 */       values[0] = null;
/* 034 */     } else {
/* 035 */       values[0] = value1;
/* 036 */     }
/* 037 */
/* 038 */     int value2 = i.getInt(1);
/* 039 */     if (false) {
/* 040 */       values[1] = null;
/* 041 */     } else {
/* 042 */       values[1] = value2;
/* 043 */     }
/* 044 */
/* 045 */     final org.apache.spark.sql.Row value = new org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema(values, schema);
/* 046 */     if (false) {
/* 047 */       mutableRow.setNullAt(0);
/* 048 */     } else {
/* 049 */
/* 050 */       mutableRow.update(0, value);
/* 051 */     }
/* 052 */
/* 053 */     return mutableRow;
/* 054 */   }
/* 055 */ }
