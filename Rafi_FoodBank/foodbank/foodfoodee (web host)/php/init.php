<?php

$dbname="1160746";
$main_user="1160746";
$main_user_pass="pizza1234";


$server_name="localhost";

//$con = mysql_connect(localhost,$main_user,$main_user_pass);
$con= mysqli_connect($server_name, $main_user, $main_user_pass, $dbname);

if(!$con)
{
	//echo "Conneciton Error: ".mysqli_connect_error();
}

else
{
	//echo "<h3>Database Connection Hoise</h3>";
}


?>
