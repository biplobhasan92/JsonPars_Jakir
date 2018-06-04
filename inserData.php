<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'connect.php';
	createStudent();
}

function createStudent(){
	global $connect;
	
	$stu_name = $_POST["stu_name"];
	$stu_email = $_POST["stu_email"];
	$age = $_POST["age"];
	
	$query = "insert into students(stu_name, stu_email, age) values('$stu_name', '$stu_email', '$age');";
	mysqli_query($connect, $query) or die (mysqli_error($connect));
	mysqli_close($connect);
	
}




?>