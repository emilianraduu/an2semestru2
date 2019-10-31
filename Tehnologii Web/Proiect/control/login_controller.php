<?php
require 'db.php';
require 'user.php';

if (isset($_POST['email'])) {

    $db = new Database;
    $email = mysqli_real_escape_string($db->getCon(), $_POST['email']);
    $password = mysqli_real_escape_string($db->getCon(), $_POST['password']);
    $password = hash("sha256", $password);

    if($user = $db->login($email, $password)){
        $user->startSession($db);
        header('location: ../home');
    } else {
        header('location: ../login/error');
    }
}