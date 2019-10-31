<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="./public/css/style.css">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css" integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ==" crossorigin="" />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js" integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og==" crossorigin=""></script>
    <title>LostPets</title>
</head>

<body>
    <!-- Header -->
    <?php include 'header.php';
    if (isset($_SESSION['SID']))
        header('location: home'); ?>
    <!-- Motto -->
    <div class="container">
        <div class="content">
            <div class="motto">
                <img src="./public/img/illu.png" alt="ilustratie" draggable="false">
                <div class="left-motto">
                    <h2>LOST?</h2>
                    <h2>OTHERS MIGHT</h2>
                    <h2>FIND IT!</h2>
                    <a href="./register">
                        <button>Sign up</button>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <!-- Lost pets panel -->
    <div class="container cyan skewUp ">
        <div class="content skewDown">
            <h2 class="first">THESE PETS WERE LOST</h2>
            <h2 class="second">RECENTLY</h2>
            <!-- Around lost pets -->
            <div class="cards" id="cards">
            </div>
            <h2>THERE IS A CHANCE YOU SEE ONE ON YOUR WAY TO WORK</h2>
        </div>
    </div>

    <!-- Map -->
    <div class="container orange skewDown">
        <div class="content skewUp">
            <h2 class="for-map">YOU CAN FIND A PET AROUND HERE</h2>
            <div id="mapid"></div>
            <input id="sid" value="<?php echo $_SESSION['UID'] ?>" hidden>
            
        </div>
</div>
    <script src="./public/js/lost-pets.js"></script>
    <!-- Footer -->
    <?php include 'footer.php'; ?>
</body>

</html>