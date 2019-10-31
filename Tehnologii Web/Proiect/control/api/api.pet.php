	
<?php

require '../db.php';
$endpoint = $_SERVER['REQUEST_URI'];
$method = $_SERVER['REQUEST_METHOD'];
$requestHeaders = getallheaders();
$requestBodyAsString = file_get_contents('php://input');
$requestBodyAsString = json_decode($requestBodyAsString);

preg_match('/^\/get\/pet\/(.+)$/', $endpoint, $matches);
header('Content-type: application/json');

if($method == 'GET'){
    $db= new Database;
    $data = $db->getAnimal($matches[1]);
    echo json_encode($data);
    
}
if($method == 'POST'){
    $db= new Database;
    $data = $db->getAnimals($matches[1]);
    echo json_encode($data);
}
?>