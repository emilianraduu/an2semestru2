/*************** index.php */
<?php
    $TWO_HOURS = 60 * 60 * 2;

    ini_set('session.cookie_lifetime', $TWO_HOURS);
    ini_set('session.gc_maxlifetime', $TWO_HOURS);

    session_start();

    $lang = 'RO';
    if(isset($_SESSION['lang']) && $_SESSION['lang'] === 'EN') {
        $lang = 'EN';
    }

    $mesaj = $lang === 'RO' ? 'Salut!' : 'Hello!';
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Session.php</title>
    <link rel="stylesheet" href="./style.css">
</head>
<body>
    <header> 
        <h1> Session.php </h1> 
    </header>
    <main>
        <div>
            <form action='./session.controller.php' method="POST">
                <button type="submit" name="submit" value="RO" <?php echo $lang === 'RO' ? 'class="is--active"' : '';?>>RO</button>
            </form>
            <form action='./session.controller.php' method="POST"  >
                <button type="submit" name="submit" value="EN" <?php echo $lang === 'EN' ? 'class="is--active"' : '';?>>EN</button>
            </form>
        </div>

        <h1> <?php echo $mesaj; ?> </h1>
    </main>
</body>
</html>

/*************** session.controller.php */
<?php 
    session_start();
    switch($_POST['submit']) {
        case 'RO':
            $_SESSION['lang'] = 'RO';
            break;
        case 'EN':
            $_SESSION['lang'] = 'EN';
            break;
        default:
            break;
    }

    header('Location: ./index.php');
?>