# emr-rds

#spark submit command

spark-submit --master yarn --deploy-mode cluster --num-executors 25 --executor-memory 2g --executor-cores 2 --packages mysql:mysql-connector-java:8.0.28 --class com.rdsconnect.RDSDataLoad.RDSApp s3://deliverylogs54544322/jars/RDSDataLoad-0.0.1-SNAPSHOT.jar

#aws EMR -- RDS
1) create RDS  database in default vpc and with default configuration
2) create EMR Cluster in same vpc of RDS 
3) edit security group of RDS to add EMR security group
4) add mysql connector jars  -- change it based on version of mysql database (latest is 8.0.28)
   https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.28/mysql-connector-java-8.0.28.jar
   sudo aws s3 cp s3://doc-example-bucket/mysql-connector-java-8.0.28.jar /usr/lib/spark/jars/