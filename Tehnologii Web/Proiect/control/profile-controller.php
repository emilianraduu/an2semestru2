<?php

require 'db.php';

$method = $_SERVER['REQUEST_METHOD'];
$requestBodyAsString = file_get_contents('php://input');


if ($method == "POST") {
    // $db = new Database;
    // echo var_dump($base64);
    $base64 = 'data:image/' . $type . ';base64,' . base64_encode($requestBodyAsString);
    echo $base64;
    // $avatar = uploadPhoto();
}
function uploadPhoto()
{
    $requestBodyAsString = file_get_contents('php://input');
    $uploaddir = '../public/img/avatars/';
    $temp = explode(".", $requestBodyAsString['pic']['name']);
    // echo var_dump($temp);
    $newfilename = round(microtime(true)) . '.' . end($temp);

    if (move_uploaded_file($_FILES['pic']['tmp_name'], $uploaddir . $newfilename)) {
        return $newfilename;
    } else {
        return "default.jpg";
    }
}
