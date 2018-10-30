import org.apache.spark.SparkContext
object earthquake{
def main(args:Array[String]){
val Conf= new SparkConf().setAppName("earthquake").setMaster("local")
val sc = new SparkContext(Conf)
val data=MLUtils.loadLibSVMFile(sc,"......")
val splits=data.random.map(Split(Array(0.0,0.4), seed = 11L)
val training = splits(0).cache()
val test=splits(1)
val numIteration = 100
val model = SVMwithSGD.train(training,numIterations)
model.clearThreshold()
val scoreAndLabels = test.map{ point => val score=  model.predict(point.features)
(score,point.label)
}
val metrics=new BinaryClassificationMetrics(scoreLabels)
val auROC = metrics.areaUnderRoc()
println("area under ROc =" + auROC)
}}
