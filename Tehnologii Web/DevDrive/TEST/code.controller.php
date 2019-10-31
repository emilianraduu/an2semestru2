<?php
$current = 0;
$v = $_POST['submit'];
function code($v)
{
    if (v === 1 && current === 0) {
            current == 1;
            return true;
        } else return false;
    if (v === 2 && current === 1) {
        current == 2;
        return true;
    } else return false;
    if (v === 3 && current === 2) {
        current == 3;
        return true;
    } else return false;
}
switch (v) {
    case '1':
        if (code(v))
            echo "OK";
    case '2':
        if (code(v))
            echo "OK";
    case '3':
        if (code(v))
            echo "OK";
    default:
        break;
}

header('Location: ./index.html');
