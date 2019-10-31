	
<?php

require '../db.php';
$endpoint = $_SERVER['REQUEST_URI'];
$method = $_SERVER['REQUEST_METHOD'];
$requestHeaders = getallheaders();
$requestBodyAsString = file_get_contents('php://input');
$requestBodyAsString = json_decode($requestBodyAsString);

preg_match('/^\/get\/profile\/(.+)$/', $endpoint, $matches);
// echo $matches[1];
header('Content-type: application/json');
if ($method == 'GET') {
    $db = new Database;
    // echo $matches[1];
    $array = $db->getJSONUser($matches[1]);
    echo $array;
}
?>