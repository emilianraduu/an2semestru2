<?php
require 'db.php';
session_start();

    
    $db = new Database;
    
    $id_user = mysqli_real_escape_string($db->getCon(), $_POST['id_user']);
    $id_pet = mysqli_real_escape_string($db->getCon(), $_POST['id_pet']);

    $db->updateNotification($id_user, $id_pet);
    header('location: ../pet#'. $id_pet);
