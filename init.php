<?php

$dbname="testing";
$main_user="admin";
$main_user_pass="admin";

$server_name="localhost";


$connection_One= mysqli_connect($server_name, $main_user, $main_user_pass, $dbname);

if(!$connection_One)
{
	//echo "Conneciton Error: ".mysqli_connect_error();
}

else
{
	//echo "<h3>Database Connection Hoise</h3>";
}


?>