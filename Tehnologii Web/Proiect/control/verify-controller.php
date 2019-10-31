<?php
require 'db.php';
require 'user.php';
session_start();

$errors = array();

if (isset($_POST['code'])) {
    $db = new Database;

    $code = mysqli_real_escape_string($db->getCon(), $_POST['code']);

    $user = $db->getUser($_SESSION['UID']);
    if ($db->verify($user, $code)) {
        header("location: ../home");
    } else {
        header("location: ../verify");
    }
}

?>
