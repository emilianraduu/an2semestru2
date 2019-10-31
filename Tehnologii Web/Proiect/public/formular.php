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
    if (!isset($_SESSION['SID']))
        header('location: home'); ?>

    <div class="container cyan">
        <div class="content">
            <form action="./control/formular-controller.php" enctype="multipart/form-data" method="post">
                <div class="info">
                    <label for="hide_img"><img id="avatar" <img id="avatar" class="avatar_choose" src=""></label>
                    <input type="file" name="pic" id="hide_img" required />
                    <div class="under">
                        <label for="name">Name: </label>
                        <input type="text" id="name" name="name" maxlength="20" required>
                    </div>
                </div>

                <input id="location" name="location" hidden required/>

                <p>Species:</p>
                <select name="species" id="species" onchange="change">
                    <option selected disabled hidden>Select</option>
                    <option id="dog">Dog</option>
                    <option id="cat">Cat</option>
                </select>

                <p>Breed:</p>
                <select id="breeds" name="breeds">
                </select>
                <p>Reward: </p>
                <select name="reward" id="reward">
                    <option>No</option>
                    <option>Yes</option>
                </select>

                <label for="details">Details: </label>
                <textarea id="details" name="details" maxlength="300" required></textarea>

                <button type="submit">I lost my pet!</button>

            </form>
        </div>
        <div class="content">
            <div id="mapid" class="spaced"></div>
            <input id="sid" value="<?php echo $_SESSION['UID']?>" hidden>
        </div>
    </div>
    
    <script src="./public/js/breeds.js"></script>
    <script src="../public/js/img.js"></script>
    <?php include 'footer.php'; ?>
</body>

</html>