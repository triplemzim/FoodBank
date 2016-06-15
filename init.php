<?php

$dbname="b9_18215737_pizzadb";
$main_user="b9_18215737";
$main_user_pass="pizza1234";

$server_name="sql309.byethost9.com";


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