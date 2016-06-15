<?php

require "init.php";


$command=$_POST["command"];
//$command="hotel";
//$command="hotel_select";
//$command="history";

switch ($command)
 {
	case "hotel":
		# code...
		$sql= "select * from hotel;";
		$query_result=mysqli_query($con,$sql);
		$response= array();

		while($row= mysqli_fetch_array($query_result))
		{

			array_push($response, array("hotel_name"=>$row["hotel_name"]));
		}

		echo json_encode(array("server_response"=>$response));
		
	break;

	case "hotel_select":

		$hotelname=$_POST["hotel_name"];
		//$hotelname="Pizza King";

		$sql= "select hotel_id from hotel where hotel_name='$hotelname';";

		$query_result=mysqli_query($con,$sql);
		
		if(mysqli_num_rows($query_result)>0)
		{
			$row= mysqli_fetch_assoc($query_result);
			$id= $row["hotel_id"];

			$sql_food= "select food_id,food_name,menu_type,price from food where hotel_id='$id';";
			$query_result_food=mysqli_query($con,$sql_food);

			$response_food= array();

			while($row= mysqli_fetch_array($query_result_food))
			{

				array_push($response_food, array("food_id"=>$row["food_id"],"food_name"=>$row["food_name"],
					"menu_type"=>$row["menu_type"],"price"=>$row["price"]));
			}

			echo json_encode(array("server_response"=>$response_food));

		}

	break;


	case "history":

		$cust_id=$_POST["customer_id"];
		//$cust_id=2;

		$sql= "select order_time,order_string,total_price from transaction where customer_id='$cust_id';";
		$query_result=mysqli_query($con,$sql);


		$response= array();

		while($row= mysqli_fetch_array($query_result))
		{
			$order_string= $row["order_string"];
			$token_array = explode("-", $order_string);

			$order_string_array=array();

			foreach ($token_array as $value) 
			{
				$sql_foodname="select H.hotel_name,F.food_name from food F join hotel H on H.hotel_id= 
								F.hotel_id where F.food_id='$value';";

				$query_result_food_details=mysqli_query($con,$sql_foodname);

				if(mysqli_num_rows($query_result_food_details)>0)
				{
					$row_details= mysqli_fetch_assoc($query_result_food_details);
					//$hotel_name= $row_details["hotel_name"];
					//$food_name= $row_details["food_name"];

					array_push($order_string_array,array("hotel_name"=>$row_details["hotel_name"],"food_name"=>$row_details["food_name"]));

				}

    		}


			array_push($response, array("order_time"=>$row["order_time"],"order_string"=>
				$order_string_array,"total_price"=>$row["total_price"]));
		}

		echo json_encode(array("server_response"=>$response));

	break;

	default:
		# code...

	break;


}


mysqli_close($con);


?>