<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="./public/css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <title>LostPets</title>
</head>

<body>
    <!-- Header -->
    <?php include 'header.php';
    if (!isset($_SESSION['SID']))
        header('location: .');
    $temp = $db->getUser($_SESSION['UID']);
    if ($db->verify($temp))
        header('location: home');
    ?>

    <!-- VERIFY -->
    <div class="container cyan skewDown">
        <div class="content skewUp">
            <form action="./control/verify-controller.php" enctype="multipart/form-data" method="post" class="form">
                <p>For the safety of our users you have to verify your email before you can help other people to find their pet.</p>
                <label for="code">Code: </label>
                <input type="text" id="code" name="code" required>

                <button type="submit" name="verifyBut">Verify</button>
            </form>
        </div>
    </div>

    <!-- FOOTER -->
    <?php include 'footer.php' ?>
</body>

</html>