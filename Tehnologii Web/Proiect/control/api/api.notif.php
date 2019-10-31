<?php
require '../db.php';
$endpoint = $_SERVER['REQUEST_URI'];
$method = $_SERVER['REQUEST_METHOD'];
$requestHeaders = getallheaders();
$requestBodyAsString = file_get_contents('php://input');
$requestBodyAsString = json_decode($requestBodyAsString);

preg_match('/^\/get\/notifications\/(.+)$/', $endpoint, $matches);
header('Content-type: application/json');

if($method == 'GET'){
    
    $db= new Database;
    // echo $matches[1];
    $data = $db->getNotifications($matches[1]);
    echo json_encode($data);
    
}