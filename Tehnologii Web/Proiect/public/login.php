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
    if(isset($_SESSION['SID']))
    header('location: ..');?>


    <div class="container orange skewUp">
        <div class="content skewDown">
            <form action="./control/login_controller.php" method="post">
                <label for="email">Email: </label>
                <input type="email" id="email" name="email" required autofocus oninvalid="this.setCustomValidity('Please enter your email')" oninput="setCustomValidity('')">
                <label for="password">Password: </label>
                <input type="password" id="password" name="password" maxlength="20" required oninvalid="this.setCustomValidity('Please enter your password')" oninput="setCustomValidity('')">

                <button class="invbut" type="submit">Login</button>
            </form>

            <div class="selection white-links">
                <a href="register">Don't have an account?</a>
            </div>


        </div>
    </div>

    <?php include 'footer.php'; ?>
</body>

</html>