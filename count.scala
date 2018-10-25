 val text = sc.textFile("saeyum.txt") 

 val counts = text.flatMap(line => line.split(" ")

 ).map(word => (word,1)).reduceByKey(_+_) .count()