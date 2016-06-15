<?php
require "init.php";

$user_name=$_POST["user_name"];
$pass= $_POST["password"];

$sql_query= "select name from user where user_name like '$user_name' and password like '$pass';";

$result=mysqli_query($connection_One,$sql_query);
if(mysqli_num_rows($result)>0)
{
	$row= mysqli_fetch_assoc($result);
	$name= $row["name"];
	echo "Success Yay";
}
else
{
	echo "Failed Login".mysqli_error($connection_One);
}

?>