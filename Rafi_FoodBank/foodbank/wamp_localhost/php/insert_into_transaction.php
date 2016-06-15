<?php
require "init.php";

$cust_id=$_POST["customer_id"];
$orderstring=$_POST["orderstring"];
$price= $_POST["price"];

//$cust_id=2;
//$orderstring="15-11-17";
//$price= 800;



$sql_query= "insert into transaction (customer_id,customer_address,order_string,order_time,total_price) 
			values ('$cust_id',(select address from customer where customer_id='$cust_id'),
			'$orderstring', CURRENT_TIMESTAMP, '$price');";


//$result=mysqli_query($con,$sql_query);

$response= array();


if ($con->query($sql_query) === TRUE) 
{
    //echo "New record created successfully";

    array_push($response,array("state"=>"insert_successful"));
	echo json_encode(array("server_response"=>$response));
} 
else 
{
    //echo "Error: " . $sql . "<br>" . $conn->error;

    array_push($response,array("state"=>"insert_not_successful"));
	echo json_encode(array("server_response"=>$response));
}
