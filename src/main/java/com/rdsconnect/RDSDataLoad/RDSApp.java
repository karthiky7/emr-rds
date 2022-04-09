package com.rdsconnect.RDSDataLoad;
import org.apache.spark.sql.*;


import java.util.Map;
import java.util.HashMap;

public class RDSApp {
   
    public static void main(String[] args) {
        // Configure and initialize the SparkSession
        // you can any extra configuration here 
        SparkSession spark = SparkSession.builder()
                                         .appName("RDS Application")
                                         .getOrCreate();
        
        // Receive  data from the rds source
        Map<String, String> optionsMap = new HashMap<String, String>();
        optionsMap.put("url","jdbc:mysql://database1.cg7iark1py3d.eu-west-1.rds.amazonaws.com:3306?useSSL=false"); // change rds domain name or ip address
        optionsMap.put("dbtable","masterdata.user");  // database_name.table_name
        optionsMap.put("user","admin");
        optionsMap.put("password","password");
        optionsMap.put("driver","com.mysql.jdbc.Driver");// mysql driver
        optionsMap.put("partitionColumn","user_id"); // primary key column (auto increment)
        optionsMap.put("numPartitions","100");  // how many parallel tasks to be created to read data from rds 
        optionsMap.put("lowerBound","1");   
        optionsMap.put("upperBound","100000000"); // total count of records in table
        optionsMap.put("fetchsize","10000");  // how many records will be fetched in single db call
        
        Dataset<Row> df = spark.read().format("jdbc").options(optionsMap).load();
        

        // do some transformation here 

        // currently writing as parquet to hdfs
        df.write().format("parquet").option("path","/data/mysql/").save();
    }
}