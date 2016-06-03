<?php

require "init.php";

//$user_name=$_POST["username"];
//$pass= $_POST["password"];

$sql= "select * from customer where username='rafi' and password='rafi';";
$query_result=mysqli_query($con,$sql);

$response= array();

while($row= mysqli_fetch_array($query_result))
{

array_push($response, array("customer_id"=>$row["customer_id"],"first_name"=>$row["first_name"],
"last_name"=>$row["last_name"],"username"=>$row["username"],"password"=>$row["password"],"location_id"=>$row["location_id"],"address"=>$row["address"]));

echo json_encode(array("server_response"=>$response));

mysqli_close($con);


}


?>