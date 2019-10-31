
<?php

require 'db.php';
require 'user.php';
require 'pet.php';

session_start();
if (isset($_SESSION['SID'])) {
    if (isset($_POST['name'])) {

        $db = new Database;
        $user = $db->getUser($_SESSION['UID']);

        $gallery = uploadPhoto();
        $name = mysqli_real_escape_string($db->getCon(), $_POST['name']);
        $species = mysqli_real_escape_string($db->getCon(), $_POST['species']);
        $breed = mysqli_real_escape_string($db->getCon(), $_POST['breeds']);
        $location = mysqli_real_escape_string($db->getCon(), $_POST['location']);
        $details = mysqli_real_escape_string($db->getCon(), $_POST['details']);
        $reward = mysqli_real_escape_string($db->getCon(), $_POST['reward']);
        
        $pet = new Pet($gallery, $location, $name, $species, $breed, $details, $reward);
        // $db->insertPet($pet, $user);
        if ($db->insertPet($pet, $user)) {
            header("location: ../home");
        } else {
            header("location: ../home");
        }
    }
}
function uploadPhoto()
{
    $uploaddir = '../public/img/pets/';
    $temp = explode(".", $_FILES['pic']['name']);
    $newfilename = round(microtime(true)) . '.' . end($temp);

    if (move_uploaded_file($_FILES['pic']['tmp_name'], $uploaddir . $newfilename)) {
        return $newfilename;
    } else {
        return "default.jpg";
    }
}
?>
