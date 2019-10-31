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
        header('location: .'); ?>
    <input id="sid" value="<?php echo $_SESSION['UID'] ?>" hidden>

    <!-- Lost pets panel -->
    <div class="container ">
        <div class="content flex">
            <h2>Generate statistics</h2>
            <form action="./control/statistics-controller.php" enctype="multipart/form-data" method="post">
                <select class='statistics' name='type'>
                    <option>HTML</option>
                    <option>CSV</option>
                    <option>PDF</option>
                </select>
                <button type='submit'>Get</button>
            </form>
        </div>
    </div>



    <!-- Footer -->
    <?php include 'footer.php'; ?>
</body>

</html>