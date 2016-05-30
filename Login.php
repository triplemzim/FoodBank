<?php
require "init.php";

$user_name=$_POST["user_name"];
$pass= $_POST["password"];

$sql_query= "select id from user where user_name like '$user_name' and password like '$pass';";

$result=mysqli_query($connection_One,$sql_query);
if(mysqli_num_rows($result)>0)
{
	$row= mysqli_fetch_assoc($result);
	$id= $row["id"];
	echo "Success Yay";
}
else
{
	echo "Failed Login".mysqli_error($connection_One);
}

?>