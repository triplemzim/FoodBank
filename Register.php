<?php
require "init.php";
$id=1;
$user_name=$_POST["$user_name"];
$pass= $_POST["$password"];

$sql_query= "insert into user values('$id','$user_name','$pass');";

if(mysqli_query($connection_One,$sql_query))
{
	echo "hmm hoise registration";
}
else
{
	echo "nope, hoy nai reg".mysqli_error($connection_One);
}

?>