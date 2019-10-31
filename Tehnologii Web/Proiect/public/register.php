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
    if (isset($_SESSION['SID']))
        header('location: ..'); ?>
    <!-- Register -->
    <div class="container cyan skewDown">
        <div class="content skewUp">
            <form action="./control/register-controller.php" enctype="multipart/form-data" method="post" class="form">
                <div class="info">
                    <label for="hide_img"><img id="avatar" class="avatar_choose" src="./public/img/avatars/default.jpg"></label>
                    <input type="file" name="pic" id="hide_img" />
                    <div class="under">
                        <label for="fname">First name: </label>
                        <input type="text" id="fname" name="fname" required oninvalid="this.setCustomValidity('Please enter your first name')" oninput="setCustomValidity('')">

                        <label for="lname">Last name: </label>
                        <input type="text" id="lname" name="lname" required oninvalid="this.setCustomValidity('Please enter your last name')" oninput="setCustomValidity('')">
                    </div>
                </div>
                <label for="email">Email: </label>
                <input type="email" id="email" name="email" required oninvalid="this.setCustomValidity('Please enter an e-mail')" oninput="setCustomValidity('')">

                <label for="pass">Password: </label>
                <input type="password" id="pass" name="pass" required minlength="8" maxlength="20" oninvalid="this.setCustomValidity('Please enter a password')" oninput="setCustomValidity('')">

                <label for="phone">Phone: </label>
                <input type="tel" id="phone" name="phone" required oninvalid="this.setCustomValidity('Please enter a phone number')" oninput="setCustomValidity('')">


                <button type="submit" name="subReg">Register</button>
                <div class="selection white-links">
                    <a href="login">Already have an account?</a></div>
            </form>

        </div>
    </div>

    <script src="./public/js/img.js"></script>
    <!-- FOOTER -->
    <?php include 'footer.php' ?>
</body>

</html>