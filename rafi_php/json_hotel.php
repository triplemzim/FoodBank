<?php

require "init.php";

//$user_name=$_POST["username"];
//$pass= $_POST["password"];


$sql= "select * from hotel;";
$query_result=mysqli_query($con,$sql);

$response= array();

while($row= mysqli_fetch_array($query_result))
{

array_push($response, array("hotel_id"=>$row["hotel_id"],"hotel_name"=>$row["hotel_name"],"location_id"=>$row["location_id"],
"hotel_address"=>$row["hotel_address"]));

}

echo json_encode(array("server_response"=>$response));

mysqli_close($con);





?>