	
<?php

require '../db.php';
$endpoint = $_SERVER['REQUEST_URI'];
$method = $_SERVER['REQUEST_METHOD'];
$requestHeaders = getallheaders();
$requestBodyAsString = file_get_contents('php://input');
$requestBodyAsString = json_decode($requestBodyAsString);

preg_match('/^\/get\/(.+)\/(.+)$/', $endpoint, $matches);
header('Content-type: application/json');
if ($method == 'GET') {
    $db = new Database;
    // echo $matches[1];
    $array = $db->getNearPets($matches[1], $matches[2]);
    echo json_encode($array);
}

// if($method == 'POST'){
//     $db= new Database;
//     $data = $db->getAnimal($matches[1]);
//     echo json_encode($data);
// }

// if($method == 'PUT'){
//     $db = new Database;
//     $data = $db->getUser(matches[1])
// }
?>