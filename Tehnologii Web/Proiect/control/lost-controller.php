<?php
require 'db.php';
session_start();

if (isset($_POST['location'])) {
    $db = new Database;
    $id_user = mysqli_real_escape_string($db->getCon(), $_POST['id_user']);
    $id_pet = mysqli_real_escape_string($db->getCon(), $_POST['id_pet']);
    $location = mysqli_real_escape_string($db->getCon(), $_POST['location']);
    $owner = mysqli_real_escape_string($db->getCon(), $_POST['owner']);
    
    $db->foundPet($id_user, $id_pet, $location);
    $db->insertNotification($owner, $id_user, $id_pet);
    header('location: ../pet#'.$id_pet);
}
