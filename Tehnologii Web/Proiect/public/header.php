<?php
require '../control/db.php';
require '../control/user.php';
session_start();
$init = "<header><div id='mapid_hide'></div>
            <div class='logo'>
                <a href='.'>
                    <h2>LostPets</h2>
                </a>
             </div> 
             <div class='links'>
                <div class='selection hidden right' id='rmBtn'>
                    <i class = 'fa fa-times' aria-hidden='true'></i>
                </div>";
if (isset($_SESSION['SID'])) {
    $db = new Database;
    $user = $db->getUser($_SESSION['UID']);
    $init = $init . "
    <div class='selection' id='notifs'><a onclick='show_hide()'><i class='fa fa-bell' aria-hidden='true'></i>notifications</a></div>
        <div id='drop-content' style='display:none;'>
        </div>";
    $init = $init . "<input id=sid value=". $_SESSION['UID'] ." hidden>
    <style> .profile-img { background: url(./public/img/avatars/" . $user->getAvatar() . "); </style>
    <div class='selection'>
                        <a href='./profile#" . $_SESSION['UID'] . "'>
                            <div class='profile-img'></div> " . $user->getLname() . " " . $user->getFname() . "
                        </a>   
                    </div>";
    $init = $init . "<div class = 'selection'>
                    <a href='./formular'>
                    <i class = 'fas fa-plus'></i> I LOST MY PET!</a>
            </div>";
    $init = $init .
        "<div class='selection'>
                            <a href='./control/logout.php'>
                            <i class = 'fas fa-sign-out-alt'></i>
                                Sign Out
                            </a>   
                    </div>";
} else {
    $init = $init . "<div class='selection'>
                <a href='login'>
                    <i class='fas fa-sign-in-alt'></i>
                        Login
                    </a>
                </div>";
}

$init = $init . "</div><div class='selection hidden' id='mobile'>
                        <i class='fa fa-bars' aria-hidden='true'></i>
                </div>
                 
                </header>";
if ($user != null) {
    if (!$db->verify($user)) {
        $init = $init . "<div class='banner'><p>Please <a href='verify'>verify</a> your email!</p></div>";
    }
}

echo $init;
?>
 <link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css" integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ==" crossorigin="" />
<script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js" integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og==" crossorigin=""></script>
<script src="./public/js/mobile.js"></script>