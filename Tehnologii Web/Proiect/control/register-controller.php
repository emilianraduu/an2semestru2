<?php
require 'db.php';
require 'user.php';

$errors = array();

if (isset($_POST['email'])) {
    $db = new Database;

    $email = mysqli_real_escape_string($db->getCon(), $_POST['email']);
    $lname = mysqli_real_escape_string($db->getCon(), $_POST['fname']);
    $fname = mysqli_real_escape_string($db->getCon(), $_POST['lname']);
    $password = mysqli_real_escape_string($db->getCon(), $_POST['pass']);
    $phone = mysqli_real_escape_string($db->getCon(), $_POST['phone']);
    $password = hash("sha256", $password);
    $avatar = uploadPhoto();

    $user = new User($email, $password, $fname, $lname, $avatar, $phone);

    if ($db->register($user)) {
        $id = $db->getId($user);
        $code = $db->unactivated($id);
        sendEmail($code, $user);
        $user->startSession($db);
        header("location: ../verify");
    } else {
        header("location: ../register/error");
    }
}

function sendEmail($code, $user)
{
    $to      = $user->getEmail();
    $subject = '[Register Code] Lost Pets';
    $message = $code;
    $headers = 'From: emiradu98@icloud.com' . "\r\n" .
        'Reply-To: emiradu98@icloud.com' . "\r\n" .
        'X-Mailer: PHP/' . phpversion();

    mail($to, $subject, $message, $headers);
}
function uploadPhoto()
{
    $uploaddir = '../public/img/avatars/';
    $temp = explode(".", $_FILES['pic']['name']);
    $newfilename = round(microtime(true)) . '.' . end($temp);

    if (move_uploaded_file($_FILES['pic']['tmp_name'], $uploaddir . $newfilename)) {
        return $newfilename;
    } else {
        return "default.jpg";
    }
}
