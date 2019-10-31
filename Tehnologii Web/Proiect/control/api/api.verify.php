	
<?php

require '../db.php';
$endpoint = $_SERVER['REQUEST_URI'];
$method = $_SERVER['REQUEST_METHOD'];
$requestHeaders = getallheaders();
$requestBodyAsString = file_get_contents('php://input');
$requestBodyAsString = json_decode($requestBodyAsString);

preg_match('/^\/add\/(.+)\/coord\/(.+)\/(.+)$/', $endpoint, $matches);
// header('Content-type: application/json');

if ($method == "POST") {

    if ($matches[1] && $matches[2] && $matches[3]) {
        $obj = (object)[
            'id' => $matches[1],
            'lat' => $matches[2],
            'lng' => $matches[3]
        ];

        $db = new Database;
        $db->updateLocation($obj);
    }
}
?>