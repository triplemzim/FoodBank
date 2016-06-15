<?php
require "init.php";

$user_name=$_POST["username"];
$pass= $_POST["password"];

//$user_name='zim';
//$pass='zim';

$sql_query= "select * from customer where username = '$user_name' and password = '$pass';";
$result=mysqli_query($con,$sql_query);


$response= array();

if(mysqli_num_rows($result)>0)
{
	$row= mysqli_fetch_assoc($result);
	$id= $row["customer_id"];
    $name=$row["first_name"];
	
	array_push($response,array("status"=>"success","customer_id"=>$id));
	echo json_encode(array("server_response"=>$response));
	
	//echo "Login Successful";
    //echo "Welcome $name";

}
else
{
	array_push($response,array("status"=>"fail"));
	
	echo json_encode(array("server_response"=>$response));
	//echo "Failed Login".mysqli_error($connection_One);
}

?>
							