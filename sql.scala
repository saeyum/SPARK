val sqlcontext = new org.apache.spark.sql.SQLContext(sc)  // here we are creating sqlcontext
 val dfs = sqlcontext.read.json("employee.json") // here we are making dataframe simillar to the employee file

dfs.show()
dfs.printSchema()

 dfs.select("name").show()

dfs.filter(dfs("age") > 23).show()

dfs.groupBy("age").count().show()